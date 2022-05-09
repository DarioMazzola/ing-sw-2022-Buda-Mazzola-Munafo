package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.model.Dashboard;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Player;

import java.util.Map;

/**
 * Class that represents the context of the Strategy pattern
 *
 * @author Dario Mazzola
 */
public class ContextCharacterCard {

    CharacterCard card;

    /**
     * Class constructor: creates a new context
     * @param card The character card used to set the context
     */
    public ContextCharacterCard (CharacterCard card){
        this.card = card;
    }

    /**
     * @see CharacterCard#doEffect(Map parameters)
     */
    public void doEffect(Map<String, Object> parameters) throws Exception{
        card.doEffect(parameters);
    }

    /**
     * @see CharacterCard#checkProf(Player[] players, Player currentPlayer, House house)
     */
    protected void checkProf(Player[] players, Player currentPlayer, House house) throws IllegalChoiceException {
       card.checkProf(players, currentPlayer, house);
    }

    /**
     * @see CharacterCard#checkInfluence(Island islandChosen, Boolean expertMode, int numPlayer, Player[] arrayPlayers)
     */
    protected Player checkInfluence(Island islandChosen, Boolean expertMode, int numPlayers, Player[] arrayPlayers){
        return card.checkInfluence(islandChosen, expertMode, numPlayers, arrayPlayers);
    }
}
