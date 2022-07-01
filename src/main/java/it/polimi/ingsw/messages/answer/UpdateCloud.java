package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedCloud;

import static it.polimi.ingsw.messages.MessageType.UPDATE_CLOUD;

/**
 * Class representing messages from the server to the client to notify the change of a Cloud.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateCloud extends AnswerMessage{

    private final ReducedCloud[] clouds;

    /**
     * Class constructor
     *
     * @param clouds the cloud to send
     */
    public UpdateCloud(ReducedCloud[] clouds) {
        super(UPDATE_CLOUD);
        this.clouds = clouds;
    }

    public ReducedCloud[] getClouds() {
        return clouds;
    }
}
