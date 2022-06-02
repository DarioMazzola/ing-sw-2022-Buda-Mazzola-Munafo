package it.polimi.ingsw.client;

import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Island;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the ReducedIsland
 * @author Gabriele Munafo'
 */
public class ReducedIsland {
    private final Map<House,Integer> houseMap;
    private Color towerColor;
    private final int numTowers;
    private final Integer noEntryTile;

    public ReducedIsland(Island i){
        houseMap = new HashMap<>();
        houseMap.putAll(i.getStudents());
        try {
            towerColor = i.getColorTower();
        } catch (IslandException e) {
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

    public Integer getNoEntryTile(){
        return(noEntryTile);
    }

    public int getNumTowers(){
        return (this.numTowers);
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("TOWER COLOR: ").append(towerColor == null ? "-" : towerColor).append(" (").append(numTowers).append(" towers)");
        if (getNoEntryTile() != null) {
            string.append("\nNO ENTRY TILE: ").append(getNoEntryTile() > 0 ? "Yes" : "No");
        }
        string.append("\nSTUDENTS:");
        for (House h : House.values()) {
            string.append(" ").append(h.getColouredHouse()).append(": ").append(getStudents().get(h));
            if (!h.equals(House.values()[House.values().length-1]))
                string.append(",");
        }
        return string.toString();
    }
}
