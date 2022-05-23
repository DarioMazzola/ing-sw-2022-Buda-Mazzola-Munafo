package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_NICKNAME;

public class SelectNickname extends AnswerMessage{


    /**
     * Class constructor
     *
     */
    public SelectNickname() {
        super(SELECT_NICKNAME);
    }
}
