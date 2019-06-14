package Server;

import Common.Card;

import java.io.*;
import java.net.*;
import java.util.*;

public class UdpMagicServer extends AbstractMagicServer {
    /** the number of bytes that the server will send in a response.*/
    private static final int SIZE = 1024;

    /**
     * Creates a new UdpmagicServer that listens for connections on the default
     * magic TCP port, and uses the default card source.
     *
     * @throws FileNotFoundException - if the file used to initialize the cards
     * is not found.
     */
    public UdpMagicServer() throws FileNotFoundException {
        super();
    }

    /**
     * Creates a new UdpMagicServer that listens for connections on the
     * Specified port.
     *
     * @param port - the port hte server will listen at.
     * @throws FileNotFoundException - if the input file cannot be located.
     */
    public UdpMagicServer(int port) throws FileNotFoundException {
        super(port);
    }

    /**
     * Creates a new UdpMagicServer that listens for connections on the
     * specified TCP port and uses the specified card source.
     *
     * @param port - the port the server will listen at.
     * @param source - source used to generate card.s
     */
    public UdpMagicServer(int port, CardSource source){
        super(port,source);
    }

    /**
     * Creates a new UdpMagicServer that listens for connections on the default
     * Magic TCP port and uses the specified card source.
     *
     * @param source - source used to genereate card.s
     */
    public UdpMagicServer(CardSource source){
        super(source);
    }

    /**
     * Causes the magic server to listen for requests.
     *
     * @throws MagicServerException - if an error occurs while trying to listen
     * for connections
     */
    public void listen() throws MagicServerException {
        try {
            DatagramSocket serverSocket = new DatagramSocket(super.getPort());
            while (!serverSocket.isClosed()) {
                byte[] receiveData = new byte[SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);
                serverSocket.receive(receivePacket);
                String flag = new String(receivePacket.getData());
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                super.setCardsReturned(flag.trim().toUpperCase());
                CardSource deck = super.getSource();
                ArrayList<Card> check = new ArrayList<>();
                Card card = null;
                int i = 0;
                while(i < this.getItemsToSend()){    
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream reply = new ObjectOutputStream(baos);
                    card = deck.next();
                    if(!check.contains(card)){
                        check.add(card);
                        reply.writeObject(card);
                        reply.flush();
                        byte[] data = baos.toByteArray();
                        DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                        i++;
                    }
                }
                check.clear();
            }
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

}
