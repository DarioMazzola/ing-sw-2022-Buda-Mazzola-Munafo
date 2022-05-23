package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.PING;

/**
 * This message is sent from the server to the client to check if it is still connected
 *
 * @author Dario Mazzola
 */
public class Ping extends AnswerMessage{

    /**
     * Message constructor
     *
     */
    public Ping() {
        super(PING);
    }
}
