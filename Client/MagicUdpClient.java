package Client;

import Common.Card;

import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Created by Cagle on 10/30/2018.
 */
public class MagicUdpClient extends AbstractMagicClient {
    /** Size of the byte array */
    private static int SIZE = 1024;
    /**
     * Initializes a new MagicUDPClient with the specified host and port.
     *
     * @param host - the address of the remote host to which tho connect.
     */
    public MagicUdpClient(InetAddress host){
        super(host);
    }
    /**
     * Initializes a new MagicUDPClient with the specified host and port.
     *
     * @param host - the address of the remote host to which tho connect.
     * @param port - the port on the remote host to which to connect.
     */
    public MagicUdpClient(InetAddress host, int port){
        super(host, port);
    }
    /**
     * Initializes a new MagicUDPClient with the specified host and port.
     *
     * @param host - the address of the remote host to which tho connect.
     * @param port - the port on the remote host to which to connect.
     * @param flag - the arguments to send to the server.
     */
    public MagicUdpClient(InetAddress host, int port, String flag) {
        super(host, port, flag);
    }

     /**
     * Establishes a UDP connection to the host/port specified when this
     * object was created.
     * @param out - The stream to which to write the random cards received.
     * @throws IOException - if there is an I/O error while receiving the data.
     */
    public void printToStream(PrintStream out) throws IOException,
                              ClassNotFoundException {
        DatagramSocket clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(500);
        byte[] data = new byte[SIZE];

        InetAddress IPAddress = super.getHost();
        data = super.getFlag().getBytes();

        DatagramPacket sendPacket = new DatagramPacket(data,
                data.length, IPAddress, super.getPort());
        clientSocket.send(sendPacket);
        byte[] receiveData = new byte[SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
        ObjectInputStream inputStream;
        clientSocket.receive(receivePacket);
        Card card = null;
        while(receivePacket.getLength() > 0){
            clientSocket.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            inputStream = new ObjectInputStream(new BufferedInputStream(bais));
            card = (Card)inputStream.readObject();
            if (receivePacket.getLength() > 0 ){
                out.print(card.toString() + "\r\n");
            }
        }
    }
}
