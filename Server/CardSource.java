package Server;

import Common.Card;
import Common.Type;

import java.io.*;
import java.util.*;


/**
 * Class that define the type of cards that can be returned for a deck
 * in Magic the Gathering.
 *
 * Created by Cameron Cagle on 11/6/2018.
 */
public class CardSource extends Object{
    /** Holds the cards */
    private ArrayList<Card> deck;
    /** Scan in the data from the input file */
    private Scanner fileIn;
    /** Used to randomly choose cards */
    private Random generator;
    /** the types of cards we can return */
    private CardType type;

    /**
     * Create a new CardSource object to store and choose cards to send back
     * to client.
     *
     * @throws FileNotFoundException
     */
    public CardSource() throws FileNotFoundException {
        this.deck = new ArrayList<Card>();
        this.generator = new Random();
        this.type = CardType.ALL; 
        initDeck();
    }

    /**
     * Reads in the cards from the input file and place them in a deck
     * (first come first server).
     */
    private final void initDeck() throws FileNotFoundException {
        fileIn = new Scanner(new FileInputStream(new File("./cards.csv")));
        while(fileIn.hasNextLine()){
            String[] data = fileIn.nextLine().split(",");
            this.deck.add(new Card(Short.parseShort(data[0]), data[1],
                    data[2], data[3]));
        }
    }

    /**
     * Change the type of card allowed to be sent back to the client.
     *
     * @param type - Type of card allowed to be sent via the network.
     */
    protected void setCardType(CardType type) {
        this.type = type;
    }

    /**
     * Displays the current deck to the screen.
     */
    public void displayDeck() {
        for(int i = 0; i < this.deck.size(); i++){
            System.out.println(this.deck.get(i));
        }
    }

    /**
     * Gets a randomly chosen card to return to the client.
     *
     * @return - a randomly chosen card to return to the client.
     */
    public Card next() {
        Boolean right = false;
        Card card = null;
        while(!right){
            card = this.deck.get(generator.nextInt(this.deck.size()));
            right = valid(card);
        }
        return card;
    }

    /**
     * Determine if a card is suitable for returning to the client. A card
     * is suitable if it is one of the type specified by the setCardType.
     *
     * @param card - card to test for validity
     * @return true if the card is suitable, false otherwise.
     */
    private boolean valid(Card card) {
        switch(this.type){
            case CREATURE :
                if (card.getType() == Type.CREATURE) {
                    return true;
                }
                break;
            case LAND :
                if (card.getType() == Type.LAND) {
                    return true;
                }
                break;
            case SPELL :
                if (card.getType() == Type.SPELL) {
                    return true;
                }
                break;
            case LC :
                if (card.getType() == Type.CREATURE ||
                        card.getType() == Type.LAND) {
                    return true;
                }
                break;
            case CS :
                if (card.getType() == Type.CREATURE ||
                        card.getType() == Type.SPELL) {
                    return true;
                }
                break;
            case LS :
                if (card.getType() == Type.LAND ||
                        card.getType() == Type.SPELL) {
                    return true;
                }
                break;
            case ALL :
                if (card.getType() == Type.LAND ||
                        card.getType() == Type.SPELL ||
                        card.getType() == Type.CREATURE){
                    return true;
                }
                break;
        }
        return false;
    }

    public static void main(String[] args)throws FileNotFoundException {
        try {
            CardSource test = new CardSource();
            test.displayDeck();
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
    }

}
