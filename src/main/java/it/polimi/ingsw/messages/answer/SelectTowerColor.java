package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to select his/her color of the tower for this match
 *
 * @author Dario Mazzola
 */
public class SelectTowerColor extends AnswerMessage{

    /**
     * Class constructor.
     *
     * @param messageType The typology of the message
     * @param nickname    The nickname of the player the message is sent to
     */
    protected SelectTowerColor(MessageType messageType, String nickname) {
        super(messageType, nickname);
    }
}
