package it.polimi.ingsw.client;

import it.polimi.ingsw.model.CharacterCard;
import it.polimi.ingsw.model.CharacterCardEnum;

/**
 * Class that represents the ReducedGameModel
 * @author Gabriele Munafo'
 */
public class ReducedCharacterCard {
    private final int cost;
    private final boolean inUse;
    private final String cardName;
    private final CharacterCardEnum type;
    private final String description;

    public ReducedCharacterCard(CharacterCard c){
        cost = c.getCost();
        inUse = c.isInUse();
        cardName = c.toString();
        type = c.getType();
        description = c.getDescription();
    }

    public String getCardName(){
        return cardName;
    }

    public CharacterCardEnum getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public boolean isInUse(){
        return inUse;
    }
}
