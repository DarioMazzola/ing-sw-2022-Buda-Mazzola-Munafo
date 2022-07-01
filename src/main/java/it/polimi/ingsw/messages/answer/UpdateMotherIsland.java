package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.UPDATE_MOTHER_ISLAND;

/**
 * This message is sent from the server to the client to update the mother nature position following a move.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateMotherIsland extends AnswerMessage{

    private final int motherIsland;

    /**
     * Class constructor
     *
     * @param motherIsland the position of the mother
     */
    public UpdateMotherIsland(int motherIsland) {
        super(UPDATE_MOTHER_ISLAND);
        this.motherIsland = motherIsland;
    }

    public int getMotherIsland() {
        return motherIsland;
    }
}
