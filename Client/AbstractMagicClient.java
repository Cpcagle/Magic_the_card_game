package Client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;


/**
 * An abstract class that contains fields and methods that may be
 * common to implementations of the 'magic' protocol.
 *
 * Created by Cagle on 10/30/2018.
 */
public abstract class AbstractMagicClient implements MagicClient {
    /**The default flag on which a client inputs */
    public static String DEFAULT_FLAG = "-A";
    /**The default port on which a magic server listens */
    public static int DEFAULT_PORT = 47511;
    /**The port on the remote host to which to connect */
    private static int port;
    /**The flag to send the server to determine what cards to send back */
    private static String flag;
    /**The remote host to which to connect. */
    private static InetAddress host;

    /**
     * Initializes a new AbstractMagicClient with the specified host, and
     * the default port.
     * @param host - the address of the remote host to which to connect.
     */
    public AbstractMagicClient(InetAddress host){
        this.host = host;
        this.port = DEFAULT_PORT;
        this.flag = DEFAULT_FLAG;
    }

    /**
     * Initializes a new AbstractMagicClient with the specified host and port.
     * @param host - the address of the remote host to which to connect.
     * @param port - the port on the remote host to which to connect.
     */
    public AbstractMagicClient(InetAddress host, int port){
        this.host = host;
        this.port = port;
        this.flag = DEFAULT_FLAG;
    }

    /**
     * Initializes a new AbstractMagicClient with the specified host and port.
     * @param host - the address of the remote host to which to connect.
     * @param port - the port on the remote host to which to connect.
     * @param flag - the flags which determine which cards to send back.
     */
    public AbstractMagicClient(InetAddress host, int port, String flag){
        this.host = host;
        this.port = port;
        this.flag = flag;
    }

    /**
     * Returns the address of the host to which to connect.
     * @return the address of the host to which to connect.
     */
    protected InetAddress getHost(){
        return this.host;
    }

    /**
     * Returns the flags that we want to send to the server.
     * @return the flags to send to the server.
     */
    protected String getFlag(){
        return this.flag;
    }

    /**
     * Returns the port on which the remote host is listening.
     * @return the port on which the remote host is listening.
     */
    protected int getPort(){
        return this.port;
    }

    /**
     * Establishes a TCP connection to the host/port specified when this object
     * was created, reads a continuous stream of random cards from the socket's
     * input stream, and prints that data to the specified output stream.
     * @param out - The stream to which to write the random cards received.
     * @throws IOException - if there is an I/O error while receiving the data.
     */
    public abstract void printToStream(PrintStream out) throws IOException, ClassNotFoundException;

}
