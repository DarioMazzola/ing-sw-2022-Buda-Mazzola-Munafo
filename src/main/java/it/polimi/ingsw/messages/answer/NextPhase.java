package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;



import static it.polimi.ingsw.messages.MessageType.NEXT_PHASE;

/**
 * This message is sent from the server to the client to communicate the end of the current round phase and the
 * beginning of next phase
 */
public class NextPhase extends AnswerMessage {

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected NextPhase(String nickname) {
        super(NEXT_PHASE, nickname);
    }
}