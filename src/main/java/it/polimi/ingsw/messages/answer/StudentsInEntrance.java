package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.House;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.messages.MessageType.STUDENTS_IN_ENTRANCE;

/**
 * This message is sent from the server to the client to communicate the students in current player's entrance.
 *
 * @author Dario Mazzola
 */
public class StudentsInEntrance extends AnswerMessage{

    private final Map<House, Integer> houseMap;

    /**
     * Message constructor
     *
     * @param nickname The nickname og the player who sends the message
     * @param studentsInEntrance His/her students in the entrance
     */
    public StudentsInEntrance(String nickname, Map<House, Integer> studentsInEntrance) {
        super(STUDENTS_IN_ENTRANCE, nickname);
        houseMap = new HashMap<>(studentsInEntrance);
    }

    public Map<House, Integer> getHouseMap() {
        return houseMap;
    }
}
