package Client;

import Common.Card;
import java.util.*;
import java.io.*;
import java.net.*;

/**
 * Created by Cagle on 10/30/2018.
 */
public class MagicTcpClient extends AbstractMagicClient {
    /**
     * Initializes a new MagicTcpClient with the specified host and the
     * default port.
     *
     * @param host - the address of the remote host to which to connect.
     */
    public MagicTcpClient(InetAddress host) {
        super(host);
    }

    /**
     * Initializes a new MagicTCPClient with the specified host and port
     *
     * @param host - the address of the remote host to which to connect.
     * @param port - the port of the remote host to which to connect.
     */
    public MagicTcpClient(InetAddress host, int port) {
        super(host, port);
    }

    /**
     * Initializes a new MagicTCPClient with the specified host and port.
     *
     * @param host - the addres of the remote host to which tho connect.
     * @param port - the port on the remote host to which to connect.
     * @param flag - the arguments to send to the server.
     */
    public MagicTcpClient(InetAddress host, int port, String flag) {
        super(host, port, flag);
    }

    /**
     * Get information from the user, send it to a server, get a response
     * and print out the response.
     *
     * @param out - The stream to which to wrtie the random cards received.
     * @throws IOException - if there is an I/O error while receiving the data
     */
    public void printToStream(PrintStream out) throws IOException {
        try {
            Socket clientSocket = new Socket(super.getHost(), super.getPort());

            DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(clientSocket.getInputStream());
            String sendLine = super.getFlag();
            toServer.writeBytes(sendLine + "\r \n");
            toServer.flush();
            Object receiveData;
            do {
                receiveData = fromServer.readObject();
                if (receiveData instanceof Card) {
                    System.out.print(receiveData + "\r \n");
                }
            }while(receiveData instanceof Card);
            clientSocket.close();
        }
        catch(ClassNotFoundException | IOException ioe){
            System.out.println(ioe.getMessage());
        }

    }
}
