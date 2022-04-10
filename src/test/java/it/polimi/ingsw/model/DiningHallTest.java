package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.StudentsTableException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiningHallTest {

    DiningHall diningHall;

    @BeforeEach
    void setup(){
        diningHall = new DiningHall();
    }

    @Test
    void addStudentTest_FullDiningHallGiven_ShouldThrowStudentsTableException(){
        refillDiningHall();

        //Color.YELLOW
        try{
            diningHall.addStudents(House.YELLOW, 1);
        }
        catch (StudentsTableException e){
            assert(true);
        }

    }

    @Test
    void addStudentTest_NullHouseGiven_ShouldThrowNullPointerException(){

        try{
            diningHall.addStudents(null, 1);
        }
        catch (StudentsTableException e){
            fail();
        }
        catch (NullPointerException e){
            assert (true);
        }
    }

    @Test
    void getHouseStudentsTest(){
        refillDiningHall();

        int studentsNumber;

        //House.YELLOW
        studentsNumber = diningHall.getHouseStudents(House.YELLOW);
        assertEquals(10, studentsNumber);

    }

    @Test
    void removeStudentsTest(){
        refillDiningHall();

        //House.YELLOW
        try{
            diningHall.removeStudents(House.YELLOW, 1);
        }
        catch(StudentsTableException e){
            fail();
        }
        assertEquals(9, diningHall.getHouseStudents(House.YELLOW));
    }

    @Test
    void removeStudentTest_EmptyDiningHallGiven_ShouldThrowStudentsTableException(){

        //House.YELLOW
        try{
            diningHall.removeStudents(House.YELLOW, 1);
        }
        catch(StudentsTableException e){
            assert(true);
        }

    }

    @Test
    void removeStudentsTest_NullHouseGiven_ShouldThrowNullPointerException(){

        try{
            diningHall.removeStudents(null, 1);
        }
        catch (StudentsTableException e){
            fail();
        }
        catch (NullPointerException e){
            assert(true);
        }
    }

    /**
     * Utility method: it creates a dashboard full of students to test the:
     *                  - addStudentTest_FullDiningHallGiven_ShouldThrowException() test method
     *                  - getHouseStudentsTest(House house)
     */
    private void refillDiningHall(){

        for(House h : House.values()){
            for(int i=0; i<10; i++){
               try{
                   diningHall.addStudents(h, 1);
               }
               catch (StudentsTableException e){
                   fail();
               }
            }
        }
    }

    @AfterEach
    void tearDown(){
        diningHall = null;
    }

}