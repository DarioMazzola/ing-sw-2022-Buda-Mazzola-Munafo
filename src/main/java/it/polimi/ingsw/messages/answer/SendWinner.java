package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

import static it.polimi.ingsw.messages.MessageType.SEND_WINNER;

public class SendWinner extends AnswerMessage {

    String winner;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected SendWinner(String nickname, String winner) {
        super(SEND_WINNER, nickname);
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
