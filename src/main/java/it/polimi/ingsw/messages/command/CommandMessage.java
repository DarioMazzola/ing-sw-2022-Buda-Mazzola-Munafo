package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.messages.Message;

import java.io.IOException;

/**
 * An abstract class representing a command message, sent from the client to the server.
 *
 * @author Dario Mazzola
 */
public abstract class CommandMessage extends Message {

    /**
     *
     * @param messageType The typology of the message
     * @param nickname The nickname of the player sending the message
     */
    protected CommandMessage(MessageType messageType, String nickname) {
        super(messageType, nickname);
    }

}