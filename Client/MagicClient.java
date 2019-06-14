package Client;

import java.io.IOException;
import java.io.PrintStream;

/**
 * The interface to a magic client component.
 * Created by Cagle on 10/30/2018.
 */
public interface MagicClient {
    /**
     * Establishes a TCP connection to the host/port specified when this object
     * was created, reads a continuous stream of random cards form the socket's
     * input stream, and prints that data to the specified output stream.
     * @param out - The stream to which to write the random cards received.
     */
    public void printToStream(PrintStream out) throws IOException, ClassNotFoundException;
}
