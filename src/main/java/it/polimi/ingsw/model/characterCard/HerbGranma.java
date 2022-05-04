package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.model.Island;

import java.util.Map;

public class HerbGranma extends CharacterCard{

    private int noEntryTileNumber = 4;

    public HerbGranma() {
        super(2, "HerbGranma");
    }

    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception {

        super.doEffect(null);

        if(parameters.get("Method").equals("addNoEntryTile")){
            addNoEntryTile((Island)parameters.get("Island"));
        }
        else if(parameters.get("Method").equals("removeNoEntryTile")){
            removeNoEntryTile();
        }
        else if(parameters.get("Method").equals("getNoEntryTileNumber")){
            parameters.put("noEntryTileNumber", noEntryTileNumber);
        }
    }

    /**
     * Adds a NoEntryTile to the island indicated as a parameter, if not already present
     * @param island the island in which to add the NoEntryTile
     */
    private void addNoEntryTile(Island island){
        island.addNoEntryTile();
        noEntryTileNumber--;
    }

    private void removeNoEntryTile(){
        noEntryTileNumber++;
        this.setInUse(false);
    }

    @Override
    public String getDescription() {
        return "Place a No Entry Tile on Island of your choice. The first time Mother Nature ends her movement there, " +
                    "put the No Entry Tile back onto this card DO NOT calculate influence on that Island, or place any Towers";
    }
}
