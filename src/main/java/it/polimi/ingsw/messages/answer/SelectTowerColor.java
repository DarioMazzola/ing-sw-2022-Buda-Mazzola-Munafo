package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.model.Color;

import java.util.List;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to select his/her color of the tower for this match
 *
 * @author Dario Mazzola
 */
public class SelectTowerColor extends AnswerMessage{
    private final List<Color> availableColors;

    /**
     * Class constructor.
     *
     * @param nickname    the nickname of the player the message is sent to
     * @param availableColors the tower colors not chosen by other players
     */
    protected SelectTowerColor(String nickname, List<Color> availableColors) {
        super(MessageType.SELECT_COLOR_TOWER, nickname);
        this.availableColors = availableColors;
    }

    public List<Color> getAvailableColors() {
        return availableColors;
    }
}
