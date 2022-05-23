package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SEND_WINNER;

/**
 * This message is sent from the server to the client to communicate the winning player
 *
 * @author Dario Mazzola
 */
public class SendWinner extends AnswerMessage {

    String winner;

    /**
     * Message constructor
     *
     */
    public SendWinner(String winner) {
        super(SEND_WINNER);
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
