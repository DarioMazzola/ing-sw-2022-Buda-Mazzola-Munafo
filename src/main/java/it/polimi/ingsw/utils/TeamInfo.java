package it.polimi.ingsw.utils;

public final class TeamInfo {
    private final String nickname;
    private final int numTeam;
    private final boolean isTeamLeader;

    public TeamInfo(String nickname, int numTeam, boolean isTeamLeader) {
        this.nickname = nickname;
        this.numTeam = numTeam;
        this.isTeamLeader = isTeamLeader;
    }

    public String nickname() {
        return nickname;
    }

    public int numTeam() {
        return numTeam;
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }
}
