package it.polimi.ingsw.messages.answer;


import static it.polimi.ingsw.messages.MessageType.GO_TO_WAITING_ROOM;

public class GoToWaitingRoom extends AnswerMessage{

    /**
     * Abstract class representing a message that is sent from the server to the client
     *
     */
    public GoToWaitingRoom() {
        super(GO_TO_WAITING_ROOM);
    }
}
