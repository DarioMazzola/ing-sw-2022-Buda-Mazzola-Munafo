package it.polimi.ingsw.client;

import it.polimi.ingsw.model.CharacterCard;
import it.polimi.ingsw.model.CharacterCardEnum;
import it.polimi.ingsw.model.House;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.*;

/**
 * Class that represents the ReducedGameModel
 * @author Gabriele Munafo'
 */
public class ReducedCharacterCard {
    private final int cost;
    private final boolean inUse;
    private final String cardName;
    private final CharacterCardEnum type;
    private final String description;
    private final Map<House, Integer> houseMap;
    private Integer noEntryTile;

    public ReducedCharacterCard(CharacterCard c){
        cost = c.getCost();
        inUse = c.isInUse();
        cardName = c.toString();
        type = c.getType();
        description = c.getDescription();

        if (c.getType() == MONK || c.getType() == JOLLY || c.getType() == SPOILED_PRINCESS) {
            houseMap = new HashMap<>();
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("method", "getStudents");

            try {
                c.doEffect(parameters);
                houseMap.putAll((HashMap<House, Integer>) parameters.get("studentsOnCard"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            houseMap = null;
        }

        if (c.getType() == HERB_GRANMA){
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Method", "getNoEntryTileNumber");
            try {
                c.doEffect(parameters);
                noEntryTile = (int) parameters.get("noEntryTileNumber");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            noEntryTile = null;
        }
    }

    public String getCardName(){
        return cardName;
    }

    public CharacterCardEnum getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public boolean isInUse(){
        return inUse;
    }

    public Integer getNoEntryTile(){
        return noEntryTile;
    }

    public Map<House, Integer> getHouseMap(){
        return houseMap;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(cardName + "\nCOST: " + cost + "\n" + description + "\n");
        if (houseMap != null) {
            for (House h : House.values()) {
                string.append(h).append(": ").append(houseMap.get(h));
                if (h != House.values()[House.values().length-1])
                    string.append(", ");
                else
                    string.append(".\n");
            }
        }
        if (noEntryTile != null) {
            string.append("NO ENTRY TILES: ").append(noEntryTile).append("\n");
        }
        return string.toString();
    }
}
