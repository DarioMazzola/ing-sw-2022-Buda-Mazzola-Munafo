package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedPlayer;

import static it.polimi.ingsw.messages.MessageType.UPDATE_PLAYER;

/**
 * Class representing messages from the server to the client to notify the change of a Player.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdatePlayer extends AnswerMessage{

    private final ReducedPlayer player;

    /**
     * Class constructor.
     *
     * @param player the player to send
     */
    public UpdatePlayer(ReducedPlayer player){
        super(UPDATE_PLAYER);
        this.player = player;
    }

    public ReducedPlayer getPlayer() {
        return player;
    }
}
