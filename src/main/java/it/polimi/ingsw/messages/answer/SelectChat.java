package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

public class SelectChat extends AnswerMessage{

    /**
     * Select chat message is sent from the server to the client
     *to ask the first player whether the member of the teams (when playing in four players) can communicate with each other.
     */
    public SelectChat() {
        super(MessageType.SELECT_CHAT);
    }
}