package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.PONG;

/**
 * This message is sent from the client to the server to signal that the connection is still alive.
 *
 * @author Dario Mazzola
 */
public class Pong extends CommandMessage{

    /**
     * Message constructor
     * @param nickname the nickname of the player sending the message
     */
    public Pong(String nickname) {
        super(PONG, nickname);
    }
}
