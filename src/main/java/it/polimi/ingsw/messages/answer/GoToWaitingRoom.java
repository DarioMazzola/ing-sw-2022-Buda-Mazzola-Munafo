package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.GO_TO_WAITING_ROOM;

/**
 * GoToWaitingRoom class represents GoToWaitingRoom network message.
 * This message is sent from the sever to the client when the server tells the client to go to the waiting room as
 * the player has finished his turn and has to wait for the others to make their moves.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class GoToWaitingRoom extends AnswerMessage{

    /**
     * Class constructor
     */
    public GoToWaitingRoom() {
        super(GO_TO_WAITING_ROOM);
    }
}
