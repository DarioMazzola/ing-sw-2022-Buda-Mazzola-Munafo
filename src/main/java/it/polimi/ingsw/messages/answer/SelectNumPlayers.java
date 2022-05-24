package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_NUM_PLAYERS;

/**
 * This message is sent from the server to the client to communicate that
 * the player has to choose the number of players for this match
 *
 * @author Dario Mazzola
 */
public class SelectNumPlayers extends AnswerMessage {

    /**
     * Class constructor
     *
     */
    public SelectNumPlayers() {
        super(SELECT_NUM_PLAYERS);
    }
}
