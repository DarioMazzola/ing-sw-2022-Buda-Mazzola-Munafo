package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.noEntryTileException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HerbGranmaTest {
    private final boolean expertMode = true;

    CharacterCard herbGranma;

    @BeforeEach
    void setUp() {
        herbGranma = new HerbGranma();
    }

    @Test
    void doEffectTest_addNoEntryTile() {
        Map<String, Object> parameters = new HashMap<>();

        Island noEntryIsland = new Island(expertMode);
        int noEntryTileNumberBefore;

        parameters.put("Method", "getNoEntryTileNumber");
        try{
            herbGranma.doEffect(parameters);
        }
        catch (Exception e){
            fail();
        }
        noEntryTileNumberBefore = (Integer) parameters.get("noEntryTileNumber");

        parameters.put("Method", "addNoEntryTile");
        parameters.put("Island", noEntryIsland);

        try{

            herbGranma.doEffect(parameters);
        }
        catch (Exception e) {
            fail();
        }

        assertTrue(noEntryIsland.isNoEntryTilePresent());

        parameters.put("Method", "getNoEntryTileNumber");

        try{
            herbGranma.doEffect(parameters);
        }
        catch (Exception e) {
            fail();
        }
        assertEquals(noEntryTileNumberBefore, (Integer) parameters.get("noEntryTileNumber") + 1);

    }

    @Test
    void doEffectTest_removeNoEntryTile() {

        Map<String, Object> parameters = new HashMap<>();

        Island noEntryIsland = new Island(expertMode);

        try{

            parameters.put("Method", "addNoEntryTile");
            parameters.put("Island", noEntryIsland);
            herbGranma.doEffect(parameters);
        }
        catch (Exception e){
            fail();
        }

        int noEntryTileNumberBefore;
        parameters.put("Method", "getNoEntryTileNumber");
        try{
            herbGranma.doEffect(parameters);
        }
        catch (Exception e){
            fail();
        }
        noEntryTileNumberBefore = (Integer) parameters.get("noEntryTileNumber");

        parameters.put("Method", "removeNoEntryTile");
        parameters.put("Island", noEntryIsland);

        try{
            herbGranma.doEffect(parameters);
        }
        catch (Exception e){
            fail();
        }

        try{
            noEntryIsland.removeNoEntryTile();
        }
        catch (noEntryTileException e){
            fail();
        }
        assertFalse(noEntryIsland.isNoEntryTilePresent());

        parameters.put("Method", "getNoEntryTileNumber");
        try{
            herbGranma.doEffect(parameters);
        }
        catch (Exception e){
            fail();
        }

        assertEquals(noEntryTileNumberBefore + 1, parameters.get("noEntryTileNumber"));
    }

    @Test
    void doEffectTest_getNoEntryTileNumber(){

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("Method", "getNoEntryTileNumber");

        try{
            herbGranma.doEffect(parameters);
        }
        catch (Exception e){
            fail();
        }

        assertEquals(4, parameters.get("noEntryTileNumber"));
    }

    @Test
    void getDescription() {
        String description = "Place a No Entry Tile on Island of your choice. The first time Mother Nature ends her movement there, " +
                "put the No Entry Tile back onto this card DO NOT calculate influence on that Island, or place any Towers";

        assertEquals(description, herbGranma.getDescription());
    }

    @AfterEach
    void tearDown() {
        herbGranma = null;
    }



}