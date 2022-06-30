package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_NICKNAME;

/**
 * This message is sent by the server to the client to ask the player to choose the
 * nickname again because the one he had chosen is not available.
 *
 * @author Dario Mazzola
 */
public class SelectNickname extends AnswerMessage {


    /**
     * Class constructor
     */
    public SelectNickname() {
        super(SELECT_NICKNAME);
    }
}
