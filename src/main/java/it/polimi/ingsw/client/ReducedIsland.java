package it.polimi.ingsw.client;

import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Island;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;

/**
 * Class that represents the ReducedIsland
 * @author Gabriele Munafo'
 */
public class ReducedIsland {
    private final Map<House,Integer> houseMap;
    private Color towerColor;
    private final int numTowers;
    private final int noEntryTile;

    public ReducedIsland(Island i){
        houseMap = new HashMap<>();
        houseMap.putAll(i.getStudents());
        try {
            towerColor = i.getColorTower();
        } catch (IslandException e) {
            e.printStackTrace();
            towerColor = null;
        }
        numTowers = i.getNumTowers();
        noEntryTile = i.getNoEntryTile();
    }

    public Color getColorTower() throws IslandException {
        if (towerColor == null){
            throw new IslandException("There isn't any tower on the island");
        }
        return (this.towerColor);
    }

    public Map<House, Integer> getStudents() {return (houseMap);}

    public int getNoEntryTile(){
        return(noEntryTile);
    }

    public int getNumTowers(){
        return (this.numTowers);
    }
}
