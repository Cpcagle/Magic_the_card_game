package Server;

import Client.AbstractMagicClient;

import java.io.FileNotFoundException;

public abstract class AbstractMagicServer implements MagicServer {
    /** the default port on which a magic server listens */
    public static final int DEFAULT_PORT = 47511;
    /** the default number of items to send back */
    public static final int NUM_ITEMS = 20;
    /** the default number of cards of one type*/
    protected static int ONE_TYPE = 20;
    /** the default number of cards of two types*/
    protected static int TWO_TYPES = 40;
    /** the default number of cards of three types*/
    protected static int THREE_TYPES = 60;
    /** the port to which the server should listen for incoming connections */
    private int port;
    /** the source of the character strea that the server will send to the client. */
    private CardSource source;
    /** the number of items to send back before ending connection */
    private int numItems;

    /**
     * Initializes a new AbstractMagicServer using the default port and the
     * default source.
     *
     * @throws FileNotFoundException - If the source file cannot be found.
     */
    public AbstractMagicServer() throws FileNotFoundException {
        this.port = DEFAULT_PORT;
        this.source = new CardSource();
        this.numItems = NUM_ITEMS;
    }

    /**
     * Initializes a new AbstractMagicServer using the default port and the
     * default source.
     *
     * @param port - the port to which the server will bind and listen
     *             for incoming connections.
     * @throws FileNotFoundException - if the source file cannot be found.
     */
    public AbstractMagicServer(int port) throws FileNotFoundException {
        this.port = port;
        this.source = new CardSource();
        this.numItems = NUM_ITEMS;
    }

    /**
     * Initializes a new AbstractMagicServer using the default port and
     * the default source.
     *
     * @param source - the source to use to send cards to connecting clients.
     */
    public AbstractMagicServer(CardSource source) {
        this.port = DEFAULT_PORT;
        this.source = source;
        this.numItems = NUM_ITEMS;
    }

    /**
     * Initializes a new AbstractMagicServer using the default port and
     * the default source.
     *
     * @param port - the port to which the server will bind and listen for
     *             incoming connections.
     * @param source - the source to use to send cards to connecting clients.
     */
    public AbstractMagicServer(int port, CardSource source) {
        this.port = port;
        this.source = source;
        this.numItems = NUM_ITEMS;
    }

    /**
     * Initializes a new AbstractMagicServer using the default port and
     * the default source.
     *
     * @param port - the port to which the server will bind and listen for
     *             incoming connections.
     * @param source - the source to use to send cards to connecting clients.
     * @param numItems - the number of items to send over the network before
     *                 closing the connection.
     */
    public AbstractMagicServer(int port, CardSource source, int numItems) {
        this.port = port;
        this.source = source;
        this.numItems = numItems;
    }

    protected int getPort() {
        return this.port;
    }

    protected CardSource getSource() {
        return this.source;
    }

    protected void changeSource(CardSource source) {
        this.source = source;
    }

    protected void changeItemsToSend(int numItems) {
        this.numItems = numItems;
    }

    protected int getItemsToSend(){
        return numItems;
    }

    protected void setCardsReturned(String command){
        System.out.println(command);
        switch(command){
            case ("-A") :
                this.numItems = THREE_TYPES;
                this.source.setCardType(CardType.ALL);
                break;
            case ("-C") :
                this.numItems = ONE_TYPE;
                this.source.setCardType(CardType.CREATURE);
                break;
            case ("-L") :
                this.numItems = ONE_TYPE;
                this.source.setCardType(CardType.LAND);
                break;
            case ("-S") :
                this.numItems = ONE_TYPE;
                this.source.setCardType(CardType.SPELL);
                break;
            case ("-LC") :
            case ("-CL") :
                this.numItems = TWO_TYPES;
                this.source.setCardType(CardType.LC);
                break;
            case ("-CS") :
            case ("-SC") :
                this.numItems = TWO_TYPES;
                this.source.setCardType(CardType.CS);
                break;
            case ("-LS") :
            case ("-SL") :
                this.numItems = TWO_TYPES;
                this.source.setCardType(CardType.LS);
                break;
            default :
                this.numItems = THREE_TYPES;
                this.source.setCardType(CardType.ALL);
                break;
        }
    }

    public abstract void listen() throws MagicServerException ;
}
