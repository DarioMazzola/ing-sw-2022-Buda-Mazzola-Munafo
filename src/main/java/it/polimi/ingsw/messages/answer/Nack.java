package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.NACK;

/**
 * This message is sent from the server to the client when a generic message has not been acknowledged. If the
 * client receives this message, it notifies the player of the error and asks him/her to make his/her choice again.
 *
 * @author Dario Mazzola
 */
public class Nack extends AnswerMessage{

    private final String typeOfError;
    /**
     * Message constructor
     *
     * @param typeOfError specifies the type of error
     */
    public Nack(String typeOfError) {
        super(NACK);
        this.typeOfError = typeOfError;
    }

    public String getTypeOfError() {
        return typeOfError;
    }
}
