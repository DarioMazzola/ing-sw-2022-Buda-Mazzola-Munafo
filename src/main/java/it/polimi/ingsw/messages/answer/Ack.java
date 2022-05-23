package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.ACK;

/**
 * This message is sent when a generic message has been acknowledged from the server.
 *
 * @author Dario Mazzola
 */
public class Ack extends AnswerMessage{

    private final int typeOfACK;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the nickname of the player to who is sent the message
     */
    public Ack(String nickname, int typeOfACK) {
        super(ACK);
        this.typeOfACK = typeOfACK;
    }

    public int getTypeOfACK() {
        return typeOfACK;
    }
}
