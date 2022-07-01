package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_NUM_PLAYERS;

/**
 * NumPLayers class represents NumPlayers network message.
 * This message is sent from the client to the server when the client represents the first player connected
 * to communicate the number of players for this game.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenNumPlayers extends CommandMessage {
    private final int numPlayers;

    /**
     * Class constructor
     *
     * @param numPlayers number of players for this game
     * @param nickname player's nickname
     */
    public ChosenNumPlayers(String nickname, int numPlayers) {
        super(CHOSEN_NUM_PLAYERS, nickname);
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
