package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.GO_TO_LOBBY;

public class GoToLobby extends AnswerMessage{

    /**
     * Class constructor.
     */
    public GoToLobby() {
        super(GO_TO_LOBBY);
    }
}
