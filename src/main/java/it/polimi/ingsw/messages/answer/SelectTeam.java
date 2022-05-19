package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to choose the team her/she wants to play in this match
 *
 * @author Dario Mazzola
 */
public class SelectTeam extends AnswerMessage{

    /**
     * Class constructor.
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected SelectTeam(MessageType messageType, String nickname) {
        super(messageType, nickname);
    }
}
