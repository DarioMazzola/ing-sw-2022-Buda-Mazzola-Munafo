package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.messages.Message;

/**
 * An abstract class representing a command message, sent from the client to the server.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public abstract class CommandMessage extends Message {
    private final String nickname;

    /**
     * Class constructor
     *
     * @param messageType The typology of the message
     * @param nickname The nickname of the player sending the message
     */
    protected CommandMessage(MessageType messageType, String nickname) {
        super(messageType);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}