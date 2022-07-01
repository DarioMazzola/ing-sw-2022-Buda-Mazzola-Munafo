package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.House;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a reduced version of a cloud
 *
 * @author Gabriele Munafo'
 */
public class ReducedCloud {
    private final Map<House, Integer> houseMap;
    private final int numMaxStud;
    private final boolean full;

    public ReducedCloud(Cloud c) {
        houseMap = new HashMap<>();
        houseMap.putAll(c.getStudents());
        numMaxStud = c.getNumMaxStud();
        full = c.isFull();
    }

    public Map<House, Integer> getStudents() {
        return (houseMap);
    }

    public int getHouseStudents(House house) {
        if (house == null) {
            throw new NullPointerException("house = null can't be used as a parameter");
        }
        return (houseMap.get(house));
    }

    public boolean isFull() {
        return (full);
    }

    public int getNumMaxStud() {
        return numMaxStud;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Students on the cloud: ");
        for (House h : House.values()) {
            string.append("\n").append(h.getColouredHouse()).append(": ").append(getHouseStudents(h));
        }
        return string.toString();
    }
}
