package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.GAME_FULL;

/**
 * GameFull class represents GameFull network message.
 * This message is sent from the sever to the client when there is already the maximum number of admissible players correctly connected.
 *
 * @author Dario Mazzola
 */
public class GameFull extends AnswerMessage {

    /**
     * Class constructor.
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected GameFull(String nickname) {
        super(GAME_FULL, nickname);
    }
}
