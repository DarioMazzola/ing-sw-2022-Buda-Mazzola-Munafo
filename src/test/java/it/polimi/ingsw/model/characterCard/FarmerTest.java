package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FarmerTest {

    CharacterCard farmer;
    int numPlayers = 2;
    Player[] players;
    Player otherPlayer;
    Player currentPlayer;
    House houseColor = House.YELLOW;

    @BeforeEach
    void setUp() {
        farmer = new Farmer();

        currentPlayer = new Player(numPlayers);
        otherPlayer = new Player(numPlayers);

        currentPlayer.setDashboard(Color.BLACK);
        otherPlayer.setDashboard(Color.GRAY);

        players = new Player[2];

        players[0] = currentPlayer;
        players[1] = otherPlayer;
    }

    @Test
    void checkProfTest_sameNumberOfStudentsOnDashboard_OtherOwnsProfessor_professorMoves() {

        //adding the same number of students to the players' dashboard
        try{
            currentPlayer.getDashboard().addStudents(houseColor, 3);
            otherPlayer.getDashboard().addStudents(houseColor, 3);
            //otherPlayer owns the professor
            otherPlayer.getDashboard().addProf(houseColor);
        }
        catch (EntranceException e){
            fail();
        }

        Map<House, Boolean> currentPlayerProfBefore = new HashMap<>();
        Map<House, Boolean> otherPlayerProfBefore = new HashMap<>();

        for(House h : House.values()) {
            currentPlayerProfBefore.put(h, currentPlayer.getDashboard().isProfPresent(h));
            otherPlayerProfBefore.put(h, otherPlayer.getDashboard().isProfPresent(h));
        }

        try{
            farmer.checkProf(players, currentPlayer, houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            if(h != houseColor){
                assertEquals(currentPlayerProfBefore.get(h), currentPlayer.getDashboard().isProfPresent(h));
                assertEquals(otherPlayerProfBefore.get(h), otherPlayer.getDashboard().isProfPresent(h));
            }
            else{
                assertEquals(!currentPlayerProfBefore.get(h), currentPlayer.getDashboard().isProfPresent(h));
                assertEquals(!otherPlayerProfBefore.get(h), otherPlayer.getDashboard().isProfPresent(h));
            }
        }
    }

    @Test
    void checkProfTest_currentPlayerWithMoreStudents_NoOneOwnsProfessor_professorMoves(){

        //adding more students to the currentPlayer's dashboard
        try{
            currentPlayer.getDashboard().addStudents(houseColor, 1);
            otherPlayer.getDashboard().addStudents(houseColor, 0);

        }
        catch (EntranceException e) {
            fail();
        }

        Map<House, Boolean> currentPlayerProfBefore = new HashMap<>();
        Map<House, Boolean> otherPlayerProfBefore = new HashMap<>();

        for(House h : House.values()) {
            currentPlayerProfBefore.put(h, currentPlayer.getDashboard().isProfPresent(h));
            otherPlayerProfBefore.put(h, otherPlayer.getDashboard().isProfPresent(h));
        }

        try{
            farmer.checkProf(players, currentPlayer, houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            if(!h.equals(houseColor)) {
                assertEquals(currentPlayer.getDashboard().isProfPresent(h), currentPlayerProfBefore.get(h));
                assertEquals(otherPlayer.getDashboard().isProfPresent(h), otherPlayerProfBefore.get(h));
            }
            else{
                assertEquals(currentPlayer.getDashboard().isProfPresent(h), !currentPlayerProfBefore.get(h));
                assertEquals(otherPlayer.getDashboard().isProfPresent(h), otherPlayerProfBefore.get(h));
            }
        }

    }

    @Test
    void checkProfTest_currentPlayerWithMoreStudents_OtherPlayerOwnsProfessor_professorMoves(){

        //adding more students to the currentPlayer's dashboard
        try{
            currentPlayer.getDashboard().addStudents(houseColor, 4);
            otherPlayer.getDashboard().addStudents(houseColor, 3);

        }
        catch (EntranceException e) {
            fail();
        }

        otherPlayer.getDashboard().addProf(houseColor);

        Map<House, Boolean> currentPlayerProfBefore = new HashMap<>();
        Map<House, Boolean> otherPlayerProfBefore = new HashMap<>();

        for(House h : House.values()) {
            currentPlayerProfBefore.put(h, currentPlayer.getDashboard().isProfPresent(h));
            otherPlayerProfBefore.put(h, otherPlayer.getDashboard().isProfPresent(h));
        }

        try{
            farmer.checkProf(players, currentPlayer, houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            if(!h.equals(houseColor)) {
                assertEquals(currentPlayer.getDashboard().isProfPresent(h), currentPlayerProfBefore.get(h));
                assertEquals(otherPlayer.getDashboard().isProfPresent(h), otherPlayerProfBefore.get(h));
            }
            else{
                assertEquals(currentPlayer.getDashboard().isProfPresent(h), !currentPlayerProfBefore.get(h));
                assertEquals(otherPlayer.getDashboard().isProfPresent(h), !otherPlayerProfBefore.get(h));
            }
        }

    }

    @Test
    void checkProfTest_OtherPlayerWithMoreStudents_OtherPlayerOwnsStudents_NothingChanges(){
        //adding more students to the currentPlayer's dashboard
        try{
            currentPlayer.getDashboard().addStudents(houseColor, 3);
            otherPlayer.getDashboard().addStudents(houseColor, 4);

        }
        catch (EntranceException e) {
            fail();
        }

        otherPlayer.getDashboard().addProf(houseColor);

        Map<House, Boolean> currentPlayerProfBefore = new HashMap<>();
        Map<House, Boolean> otherPlayerProfBefore = new HashMap<>();

        for(House h : House.values()) {
            currentPlayerProfBefore.put(h, currentPlayer.getDashboard().isProfPresent(h));
            otherPlayerProfBefore.put(h, otherPlayer.getDashboard().isProfPresent(h));
        }

        try{
            farmer.checkProf(players, currentPlayer, houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            assertEquals(currentPlayer.getDashboard().isProfPresent(h), currentPlayerProfBefore.get(h));
            assertEquals(otherPlayer.getDashboard().isProfPresent(h), otherPlayerProfBefore.get(h));
        }
    }

    @Test
    void getDescription() {

        String description = "During this turn, you take control of any number of Professors even if you have " +
                "the same number of Students as the player who currently controls them";

        assertEquals(description, farmer.getDescription());
    }

    @AfterEach
    void tearDown() {
        farmer = null;
    }


}