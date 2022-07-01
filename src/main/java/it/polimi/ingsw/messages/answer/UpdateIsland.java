package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedIsland;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.UPDATE_ISLAND;

/**
 * Class representing messages from the server to the client to notify the change of an Island.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateIsland extends AnswerMessage{

    private final List<ReducedIsland> island;

    /**
     * Class constructor
     *
     * @param island the island to send.
     */
    public UpdateIsland(List<ReducedIsland> island) {
        super(UPDATE_ISLAND);
        this.island = island;
    }

    public List<ReducedIsland> getIsland() {
        return island;
    }
}
