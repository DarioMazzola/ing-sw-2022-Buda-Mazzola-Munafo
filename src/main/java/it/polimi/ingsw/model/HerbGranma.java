package it.polimi.ingsw.model;

import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.HERB_GRANMA;

/**
 * HerbGranma class represents herb granma character card
 *
 * @author Dario Mazzola
 */
public class HerbGranma extends CharacterCard{

    private int noEntryTileNumber = 4;

    /**
     * Class constructor, initializes card with name and initial cost.
     */
    public HerbGranma() {
        super(2, "HerbGranma", HERB_GRANMA);
    }

    /**
     * Receives parameters, saves them and calls the correct method to perform the wanted operation.
     *
     * @param parameters A map that contains the objects that need to the characterCards and the objects that must be returned
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception {

        if(parameters.get("Method").equals("addNoEntryTile")){
            addNoEntryTile((Island)parameters.get("Island"));
            super.doEffect(null);
        }
        else if(parameters.get("Method").equals("removeNoEntryTile")){
            removeNoEntryTile();
        }
        else if(parameters.get("Method").equals("getNoEntryTileNumber")){
            parameters.put("noEntryTileNumber", noEntryTileNumber);
        }
    }

    /**
     * Adds a NoEntryTile to the island indicated as a parameter
     * @param island the island in which to add the NoEntryTile
     */
    private void addNoEntryTile(Island island){
        island.addNoEntryTile();
        noEntryTileNumber--;
    }

    /**
     * Removes a NoEntryTile from the game board and put the no entry tile  back on this card
     */
    private void removeNoEntryTile(){
        noEntryTileNumber++;
        this.setInUse(false);
    }

    @Override
    public String getDescription() {
        return "Place a No Entry Tile on Island of your choice. The first time Mother Nature ends her movement there, " +
                    "put the No Entry Tile back onto this card DO NOT calculate influence on that Island, or place any Towers";
    }

    @Override
    public CharacterCardEnum getType() {
        return HERB_GRANMA;
    }
}
