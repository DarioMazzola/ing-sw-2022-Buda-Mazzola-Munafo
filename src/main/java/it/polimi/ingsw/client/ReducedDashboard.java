package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Dashboard;

import it.polimi.ingsw.model.House;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the ReducedDashboard
 * @author Gabriele Munafo'
 */
public class ReducedDashboard {
    private final Map<House, Integer> houseMap;
    private final Map<House, Boolean> profMap;
    private final Color towerColor;
    private final int numMaxStudents;
    private final int numStudentsIn;
    private final int numMaxTowers;
    private final int numTowersIn;
    private ReducedDiningHall diningHall;

    public ReducedDashboard(Dashboard d){
        houseMap = new HashMap<>();
        houseMap.putAll(d.getStudents());

        profMap = new HashMap<>();
        profMap.putAll(d.getProfMap());

        towerColor = d.getTowerColor();

        numMaxStudents = d.getNumMaxStudents();

        numStudentsIn = d.getNumStudentsIn();

        numMaxTowers = d.getNumMaxTowers();

        numTowersIn = d.getNumTowers();

        diningHall = new ReducedDiningHall(d.getDiningHall());
    }

    public Map<House, Integer> getStudents(){
        return houseMap;
    }

    public int getNumStudentsIn() {
        return numStudentsIn;
    }

    public int getHouseStudents(House house) throws NullPointerException{

        if(house == null)
            throw new NullPointerException("The house given is null");
        return houseMap.get(house);
    }

    public int getNumTowersIn() {
        return numTowersIn;
    }

    public int getNumTowers() {
        return numTowersIn;
    }

    public Color getTowerColor() {
        return towerColor;
    }

    public boolean isProfPresent(House house) throws NullPointerException{
        if(house == null)
            throw new NullPointerException("The house given is null");
        return profMap.get(house);
    }

    public ReducedDiningHall getDiningHall(){
        return diningHall;
    }

    public int getNumMaxStudents() {
        return numMaxStudents;
    }

    public Map<House, Boolean> getProfMap(){
        return profMap;
    }

    public int getNumMaxTowers(){
        return numMaxTowers;
    }

    public void setDiningHall(ReducedDiningHall d){
        diningHall = d;
    }
}
