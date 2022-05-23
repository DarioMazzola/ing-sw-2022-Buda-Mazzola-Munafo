package it.polimi.ingsw.messages;

public abstract class Message {

    private final MessageType messageType;

    protected Message(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getType() {
        return messageType;
    }
}
