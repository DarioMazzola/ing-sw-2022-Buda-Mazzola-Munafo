package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.messages.answer.AnswerMessage;

import java.util.List;

/**
 * This message is sent from the server to the client to communicate all the possible actions
 * that the player can do.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ActionPhase extends AnswerMessage {
    private final List<String> availableActions;

    /**
     * Class constructor.
     * @param availableActions all the available actions for the player
     */
    public ActionPhase(List<String> availableActions) {
        super(MessageType.ACTION_PHASE);
        this.availableActions = availableActions;
    }

    /**
     * Returns all the available action for the player
     */
    public List<String> getAvailableActions() {
        return availableActions;
    }
}
