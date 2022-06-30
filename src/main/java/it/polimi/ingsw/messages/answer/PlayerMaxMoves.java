package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.PLAYER_MAX_MOVES;

/**
 * This message is sent from the server to the client to communicate the maximum number of steps of mother
 * nature for the current player in this round.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class PlayerMaxMoves extends AnswerMessage{

    private final int maxMoves;
    /**
     * Message constructor
     *
     */
    public PlayerMaxMoves(int maxMoves) {
        super(PLAYER_MAX_MOVES);
        this.maxMoves = maxMoves;
    }

    public int getMaxMoves() {
        return maxMoves;
    }
}
