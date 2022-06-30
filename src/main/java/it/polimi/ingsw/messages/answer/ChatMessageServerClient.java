package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

/**
 * MessageServerClient class represents a chat message to be forwarded by the server to a player.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChatMessageServerClient extends AnswerMessage {
    private final String message;

    /**
     * Class constructor.
     *
     * @param message the message to be forwarded by the server to a player
     */
    public ChatMessageServerClient(String message) {
        super(MessageType.CHAT_MESSAGE_SERVER_CLIENT);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
