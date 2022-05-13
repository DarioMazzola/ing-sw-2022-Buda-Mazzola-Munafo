package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.Cloud;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.AVAILABLE_CLOUDS;

/**
 * This message is sent from the server to the client to communicate the available clouds and the students on
 * them.
 */
public class AvailableClouds extends AnswerMessage{

    private final List<Cloud> cloudList;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player the message is sent to
     * @param cloudList The list of the cloud available for this round
     */
    protected AvailableClouds(String nickname, List<Cloud> cloudList) {
        super(AVAILABLE_CLOUDS, nickname);
        this.cloudList = cloudList;
    }

    public List<Cloud> getCloudList() {
        return cloudList;
    }
}
