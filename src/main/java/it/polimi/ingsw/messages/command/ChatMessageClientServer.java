package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.MessageType;

/**
 * MessageClientServer class represents a message sent from the client
 * to the server in order to be forwarded to the teammate of the player.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChatMessageClientServer extends CommandMessage {
    private final String message;

    /**
     * Class constructor.
     *
     * @param nickname the player's nickname
     * @param message the message the player wants to send to his/hers teammate
     */
    public ChatMessageClientServer(String nickname, String message) {
        super(MessageType.CHAT_MESSAGE_CLIENT_SERVER, nickname);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
