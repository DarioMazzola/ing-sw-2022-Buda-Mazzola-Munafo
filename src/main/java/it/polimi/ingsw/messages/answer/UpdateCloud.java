package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedCloud;

import static it.polimi.ingsw.messages.MessageType.UPDATE_CLOUD;

/**
 * Class representing messages from the server to the client to notify the change of a Cloud.
 *
 * @author Dario Mazzola
 */
public class UpdateCloud extends AnswerMessage{

    private final ReducedCloud cloud;

    /**
     * Class constructor.
     *
     * @param cloud the cloud to send
     */
    public UpdateCloud(ReducedCloud cloud) {
        super(UPDATE_CLOUD);
        this.cloud = cloud;
    }

    public ReducedCloud getCloud() {
        return cloud;
    }
}
