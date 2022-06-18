package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.WAIT_FOR_OTHERS_MOVES;

public class WaitForOthersMoves extends AnswerMessage {

    private final String move;

    /**
     * Class constructor.
     *
     */
    public WaitForOthersMoves(String move) {
        super(WAIT_FOR_OTHERS_MOVES);
        this.move = move;
    }

    public String getMove() {
        return move;
    }
}
