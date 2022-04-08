package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.model.interfaces.StudentAdderInterface;


import java.util.*;

import static it.polimi.ingsw.model.House.*;

public class Bag implements StudentAdderInterface {

    private final Map<House, Integer> houseMap;
    private int totalStudentsNumber = 120;

    public Bag(){
        houseMap = new HashMap<>();

        houseMap.put(YELLOW, 24);
        houseMap.put(BLUE, 24);
        houseMap.put(GREEN, 24);
        houseMap.put(RED, 24);
        houseMap.put(PINK, 24);
    }

    public boolean isEmpty(){
        return totalStudentsNumber == 0;
    }

    public int getTotalStudentsNumber() {
        return totalStudentsNumber;
    }

    /**
     * @throws BagException bag Exception
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

