/** Describes the DiningHall
 * @author Dario Mazzola
 */

package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.StudentsTableException;
import it.polimi.ingsw.model.interfaces.StudentModifierInterface;

import java.util.HashMap;
import java.util.Map;
import static it.polimi.ingsw.model.House.*;

public class DiningHall  implements StudentModifierInterface {

    private final Map<House, Integer> tableOccupation;

    /**
     * Dining hall class constructor: initializes the students' number from each house to zero
     */
    public DiningHall(){
        tableOccupation = new HashMap<>();

        for(House h : values())
            tableOccupation.put(h, 0);

    }

    public int getHouseStudents(House house){
        return tableOccupation.get(house);
    }

    @Override
    public Map<House, Integer> getStudents() {
        return tableOccupation;
    }

    /**
     * @throws StudentsTableException if the house table to remove a student from is already full
     * @throws NullPointerException If the house given as a parameter is a null value
     * @throws IllegalArgumentException if the number of students given as a parameter is negative
     */
    @Override
    public void addStudents(House house, int numStudents) throws StudentsTableException, NullPointerException, IllegalArgumentException{

        if(house == null)
            throw new NullPointerException("The house given is null");

        if(numStudents < 0){
            throw new IllegalArgumentException("You cannot add a negative number of students!");
        }

        if(tableOccupation.get(house) == 10){
            throw new StudentsTableException("The table of the house where you want to add a student is already full");
        }
        tableOccupation.replace(house, tableOccupation.get(house) + numStudents);
    }

    /**
     * @throws StudentsTableException if the house table to remove a student from is already empty
     * @throws NullPointerException If the house given as a parameter is a null value
     * @throws IllegalArgumentException if the number of students given as a parameter is negative
     */
    @Override
    public void removeStudents(House house, int numStudents) throws StudentsTableException, NullPointerException, IllegalArgumentException{

        if(house == null)
            throw new NullPointerException("The house given is null");

        if(numStudents < 0){
            throw new IllegalArgumentException("You cannot remove a negative number of students!");
        }
        if (tableOccupation.get(house) == 0)
            throw new StudentsTableException("The table of the house where you want to remove a student is already empty");

        tableOccupation.replace(house, tableOccupation.get(house) - numStudents);
    }
}
