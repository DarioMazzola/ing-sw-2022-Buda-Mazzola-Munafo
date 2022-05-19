package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.utils.TeamInfo;

import java.util.List;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to choose the team her/she wants to play in this match
 *
 * @author Dario Mazzola
 */
public class SelectTeam extends AnswerMessage{
    private final List<TeamInfo> teamInfos;

    /**Class constructor.
     *
     * @param nickname the nickname of the player the message is sent to
     * @param teamInfos a list containing for every player (identified by his/hers nickname) the selected team and if the player is leader of the team
     */
    protected SelectTeam(String nickname, List<TeamInfo> teamInfos) {
        super(MessageType.SELECT_TEAM, nickname);
        this.teamInfos = teamInfos;
    }

    public List<TeamInfo> getTeamInfos() {
        return teamInfos;
    }
}
