package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.StudentsTableException;
import it.polimi.ingsw.exceptions.TowerAreaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;
import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {

    Dashboard dashboard;
    House houseDefault = YELLOW;

    @BeforeEach
    public void setup(){
        dashboard = new Dashboard(Color.BLACK, 7, 5, "nickname");
    }

    /**
     * addStudent method adds a student to the Dashboard Entrance
     * Verifies: - number of students from other houses is unchanged
     *           - total number is increased
     *           - students' number of house chosen is correctly increased
     */
    @Test
    void addStudentTest(){

        Map<House, Integer> housesValueBefore= new HashMap<>();

        int totalNumberBefore = dashboard.getNumStudentsIn();

        housesValueBefore.put(YELLOW, dashboard.getHouseStudents(YELLOW));
        housesValueBefore.put(BLUE, dashboard.getHouseStudents(BLUE));
        housesValueBefore.put(GREEN, dashboard.getHouseStudents(GREEN));
        housesValueBefore.put(RED, dashboard.getHouseStudents(RED));
        housesValueBefore.put(PINK, dashboard.getHouseStudents(PINK));

        try{
            dashboard.addStudents(houseDefault, 7, false);
        }
        catch (EntranceException e){
            fail();
        }

        assertEquals(totalNumberBefore +7, dashboard.getNumStudentsIn());

        for(House h: values()){
            if(h.equals(houseDefault))
                assertEquals(housesValueBefore.get(h) +7, dashboard.getHouseStudents(h));
            else
                assertEquals(housesValueBefore.get(h), dashboard.getHouseStudents(h));
        }

    }

    @Test
    void addStudentsTest_FullEntranceGiven_ShouldThrowEntranceException(){
        fillEntrance();

        assertThrows(EntranceException.class, ()->dashboard.addStudents(House.RED, 1, false));
    }

    @Test
    void addStudentsTest_TooManyStudentsToAdd_ShouldThrowEntranceException(){

        assertThrows(EntranceException.class, ()->dashboard.addStudents(RED, 8, false));
    }

    @Test
    void addStudentsTest_NullHouseGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->dashboard.addStudents(null, 1, false));
    }

    /**
     * removeStudent method removes a student to the Dashboard Entrance
     * Verifies: - number of students from other houses is unchanged
     *           - total number is decreased
     *           - students' number of house chosen is correctly decreased
     */
    @Test
    void removeStudentTest(){

        Map<House, Integer> housesValueBefore= new HashMap<>();

        try{
            dashboard.addStudents(houseDefault, 2, false);
        }
        catch (EntranceException e){
            fail();
        }

        int totalNumberBefore = dashboard.getNumStudentsIn();

        housesValueBefore.put(YELLOW, dashboard.getHouseStudents(YELLOW));
        housesValueBefore.put(BLUE, dashboard.getHouseStudents(BLUE));
        housesValueBefore.put(GREEN, dashboard.getHouseStudents(GREEN));
        housesValueBefore.put(RED, dashboard.getHouseStudents(RED));
        housesValueBefore.put(PINK, dashboard.getHouseStudents(PINK));

        try{
            dashboard.removeStudents(houseDefault, 1, false);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        assertEquals(totalNumberBefore -1, dashboard.getNumStudentsIn());

        for(House h: House.values()){
            if(h.equals(houseDefault))
                assertEquals(housesValueBefore.get(h) -1, dashboard.getHouseStudents(h));
            else
                assertEquals(housesValueBefore.get(h), dashboard.getHouseStudents(h));
        }

    }

    @Test
    void removeStudentsTest_TooManyStudentsToRemoveGiven_ShouldThrowIllegalChoice(){

        fillEntrance();

        assertThrows(IllegalChoiceException.class, ()->dashboard.removeStudents(houseDefault, 8, false));
    }

    @Test
    void removeStudentsTest_EmptyEntranceGiven_ShouldThrowIllegalChoiceException(){

        assertThrows(IllegalChoiceException.class, ()->dashboard.removeStudents(houseDefault, 1, false));
    }

    @Test
    void removeStudentsTest_NullHouseGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->dashboard.removeStudents(null, 1, false));
    }

    @Test
    void getHouseStudents_NullHouseGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->dashboard.getHouseStudents(null));
    }

    @Test
    void isProfPresentTest_NullColorGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->dashboard.isProfPresent(null));
    }

    @Test
    void addProfTest_NullColorGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->dashboard.addProf(null));

    }

    /**
     * addProf method adds a prof to the Professors' table
     * Verifies: - state of the other professors is unchanged
     *           - professor of house chosen is correctly set
     */
    @Test
    void addProfTest(){

        Map<House, Boolean> profValueBefore= new HashMap<>();

        profValueBefore.put(YELLOW, dashboard.isProfPresent(YELLOW));
        profValueBefore.put(BLUE, dashboard.isProfPresent(BLUE));
        profValueBefore.put(GREEN, dashboard.isProfPresent(GREEN));
        profValueBefore.put(RED, dashboard.isProfPresent(RED));
        profValueBefore.put(PINK, dashboard.isProfPresent(PINK));

        dashboard.addProf(houseDefault);

        for(House h: House.values()){
            if(h.equals(houseDefault))
                assertEquals(!profValueBefore.get(h), dashboard.isProfPresent(h));
            else
                assertEquals(profValueBefore.get(h), dashboard.isProfPresent(h));
        }

    }

    /**
     * removeProf method removes a prof to the Professors' table
     * Verifies: - state of the other professors is unchanged
     *           - professor of house chosen is correctly set
     */
    @Test
    void removeProfTest(){

        Map<House, Boolean> profValueBefore= new HashMap<>();

        dashboard.addProf(houseDefault);
        dashboard.addProf(BLUE);
        dashboard.addProf(PINK);

        profValueBefore.put(YELLOW, dashboard.isProfPresent(YELLOW));
        profValueBefore.put(BLUE, dashboard.isProfPresent(BLUE));
        profValueBefore.put(GREEN, dashboard.isProfPresent(GREEN));
        profValueBefore.put(RED, dashboard.isProfPresent(RED));
        profValueBefore.put(PINK, dashboard.isProfPresent(PINK));

        try{
            dashboard.removeProf(houseDefault);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h: House.values()){
            if(h.equals(houseDefault))
                assertEquals(!profValueBefore.get(h), dashboard.isProfPresent(h));
            else
                assertEquals(profValueBefore.get(h), dashboard.isProfPresent(h));
        }

    }

    @Test
    void removeProfTest_ProfNotPresent_ShouldThrowIllegalChoiceExceptionException(){

        assertThrows(IllegalChoiceException.class, ()-> dashboard.removeProf(House.YELLOW));
    }

    @Test
    void removeProfTest_NullColorGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->dashboard.removeProf(null));
    }

    @Test
    void getNumTowersTest(){

        int towersNumber = dashboard.getNumTowers();

        assertEquals(5, towersNumber);
    }

    @Test
    void getTowerColorTest(){
        assertEquals(Color.BLACK, dashboard.getTowerColor());
    }

    @Test
    void addTowerTest(){

        try{
            dashboard.removeTower();
        }
        catch (Exception e){
            fail();
        }

        try {
            dashboard.addTower();
        }
        catch (TowerAreaException e){
            fail();
        }

        assertEquals(5, dashboard.getNumTowers());
    }

    @Test
    void addTowerTest_FullTowerAreaGiven_ShouldThrowTowerAreaException(){

        assertThrows(TowerAreaException.class,()->dashboard.addTower());
    }

    @Test
    void removeTowerTest(){

        try{
            dashboard.removeTower();
        }catch (TowerAreaException e){
            fail();
        }

        assertEquals(4, dashboard.getNumTowers());
    }

    @Test
    void removeTowerTest_EmptyTowerAreaGiven_ShouldThrowTowerAreaException(){

        try{
            for(int i=0; i<5; i++)
                dashboard.removeTower();
        }
        catch (Exception e){
            fail();
        }

        assertThrows(TowerAreaException.class, ()->dashboard.removeTower());
    }

    @Test
    void addStudentToDiningHall(){

        try {
            dashboard.getDiningHall().addStudents(YELLOW, 1, false);
        }
        catch (StudentsTableException e){
            fail();
        }

        assertEquals(1, dashboard.getDiningHall().getHouseStudents(YELLOW));
    }

    //Utility method: fill completely the Entrance of the Dashboard
    private void fillEntrance(){

        for(int i=0; i<7; i++){
            try{
                dashboard.addStudents(House.values()[i%5], 1, false);
            }
            catch (EntranceException e){
                e.printStackTrace();
            }
        }
    }

    @AfterEach
    public void tearDown(){
        dashboard = null;
    }
}