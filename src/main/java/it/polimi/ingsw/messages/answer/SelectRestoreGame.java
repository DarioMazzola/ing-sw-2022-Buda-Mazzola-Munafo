package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_RESTORE_GAME;

/**
 * This message is sent from the server to the client to notify that there is a match saved on the server.
 * The client has to choose whether to restore the game or not.
 *
 * @author Dario Mazzola
 */
public class SelectRestoreGame extends AnswerMessage{

    /**
     * Class constructor.
     */
    public SelectRestoreGame() {
        super(SELECT_RESTORE_GAME);
    }
}
