package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.MOVE_MOTHER;

/**
 * This message is sent from the client to the server to communicate the number mother nature steps selected
 * by the player.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class MoveMother extends CommandMessage{

    private final int motherMoves;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param motherMoves The mother nature steps chosen by the player
     */
    public MoveMother(String nickname, int motherMoves) {
        super(MOVE_MOTHER, nickname);
        this.motherMoves = motherMoves;
    }

    public int getMotherMoves() {
        return motherMoves;
    }
}
