package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedPlayer;

import static it.polimi.ingsw.messages.MessageType.UPDATE_CURRENT_PLAYER;

/**
 * Class representing message from the server to the client to notify the change of the current player.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateCurrentPlayer extends AnswerMessage{

    private final ReducedPlayer currentPlayer;

    /**
     * Class constructor
     */
    public UpdateCurrentPlayer(ReducedPlayer currentPlayer) {
        super(UPDATE_CURRENT_PLAYER);
        this.currentPlayer = currentPlayer;
    }

    public ReducedPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}
