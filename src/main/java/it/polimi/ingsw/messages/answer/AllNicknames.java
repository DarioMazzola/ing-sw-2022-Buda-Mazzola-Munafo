package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.AnswerMessage;
import it.polimi.ingsw.messages.MessageType;

import java.util.List;

/**
 * AllNicknames class represents AllNicknames network message.
 * This message is sent from the sever to the client when there are 4 players correctly connected.
 * This message is used to manage the team creation phase and it indicates the end of the wizard selection phase the beginning of the team selection phase.
 *
 * @author Alessio Buda
 */
public class AllNicknames extends AnswerMessage {
    private final List<String> selectedNicknames;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param selectedNicknames list of all nicknames selected by other players
     */
    public AllNicknames(String nickname, List<String> selectedNicknames) {
        super(MessageType.ALL_NICKNMAES, nickname);
        this.selectedNicknames = selectedNicknames;
    }

    public List<String> getSelectedNicknames() {
        return selectedNicknames;
    }
}
