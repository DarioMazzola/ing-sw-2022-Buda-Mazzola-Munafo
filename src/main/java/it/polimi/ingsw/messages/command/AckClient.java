package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.answer.AnswerMessage;

import static it.polimi.ingsw.messages.MessageType.ACK;

/**
 * This message is sent when a generic message has been acknowledged from the client.
 *
 * @author Dario Mazzola
 */
public class AckClient extends CommandMessage {

    /**
     * Message constructor
     *
     * @param nickname the nickname of the player sending the message
     */
    public AckClient(String nickname) {
        super(ACK, nickname);
    }
}
