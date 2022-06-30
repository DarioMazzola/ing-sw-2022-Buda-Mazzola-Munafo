package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.House;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.messages.MessageType.STUDENTS_IN_ENTRANCE;

/**
 * This message is sent from the server to the client to communicate the students in current player's entrance.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class StudentsInEntrance extends AnswerMessage{

    private final Map<House, Integer> houseMap;

    /**
     * Message constructor
     *
     * @param studentsInEntrance His/her students in the entrance
     */
    public StudentsInEntrance(Map<House, Integer> studentsInEntrance) {
        super(STUDENTS_IN_ENTRANCE);
        houseMap = new HashMap<>(studentsInEntrance);
    }

    public Map<House, Integer> getHouseMap() {
        return houseMap;
    }
}
