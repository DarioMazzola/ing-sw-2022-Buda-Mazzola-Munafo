package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.REMEMBER_NICKNAME;

/**
 * This message is sent by the server to the client to remind the client of the nickname
 * chosen by the player following a disconnection.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
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
