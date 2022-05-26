package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedIsland;

import static it.polimi.ingsw.messages.MessageType.UPDATE_ISLAND;

/**
 * Class representing messages from the server to the client to notify the change of an Island.
 *
 * @author Dario Mazzola
 */
public class UpdateIsland extends AnswerMessage{

    private final ReducedIsland island;

    /**
     * Class constructor.
     *
     * @param island the island to send.
     */
    public UpdateIsland(ReducedIsland island) {
        super(UPDATE_ISLAND);
        this.island = island;
    }

    public ReducedIsland getIsland() {
        return island;
    }
}
