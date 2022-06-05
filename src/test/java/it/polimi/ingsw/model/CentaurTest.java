package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static it.polimi.ingsw.model.House.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CentaurTest class tests Centaur class
 *
 * @author Gabriele Munafo'
 * @see Centaur
 */
class CentaurTest {
    private int numPlayers;
    private boolean expertMode;
    private Island island;
    private Player[] arrayPlayers;
    private Centaur c;
    private CharacterCard[] characterCardDeck;
    /**
     * Setups the centaur card c which will be used in the following tests
     */
    @BeforeEach
    public void setup() {
        c = new Centaur();

        characterCardDeck = new CharacterCard[3];

        characterCardDeck[0] = new Centaur();
        characterCardDeck[1] = new Knight();
        characterCardDeck[2] = new HerbGranma();
    }

    @AfterEach
    void tearDown() {
        c = null;
        characterCardDeck = null;
    }

    /**
     * Verifies that the influence for 2 players is calculated correctly
     */
    @Test
    void checkInfluenceTest_2Players() {
        numPlayers = 2;
        expertMode = false;
        island = new Island(expertMode);

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[0].setDashboard(Color.WHITE, "p0");
        arrayPlayers[1].setDashboard(Color.BLACK, "p1");

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[0].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);
        arrayPlayers[1].getDashboard().addProf(GREEN);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
        assertEquals(arrayPlayers[1], result);
    }

    /**
     * Verifies that the influence for 2 players is calculated correctly in case of a tie
     */
    @Test
    void checkInfluenceTest_2Players_Tie() {
        numPlayers = 2;
        expertMode = false;
        island = new Island(expertMode);

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[0].setDashboard(Color.WHITE, "p0");
        arrayPlayers[1].setDashboard(Color.BLACK, "p1");

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[0].getDashboard().getTowerColor());

        island.addStudents(YELLOW, 1);
        island.addStudents(GREEN, 1);
        arrayPlayers[1].getDashboard().addProf(GREEN);
        arrayPlayers[0].getDashboard().addProf(GREEN);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
        assertNull(result);
    }

    /**
     * Verifies that the influence for 4 players is calculated correctly
     */
    @Test
    void checkInfluenceTest_4Players() {
        numPlayers = 4;
        expertMode = false;
        island = new Island(expertMode);

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[1].setTeamLeader(true);
        arrayPlayers[0].setTeamLeader(false);
        arrayPlayers[2].setTeamLeader(true);
        arrayPlayers[3].setTeamLeader(false);

        arrayPlayers[0].setDashboard(Color.WHITE, "p0");
        arrayPlayers[1].setDashboard(Color.WHITE, "p1");
        arrayPlayers[2].setDashboard(Color.BLACK, "p2");
        arrayPlayers[3].setDashboard(Color.BLACK, "p3");

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[1].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);
        island.addStudents(PINK, 1);

        arrayPlayers[2].getDashboard().addProf(GREEN);
        arrayPlayers[3].getDashboard().addProf(PINK);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
        assertEquals(arrayPlayers[2], result);

        island.addStudents(YELLOW, 2);
        island.addStudents(BLUE, 1);

        arrayPlayers[1].getDashboard().addProf(YELLOW);
        arrayPlayers[0].getDashboard().addProf(BLUE);

        result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
        assertEquals(arrayPlayers[1], result);
    }


    /**
     * Verifies that the influence for 4 players is calculated correctly in case of a tie
     */
    @Test
    void checkInfluenceTest_4Players_Tie() {
        numPlayers = 4;
        expertMode = false;
        island = new Island(expertMode);

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[1].setTeamLeader(true);
        arrayPlayers[0].setTeamLeader(false);
        arrayPlayers[2].setTeamLeader(true);
        arrayPlayers[3].setTeamLeader(false);

        arrayPlayers[0].setDashboard(Color.WHITE, "p0");
        arrayPlayers[1].setDashboard(Color.WHITE, "p1");
        arrayPlayers[2].setDashboard(Color.BLACK, "p2");
        arrayPlayers[3].setDashboard(Color.BLACK, "p3");

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[2].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);
        island.addStudents(YELLOW, 1);
        arrayPlayers[1].getDashboard().addProf(GREEN);
        arrayPlayers[2].getDashboard().addProf(YELLOW);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
        assertNull(result);
    }

    /**
     * Verifies that if the island has a noEntryTile on it, we will receive null as a result
     */
    @Test
    void checkInfluenceTest_NoEntryTile() {
        numPlayers = 2;
        expertMode = true;
        island = new Island(expertMode);

        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new Player(numPlayers);
        }

        arrayPlayers[0].setDashboard(Color.WHITE, "p0");
        arrayPlayers[1].setDashboard(Color.BLACK, "p1");

        island.addNoEntryTile();

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[0].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);

        arrayPlayers[1].getDashboard().addProf(GREEN);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck);
        assertNull(result);
    }

    /**
     * Verifies that getDescription works properly
     */
    @Test
    void getDescriptionTest(){
        assertEquals(c.getDescription(), "When resolving a Conquering on an Island, Towers do not count towards influence.");
    }
}