package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.exceptions.noEntryTileException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;
import static it.polimi.ingsw.model.House.PINK;
import static org.junit.jupiter.api.Assertions.*;
/**
 * IslandTest class tests Island class
 *
 * @author Gabriele Munafo'
 * @see Island
 */

class IslandTest {
    private Island isl;

    /**
     * Setups the island isl which will be used in the following tests
     */
    @BeforeEach
    public void setup() {
        isl = new Island();
    }

    /**
     * Verifies: - addStudents method adds students to the island
     *           - number of students from other houses is unchanged
     *           - total number is increased
     *           - students' number of house chosen is correctly increased
     */
    @Test
    void addStudentTest() {
        int totalNumberBefore = 0;
        int totalNumberAfter = 0;
        Map<House, Integer> housesValueBefore = new HashMap<>();
        House house = GREEN;

        for (House h : values()) {
            try{
                totalNumberBefore = totalNumberBefore + isl.getHouseStudents(h);
            }
            catch (NullPointerException e){
                fail();
            }
        }

        try{
            housesValueBefore.put(YELLOW, isl.getHouseStudents(YELLOW));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(BLUE, isl.getHouseStudents(BLUE));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(GREEN, isl.getHouseStudents(GREEN));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(RED, isl.getHouseStudents(RED));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(PINK, isl.getHouseStudents(PINK));
        }
        catch (NullPointerException e){
            fail();
        }

        try {
            isl.addStudents(house, 3);
        } catch (IllegalArgumentException | NullPointerException e){
            fail();
        }

        for (House h : values()) {
            try{
                totalNumberAfter = totalNumberAfter + isl.getHouseStudents(h);
            } catch (NullPointerException e){
                fail();
            }
        }

        assertEquals(totalNumberBefore + 3, totalNumberAfter);

        for (House h : values()) {
            if (h != house)
                try{
                    assertEquals(housesValueBefore.get(h), isl.getHouseStudents(h));
                } catch (NullPointerException e){
                    fail();
                }
            else
                try{
                    assertEquals(housesValueBefore.get(h) + 3, isl.getHouseStudents(h));
                } catch (NullPointerException e){
                    fail();
                }
        }
    }

    /**
     * Verifies that the NullPointerException is correctly thrown by addStudents, when an invalid house is passed
     */
    @Test
    void addStudents_NullHouseGiven_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, ()->isl.addStudents(null, 1));
    }

    /**
     * Verifies that the IllegalArgumentException is correctly thrown by addStudents, when an invalid house is passed
     */
    @Test
    void addStudents_NegativeNumStudentsGiven_ShouldThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->isl.addStudents(GREEN, -2));
    }

    /**
     * Verifies that the student's number is correctly returned by the method getHouseStudents
     */
    @Test
    void getHouseStudentsTest(){
        try {
            assertEquals(0, isl.getHouseStudents(YELLOW));
        } catch (NullPointerException e) {
            fail();
        }
    }

    /**
     * Verifies that the student's number is correctly returned by the method getHouseStudents
     */
    @Test
    void getHouseStudentsTest_NullHousePassed_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, ()->isl.getHouseStudents(null));
    }

    /**
     * Verifies that the IslandException is correctly thrown by getColorTower
     */
    @Test
    void getColorTower_ShouldThrowIslandException(){
        assertThrows(IslandException.class, ()->isl.getColorTower());
    }

    /**
     * Verifies that the NullPointerException is correctly thrown by setTowerColor
     */
    @Test
    void setTowerColor_NullHouseGiven_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, ()->isl.setTowerColor(null));
    }

    /**
     * Verifies: - addTowers method adds towers to the island
     *           - total number of towers is increased
     */
    @Test
    void addTowerTest(){
        int numTowers = 10;
        isl.addTowers(numTowers);
        assertEquals(isl.getNumTower(), numTowers);
    }

    /**
     * Verifies that the noEntryTileException is correctly thrown by addNoEntryTile
     */
    @Test
    void addNoEntryTile_ShouldThrowNoEntryTileException(){
        try {
            isl.addNoEntryTile();
        } catch (noEntryTileException e) {
            fail();
        }
        assertThrows(noEntryTileException.class, ()->isl.addNoEntryTile());
    }

    /**
     * Verifies that the noEntryTileException is correctly thrown by addNoEntryTile
     */
    @Test
    void removeNoEntryTile_ShouldThrowNoEntryTileException(){
        assertThrows(noEntryTileException.class, ()->isl.removeNoEntryTile());
    }

    /**
     * Tears down the island isl after every test, so that there are no interferences between the tests
     */
    @AfterEach
    public void tearDown(){isl = null;}
}