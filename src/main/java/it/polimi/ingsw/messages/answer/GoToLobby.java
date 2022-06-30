package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.GO_TO_LOBBY;

/**
 * GoToLobby class represents GoToLobby network message.
 * This message is sent from the sever to the client when the server tells the client to go to the
 * lobby as the game hasn't started yet.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class GoToLobby extends AnswerMessage{

    /**
     * Class constructor.
     */
    public GoToLobby() {
        super(GO_TO_LOBBY);
    }
}
