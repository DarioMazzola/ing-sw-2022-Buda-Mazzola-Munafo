package it.polimi.ingsw.model;

import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.MAGICAL_MAILMAN;

/**
 * MagicalMailMan class represents Magical Mailman character card.
 *
 * @author Dario Mazzola
 */
public class MagicalMailMan extends CharacterCard{

    /**
     * Class constructor, initializes card with name and initial cost.
     */
    public MagicalMailMan() {
        super(1, "MagicalMailMan", MAGICAL_MAILMAN);
    }

    /**
     * Modifies the player's maximum number of possible moves, giving the possibility to do up to 2 additional moves.
     *
     *  @param parameters A map that contains the objects that need to the characterCards and the objects that must be returned
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception {

        super.doEffect(null);

        Player currentPlayer = (Player) parameters.get("currentPlayer");

        currentPlayer.setMaxMoves(currentPlayer.getMaxMoves()+2);
    }

    @Override
    public String getDescription() {
        return "You may move Mother Nature up to 2 additional Islands than \n" +
                "is indicated by the Assistant card you've played";
    }

    @Override
    public CharacterCardEnum getType() {
        return MAGICAL_MAILMAN;
    }
}
