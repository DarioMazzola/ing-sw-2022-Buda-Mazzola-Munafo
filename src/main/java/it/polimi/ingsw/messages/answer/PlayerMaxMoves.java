package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.PLAYER_MAX_MOVES;

/**
 * This message is sent from the server to the client to communicate the maximum number of steps of mother
 * nature for the current player in this round.
 *
 * @author Dario Mazzola
 */
public class PlayerMaxMoves extends AnswerMessage{

    private final int maxMoves;
    /**
     * Message constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    public PlayerMaxMoves(String nickname, int maxMoves) {
        super(PLAYER_MAX_MOVES, nickname);
        this.maxMoves = maxMoves;
    }

    public int getMaxMoves() {
        return maxMoves;
    }
}
