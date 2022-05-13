package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.PING;

/**
 * This message is sent from the server to the client to check if it is still connected.
 */
public class Ping extends AnswerMessage{

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected Ping(String nickname) {
        super(PING, nickname);
    }
}
