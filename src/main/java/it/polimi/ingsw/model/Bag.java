/**
 * Describes the Bag Class
 *
 * @autor Dario Mazzola
 */
package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.model.interfaces.StudentAdderInterface;


import java.util.*;

import static it.polimi.ingsw.model.House.*;

public class Bag implements StudentAdderInterface {

    private final Map<House, Integer> houseMap;
    private int totalStudentsNumber = 120;

    /**
     * Bag class constructor: creates the bag full of all the students of each class
     */
    public Bag(){
        houseMap = new HashMap<>();

        houseMap.put(YELLOW, 24);
        houseMap.put(BLUE, 24);
        houseMap.put(GREEN, 24);
        houseMap.put(RED, 24);
        houseMap.put(PINK, 24);
    }

    /**
     * Return true if there are no students in the bag
     */
    public boolean isEmpty(){
        return totalStudentsNumber == 0;
    }

    /**
     * Returns the number of students present in the bag
     */
    public int getTotalStudentsNumber() {
        return totalStudentsNumber;
    }

    /**
     * @throws BagException If the bag is already full
     * @throws NullPointerException If the house given as a parameter is a null value
     */
    @Override
    public void addStudents(House house, int numStudents) throws BagException, NullPointerException{

        if(house == null)
            throw new NullPointerException("The house given is null");
        if(houseMap.get(house) + numStudents > 24){
            throw new BagException("You cannot add students to the Bag, it is already full");
        }

        houseMap.replace(house, houseMap.get(house) + numStudents);
        totalStudentsNumber += numStudents;
    }

    /**
     * Pulls out a student from a random house from the bag
     * @return The house of the student pulled out
     * @throws BagException If the bag is already empty
     */

    public House pull() throws BagException {
        Random rand = new Random();
        int value;

        House chosen;

        ArrayList<House> houses =  new ArrayList<>();
        houses.add(YELLOW);
        houses.add(BLUE);
        houses.add(GREEN);
        houses.add(RED);
        houses.add(PINK);

        do{
            if(this.isEmpty())
                throw new BagException("You cannot pull a student from the bag, it is already empty");

            value = rand.nextInt(houses.toArray().length);

            chosen = houses.remove(value);
        } while(houseMap.get(chosen) == 0);

        totalStudentsNumber--;
        houseMap.replace(chosen, houseMap.get(chosen) - 1);

        return chosen;
    }

    /**
     * @throws NullPointerException If the house given as a parameter is a null value
     */
    @Override
    public int getHouseStudents(House house) throws NullPointerException{

        if(house == null)
            throw new NullPointerException("The house given is null");
        return houseMap.get(house);
    }

    @Override
    public Map<House, Integer> getStudents() {
        return houseMap;
    }
}

