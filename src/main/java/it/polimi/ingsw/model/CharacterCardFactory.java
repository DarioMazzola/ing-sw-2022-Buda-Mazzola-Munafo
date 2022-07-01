package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;

/**
 * CharacterCardFactory class, representation of the characterCard creator class
 *
 * @author Dario Mazzola
 */

public class CharacterCardFactory {

    /**
     * Creates a characterCard from its name and the elements that card needs to be created
     *
     * @param cardType the characterCard name
     * @param bag      the bag of the match that some cards needs for creation
     * @return the character chard of the type indicated by the parameters
     * @throws BagException             when a BagException occurs while creating a card of type Monk, Jolly or SpoiledPrincess
     * @throws IllegalArgumentException when the given cardType does not correspond to any card
     */
    public CharacterCard getCharacterCard(CharacterCardEnum cardType, Bag bag) throws BagException, IllegalArgumentException {

        CharacterCard c;

        switch (cardType) {
            case FARMER:
                c = new Farmer();
                break;
            case MAGICAL_MAILMAN:
                c = new MagicalMailMan();
                break;
            case HERB_GRANMA:
                c = new HerbGranma();
                break;
            case MINSTREL:
                c = new Minstrel();
                break;
            case MONK:
                c = new Monk(bag);
                break;
            case HERALD:
                c = new Herald();
                break;
            case CENTAUR:
                c = new Centaur();
                break;
            case JOLLY:
                c = new Jolly(bag);
                break;
            case KNIGHT:
                c = new Knight();
                break;
            case MUSHROOM_HUNTER:
                c = new MushroomHunter();
                break;
            case SPOILED_PRINCESS:
                c = new SpoiledPrincess(bag);
                break;
            case THIEF:
                c = new Thief();
                break;
            default:
                throw new IllegalArgumentException("There is no characterCard with this name");
        }

        return c;
    }
}