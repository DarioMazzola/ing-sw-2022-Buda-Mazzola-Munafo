package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static it.polimi.ingsw.model.House.*;

/**
 * MushroomHunterTest class tests MushroomHunter class
 *
 * @author Gabriele Munafo'
 * @see MushroomHunter
 */
class MushroomHunterTest {
    private int numPlayers;
    private boolean expertMode;
    private Island island;
    private Player[] arrayPlayers;
    private MushroomHunter m;
    private CharacterCard[] characterCardDeck;

    /**
     * Setups the mushroomhunter card which will be used in the following tests
     */
    @BeforeEach
    public void setup() {
        m = new MushroomHunter();

        characterCardDeck = new CharacterCard[3];

        characterCardDeck[0] = new MushroomHunter();
        characterCardDeck[1] = new Knight();
        characterCardDeck[2] = new HerbGranma();
    }

    @AfterEach
    void tearDown() {
        m = null;
        characterCardDeck = null;
    }

    /**
     * Verifies that the influence for 2 players is calculated correctly
     */
    @Test
    void checkInfluenceTest_2Players(){
        numPlayers = 2;
        expertMode = false;
        island = new Island();

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[0].setDashboard(Color.WHITE);
        arrayPlayers[1].setDashboard(Color.BLACK);

        island.addStudents(GREEN, 2);
        island.addStudents(BLUE, 1);

        arrayPlayers[0].getDashboard().addProf(GREEN);
        arrayPlayers[1].getDashboard().addProf(BLUE);

        Map<String, Object> map = new HashMap<>();
        map.put("House", GREEN);
        try {
            m.doEffect(map);
        } catch (Exception e) {
            fail();
        }

        try{
            Player winner = m.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertEquals(arrayPlayers[1], winner);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }



    }

    /**
     * Verifies that the exceptions on doEffect work properly
     */
    @Test
    void doEffect_exceptions(){
        assertThrows(NullPointerException.class, ()->m.doEffect(null));
        Map<String, Object> map = new HashMap<>();
        map.put("House", null);
        assertThrows(NullPointerException.class, ()->m.doEffect(map));
    }

    /**
     * Verifies that the influence for 4 players is calculated correctly
     */
    @Test
    void checkInfluenceTest_4Players(){
        numPlayers = 4;
        expertMode = false;
        island = new Island();

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[1].setTeamLeader(true);
        arrayPlayers[0].setTeamLeader(false);
        arrayPlayers[2].setTeamLeader(true);
        arrayPlayers[3].setTeamLeader(false);

        arrayPlayers[0].setDashboard(Color.WHITE);
        arrayPlayers[1].setDashboard(Color.WHITE);
        arrayPlayers[2].setDashboard(Color.BLACK);
        arrayPlayers[3].setDashboard(Color.BLACK);

        island.addStudents(GREEN, 2);
        island.addStudents(BLUE, 1);

        arrayPlayers[0].getDashboard().addProf(GREEN);
        arrayPlayers[1].getDashboard().addProf(BLUE);

        Map<String, Object> map = new HashMap<>();
        map.put("House", GREEN);
        try {
            m.doEffect(map);
        } catch (Exception e) {
            fail();
        }

        try{
            Player winner = m.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertEquals(arrayPlayers[1], winner);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }



    }

    /**
     * Verifies that if the island has a noEntryTile on it, we will receive null as a result
     */
    @Test
    void checkInfluenceTest_NoEntryTile() {
        numPlayers = 2;
        expertMode = true;
        island = new Island();

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }
        arrayPlayers[0].setDashboard(Color.WHITE);
        arrayPlayers[1].setDashboard(Color.BLACK);

        island.addNoEntryTile();

        island.addStudents(GREEN, 1);

        arrayPlayers[1].getDashboard().addProf(GREEN);

        Map<String, Object> map = new HashMap<>();
        map.put("House", GREEN);
        try {
            m.doEffect(map);
        } catch (Exception e) {
            fail();
        }

        try{
            Player result = m.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertNull(result);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Verifies that getDescription works properly
     */
    @Test
    void getDescriptionTest(){
        assertEquals(m.getDescription(), "Choose a color of Student: during the influence calculation this, that color adds no influence");
    }
}