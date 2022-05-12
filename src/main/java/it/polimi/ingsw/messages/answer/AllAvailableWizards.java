package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.AnswerMessage;
import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.model.Wizard;

import java.util.List;

/**
 * AllAvailableWizards class represents AllAvailableWizard network message.
 *This message is sent from the client to the server to communicate all the available wizards.
 * It indicates the end of the nickname selection phase and the beginning of the wizard selection phase.
 *
 * @author Alessio Buda
 */
public class AllAvailableWizards extends AnswerMessage {
    private final List<Wizard> availableWizards;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param availableWizards list of all the wizard that have not been selected by other players
     */
    public AllAvailableWizards(String nickname, List<Wizard> availableWizards) {
        super(MessageType.ALL_AVAILABLE_WIZARDS, nickname);
        this.availableWizards = availableWizards;
    }

    public List<Wizard> getAvailableWizards() {
        return availableWizards;
    }
}
