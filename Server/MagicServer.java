package Server;

/**
 * The interface to a magic server.
 *
 * Created by Cameron Cagle 11/1/18
 */
public interface MagicServer {
    /**
     * Causes the magic server to listen for requests.
     *
     * @throws MagicServerException - if an error occurs while trying to listen
     *                                for connections.
     */
    void listen() throws MagicServerException;
}
