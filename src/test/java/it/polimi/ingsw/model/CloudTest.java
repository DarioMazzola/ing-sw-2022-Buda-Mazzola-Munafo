package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.CloudException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;
import static it.polimi.ingsw.model.House.PINK;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CloudTest class tests Cloud class
 *
 * @author Gabriele Munafo'
 * @see Cloud
 */
class CloudTest {
    private Cloud c;
    private int numPlayers = 2;

    /**
     * Setups the cloud c which will be used in the following tests
     */
    @BeforeEach
    public void setup() {
        c = new Cloud(numPlayers);
    }

    /**
     * Verifies that the student's number is correctly returned by the method getHouseStudents
     */
    @Test
    void getHouseStudentsTest(){
        Cloud d = new Cloud(3);
        try {
            assertEquals(0, d.getHouseStudents(YELLOW));
        } catch (NullPointerException e) {
            fail();
        }
    }

    /**
     * Verifies that the IllegalArgumentException is correctly thrown by getHouseStudents
     */
    @Test
    void getHouseStudentTest_NullHouseGiven_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, ()->c.getHouseStudents(null));
    }

    /**
     * Verifies: - addStudents method adds a student to the cloud
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
                totalNumberBefore = totalNumberBefore + c.getHouseStudents(h);
            }
            catch (NullPointerException e){
                fail();
            }
        }
        try{
            housesValueBefore.put(YELLOW, c.getHouseStudents(YELLOW));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(BLUE, c.getHouseStudents(BLUE));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(GREEN, c.getHouseStudents(GREEN));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(RED, c.getHouseStudents(RED));
        }
        catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(PINK, c.getHouseStudents(PINK));
        }
        catch (NullPointerException e){
            fail();
        }

        try {
            c.addStudents(house, 3);
        } catch (CloudException | IllegalArgumentException | NullPointerException e){
            fail();
        }

        for (House h : values()) {
            try{
                totalNumberAfter = totalNumberAfter + c.getHouseStudents(h);
            } catch (NullPointerException e){
                fail();
            }
        }

        assertEquals(totalNumberBefore + 3, totalNumberAfter);

        for (House h : values()) {
            if (h != house)
                try{
                    assertEquals(housesValueBefore.get(h), c.getHouseStudents(h));
                } catch (NullPointerException e){
                    fail();
                }
            else
                try{
                    assertEquals(housesValueBefore.get(h) + 3, c.getHouseStudents(h));
                } catch (NullPointerException e){
                    fail();
                }
        }
    }

    /**
     * Verifies that the CloudException is correctly thrown by addStudents
     */
    @Test
    void addStudent_FullCloudGiven_ShouldThrowCloudException(){
        try {
            c.addStudents(GREEN, 3);
        } catch (CloudException | IllegalArgumentException |NullPointerException e) {
            fail();
        }
        assertThrows(CloudException.class, ()->c.addStudents(GREEN, 10));
    }

    /**
     * Verifies that the IllegalArgumentException is correctly thrown by addStudents, when an invalid house is passed
     */
    @Test
    void addStudents_NegativeNumStudentsGiven_ShouldThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->c.addStudents(GREEN, -2));
    }

    /**
     * Verifies that the IllegalArgumentException is correctly thrown by addStudents, when an invalid house is passed
     */
    @Test
    void addStudents_NullHouseGiven_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, ()->c.addStudents(null, 1));
    }

    /**
     * Verifies: - removeStudents method removes a student from the cloud
     *           - number of students from other houses is unchanged
     *           - total number is decreased
     *           - students' number of house chosen is correctly decreased
     */
    @Test
    void removeStudentsTest() {
        int totalNumberBefore = 0;
        int totalNumberAfter = 0;
        Map<House, Integer> housesValueBefore = new HashMap<>();

        try {
            c.addStudents(GREEN, 3);
        } catch (CloudException | IllegalArgumentException | NullPointerException e) {
            fail();
        }

        assertTrue(c.isFull());

        House house = GREEN;

        for (House h : House.values()) {
            try{
                totalNumberBefore = totalNumberBefore + c.getHouseStudents(h);
            } catch (NullPointerException e){
                fail();
            }
        }
        try{
            housesValueBefore.put(YELLOW, c.getHouseStudents(YELLOW));
        } catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(BLUE, c.getHouseStudents(BLUE));
        } catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(GREEN, c.getHouseStudents(GREEN));
        } catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(RED, c.getHouseStudents(RED));
        } catch (NullPointerException e){
            fail();
        }
        try{
            housesValueBefore.put(PINK, c.getHouseStudents(PINK));
        } catch (NullPointerException e){
            fail();
        }

        try {
            c.removeStudents(house, 3);
        } catch (IllegalArgumentException | CloudException | NullPointerException e) {
            fail();
        }

        for (House h : values()) {
            try{
                totalNumberAfter = totalNumberAfter + c.getHouseStudents(h);
            } catch (NullPointerException e){
                fail();
            }
        }

        assertEquals(totalNumberBefore - 3, totalNumberAfter);

        for (House h : values()) {
            if (h != house)
                try{
                    assertEquals(housesValueBefore.get(h), c.getHouseStudents(h));
                } catch (NullPointerException e){
                    fail();
                }
            else
                try{
                    assertEquals(housesValueBefore.get(h) - 3, c.getHouseStudents(h));
                } catch (NullPointerException e){
                    fail();
                }
        }
        assertFalse(c.isFull());
    }

    /**
     * Verifies that the CloudException is correctly thrown by removeStudents, when the cloud is already empty
     */
    @Test
    void removeStudentsTest_EmptyCloudGiven_CloudException_Empty(){
        assertThrows(CloudException.class, ()->c.removeStudents(GREEN, 1));
    }

    /**
     * Verifies that the CloudException is correctly thrown by removeStudents, when the cloud doesn't contain any student of the passed house
     */
    @Test
    void removeStudentsTest_NotPresentHouseGiven_ShouldThrowCloudException_NotPresent(){
        try {
            c.addStudents(YELLOW, 1);
        } catch (CloudException | IllegalArgumentException | NullPointerException e) {
            fail();
        }
        assertThrows(CloudException.class, ()->c.removeStudents(GREEN, 1));
    }

    /**
     * Verifies that the IllegalArgumentException is correctly thrown by removeStudents
     */
    @Test
    void removeStudentsTest_NegativeNumStudentsGiven_ShouldThrowIllegalArgumentException(){
        try {
            c.addStudents(GREEN, 3);
        } catch (CloudException | IllegalArgumentException | NullPointerException e) {
            fail();
        }
        assertThrows(IllegalArgumentException.class, ()->c.removeStudents(GREEN, -1));
    }

    /**
     * Verifies that the NullPointerException is correctly thrown by removeStudents
     */
    @Test
    void removeStudentsTest_NullHouseGiven_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, ()->c.removeStudents(null, 1));
    }

    /**
     * Tears down the cloud c after every test, so that there are no interferences between the tests
     */
    @AfterEach
    public void tearDown(){c = null;}

}