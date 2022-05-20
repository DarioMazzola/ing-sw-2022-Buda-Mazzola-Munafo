package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_NICKNAME;

public class SelectNickname extends AnswerMessage{


    /**
     * Class constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    public SelectNickname(String nickname) {
        super(SELECT_NICKNAME, nickname);
    }
}
