package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

import static it.polimi.ingsw.messages.MessageType.NEXT_PHASE;

/**
 * This message is sent from the server to the client to communicate the end of the current round phase and the
 * beginning of next phase
 *
 * @author Dario Mazzola
 */
public class NextPhase extends AnswerMessage {

    MessageType nextPhase;

    /**
     * Message constructor
     *
     */
    public NextPhase(MessageType nextPhase) {
        super(NEXT_PHASE);
        this.nextPhase = nextPhase;
    }

    public MessageType getNextPhase() {
        return nextPhase;
    }
}
