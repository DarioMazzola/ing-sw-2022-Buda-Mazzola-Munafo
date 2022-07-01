package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.SELECT_CLOUD;

/**
 * This message is sent from the server to the client to communicate the available clouds and the students on
 * them.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class SelectCloud extends AnswerMessage{

    /**
     * Message constructor
     */
    public SelectCloud() {
        super(SELECT_CLOUD);
    }

}
