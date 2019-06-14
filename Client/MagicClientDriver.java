package Client;

import Client.MagicUdpClient;

import java.net.InetAddress;
import java.io.*;
import java.net.UnknownHostException;

/**
 *
 * Created by Cagle on 10/30/2018.
 */
public class MagicClientDriver {
    private static final int PORTNUM = 47511;
    private static final String FLAG = "-A";
    public static void main(String[] args){
        try {
            if (args.length != 2 && args.length != 3 && args.length != 4) {
                printUsageAndExit();
            }// end if

            InetAddress host = InetAddress.getByName(args[1]);
            AbstractMagicClient magicClient = null;
            int portNum = PORTNUM;
            String flag = FLAG;
            if (args.length == 3 && args[2].matches("-?\\d+")) {
                portNum = Integer.parseInt(args[2]);
            } else if (args.length == 3 && args[2].contains("-")) {
                flag = args[2];
            } else if (args.length == 4) {
                portNum = Integer.parseInt(args[2]);
                flag = args[3];
            }
            if (args[0].equalsIgnoreCase("tcp")) {
                magicClient = new MagicTcpClient(host, portNum, flag);
            } else if (args[0].equalsIgnoreCase("udp")) {
                magicClient = new MagicUdpClient(host, portNum, flag);
            } else {
                printUsageAndExit();
            }
            magicClient.printToStream(System.out);
        }
        catch(IOException | ClassNotFoundException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    private static void printUsageAndExit(){
        System.out.println("How to use: Java Client.MagicClientDriver (TCP or UDP)" +
                "(IPaddress or hostname) (Optional: port number or valid flag)" +
                "(Optional: flag)");
        System.exit(1);
    }
}

