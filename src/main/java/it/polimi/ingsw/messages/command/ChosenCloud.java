package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_CLOUD;

/**
 * This message is sent from the client to the server to communicate the player's selected cloud.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenCloud extends CommandMessage{

    private final int cloud;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param cloud The cloud selected by the player
     */
    public ChosenCloud(String nickname, int cloud) {
        super(CHOSEN_CLOUD, nickname);
        this.cloud = cloud;
    }

    public int getCloud() {
        return cloud;
    }
}
