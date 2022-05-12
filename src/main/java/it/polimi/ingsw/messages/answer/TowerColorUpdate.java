package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.AnswerMessage;
import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.model.Color;

import java.util.List;

/**
 * TowerColorUpdate class represents TowerColorUpdate network message.
 * This message is sent from the server to the client to comunicate all the available tower colors.
 * If there are more than 4 players it is only sent to the team leader.
 *
 * @author Alessio Buda
 */
public class TowerColorUpdate extends AnswerMessage {
    private final List<Color> availableColors;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param availableColors list of all the tower colors that have not been chosen by other players.
     */
    public TowerColorUpdate(String nickname, List<Color> availableColors) {
        super(MessageType.TOWER_COLOR_UPDATE, nickname);
        this.availableColors = availableColors;
    }

    public List<Color> getAvailableColors() {
        return availableColors;
    }
}
