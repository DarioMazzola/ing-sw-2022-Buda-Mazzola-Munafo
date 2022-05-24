package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.Cloud;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.SELECT_CLOUD;

/**
 * This message is sent from the server to the client to communicate the available clouds and the students on
 * them.
 *
 * @author Dario Mazzola
 */
public class SelectCloud extends AnswerMessage{

    private final List<Integer> cloudList;

    /**
     * Message constructor
     *
     * @param cloudList The list of the cloud available for this round
     */
    public SelectCloud(List<Integer> cloudList) {
        super(SELECT_CLOUD);
        this.cloudList = cloudList;
    }

    public List<Integer> getCloudList() {
        return cloudList;
    }
}
