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
     */
    protected AnswerMessage(MessageType messageType) {
        super(messageType);
    }
}
