package it.polimi.ingsw.client;

import it.polimi.ingsw.model.DiningHall;
import it.polimi.ingsw.model.House;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the ReducedDiningHall
 * @author Gabriele Munafo'
 */
public class ReducedDiningHall {
    private final Map<House, Integer> tableOccupation;

    public ReducedDiningHall(DiningHall d){
        tableOccupation = new HashMap<>();
        tableOccupation.putAll(d.getStudents());
    }

    public int getHouseStudents(House house){
        return tableOccupation.get(house);
    }

    public Map<House, Integer> getStudents() {
        return tableOccupation;
    }
}
