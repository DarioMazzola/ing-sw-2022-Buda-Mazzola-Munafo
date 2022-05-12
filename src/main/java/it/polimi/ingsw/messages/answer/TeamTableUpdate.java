package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.AnswerMessage;
import it.polimi.ingsw.messages.MessageType;

/**
 * TeamTableUpdate class represents TeamTableUpdate network message.
 * This message is sent from the server to the client during the team selection phase.
 * It is used to update information about the forming teams.
 *
 * @author Alessio Buda
 */
public class TeamTableUpdate extends AnswerMessage {
    private final String player_nickname;
    private final int teamChosen;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param player_nickname nickname of the player to whom the update refers
     * @param teamChosen the player's chosen team
     */
    public TeamTableUpdate(String nickname, String player_nickname, int teamChosen) {
        super(MessageType.TEAM_TABLE_UPDATE, nickname);
        this.player_nickname = player_nickname;
        this.teamChosen = teamChosen;
    }

    public String getPlayer_nickname() {
        return player_nickname;
    }
}
