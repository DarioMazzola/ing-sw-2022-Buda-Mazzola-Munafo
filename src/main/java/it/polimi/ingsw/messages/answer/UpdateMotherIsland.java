package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.UPDATE_MOTHER_ISLAND;

/**
 * This message is sent from the server to the client to update the mother nature position following a move.
 *
 * @author Dario Mazzola
 */
public class UpdateMotherIsland extends AnswerMessage{

    private final int motherIsland;

    public UpdateMotherIsland(int motherIsland) {
        super(UPDATE_MOTHER_ISLAND);
        this.motherIsland = motherIsland;
    }

    public int getMotherIsland() {
        return motherIsland;
    }
}
