package Common;

import java.io.Serializable;

/**
 * Created by Cagle on 11/2/2018.
 */
public class Card implements Serializable {
    /** Name of the card */
    private String cardName;
    /** Which type of card(speel creature, land, spell, etc) */
    private Type type;
    /** Energy required to use this card */
    private String mana;
    /** Card location in the input file */
    private short id;

    /**
     * Create a single card for magic the gathering.
     * @param id - unique identifier(currently location in input file
     * @param name - name of the card
     * @param type - type of card
     * @param mana - energy required to use the card.
     */
    public Card(short id, String name, Type type, String mana){
        this.id = id;
        this.cardName = name;
        this.type = type;
        this.mana = mana;
    }

    /**
     * Create a single card for magic the gathering
     * @param id - unique identifier.
     * @param name - name of the card.
     * @param type - string version of the type of card.
     * @param mana - energy required to use the card
     */
    public Card(short id, String name, String type, String mana){
        this.id = id;
        this.cardName = name;
        this.assignType(type);
        this.mana = mana;
    }

    /**
     * Given a string representing card type the method will change it into the
     * enumeration version of the type.
     * @param info - string representing the type
     */
    private final void assignType(String info){
        this.type = Type.UNKNOWN;
        Type[] types = Type.values();
        for( Type find : types ) {
            
            if (info.contains(find.toString())) {
                this.type = find;
            }
            if (this.type == Type.UNKNOWN){
                if (info.contains("Instant") ||
                    info.contains("Sorcery") ||
                    info.contains("Enchantment")){
                    this.type = Type.SPELL;
                }
                else{
                    this.type = Type.UNKNOWN;
                }
            }
        }
    }

    /**
     * Get the card's unique id number
     * @return a card's unique id number.
     */
    public short getId(){
        return this.id;
    }

    /**
     * Get a card's name
     * @return a card's name
     */
    public String getCardName(){
        return this.cardName;
    }

    /**
     * Get a card's type.
     * @return a card's type
     */
    public Type getType(){
        return this.type;
    }

    /**
     * Change a card's type
     * @param type - new type to assign a card.
     */
    public void setType(Type type){
        this.type = type;
    }

    /**
     * Returns a card in string format.
     * @return - card in string format.
     */
    public String toString(){
        return String.format("%30s: %10s( %4s)",this.cardName, this.type,
                             this.mana );
    }

}
