package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.GO_TO_WAITING_ROOM;

public class GoToWaitingRoom extends AnswerMessage{

    /**
     * Class constructor.
     */
    public GoToWaitingRoom() {
        super(GO_TO_WAITING_ROOM);
    }
}
