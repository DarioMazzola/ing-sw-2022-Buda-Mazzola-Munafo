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
    /**
     * Message constructor
     *
     * @param nickname The nickname of the player the message is sent to
     */
    protected EndGameDisconnection(String nickname) {
        super(END_GAME_DISCONNECTION, nickname);
    }
}
