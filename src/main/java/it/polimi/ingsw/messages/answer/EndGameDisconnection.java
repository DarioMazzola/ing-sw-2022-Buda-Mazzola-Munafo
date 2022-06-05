package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

import static it.polimi.ingsw.messages.MessageType.END_GAME_DISCONNECTION;

/**
 * This message is sent from the server to the client to signal that the game has ended due to a client
 * disconnection.
 *
 * @author Dario Mazzola
 */
public class EndGameDisconnection extends AnswerMessage{

    private final String errorCause;
    /**
     * Message constructor
     *
     * @param errorCause the message explaining why there was a disconnection
     */
    public EndGameDisconnection(String errorCause) {
        super(END_GAME_DISCONNECTION);
        this.errorCause = errorCause;
    }

    public String getErrorCause() {
        return errorCause;
    }
}
