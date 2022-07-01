package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.WAIT_FOR_OTHERS_MOVES;

/**
 * This message is sent from the server to the client to tell the player to wait for another
 * player to make a choice. The choice is represented by the move parameter.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class WaitForOthersMoves extends AnswerMessage {

    private final String move;

    /**
     * Class constructor
     */
    public WaitForOthersMoves(String move) {
        super(WAIT_FOR_OTHERS_MOVES);
        this.move = move;
    }

    public String getMove() {
        return move;
    }
}
