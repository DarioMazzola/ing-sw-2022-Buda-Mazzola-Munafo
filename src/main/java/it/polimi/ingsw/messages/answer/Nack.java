package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

import static it.polimi.ingsw.messages.MessageType.NACK;

/**
 * This message is sent from the server to the client when a generic message has not been acknowledged. If the
 * client receives this message, it notifies the player of the error and asks him/her to make his/her choice again.
 */
public class Nack extends AnswerMessage{

    private final int typeOfError;
    /**
     * Message constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected Nack(String nickname, int typeOfError) {
        super(NACK, nickname);
        this.typeOfError = typeOfError;
    }

    public int getTypeOfError() {
        return typeOfError;
    }
}
