package Server;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 */
public class MagicServerDriver extends Object {
    /** the default port number the server listens to */
    public static final int DEFAULT_PORT = 47511;
    /**
     * Creates a default MagicServerDriver.
     */
    public MagicServerDriver(){
    }

    /**
     * This method serves as the entry point of the program.
     *
     * @param args - command line arguments to the program. There must be
     *             exactly 0 or 1 arguments. the second parameter, if
     *             present, must be the port number on which the
     *             server will listen for requests.
     */
    public static void main(String[] args) {
        try {
            if (args.length != 2 && args.length != 1) {
                printUsageAndExit();
            }

            AbstractMagicServer magicServer = null;
            int portNum = DEFAULT_PORT;
            if (args.length == 2) {
                portNum = Integer.parseInt(args[2]);
            }
            if (args[0].equalsIgnoreCase("tcp")) {
                magicServer = new TcpMagicServer(portNum);
            } else if (args[0].equalsIgnoreCase("udp")) {
                magicServer = new UdpMagicServer(portNum);
            }
            else{
                printUsageAndExit();
            }
            magicServer.listen();
        }
        catch(MagicServerException |FileNotFoundException ioe){
            System.out.println(ioe.getMessage());
        }

    }

    /**
     * Prints a simple usage message to stderr and terminates the program.
     */
    private static void printUsageAndExit() {
        System.out.println("How to use: java Server.MagicServerDriver " +
                    "(TCP or UDP) (Optional: port number)");
        System.exit(1);
    }
}
