package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.TowerAreaException;
import it.polimi.ingsw.exceptions.noEntryTileException;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Player;
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
    /**
     * Setups the centaur card c which will be used in the following tests
     */
    @BeforeEach
    public void setup() {
        c = new Centaur();
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

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[0].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);
        arrayPlayers[1].getDashboard().addProf(GREEN);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers);
        assertEquals(arrayPlayers[1], result);
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

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[0].getDashboard().getTowerColor());

        island.addStudents(YELLOW, 1);
        island.addStudents(GREEN, 1);
        arrayPlayers[1].getDashboard().addProf(GREEN);
        arrayPlayers[0].getDashboard().addProf(GREEN);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers);
        assertNull(result);
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

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[1].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);
        island.addStudents(PINK, 1);

        arrayPlayers[2].getDashboard().addProf(GREEN);
        arrayPlayers[3].getDashboard().addProf(PINK);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers);
        assertEquals(arrayPlayers[2], result);

        island.addStudents(YELLOW, 2);
        island.addStudents(BLUE, 1);

        arrayPlayers[1].getDashboard().addProf(YELLOW);
        arrayPlayers[0].getDashboard().addProf(BLUE);

        result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers);
        assertEquals(arrayPlayers[1], result);
    }


    /**
     * Verifies that the influence for 4 players is calculated correctly in case of a tie
     */
    @Test
    void checkInfluenceTest_4Players_Tie() {
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

        island.addTowers(2);
        island.setTowerColor(arrayPlayers[2].getDashboard().getTowerColor());

        island.addStudents(GREEN, 1);
        island.addStudents(YELLOW, 1);
        arrayPlayers[1].getDashboard().addProf(GREEN);
        arrayPlayers[2].getDashboard().addProf(YELLOW);

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers);
        assertNull(result);
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

        Player result = c.checkInfluence(island, expertMode, numPlayers, arrayPlayers);
        assertNull(result);
    }
}