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
import static it.polimi.ingsw.model.House.PINK;
import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {

    Dashboard dashboard;

    @BeforeEach
    public void setup(){
        dashboard = new Dashboard(Color.BLACK, 7, 5);
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
            dashboard.addStudents(YELLOW, 7);
        }
        catch (EntranceException e){
            fail();
        }

        assertEquals(totalNumberBefore +7, dashboard.getNumStudentsIn());

        for(House h: values()){
            if(h.equals(YELLOW))
                assertEquals(housesValueBefore.get(h) +7, dashboard.getHouseStudents(h));
            else
                assertEquals(housesValueBefore.get(h), dashboard.getHouseStudents(h));
        }

    }

    @Test
    void addStudentsTest_FullEntranceGiven_ShouldThrowEntranceException(){
        fillEntrance();

        try {
            dashboard.addStudents(House.RED, 1);
        }
        catch (EntranceException e){
            assert(true);
        }
    }

    @Test
    void addStudentsTest_TooManyStudentsToAdd_ShouldThrowEntranceException(){

        try{
            dashboard.addStudents(RED, 8);
        }
        catch (EntranceException e){
            assert(true);
        }
    }

    @Test
    void addStudentsTest_NullHouseGiven_ShouldThrowNullPointerException(){

        try{
            dashboard.addStudents(null, 1);
        }
        catch (EntranceException e){
            fail();
        }
        catch (NullPointerException e){
            assert(true);
        }
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

        //House.RED
        try{
            dashboard.addStudents(RED, 2);
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
            dashboard.removeStudents(RED, 1);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        assertEquals(totalNumberBefore -1, dashboard.getNumStudentsIn());

        for(House h: House.values()){
            if(h.equals(RED))
                assertEquals(housesValueBefore.get(h) -1, dashboard.getHouseStudents(h));
            else
                assertEquals(housesValueBefore.get(h), dashboard.getHouseStudents(h));
        }

    }

    @Test
    void removeStudentsTest_TooManyStudentsToRemoveGiven_ShouldThrowIllegalChoice(){

        fillEntrance();
        try{
            dashboard.removeStudents(RED, 8);
        }
        catch (IllegalChoiceException e) {
            assert(true);
        }
    }

    @Test
    void removeStudentsTest_EmptyEntranceGiven_ShouldThrowIllegalChoiceException(){

        try{
            dashboard.removeStudents(RED, 1);
        }
        catch (IllegalChoiceException e) {
            assert(true);
        }
    }

    @Test
    void removeStudentsTest_NullHouseGiven_ShouldThrowNullPointerException(){

        try{
            dashboard.removeStudents(null, 1);
        }
        catch (IllegalChoiceException e){
            fail();
        }
        catch (NullPointerException e){
            assert (true);
        }
    }

    @Test
    void getHouseStudents_NullHouseGiven_ShouldThrowNullPointerException(){

        try{
            dashboard.getHouseStudents(null);
        }
        catch (NullPointerException e){
            assert(true);
        }
    }

    @Test
    void isProfPresentTest_NullColorGiven_ShouldThrowNullPointerException(){

        try{
            dashboard.isProfPresent(null);
        }
        catch (NullPointerException e){
            assert(true);
        }
    }

    @Test
    void addProfTest_NullColorGiven_ShouldThrowNullPointerException(){

        try{
            dashboard.addProf(null);
        }
        catch (NullPointerException e){
            assert(true);
        }
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

        dashboard.addProf(BLUE);

        for(House h: House.values()){
            if(h.equals(BLUE))
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

        dashboard.addProf(RED);
        dashboard.addProf(BLUE);
        dashboard.addProf(PINK);

        profValueBefore.put(YELLOW, dashboard.isProfPresent(YELLOW));
        profValueBefore.put(BLUE, dashboard.isProfPresent(BLUE));
        profValueBefore.put(GREEN, dashboard.isProfPresent(GREEN));
        profValueBefore.put(RED, dashboard.isProfPresent(RED));
        profValueBefore.put(PINK, dashboard.isProfPresent(PINK));

        try{
            dashboard.removeProf(House.RED);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h: House.values()){
            if(h.equals(RED))
                assertEquals(!profValueBefore.get(h), dashboard.isProfPresent(h));
            else
                assertEquals(profValueBefore.get(h), dashboard.isProfPresent(h));
        }

    }

    @Test
    void removeProfTest_ProfNotPresent_ShouldThrowException(){

        try {
            dashboard.removeProf(House.YELLOW);
        }
        catch (IllegalChoiceException e){
            assert(true);
        }
    }

    @Test
    void removeProfTest_NullColorGiven_ShouldThrowNullPointerException(){

        try{
            dashboard.removeProf(null);
        }
        catch (IllegalChoiceException e){
            fail();
        }
        catch (NullPointerException e){
            assert(true);
        }
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
    void addTowerTest_FullTowerAreaGiven_ShouldThrowException(){

        try {
            dashboard.addTower();
        }
        catch (TowerAreaException e){
            assert(true);
        }
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
    void removeTowerTest_EmptyTowerAreaGiven_ShouldThrowException(){

        try{
            dashboard.removeTower();
        }
        catch (TowerAreaException e) {
            assert(true);
        }
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

        try{
            dashboard.removeTower();
        }
        catch (TowerAreaException e){
            assert(true);
        }
    }

    @Test
    void addStudentToDiningHall(){

        try {
            dashboard.getDiningHall().addStudents(YELLOW, 1);
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
                dashboard.addStudents(House.values()[i%5], 1);
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