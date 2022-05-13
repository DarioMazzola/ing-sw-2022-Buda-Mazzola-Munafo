package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.House;

import static it.polimi.ingsw.messages.MessageType.MOVE_STUDENT_TO_DINING_HALL;

/**
 * This message is sent from the client to the server to communicate the student to move from the player's
 * entrance to his/hers dining hall.
 *
 * @author Dario Mazzola
 */
public class MoveStudentToDiningHall extends CommandMessage{

    private final House house;
    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param house the house of the students to move
     */
    protected MoveStudentToDiningHall(String nickname, House house) {
        super(MOVE_STUDENT_TO_DINING_HALL, nickname);
        this.house = house;
    }

    public House getHouse() {
        return house;
    }
}
