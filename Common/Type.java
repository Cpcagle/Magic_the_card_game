package Common;

import java.io.Serializable;

/**
 *
 * Created by Cagle on 11/2/2018.
 */
public enum Type {
    /**
     * Literal for Creature cards
     */
    CREATURE("Creature"),
    /**
     * Literal for Land cards
     */
    LAND("Land"),
    /**
     * Literal for Spell cards
     */
    SPELL("Spell"),
    /**
     * Literal for Artifact cards
     */
    ARTIFACT("Artifact"),
    /**
     * Literal for Unknown cards
     */
    UNKNOWN("Unknown");
    /**
     * Name of the card
     */
    private String name;

    /**
     * Creates a type.
     *
     * @param type - the type of the card.
     */
    Type(String type) {
        this.name = type;
    }

    /**
     * Prints the name of the type.
     *
     * @return - the name of the type.
     */
    public String toString(){
        return String.format(this.name);
    }

}
