package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.model.Player;

import java.util.Map;

public class MagicalMailMan extends CharacterCard{

    public MagicalMailMan() {
        super(1, "MagicalMailMan");
    }

    /**
     * Modifies the player's maximum number of possible moves, giving the possibility to do up to 2 additional moves.
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception {

        super.doEffect(null);

        Player currentPlayer = (Player) parameters.get("currentPlayer");

        currentPlayer.setMaxMoves(currentPlayer.getMaxMoves()+2);
    }

    @Override
    public String getDescription() {
        return "You may move Mother Nature up to 2 additional Islands than is indicated by " +
                    "the Assistant card you've played";
    }
}
