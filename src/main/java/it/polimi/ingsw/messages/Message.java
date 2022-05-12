package it.polimi.ingsw.messages;

public abstract class Message {

    private final MessageType messageType;
    private final String nickname;

    protected Message(MessageType messageType, String nickname) {
        this.messageType = messageType;
        this.nickname = nickname;
    }

    public MessageType getType() {
        return messageType;
    }

    public String getNickname() {
        return nickname;
    }
}
