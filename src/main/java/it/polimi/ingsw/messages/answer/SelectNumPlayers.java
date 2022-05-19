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
     * @param nickname The nickname of the player the message is sent to
     */
    protected SelectNumPlayers(String nickname) {
        super(SELECT_NUM_PLAYERS, nickname);
    }
}
