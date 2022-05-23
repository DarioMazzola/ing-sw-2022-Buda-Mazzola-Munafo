package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_EXPERT_MODE;

/**
 * ExpertMode class represents ExpertMode network message.
 * This message is sent from the client to the server, when the client represents the first player connected,
 * to communicate whether the current game will be played in expert mode.
 *
 * @author Alessio Buda
 */
public class ChosenExpertMode extends CommandMessage {
    private final boolean expertMode;

    /**
     * CLass constructor.
     *
     * @param expertMode true if player wants to play in expert mode, false otherwise
     * @param nickname player's nickname
     */
    public ChosenExpertMode(String nickname, boolean expertMode) {
        super(CHOSEN_EXPERT_MODE, nickname);
        this.expertMode = expertMode;
    }

    public boolean isExpertMode() {
        return expertMode;
    }
}
