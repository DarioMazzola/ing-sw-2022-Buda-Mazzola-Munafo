package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CentaurTest class tests Centaur class
 *
 * @author Gabriele Munafo'
 * @see Knight
 */
class KnightTest {

    private int numPlayers;
    private boolean expertMode;
    private Island island;
    private Player[] arrayPlayers;
    private Knight k;
    private CharacterCard[] characterCardDeck;

    /**
     * Setups the knight card k which will be used in the following tests
     */
    @BeforeEach
    public void setup() throws BagException {
        k = new Knight();
        characterCardDeck = new CharacterCard[3];

        characterCardDeck[0] = new Monk(new Bag());
        characterCardDeck[1] = new Knight();
        characterCardDeck[2] = new HerbGranma();
    }

    @AfterEach
    void tearDown() {
        k = null;
        characterCardDeck = null;
    }

    /**
     * Verifies that the influence for 2 players is calculated correctly
     */
    @Test
    void checkInfluenceTest_2Players() {
        numPlayers = 2;
        expertMode = false;
        island = new Island();

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[0].setDashboard(Color.WHITE);
        arrayPlayers[1].setDashboard(Color.BLACK);

        island.addStudents(GREEN, 1);
        arrayPlayers[1].getDashboard().addProf(GREEN);


        Map<String, Object> map = new HashMap<>();
        map.put("CurrentPlayer", arrayPlayers[0]);
        try {
            k.doEffect(map);
        } catch (Exception e) {
            fail();
        }

        Player result = null;
        try{
            result = k.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }
        assertEquals(arrayPlayers[0], result);
    }

    /**
     * Verifies that the influence for 2 players is calculated correctly in case of a tie
     */
    @Test
    void checkInfluenceTest_2Players_Tie() {
        numPlayers = 2;
        expertMode = false;
        island = new Island();

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[0].setDashboard(Color.WHITE);
        arrayPlayers[1].setDashboard(Color.BLACK);

        island.addStudents(GREEN, 1);
        arrayPlayers[0].getDashboard().addProf(GREEN);

        Map<String, Object> map = new HashMap<>();
        map.put("CurrentPlayer", arrayPlayers[1]);
        try {
            k.doEffect(map);
        } catch (Exception e) {
            fail();
        }

        Player result;
        try{
           result = k.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertEquals(arrayPlayers[1], result);

            island.addStudents(GREEN, 1);
            result = k.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertNull(result);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Verifies that the influence for 4 players is calculated correctly
     */
    @Test
    void checkInfluenceTest_4Players() {
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

        island.addStudents(GREEN, 1);
        arrayPlayers[2].getDashboard().addProf(GREEN);

        Map<String, Object> map = new HashMap<>();
        map.put("CurrentPlayer", arrayPlayers[0]);
        try {
            k.doEffect(map);
        } catch (Exception e) {
            fail();
        }

        Player result;
        try{
            result = k.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertEquals(arrayPlayers[1], result);

            island.addStudents(BLUE, 4);
            arrayPlayers[3].getDashboard().addProf(BLUE);
            island.addStudents(YELLOW, 1);
            arrayPlayers[1].getDashboard().addProf(YELLOW);

            result = k.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertEquals(arrayPlayers[2], result);
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

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[0].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);

        arrayPlayers[1].getDashboard().addProf(GREEN);

        try{
            Player result = k.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
            assertNull(result);
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
    void doEffect_Exception(){
        assertThrows(NullPointerException.class, ()->k.doEffect(null));
        Map<String, Object> map = new HashMap<>();
        map.put("CurrentPlayer", null);
        assertThrows(NullPointerException.class, ()->k.doEffect(map));
    }

    /**
     * Verifies that getDescription works properly
     */
    @Test
    void getDescriptionTest(){
        assertEquals(k.getDescription(), "During the influence calculation this turn, you count as having 2 more influence");
    }

}