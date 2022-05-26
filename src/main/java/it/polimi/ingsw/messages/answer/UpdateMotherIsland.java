package it.polimi.ingsw.messages.answer;

import static it.polimi.ingsw.messages.MessageType.UPDATE_MOTHER_ISLAND;

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
