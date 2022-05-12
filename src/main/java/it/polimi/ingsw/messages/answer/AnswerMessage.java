package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.MessageType;

/**
 * An abstract class representing an answer message, sent form the server to the client.
 *
 * @author Dario Mazzola
 */

public abstract class AnswerMessage extends Message {

    /**
     * Abstract class representing a message that is sent from the server to the client
     * @param messageType The typology of the message
     * @param nickname The nickname of the player the message is sent to
     */
    protected AnswerMessage(MessageType messageType, String nickname) {
        super(messageType, nickname);
    }
}
