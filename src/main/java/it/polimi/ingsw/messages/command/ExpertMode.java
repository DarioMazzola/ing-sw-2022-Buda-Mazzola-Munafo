package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.CommandMessage;
import it.polimi.ingsw.messages.MessageType;

/**
 * ExpertMode class represents ExpertMode network message.
 * This message is sent from the client to the server, when the client represents the first player connected,
 * to communicate whether the current game will be played in expert mode.
 *
 * @author Alessio Buda
 */
public class ExpertMode extends CommandMessage {
    private final boolean expertMode;

    /**
     * CLass constructor.
     *
     * @param expertMode true if player wants to play in expert mode, false otherwise
     * @param nickname player's nickname
     */
    public ExpertMode(String nickname, boolean expertMode) {
        super(MessageType.EXPERT_MODE, nickname);
        this.expertMode = expertMode;
    }

    public boolean isExpertMode() {
        return expertMode;
    }
}
