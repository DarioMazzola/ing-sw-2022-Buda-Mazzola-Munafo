package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.PLAYERS_NUMBER;

/**
 * NumPLayers class represents NumPlayers network message.
 * This message is sent from the client to the server when the client represents the first player connected
 * to communicate the number of players for this game.
 *
 * @author Alessio Buda
 */
public class NumPlayers extends CommandMessage {
    private final int numPlayers;

    /**
     * Class constructor.
     *
     * @param numPlayers number of players for this game
     * @param nickname player's nickname
     */
    public NumPlayers(String nickname, int numPlayers) {
        super(PLAYERS_NUMBER, nickname);
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
