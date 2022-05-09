package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MinstrelTest {

    CharacterCard minstrel;
    Player currentPlayer;
    House[] fromDashboard = {House.YELLOW, House.YELLOW, House.RED};
    House[] fromDiningHall = {House.GREEN, House.PINK, House.PINK};
    Map<House, Integer> fromDashboardMap;
    Map<House, Integer> fromDiningHallMap;

    @BeforeEach
    void setUp() {

        minstrel = new Minstrel();
        currentPlayer = new Player(2);
        currentPlayer.setDashboard(Color.BLACK);

        fromDashboardMap = new HashMap<>();
        fromDiningHallMap = new HashMap<>();

        for(House h : House.values()){
            fromDiningHallMap.put(h, 0);
            fromDashboardMap.put(h, 0);
        }


        for(int i=0; i<fromDashboard.length; i++){
            fromDashboardMap.put(fromDashboard[i], fromDashboardMap.getOrDefault(fromDashboard[i], 0) +1);
            fromDiningHallMap.put(fromDiningHall[i], fromDiningHallMap.getOrDefault(fromDiningHall[i], 0) +1);
        }

        for(int i=0; i<fromDashboard.length; i++){
            try{
                currentPlayer.getDashboard().addStudents(fromDashboard[i], 1);
                currentPlayer.getDashboard().getDiningHall().addStudents(fromDiningHall[i], 1);
            }
            catch (Exception e){
                fail();
            }
        }
    }

    @Test
    void doEffect_moveStudents() {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("Method", "moveStudents");
        parameters.put("Dashboard", currentPlayer.getDashboard());
        parameters.put("DiningHall", currentPlayer.getDashboard().getDiningHall());
        parameters.put("fromDashboard", fromDashboard);
        parameters.put("fromDiningHall", fromDiningHall);

        try{
            minstrel.doEffect(parameters);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(fromDiningHallMap, currentPlayer.getDashboard().getStudents());
        assertEquals(fromDashboardMap, currentPlayer.getDashboard().getDiningHall().getStudents());
    }

    @Test
    void doEffect_moveStudents_ShouldThrowIllegalArgumentException(){
        Map<String, Object> parameters = new HashMap<>();

        fromDashboard = new House[]{House.YELLOW, House.YELLOW};

        parameters.put("Method", "moveStudents");
        parameters.put("Dashboard", currentPlayer.getDashboard());
        parameters.put("DiningHall", currentPlayer.getDashboard().getDiningHall());
        parameters.put("fromDashboard", fromDashboard);
        parameters.put("fromDiningHall", fromDiningHall);

        assertThrows(IllegalArgumentException.class, ()-> minstrel.doEffect(parameters));
    }
    @Test
    void getDescription() {
        String description = "You may exchange up to 2 Students between your Entrance and your Dining Room";

        assertEquals(description, minstrel.getDescription());
    }

    @AfterEach
    void tearDown() {
    }


}