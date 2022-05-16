package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.Cloud;

import static it.polimi.ingsw.messages.MessageType.SELECT_CLOUD;

/**
 * This message is sent from the client to the server to communicate the player's selected cloud.
 *
 * @author Dario Mazzola
 */
public class SelectCloud extends CommandMessage{

    private final int cloud;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param cloud The cloud selected by the player
     */
    public SelectCloud(String nickname, int cloud) {
        super(SELECT_CLOUD, nickname);
        this.cloud = cloud;
    }

    public int getCloud() {
        return cloud;
    }
}
