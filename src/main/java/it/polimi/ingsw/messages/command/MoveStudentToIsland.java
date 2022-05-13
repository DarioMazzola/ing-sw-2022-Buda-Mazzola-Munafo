package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Island;

import static it.polimi.ingsw.messages.MessageType.MOVE_STUDENT_TO_ISLAND;

/**
 *This message is sent from the client to the server to communicate the student the player wants to move from
 * his/hers dining hall to an island.
 *
 * @author Dario Mazzola
 */
public class MoveStudentToIsland extends CommandMessage{

    private final House house;
    private final Island island;
    /**
     * Message constructor
     *
     * @param house the house of the students to move
     * @param island the island where to add the student
     * @param nickname the nickname of the player sending the message
     */
    protected MoveStudentToIsland(House house, Island island, String nickname) {
        super(MOVE_STUDENT_TO_ISLAND, nickname);
        this.house = house;
        this.island = island;
    }

    public House getHouse() {
        return house;
    }

    public Island getIsland() {
        return island;
    }
}
