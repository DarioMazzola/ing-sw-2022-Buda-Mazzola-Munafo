package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_EXPERT_MODE;

/**
 * This message is sent from the server to the client to communicate that
 * the player has to choose whether the match will be played in expert mode.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class SelectExpertMode extends AnswerMessage {

    /**
     * Class constructor.
     *
     */
    public SelectExpertMode() {
        super(SELECT_EXPERT_MODE);
    }
}
