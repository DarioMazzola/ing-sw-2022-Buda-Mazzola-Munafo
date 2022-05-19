package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.answer.AnswerMessage;

import static it.polimi.ingsw.messages.MessageType.SELECT_EXPERT_MODE;

/**
 * This message is sent from the server to the client to communicate that
 * the player has to choose whether the match will be played in expert mode.
 *
 * @author Dario Mazzola
 */
public class SelectExpertMode extends AnswerMessage {

    /**
     * Class constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected SelectExpertMode(String nickname) {
        super(SELECT_EXPERT_MODE, nickname);
    }
}
