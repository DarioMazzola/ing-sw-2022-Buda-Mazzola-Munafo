package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_TEAM;

/**
 * SelectTeam class represents selectTeam network message.
 * This message is sent from the client to the server when there are 4 players.
 * It is used to communicate to the server the player's chosen team and if he/she wants to be the team leader.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenTeam extends CommandMessage {
    private final int selectedTeam;
    private final boolean isTeamLeader;

    /**
     * Class constructor
     *
     * @param nickname player's nickname
     * @param selectedTeam the team chosen by the player identified by nickname
     * @param isTeamLeader true if the player wants to be team leader, false otherwise
     */
    public ChosenTeam(String nickname, int selectedTeam, boolean isTeamLeader) {
        super(CHOSEN_TEAM, nickname);
        this.selectedTeam = selectedTeam;
        this.isTeamLeader = isTeamLeader;
    }

    public int getSelectedTeam() {
        return selectedTeam;
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }
}
