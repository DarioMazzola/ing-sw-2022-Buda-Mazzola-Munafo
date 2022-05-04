package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.exceptions.noEntryTileException;
import it.polimi.ingsw.model.interfaces.StudentAdderInterface;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;

/**
 * Class that represents an Island
 * @author Gabriele Munafo'
 */
public class Island implements StudentAdderInterface {
    private final Map<House,Integer> houseMap;
    private Color towerColor;
    private int numTowers;
    private int noEntryTile;

    /**
     * Class constructor, initializes the Island
     */
    public Island () {
        houseMap = new HashMap<>();
        houseMap.put(YELLOW, 0);
        houseMap.put(BLUE, 0);
        houseMap.put(GREEN, 0);
        houseMap.put(RED, 0);
        houseMap.put(PINK, 0);
        towerColor = null;
        numTowers = 0;
        noEntryTile = 0;
    }

    /**
     * @throws IllegalArgumentException when the number of students passed is negative
     * @throws NullPointerException when the house passed is null
     */
    @Override
    public void addStudents(House house, int numStudents){
        if (house == null){
            throw new NullPointerException("house = null can't be used as a parameter");
        }
        if (numStudents < 0){
            throw new IllegalArgumentException("Number of students can't be negative");
        }
        int value = houseMap.get(house);
        value = value + numStudents;
        houseMap.replace(house, value);
    }

    /**
     * @throws NullPointerException when the house passed is null
     */
    @Override
    public int getHouseStudents (House house){
        if (house == null){
            throw new NullPointerException("house = null can't be used as a parameter");
        }
        return houseMap.get(house);
    }

    @Override
    public Map<House, Integer> getStudents() {return (houseMap);}

    /**
     * @throws IslandException when the towerColor is not initialized
     */
    public Color getColorTower() throws IslandException {
        if (towerColor == null){
            throw new IslandException("There isn't any tower on the island");
        }
        return (this.towerColor);
    }

    /**
     * @throws IllegalArgumentException when the color passed is null
     */
    public void setTowerColor(Color color){
        if (color == null){
            throw new NullPointerException("color = null can't be used as a parameter");
        }
        this.towerColor = color;}

    public int getNumTower(){
        return (this.numTowers);
    }

    /**
     * Adds a certain number of towers to the island
     * @param TowersNumber number of towers added
     */
    public void addTowers(int TowersNumber){
        this.numTowers = this.numTowers + TowersNumber;
    }

    public boolean isNoEntryTilePresent(){
        return this.noEntryTile > 0;
    }

    /**
     * Adds the noEntryTile from the island
     */
    public void addNoEntryTile(){
        this.noEntryTile += 1;
    }

    /**
     * Removes the noEntryTile from the island
     * @throws noEntryTileException when no noEntryTile are present on the island
     */
    public void removeNoEntryTile() throws noEntryTileException{
        if (this.noEntryTile < 1){throw new noEntryTileException("There aren't noEntryTiles on this island");}
        this.noEntryTile -= 1;
    }

    public int getNoEntryTile(){
        return(noEntryTile);
    }
}