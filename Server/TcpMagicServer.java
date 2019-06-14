package Server;


import Common.Card;

import java.io.*;
import java.net.*;
import java.util.*;

public class TcpMagicServer extends AbstractMagicServer {

    public TcpMagicServer() throws FileNotFoundException {
       super();
    }

    /**
     *
     * @param port
     * @throws FileNotFoundException
     */
    public TcpMagicServer(int port) throws FileNotFoundException {
       super(port);
    }

    /**
     *
     * @param source
     */
    public TcpMagicServer(CardSource source) {
        super(source);
    }
    /**
     *
     * @param port
     * @param source
     */
    public TcpMagicServer(int port, CardSource source) {
        super(port, source);
    }

    /**
     *
     * @throws MagicServerException
     */
    public void listen() throws MagicServerException {
        try {
            ServerSocket welcomeSocket = new ServerSocket(super.getPort());
            while (!welcomeSocket.isClosed()) {
                Socket connectionSocket = welcomeSocket.accept();
                Scanner receiveData = new Scanner(connectionSocket.getInputStream());

                DataOutputStream serverOut = new DataOutputStream(
                        connectionSocket.getOutputStream());
                ObjectOutputStream sendData = new ObjectOutputStream(serverOut);

                String flag = new String(receiveData.nextLine());
                setCardsReturned(flag.toUpperCase());
                CardSource deck = super.getSource();
                ArrayList<Card> check = new ArrayList<>();
                Card card = null;
                int count = 0;

                while(count < super.getItemsToSend()){
                    card = deck.next();
                    if(!check.contains(card)) {
                        check.add(card);
                        sendData.writeObject(card);
                        count++;
                        sendData.flush();
                    }
                }
                check.clear();
                sendData.writeObject("");
                connectionSocket.close();
                serverOut.close();
                receiveData.close();
            }
        }
        catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
