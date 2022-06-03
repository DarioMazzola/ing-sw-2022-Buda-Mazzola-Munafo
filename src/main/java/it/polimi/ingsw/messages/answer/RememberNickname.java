package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.REMEMBER_NICKNAME;

public class RememberNickname extends AnswerMessage{

    private final String nickname;
    public RememberNickname(String nickname) {
        super(REMEMBER_NICKNAME);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
