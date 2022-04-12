package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;
import static org.junit.jupiter.api.Assertions.*;

public class BagTest {

    private Bag bag;
    House houseDefault = YELLOW;

    @BeforeEach
    public void setup(){
        bag = new Bag();

        refillBag();
    }

    /**
     * Verifies: - pull method is taking a student by one of the houses available
     *           - the number of available students is correctly decreased
     *           - the number of all available students is correctly decreased
     */
    @Test
    void pullTest(){

        int totalNumberBefore = bag.getTotalStudentsNumber();
        int totalNumberAfter;
        House housePulled = null;
        Map<House, Integer> housesValueBefore= new HashMap<>(); // stores the number of students for each house that are in the bag
                                                                // before performing the method
        housesValueBefore.put(YELLOW, bag.getHouseStudents(YELLOW));
        housesValueBefore.put(BLUE, bag.getHouseStudents(BLUE));
        housesValueBefore.put(GREEN, bag.getHouseStudents(GREEN));
        housesValueBefore.put(RED, bag.getHouseStudents(RED));
        housesValueBefore.put(PINK, bag.getHouseStudents(PINK));

        try{
            housePulled = bag.pull();
        }
        catch (BagException e){
            fail();
        }
        boolean value = false;

        //finding which house was pulled from the bag
        for(House c : values()){
            if (c.equals(housePulled)) {
                value = true;
                break;
            }
        }

        totalNumberAfter = bag.getTotalStudentsNumber();

        assertTrue(value); //true if the method caught a student
        assertEquals(totalNumberBefore - 1, totalNumberAfter); //post-execution total students' number is decreased

        for(House h: housesValueBefore.keySet()){
            if(h.equals(housePulled)) // the decreased value corresponds to the house pulled out
                assertEquals(housesValueBefore.get(h) - 1, bag.getHouseStudents(h));
            else // the students' occupation number from other houses is unchanged
                assertEquals(housesValueBefore.get(h), bag.getHouseStudents(h));
        }

    }

    @Test
    void pullTest_EmptyBagGiven_ShouldThrowBagException(){
        removeAllStudents();

        assertThrows(BagException.class, ()->bag.pull());
    }

    @Test
    void addStudentTest_NullHouseGiven_ShouldThrowNullPointerException(){

        try{
            bag.pull();
        }
        catch (Exception e){
            fail();
        }

        assertThrows(NullPointerException.class, ()->bag.addStudents(null, 1));
    }

    @Test
    void addStudentTest_FullBagGiven_ShouldThrowBagException() {

        assertThrows(BagException.class, ()->bag.addStudents(houseDefault, 3));
    }

    /**
     * Verifies: - addStudents method adds a student to the bag
     *           - number of students from other houses is unchanged
     *           - total number is increased
     *           - students' number of house chosen is correctly increased
     */
    @Test
    void addStudentTest(){

        int totalNumberBefore;
        Map<House, Integer> housesValueBefore= new HashMap<>();

        removeAllStudents(); //removing all students from the bag

        totalNumberBefore = bag.getTotalStudentsNumber();

        // stores the number of students for each house that are in the bag before performing the method
        housesValueBefore.put(YELLOW, bag.getHouseStudents(YELLOW));
        housesValueBefore.put(BLUE, bag.getHouseStudents(BLUE));
        housesValueBefore.put(GREEN, bag.getHouseStudents(GREEN));
        housesValueBefore.put(RED, bag.getHouseStudents(RED));
        housesValueBefore.put(PINK, bag.getHouseStudents(PINK));

        //adding a houseDefault student to the bag
        try{
            bag.addStudents(houseDefault, 1);
        }
        catch (BagException e){
            fail();
        }

        assertEquals(totalNumberBefore +1, bag.getTotalStudentsNumber()); //post-execution total students' number is increased

        for(House h : values()){
            if(h != houseDefault) // the students' occupation number from other houses is unchanged
                assertEquals(housesValueBefore.get(h), bag.getHouseStudents(h));
           else // the increased value corresponds to the house added
                assertEquals(housesValueBefore.get(h) +1, bag.getHouseStudents(h));
        }
    }

    /**
     * Verifies that the student's number is correctly returned by the method getHouseStudents
     */

    @Test
    void getHouseStudentsTest(){

        assertEquals(24, bag.getHouseStudents(houseDefault));

    }

    @Test
    void getHouseStudentTest_NullHouseGiven_ShouldThrowNullPointerException(){

        assertThrows(NullPointerException.class, ()->bag.getHouseStudents(null));
    }

    //Utility method:
    private void removeAllStudents(){

        for(int i=0; i<120; i++) {
            try{
                bag.pull();
            }
            catch(BagException e){
                fail();
            }
        }
    }

    //Utility method : restore the number of students by each house
    private void refillBag(){
        for(House h : values()){
            if(bag.getHouseStudents(h) != 24){
                try{
                    bag.addStudents(h, 24 - bag.getHouseStudents(h));
                }
                catch (BagException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @AfterEach
    public void tearDown(){
        bag = null;
    }
}