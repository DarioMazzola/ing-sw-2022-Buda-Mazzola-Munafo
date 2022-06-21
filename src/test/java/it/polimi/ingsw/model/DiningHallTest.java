package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.StudentsTableException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiningHallTest {

    DiningHall diningHall;
    House houseDefault = House.YELLOW;

    @BeforeEach
    void setup(){
        diningHall = new DiningHall("Nickname");
    }

    @Test
    void addStudentTest_FullDiningHallGiven_ShouldThrowStudentsTableException(){
        refillDiningHall();

        assertThrows(StudentsTableException.class, ()->diningHall.addStudents(houseDefault, 1, false));

    }


    @Test
    void addStudentTest_NullHouseGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->diningHall.addStudents(null, 1, false));
    }

    @Test
    void getHouseStudentsTest(){
        refillDiningHall();

        int studentsNumber;

        studentsNumber = diningHall.getHouseStudents(houseDefault);
        assertEquals(10, studentsNumber);

    }

    @Test
    void removeStudentsTest(){
        refillDiningHall();

        try{
            diningHall.removeStudents(houseDefault, 1, false);
        }
        catch(StudentsTableException e){
            fail();
        }
        assertEquals(9, diningHall.getHouseStudents(houseDefault));
    }

    @Test
    void removeStudentTest_EmptyDiningHallGiven_ShouldThrowStudentsTableException(){


        try{
            diningHall.removeStudents(houseDefault, 1, false);
        }
        catch(StudentsTableException e){
            assert(true);
        }

    }

    @Test
    void removeStudentsTest_NullHouseGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->diningHall.removeStudents(null, 1, false));
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
                   diningHall.addStudents(h, 1, false);
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