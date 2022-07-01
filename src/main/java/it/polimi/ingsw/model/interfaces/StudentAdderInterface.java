package it.polimi.ingsw.model.interfaces;

import it.polimi.ingsw.model.House;

import java.util.Map;

/**
 * Declares methods used to manage the students
 *
 * @author Gabriele Munafo'
 */
public interface StudentAdderInterface {
    /**
     * Adds the number of students entered as a parameter
     * to the total number of students for that house
     *
     * @param house       The house that will have the number of students increased
     * @param numStudents The students' number added to the house
     */
    void addStudents(House house, int numStudents) throws Exception;

    /**
     * @param house The house for which to calculate the students' number
     * @return The students' number of that house
     */
    int getHouseStudents(House house) throws Exception;

    /**
     * @return A map containing the students' number divided by house
     */
    Map<House, Integer> getStudents() throws Exception;
}
