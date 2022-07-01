package it.polimi.ingsw.messages;

/**
 * Represents a message
 *
 * @author Dario Mazzola & Alessio Buda & Gabriele Munafo'
 */
public abstract class Message {

    private final MessageType messageType;

    /**
     * Class constructor
     *
     * @param messageType the type of message to create
     */
    protected Message(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getType() {
        return messageType;
    }
}
