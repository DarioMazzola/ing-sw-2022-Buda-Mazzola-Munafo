package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.model.Color;

import java.util.List;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to select his/her color of the tower for this match.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class SelectColorTower extends AnswerMessage{
    private final List<Color> availableColors;

    /**
     * Class constructor
     *
     * @param availableColors the tower colors not chosen by other players
     */
    public SelectColorTower(List<Color> availableColors) {
        super(MessageType.SELECT_COLOR_TOWER);
        this.availableColors = availableColors;
    }

    public List<Color> getAvailableColors() {
        return availableColors;
    }
}
