package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;


/**
 * This message is sent from the server to the client to communicate that the player
 * has to choose the team her/she wants to play in this match
 *
 * @author Dario Mazzola
 */
public class SelectTeam extends AnswerMessage{
    private final String[] teamArray;
    private final String[] leaderArray;

    /**Class constructor.
     *
     * @param teamArray an array with the nicknames of the players that are not leaders of their team, the number of the team is identified by the index in the array + 1
     * @param leaderArray an array with the nicknames of the players that are leaders of their team, the number of the team is identified by the index in the array + 1
     */
    public SelectTeam(String[] teamArray, String[] leaderArray) {
        super(MessageType.SELECT_TEAM);
        this.teamArray = teamArray;
        this.leaderArray = leaderArray;
    }

    public String[] getTeamArray() {
        return teamArray;
    }

    public String[] getLeaderArray() {
        return leaderArray;
    }
}
