package Server;

/**
 * Created by Cagle on 11/5/2018.
 */
public enum CardType {
    /** All types of cards are allowed */
    ALL,
    /** Creature cards are allowed */
    CREATURE,
    /** Creature or Spell cards are allowed */
    CS,
    /** Land or Creature cards are allowed */
    LC,
    /** Land cards are allowed */
    LAND,
    /** Land or Spell cards are allowed */
    LS,
    /** Spell cards are allowed */
    SPELL;

}
