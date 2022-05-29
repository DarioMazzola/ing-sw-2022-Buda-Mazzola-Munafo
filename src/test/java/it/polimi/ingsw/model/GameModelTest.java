package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.*;
import static it.polimi.ingsw.model.CharacterCardEnum.THIEF;
import static it.polimi.ingsw.model.House.PINK;
import static it.polimi.ingsw.model.House.YELLOW;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GameModelTest class tests GameModel class
 *
 * @author Gabriele Munafo', Alessio Buda & Dario Mazzola
 * @see GameModel
 */
class GameModelTest {
    private final int numPlayers = 2;
    private GameModel gm;
    private boolean expertMode = true;
    private final static Color towerColor = Color.WHITE;
    private final static int numTowersToMove = 1;
    Island island;
    private Map<String, Object> parameters;

    @BeforeEach
    void setup(){
        try {
            gm = new GameModel(numPlayers, expertMode);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        parameters = new HashMap<>();
    }

    /**
     * Verifies that the dashboard is initialized correctly
     */
    @Test
    void initializeDashboard_Test(){
        try {
            gm.initializeDashboard(gm.getArrayPlayers()[0], Color.WHITE);
        } catch (BagException | EntranceException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(Color.WHITE, gm.getArrayPlayers()[0].getDashboard().getTowerColor());

        int counter = 0;
        for (House h : House.values()){
            counter = counter + gm.getArrayPlayers()[0].getDashboard().getHouseStudents(h);
        }
        int numRep;
        if (gm.getNumPlayers() == 2 || gm.getNumPlayers() == 4){
            numRep = 7;
        }
        else {
            numRep = 9;
        }
        assertEquals(counter, numRep);
    }

    /**
     * Verifies that the students are moved correctly from a StudentModifierInterface to a StudentModifierInterface
     */
    @Test
    void moveStudents_Test() {
        gm.getArrayPlayers()[0].setDashboard(Color.WHITE, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.BLACK, "p1");

        try {
            gm.getArrayPlayers()[0].getDashboard().addStudents(House.YELLOW,1);
        } catch (EntranceException e) {
            fail();
        }

        try {
            gm.moveStudents(gm.getArrayPlayers()[0].getDashboard(), gm.getArrayPlayers()[1].getDashboard(), House.YELLOW, 1);
        } catch (Exception e) {
            fail();
        }

        assertEquals(0, gm.getArrayPlayers()[0].getDashboard().getHouseStudents(House.YELLOW));
        assertEquals(1, gm.getArrayPlayers()[1].getDashboard().getHouseStudents(House.YELLOW));
    }

    /**
     * Verifies that the students are moved correctly from a Dashboard to a DiningHall
     */
    @Test
    void moveStudents_DashboardToDiningHall_Test() {
        gm.getArrayPlayers()[0].setDashboard(Color.WHITE, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.BLACK, "p1");

        try {
            gm.getArrayPlayers()[0].getDashboard().addStudents(House.YELLOW,3);
        } catch (EntranceException e) {
            fail();
        }

        gm.setCurrentPlayer(0);

        try {
            gm.moveStudents(gm.getArrayPlayers()[0].getDashboard(), gm.getArrayPlayers()[0].getDashboard().getDiningHall(), House.YELLOW, 3);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(0, gm.getArrayPlayers()[0].getDashboard().getHouseStudents(House.YELLOW));
        assertEquals(3, gm.getArrayPlayers()[0].getDashboard().getDiningHall().getHouseStudents(House.YELLOW));
        assertEquals(2, gm.getArrayPlayers()[0].getCoins());
        assertEquals(17, gm.getTotalCoins());
    }

    /**
     * Verifies that the NullPointerException is correctly thrown by moveStudents, when invalid parameters are passed
     */
    @Test
    void moveStudents_Exceptions(){
        Player p = new Player(numPlayers);
        Island i = new Island();
        Island b = null;
        Dashboard a = null;
        House c = null;
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, i, House.YELLOW, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(p.getDashboard(), b, House.YELLOW, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(p.getDashboard(), i, c, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, b, c, 1));
    }

    /**
     * Verifies that the students are moved correctly from a StudentModifierInterface to a StudentAdderInterface
     */
    @Test
    void MoveStudents2_Test() {
        Island to = new Island();
        gm.getArrayPlayers()[0].setDashboard(Color.WHITE, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.BLACK, "p1");

        try {
            gm.getArrayPlayers()[0].getDashboard().addStudents(House.YELLOW, 1);
        } catch (EntranceException e) {
            fail();
        }

        try {
            gm.moveStudents(gm.getArrayPlayers()[0].getDashboard(), to, House.YELLOW, 1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(0, gm.getArrayPlayers()[0].getDashboard().getHouseStudents(House.YELLOW));
        assertEquals(1, to.getHouseStudents(House.YELLOW));
    }

    /**
     * Verifies that the NullPointerException is correctly thrown by moveStudents, when invalid parameters are passed
     */
    @Test
    void moveStudents2_Exceptions(){
        Player p = new Player(numPlayers);
        Player q = new Player(numPlayers);
        Dashboard b = null;
        Dashboard a = null;
        House c = null;
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, q.getDashboard(), House.YELLOW, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(p.getDashboard(), b, House.YELLOW, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(p.getDashboard(), q.getDashboard(), c, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, b, c, 1));
    }

    /**
     * Verifies that the students are moved correctly from a Bag to a StudentModifierInterface
     */
    @Test
    void MoveStudents3_Test() {
        Bag from = new Bag();
        gm.getArrayPlayers()[0].setDashboard(Color.WHITE, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.BLACK, "p1");

        for (int i=0; i<120; i++){
            try {
                from.pull();
            } catch (BagException e) {
                fail();
            }
        }
        try {
            from.addStudents(House.YELLOW, 5);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        try {
            gm.moveStudents(from, gm.getArrayPlayers()[0].getDashboard(), 1);
        } catch (Exception e) {
            fail();
        }

        assertEquals(4, from.getHouseStudents(House.YELLOW));
        assertEquals(1, gm.getArrayPlayers()[0].getDashboard().getHouseStudents(House.YELLOW));
    }

    /**
     * Verifies that the NullPointerException is correctly thrown by moveStudents, when invalid parameters are passed
     */
    @Test
    void moveStudents3_Exceptions(){
        Bag p = new Bag();
        Player q = new Player(numPlayers);
        Dashboard b = null;
        Bag a = null;
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, q.getDashboard(), 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(p, b, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(p, q.getDashboard(), 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, b,1));
    }

    /**
     * Verifies that the students are moved correctly from a Bag to a StudentAdderInterface
     */
    @Test
    void MoveStudents4_Test() {
        Bag from = new Bag();
        Island to = new Island();

        for (int i=0; i<120; i++){
            try {
                from.pull();
            } catch (BagException e) {
                fail();
            }
        }
        try {
            from.addStudents(House.YELLOW, 5);
        } catch (BagException e) {
            fail();
        }

        try {
            gm.moveStudents(from, to, 1);
        } catch (Exception e) {
            fail();
        }

        assertEquals(4, from.getHouseStudents(House.YELLOW));
        assertEquals(1, to.getHouseStudents(House.YELLOW));
    }

    /**
     * Verifies that the NullPointerException is correctly thrown by moveStudents, when invalid parameters are passed
     */
    @Test
    void moveStudents4_Exceptions(){
        Bag p = new Bag();
        Island q = new Island();
        Island b = null;
        Bag a = null;
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, q, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(p, b, 1));
        assertThrows(NullPointerException.class, ()->gm.moveStudents(a, b,1));
    }

    /**
     * Verifies that the player's entrance is correctly refilled with the cloud's students
     */
    @Test
    void refillFromCloud_Test() {
        Cloud c = new Cloud(numPlayers);
        gm.getArrayPlayers()[0].setDashboard(Color.WHITE, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.BLACK, "p1");
        gm.setCurrentPlayer(0);

        try {
            c.addStudents(House.YELLOW, 1);
        } catch (CloudException e) {
            e.printStackTrace();
            fail();
        }
        try {
            c.addStudents(House.BLUE, 1);
        } catch (CloudException e) {
            e.printStackTrace();
            fail();
        }
        try {
            c.addStudents(House.RED, 1);
        } catch (CloudException e) {
            e.printStackTrace();
            fail();
        }
        try {
            gm.refillFromCloud(c);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(0, c.getHouseStudents(House.YELLOW));
        assertEquals(0, c.getHouseStudents(House.RED));
        assertEquals(0, c.getHouseStudents(House.BLUE));
        assertEquals(1, gm.getArrayPlayers()[0].getDashboard().getHouseStudents(House.YELLOW));
        assertEquals(1, gm.getArrayPlayers()[0].getDashboard().getHouseStudents(House.RED));
        assertEquals(1, gm.getArrayPlayers()[0].getDashboard().getHouseStudents(House.BLUE));
    }

    /**
     * Verfies that the NullPointerException is correctly thrown by refillFromCloud when an invalid cloud is passed
     */
    @Test
    void refillFromCloud_Exceptions() {
        Cloud c = null;
        assertThrows(NullPointerException.class, ()->gm.refillFromCloud(c));
    }


    /**
     * Verifies that the desired number of towers is removed from the player's dashboard and added to the specified island.
     */
    @Test
    void moveTowersTest() {
        Player player = new Player(numPlayers);
        Island island = new Island();

        player.setDashboard(towerColor, "nickname");
        int oldNumTowersPlayer = player.getDashboard().getNumTowers();
        int oldNumTowerIsland = island.getNumTowers();
        try {
            gm.moveTowers(player.getDashboard(), island, numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }
        Assertions.assertEquals(oldNumTowersPlayer - numTowersToMove, player.getDashboard().getNumTowers());
        assertEquals(oldNumTowerIsland + numTowersToMove, island.getNumTowers());
    }

    /**
     * Verifies the merging of two adjacent islands.
     * In this case the island to merge follows the island where the tower has been placed.
     *
     */
    @Test
    void moveTowersTest_merge10() {
        gm.getArrayPlayers()[0].setDashboard(towerColor, "p0");

        int oldNumIsland = gm.getNumIslands();
        Map<House, Integer> oldHouseMap0 = new HashMap<>();
        Map<House, Integer> oldHouseMap11 = new HashMap<>();
        for (House h : House.values()) {
            oldHouseMap0.put(h, gm.getIslandList().get(0).getHouseStudents(h));
            oldHouseMap11.put(h, gm.getIslandList().get(1).getHouseStudents(h));
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(1), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(0), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(oldNumIsland - 1, gm.getNumIslands());
        assertEquals(2, gm.getIslandList().get(0).getNumTowers());
        for (House h : House.values()) {
            assertEquals(oldHouseMap0.get(h) + oldHouseMap11.get(h), gm.getIslandList().get(0).getHouseStudents(h));
        }
      assertFalse(gm.getIslandList().get(0).isNoEntryTilePresent());
    }

    /**
     *
     */
    @Test
    void moveTowersTest_merge10_WithNoEntryTile() {
        gm.getArrayPlayers()[0].setDashboard(towerColor, "p0");

        int oldNumIsland = gm.getNumIslands();
        Map<House, Integer> oldHouseMap0 = new HashMap<>();
        Map<House, Integer> oldHouseMap11 = new HashMap<>();
        for (House h : House.values()) {
            oldHouseMap0.put(h, gm.getIslandList().get(0).getHouseStudents(h));
            oldHouseMap11.put(h, gm.getIslandList().get(1).getHouseStudents(h));
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(1), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }

        gm.getIslandList().get(1).addNoEntryTile();

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(0), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(oldNumIsland - 1, gm.getNumIslands());
        assertEquals(2, gm.getIslandList().get(0).getNumTowers());
        for (House h : House.values()) {
            assertEquals(oldHouseMap0.get(h) + oldHouseMap11.get(h), gm.getIslandList().get(0).getHouseStudents(h));
        }
        assertTrue(gm.getIslandList().get(0).isNoEntryTilePresent());
    }

    /**
     * Verifies the merging of two adjacent islands.
     * In this case, when a tower is placed on the last island, it merges with the first one.
     *
     */
    @Test
    void moveTowersTest_merge011() {
        gm.getArrayPlayers()[0].setDashboard(towerColor, "p0");

        int oldNumIsland = gm.getNumIslands();
        Map<House, Integer> oldHouseMap0 = new HashMap<>();
        Map<House, Integer> oldHouseMap11 = new HashMap<>();
        for (House h : House.values()) {
            oldHouseMap0.put(h, gm.getIslandList().get(0).getHouseStudents(h));
            oldHouseMap11.put(h, gm.getIslandList().get(11).getHouseStudents(h));
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(0), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(11), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(oldNumIsland - 1, gm.getNumIslands());
        assertEquals(2, gm.getIslandList().get(0).getNumTowers());
        for (House h : House.values()) {
            assertEquals(oldHouseMap0.get(h) + oldHouseMap11.get(h), gm.getIslandList().get(0).getHouseStudents(h));
        }
        assertFalse(gm.getIslandList().get(0).isNoEntryTilePresent());
    }

    /**
     * Verifies the merging of two adjacent islands.
     * In this case the island to merge precedes the island where the tower has been placed.
     *
     */
    @Test
    void moveTowersTest_merge01() {
        gm.getArrayPlayers()[0].setDashboard(towerColor, "p0");

        int oldNumIsland = gm.getNumIslands();
        Map<House, Integer> oldHouseMap0 = new HashMap<>();
        Map<House, Integer> oldHouseMap1 = new HashMap<>();
        for (House h : House.values()) {
            oldHouseMap0.put(h, gm.getIslandList().get(0).getHouseStudents(h));
            oldHouseMap1.put(h, gm.getIslandList().get(1).getHouseStudents(h));
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(0), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(1), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(oldNumIsland - 1, gm.getNumIslands());
        assertEquals(2, gm.getIslandList().get(0).getNumTowers());
        for (House h : House.values()) {
            assertEquals(oldHouseMap0.get(h) + oldHouseMap1.get(h), gm.getIslandList().get(0).getHouseStudents(h));
        }
        assertFalse(gm.getIslandList().get(0).isNoEntryTilePresent());
        assertEquals(0, gm.getMotherIsland());
    }

    /**
     * Verifies the merging of two adjacent islands.
     * In this case, when a tower is placed on the first island, it merges with the last one.
     *
     */
    @Test
    void moveTowersTest_merge110() {
        gm.getArrayPlayers()[0].setDashboard(towerColor, "p0");

        int oldNumIsland = gm.getNumIslands();
        Map<House, Integer> oldHouseMap0 = new HashMap<>();
        Map<House, Integer> oldHouseMap11 = new HashMap<>();
        for (House h : House.values()) {
            oldHouseMap0.put(h, gm.getIslandList().get(0).getHouseStudents(h));
            oldHouseMap11.put(h, gm.getIslandList().get(11).getHouseStudents(h));
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(11), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }

        try {
            gm.moveTowers(gm.getArrayPlayers()[0].getDashboard(), gm.getIslandList().get(0), numTowersToMove);
        } catch (TowerAreaException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(oldNumIsland - 1, gm.getNumIslands());
        assertEquals(2, gm.getIslandList().get(0).getNumTowers());
        for (House h : House.values()) {
            assertEquals(oldHouseMap0.get(h) + oldHouseMap11.get(h), gm.getIslandList().get(0).getHouseStudents(h));
        }
        assertFalse(gm.getIslandList().get(0).isNoEntryTilePresent());
    }

    /**
     * Verifies that a NullPointerException is thrown when the given dashboard is null.
     */
    @Test
    void moveTowersTest_NullDashboardGiven_ThrowsNullPointerException() {
        Dashboard dashboard = null;
        Island island = new Island();
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> gm.moveTowers(dashboard, island, numTowersToMove));

    }

    /**
     * Verifies that a NullPointerException is thrown when the given island is null.
     */
    @Test
    void moveTowersTest_NullIslandGiven_ThrowsNullPointerException() {
        Dashboard dashboard = new Dashboard(towerColor, 7, 8, "nickname");
        Island island = null;
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> gm.moveTowers(dashboard, island, numTowersToMove));

    }

    /**
     * Verifies that a EntranceException is thrown when there are not enough towers to remove from player's dashboard.
     */
    @Test
    void moveTowersTest_NotEnoughTowersInDashboard_ThrowsEntranceException() {
        Dashboard dashboard = new Dashboard(towerColor, 7, 8, "p0");
        Island island = new Island();

        TowerAreaException thrown = Assertions.assertThrows(TowerAreaException.class, () -> gm.moveTowers(dashboard, island, 9));
        Assertions.assertEquals("There are not enough towers in the dashboard", thrown.getMessage());
    }

    /**
     * Verifies that clouds are correctly refilled.
     */
    @Test
    void refillCloudsTest() {
        for (int i = 0; i < gm.getArrayClouds().length; i++) {
            for (House h : House.values()) {
                assertEquals(0, gm.getArrayClouds()[i].getHouseStudents(h));
            }
        }

        try {
            try {
                gm.refillClouds();
            } catch (BagException e) {
                e.printStackTrace();
                fail();
            }
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
            fail();
        }

        for (int i = 0; i < gm.getArrayClouds().length; i++) {
            int numStudentsOnCloud = 0;
            for (House h : House.values()) {
                numStudentsOnCloud += gm.getArrayClouds()[i].getHouseStudents(h);
            }
            assertEquals(3, numStudentsOnCloud);
        }
    }

    /**
     * Verifies that clouds are correctly refilled when there are three players.
     */
    @Test
    void refillCloudsTest_ThreePlayers() {
        try {
            gm = new GameModel(3, expertMode);
        } catch (EntranceException | BagException e) {
            e.printStackTrace();
            fail();
        }
        for (int i = 0; i < gm.getArrayClouds().length; i++) {
            for (House h : House.values()) {
                assertEquals(0, gm.getArrayClouds()[i].getHouseStudents(h));
            }
        }

        try {
            try {
                gm.refillClouds();
            } catch (BagException e) {
                e.printStackTrace();
                fail();
            }
        } catch (IllegalChoiceException e) {
            e.printStackTrace();
            fail();
        }

        for (int i = 0; i < gm.getArrayClouds().length; i++) {
            int numStudentsOnCloud = 0;
            for (House h : House.values()) {
                numStudentsOnCloud += gm.getArrayClouds()[i].getHouseStudents(h);
            }
            assertEquals(4, numStudentsOnCloud);
        }
    }

    /**
     * Verifies that mother nature is moved of the given valid number of moves.
     */
    @Test
    void setMotherIslandTest() {
        gm.setCurrentPlayer(0);
        try {
            gm.getArrayPlayers()[0].useCard(Card.CARD1);
        } catch (CardNotInDeckException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(0, gm.getMotherIsland());
        gm.setMotherIsland(Card.CARD1.getMoves());
        assertEquals(1, gm.getMotherIsland());
    }

    /**
     * Verifies that mother nature is moved of the given valid number of moves.
     * In this case the number of moves exceeds the number of islands and mother nature is placed again on the first island.
     */
    @Test
    void setMotherIslandTest_CircularMove() {
        gm.setCurrentPlayer(0);
        try {
            gm.getArrayPlayers()[0].useCard(Card.CARD1);
        } catch (CardNotInDeckException e) {
            e.printStackTrace();
            fail();
        }
        gm.getArrayPlayers()[0].setMaxMoves(gm.getNumIslands());
        assertEquals(0, gm.getMotherIsland());
        gm.setMotherIsland(gm.getArrayPlayers()[0].getMaxMoves());
        assertEquals(0, gm.getMotherIsland());
    }

    /**
     * Verifies that a IllegalArgumentException is thrown when the given number of moves exceeds the maximum number of moves permitted for the player.
     */
    @Test
    void setMotherIslandTest_InvalidNumberOfMoves_ThrowsIllegalArgumentException() {
        gm.setCurrentPlayer(0);
        try {
            gm.getArrayPlayers()[0].useCard(Card.CARD1);
        } catch (CardNotInDeckException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(0, gm.getMotherIsland());
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> gm.setMotherIsland(Card.CARD1.getMoves()+1));
        Assertions.assertEquals("You can't move mother nature in this way!", thrown.getMessage());
    }

    /**
     * Verifies that current player is correctly set.
     */
    @Test
    void setCurrentPlayerTest() {
        gm.setCurrentPlayer(0);
        assertEquals(gm.getArrayPlayers()[0], gm.getCurrentPlayer());
        gm.setCurrentPlayer(Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getCurrentPlayer()) + 1);
        assertEquals(gm.getArrayPlayers()[1], gm.getCurrentPlayer());
    }

    /**
     * Verifies that when the current player is set with an index greater than the number of players - 1 an IllegalArgumentException is thrown
     */
    @Test
    void setCurrentPlayerTest_IndexNotPresent_ThrowsIllegalArgumentException() {
        gm.setCurrentPlayer(0);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->  gm.setCurrentPlayer(gm.getNumPlayers()));
        Assertions.assertEquals("This player does not exit!", thrown.getMessage());
    }

    /**
     * Case tested:
     *      - expertMode on
     *      - numPlayers = 2
     *      - noEntryTile not present on the island
     *      - currentPlayer with more influence value than the otherPlayer
     *      - otherPlayer has a tower on the island
     * Expected result:
     *      - winner is currentPlayer
     */
    @Test
    void checkInfluenceTest(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);


        Player winner = null;

        expertMode = true;

        island.addStudents(YELLOW, 2);
        island.addStudents(House.RED, 1);

        island.addStudents(House.BLUE, 1);
        island.setTowerColor(gm.getArrayPlayers()[1].getDashboard().getTowerColor());
        island.addTowers(1);

        gm.getArrayPlayers()[0].getDashboard().addProf(YELLOW);
        gm.getArrayPlayers()[0].getDashboard().addProf(House.RED);
        gm.getArrayPlayers()[1].getDashboard().addProf(House.BLUE);

        try{
            winner = gm.checkInfluence(island);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(gm.getArrayPlayers()[0], winner);
    }

    @Test
    void useCharacterCardTest_playerWithNotEnoughCoins_ShouldThrowsIllegalArgumentException(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        gm.getCurrentPlayer().removeCoins(1);

        assertThrows(IllegalArgumentException.class, ()->gm.useCharacterCard(0, null));
    }

    @Test
    void useCharacterCardTest_playerWithEnoughCoins(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        CharacterCard characterCard = gm.getCharacterCardDeck()[0];

        gm.getCurrentPlayer().addCoins(5);

        int coinsBefore = gm.getCurrentPlayer().getCoins();

        prepareContext();

        try {
            gm.useCharacterCard(0, parameters);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(coinsBefore, gm.getCurrentPlayer().getCoins() + characterCard.getCost() - 1);
    }

    @Test
    void setBaseContextTest(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        gm.setBaseContext();

        ContextCharacterCard ctx = new ContextCharacterCard(new CharacterCard(0, "Base", null));

        assertEquals(ctx.getCard().getType(), gm.getContext().getCard().getType());
        assertEquals(ctx.getCard().getCost(), gm.getContext().getCard().getCost());
        assertEquals(ctx.getCard().getDescription(), gm.getContext().getCard().getDescription());
    }

    /**
     * Case tested:
     *      - players have the same number students on dashboard
     *      - the otherPlayer owns the professor of the house indicated as parameter
     * Expected result:
     *      - the professor remains with the otherPlayer
     */
    @Test
    void checkProfTest_1() {
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        House houseColor = House.YELLOW;
        //adding the same number of students to the players' dashboard
        try{
            gm.getArrayPlayers()[0].getDashboard().addStudents(houseColor, 3);
            gm.getArrayPlayers()[1].getDashboard().addStudents(houseColor, 3);
            //otherPlayer owns the professor
            gm.getArrayPlayers()[1].getDashboard().addProf(houseColor);
        }
        catch (EntranceException e){
            fail();
        }

        Map<House, Boolean> currentPlayerProfBefore = new HashMap<>();
        Map<House, Boolean> otherPlayerProfBefore = new HashMap<>();

        for(House h : House.values()) {
            currentPlayerProfBefore.put(h, gm.getArrayPlayers()[0].getDashboard().isProfPresent(h));
            otherPlayerProfBefore.put(h, gm.getArrayPlayers()[1].getDashboard().isProfPresent(h));
        }

        try{
            gm.checkProf(houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            assertEquals(gm.getArrayPlayers()[0].getDashboard().isProfPresent(h), currentPlayerProfBefore.get(h));
            assertEquals(gm.getArrayPlayers()[1].getDashboard().isProfPresent(h), otherPlayerProfBefore.get(h));
        }
    }

    @Test
    void addCoinsTest_ToManyCoinsAdded_ShouldThrowTotalCoinsException(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        assertThrows(TotalCoinsException.class, () ->gm.addCoins(12));
    }

    @Test
    void addCoinsTest_WalletAlreadyFull_AddedAnotherCoin_ShouldThrowTotalCoinsException(){island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);


        try{
            gm.addCoins(2);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        assertThrows(TotalCoinsException.class, () ->gm.addCoins(1));
    }

    @Test
    void addCoinsTest(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        int coinsBefore = gm.getTotalCoins();

        try{
            gm.addCoins(2);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        assertEquals(coinsBefore +2, gm.getTotalCoins());
    }

    @Test
    void removeCoinsTest_ToManyCoinsRemoved_ShouldThrowTotalCoinsException(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);
        assertThrows(TotalCoinsException.class, () ->gm.removeCoins(50));
    }

    @Test
    void removeCoinsTest(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        int coinsBefore = gm.getTotalCoins();

        try{
            gm.removeCoins(2);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        assertEquals(coinsBefore -2, gm.getTotalCoins());
    }

    private void prepareContext(){
        island = new Island();

        expertMode = true;

        gm.getArrayPlayers()[0].setDashboard(Color.BLACK, "p0");
        gm.getArrayPlayers()[1].setDashboard(Color.GRAY, "p1");

        gm.setCurrentPlayer(0);

        CharacterCard characterCard = gm.getCharacterCardDeck()[0];

        switch (characterCard.getType()) {
            case MONK: {

                parameters.put("method", "getStudents");

                try {
                    characterCard.doEffect(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }

                Map<House, Integer> studentsOnCard = (Map<House, Integer>) parameters.get("studentsOnCard");
                House wantedStudent = null;
                for (House h : House.values()) {
                    if (studentsOnCard.get(h) != 0) {
                        wantedStudent = h;
                        break;
                    }
                }

                parameters.put("method", "move");
                parameters.put("wantedHouse", wantedStudent);
                parameters.put("destinationIsland", island);
                parameters.put("bag", gm.getBag());
                break;
            }
            case HERALD:
                parameters.put("Island", island);
                parameters.put("ArrayPlayers", gm.getArrayPlayers());
                parameters.put("ExpertMode", gm.isExpertMode());
                parameters.put("NumPlayers", gm.getNumPlayers());
                parameters.put("CharacterCardDeck", gm.getCharacterCardDeck());
                break;
            case HERB_GRANMA:
                parameters.put("Method", "addNoEntryTile");
                parameters.put("Island", island);
                break;
            case JOLLY: {
                parameters.put("method", "getStudents");

                try {
                    characterCard.doEffect(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }

                Map<House, Integer> studentsOnCard = (Map<House, Integer>) parameters.get("studentsOnCard");
                Map<House, Integer> wantedStudents = new HashMap<>();
                House house = null;
                int i = 0;
                for (House h : House.values()) {
                    if (studentsOnCard.get(h) > 0) {
                        house = h;
                        break;
                    }
                }

                parameters.put("method", "move");

                Map<House, Integer> returnedStudents = new HashMap<>();

                try {
                    gm.getCurrentPlayer().getDashboard().addStudents(house, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }
                for (House h : House.values()) {
                    returnedStudents.put(h, 0);
                    wantedStudents.put(h, 0);
                }
                returnedStudents.put(house, 1);
                wantedStudents.put(house, 1);
                parameters.put("wantedStudents", wantedStudents);
                parameters.put("returnedStudents", returnedStudents);
                parameters.put("playerDashboard", gm.getCurrentPlayer().getDashboard());
                break;
            }
            case KNIGHT:
                parameters.put("CurrentPlayer", gm.getCurrentPlayer());
                break;
            case MAGICAL_MAILMAN:
                parameters.put("currentPlayer", gm.getCurrentPlayer());
                break;
            case MINSTREL:
                parameters.put("Dashboard", gm.getCurrentPlayer().getDashboard());
                parameters.put("DiningHall", gm.getCurrentPlayer().getDashboard().getDiningHall());
                House[] fromDashboard = {YELLOW, YELLOW, YELLOW};
                House[] fromDiningHall = {PINK, PINK, PINK};
                try {
                    gm.getCurrentPlayer().getDashboard().addStudents(YELLOW, 3);
                    gm.getCurrentPlayer().getDashboard().getDiningHall().addStudents(PINK, 3);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }

                parameters.put("fromDashboard", fromDashboard);
                parameters.put("fromDiningHall", fromDiningHall);
                break;
            case MUSHROOM_HUNTER:
                parameters.put("House", YELLOW);
                break;
            case SPOILED_PRINCESS:

                parameters.put("method", "getStudents");

                try {
                    characterCard.doEffect(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }

                Map<House, Integer> studentsOnCard = (Map<House, Integer>) parameters.get("studentsOnCard");
                House house = null;
                int i = 0;
                for (House h : House.values()) {
                    if (studentsOnCard.get(h) > 0) {
                        house = h;
                        break;
                    }
                }

                parameters.put("method", "move");

                try {
                    gm.getCurrentPlayer().getDashboard().addStudents(house, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail();
                }

                parameters.put("method", "move");
                parameters.put("wantedHouse", house);
                parameters.put("currentPlayer", gm.getCurrentPlayer());
                parameters.put("bag", gm.getBag());
                break;
            case THIEF:

                parameters.put("wantedHouse", YELLOW);
                parameters.put("players", gm.getArrayPlayers());
                parameters.put("bag", gm.getBag());

                break;
        }
    }

    /**
     * Verifies that the player returned correspond to the one with the given nickname
     */
    @Test
    void getPlayerByNicknameTest() {
        String nickname = "Foo";
        gm.getArrayPlayers()[0].setNickname(nickname);
        assertEquals(gm.getArrayPlayers()[0], gm.getPlayerByNickname(nickname));
    }

    /**
     * Tears down test.
     */
    @AfterEach
    void tearDown(){
        gm = null;
    }
}
