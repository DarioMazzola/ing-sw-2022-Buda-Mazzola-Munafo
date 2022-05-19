package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_WIZARD;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to choose the wizard for this match
 *
 * @author Dario Mazzola
 */
public class SelectWizard extends AnswerMessage{

    /**
     * Abstract class representing a message that is sent from the server to the client
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected SelectWizard(String nickname) {
        super(SELECT_WIZARD, nickname);
    }
}
