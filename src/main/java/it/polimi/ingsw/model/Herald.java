package it.polimi.ingsw.model;

import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.HERALD;

/**
 * Class that represent the Herald character card
 * @author Gabriele Munafò
 */
public class Herald extends CharacterCard {

    public Herald() {
        super(3, "Herald", HERALD);
    }

    /**
     * Calculates the influence on a provided island
     * @param parameters map that provides the island where to calculate the influence
     * @throws NullPointerException when parameters is null or when the island given in the map parameters is null
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception{
        super.doEffect(parameters);

        if (parameters == null){
            throw new NullPointerException();
        }
        Island island = (Island) parameters.get("Island");
        if (island == null){
            throw new NullPointerException();
        }
        Player[] arrayPlayers = (Player[]) parameters.get("ArrayPlayers");
        if (arrayPlayers == null){
            throw new NullPointerException();
        }
        int numPlayers = (int) parameters.get("NumPlayers");
        CharacterCard[] characterCardDeck = (CharacterCard[]) parameters.get("CharacterCardDeck");

        Player player = super.checkInfluence(island, true, numPlayers, arrayPlayers, characterCardDeck);

        parameters.put("Output", player);

    }

    @Override
    public String getDescription() {
        return "Choose an Island and resolve the Island as if Mother Nature had ended her movement there. \n" +
                "Mother Nature will still move and the Island where she ends her movement will also be resolved.";
    }

    @Override
    public CharacterCardEnum getType() {
        return HERALD;
    }
}