package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCardTest {

    CharacterCard characterCard;
    int cost = 0;
    String cardName = "BasicContext";

    int numPlayers = 2;
    Player[] players;
    Player otherPlayer;
    Player currentPlayer;
    House houseColor = House.YELLOW;

    boolean expertMode = false;
    Island island;
    CharacterCard[] characterCardDeck;

    @BeforeEach
    void setUp() throws BagException {
        characterCard = new CharacterCard(cost, cardName, null);

        currentPlayer = new Player(numPlayers);
        otherPlayer = new Player(numPlayers);

        currentPlayer.setDashboard(Color.BLACK, "currentPlayer");
        otherPlayer.setDashboard(Color.GRAY, "otherPlayer");

        players = new Player[2];

        players[0] = currentPlayer;
        players[1] = otherPlayer;

        characterCardDeck = new CharacterCard[3];

        characterCardDeck[0] = new Monk(new Bag());
        characterCardDeck[1] = new Knight();
        characterCardDeck[2] = new HerbGranma();

        island = new Island(expertMode);
    }

    @Test
    void getCostTest() {
        assertEquals(cost, characterCard.getCost());
    }

    @Test
    void toStringTest(){
        assertEquals(cardName, characterCard.toString());
    }

    @Test
    void getDescriptionTest() {
        String description = "Standard context";
        assertEquals(description, characterCard.getDescription());
    }

    /**
     * Ensures that when the method is called:
     *      - the cost is increased
     *      - the neverUse attribute is set to false
     *      - the card now is in use
     */
    @Test
    void doEffectTest() {

        int costBefore = characterCard.getCost();
        boolean neveUsedBefore = characterCard.isNeverUsed();
        boolean inUseBefore = characterCard.isInUse();
        try{
            characterCard.doEffect(null);
        }
        catch (Exception e){
            fail();
        }
        assertEquals(costBefore + 1, characterCard.getCost());
        assertEquals(!neveUsedBefore, characterCard.isNeverUsed());
        assertEquals(!inUseBefore, characterCard.isInUse());
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
            characterCard.checkProf(players, currentPlayer, houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            assertEquals(currentPlayer.getDashboard().isProfPresent(h), currentPlayerProfBefore.get(h));
            assertEquals(otherPlayer.getDashboard().isProfPresent(h), otherPlayerProfBefore.get(h));
        }
    }

    /**
     * Case tested:
     *      - currentPlayer has the most students' number of the house indicated as parameter
     *      - no one owns the professor of the color indicated as parameter
     * Expected result:
     *      - the professor is assigned to the currentPlayer
     */
    @Test
    void checkProfTest_2(){

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
            characterCard.checkProf(players, currentPlayer, houseColor);
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

    /**
     * Case tested:
     *      - currentPlayer has the most students' number of the house indicated as parameter
     *      - otherPlayer owns the professor of the color indicated as parameter
     * Expected result:
     *      - the professor is assigned to the currentPlayer
     */
    @Test
    void checkProfTest_currentPlayerWithMoreStudents_OtherPlayerOwnsProfessor_professorMoves(){

        //adding more students to the currentPlayer's dashboard
        try{
            currentPlayer.getDashboard().addStudents(houseColor, 4);
            otherPlayer.getDashboard().addStudents(houseColor, 3);
            //otherPlayer owns the professor
            otherPlayer.getDashboard().addProf(houseColor);
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
            characterCard.checkProf(players, currentPlayer, houseColor);
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

    /**
     * Case tested:
     *      - otherPlayer has the most students' number of the house indicated as parameter
     *      - otherPlayer owns the professor of the color indicated as parameter
     * Expected result:
     *      - the professor remains to the currentPlayer
     */
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
            characterCard.checkProf(players, currentPlayer, houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            assertEquals(currentPlayer.getDashboard().isProfPresent(h), currentPlayerProfBefore.get(h));
            assertEquals(otherPlayer.getDashboard().isProfPresent(h), otherPlayerProfBefore.get(h));
        }
    }

    /**
     * Ensures that when the method is called:
     *      - in dashboard from the professor is removed
     *      - in the dashboard to the professor is added
     *      - the other professors of the two dashboards are not modified
     */
    @Test
    void moveProfTest() {
        Dashboard from = new Dashboard(Color.BLACK, 5, 7, "from");
        Dashboard to = new Dashboard(Color.BLACK, 5, 7, "to");

        from.addProf(houseColor);

        Map<House, Boolean> fromMapProfBefore = new HashMap<>();
        Map<House, Boolean> toMapProfBefore = new HashMap<>();

        for(House h : House.values()){
            fromMapProfBefore.put(h, from.isProfPresent(h));
            toMapProfBefore.put(h, to.isProfPresent(h));
        }

        try{
            characterCard.moveProf(from, to, houseColor);
        }
        catch (IllegalChoiceException e){
            fail();
        }

        for(House h : House.values()){
            if(h.equals(houseColor)){
                assertEquals(!fromMapProfBefore.get(h), from.isProfPresent(h));
                assertEquals(!toMapProfBefore.get(h), to.isProfPresent(h));
            }
            else{
                assertEquals(fromMapProfBefore.get(h), from.isProfPresent(h));
                assertEquals(toMapProfBefore.get(h), to.isProfPresent(h));
            }
        }
    }

    /**
     * Case tested:
     *      - expertMode on
     *      - noEntryTile present on the island
     * Expected result:
     *      - null is returned
     */
    @Test
    void checkInfluence_1() {

        Player winner = null;

        island.addNoEntryTile();


        try{
            winner = characterCard.checkInfluence(island, expertMode, numPlayers, players, characterCardDeck);
        }
        catch (Exception e){
            fail();
        }

        assertNull(winner);
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
    void checkInfluence_2(){
        Player winner = null;

        expertMode = true;

        island.addStudents(House.YELLOW, 2);
        island.addStudents(House.RED, 1);

        island.addStudents(House.BLUE, 1);
        island.setTowerColor(otherPlayer.getDashboard().getTowerColor());
        island.addTowers(1);

        currentPlayer.getDashboard().addProf(House.YELLOW);
        currentPlayer.getDashboard().addProf(House.RED);
        otherPlayer.getDashboard().addProf(House.BLUE);

        try{
            winner = characterCard.checkInfluence(island, expertMode, numPlayers, players, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(currentPlayer, winner);
    }

    /**
     * Case tested:
     *      - expertMode off
     *      - numPlayers = 2
     *      - noEntryTile not present on the island
     *      - currentPlayer with influence value than the otherPlayer
     *      - otherPlayer has a tower on the island
     * Expected result:
     *      - winner is currentPlayer
     */
    @Test
    void checkInfluence_3(){
        Player winner = null;

        expertMode = false;

        island.addStudents(House.YELLOW, 2);
        island.addStudents(House.RED, 1);

        island.addStudents(House.BLUE, 1);
        island.setTowerColor(otherPlayer.getDashboard().getTowerColor());
        island.addTowers(1);

        currentPlayer.getDashboard().addProf(House.YELLOW);
        currentPlayer.getDashboard().addProf(House.RED);
        otherPlayer.getDashboard().addProf(House.BLUE);

        try{
            winner = characterCard.checkInfluence(island, expertMode, numPlayers, players, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(currentPlayer, winner);
    }

    /**
     * Case tested:
     *      - expertMode on
     *      - numPlayers = 2
     *      - noEntryTile not present on the island
     *      - players with the same influence value
     * Expected result:
     *      - tie, null is returned
     */
    @Test
    void checkInfluence_4(){
        Player winner = null;

        expertMode = true;

        island.addStudents(House.YELLOW, 3);
        island.addStudents(House.BLUE, 3);

        currentPlayer.getDashboard().addProf(House.YELLOW);
        otherPlayer.getDashboard().addProf(House.BLUE);

        try{
            winner = characterCard.checkInfluence(island, expertMode, numPlayers, players, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertNull(winner);
    }

    /**
     * Case tested:
     *      - expertMode on
     *      - numPlayers = 2
     *      - noEntryTile not present on the island
     *      - otherPlayer with more influence value than the currentPlayer
     *      - otherPlayer has 2 tower on the island
     * Expected result:
     *      - winner is otherPlayer
     */
    @Test
    void checkInfluence_5(){
        Player winner = null;

        expertMode = true;

        island.addStudents(House.YELLOW, 1);
        island.addStudents(House.RED, 1);

        island.addStudents(House.BLUE, 1);
        island.setTowerColor(otherPlayer.getDashboard().getTowerColor());
        island.addTowers(2);

        currentPlayer.getDashboard().addProf(House.YELLOW);
        currentPlayer.getDashboard().addProf(House.RED);
        otherPlayer.getDashboard().addProf(House.BLUE);

        try{
            winner = characterCard.checkInfluence(island, expertMode, numPlayers, players, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(otherPlayer, winner);
    }

    /**
     * Case tested:
     *      - numPlayers = 4
     *      - expertMode off
     *      - noEntryTile not present on the island
     *      - team1 has more influence value than the team2
     *      - team2 has 1 tower on the island
     * Expected result:
     *      - winner is team1, represented by p1 (team1 leader)
     */
    @Test
    void checkInfluence_6(){

        Player winner = null;

        setUp4Players();

        island.addStudents(House.YELLOW, 2);
        island.addStudents(House.RED, 1);

        island.addStudents(House.BLUE, 1);
        island.setTowerColor(players[1].getDashboard().getTowerColor());
        island.addTowers(1);

        players[0].getDashboard().addProf(House.YELLOW);
        players[1].getDashboard().addProf(House.RED);
        players[2].getDashboard().addProf(House.BLUE);

        try{
            winner = characterCard.checkInfluence(island, expertMode, 4, players, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(players[0], winner);
    }

    /**
     * Case tested:
     *      - numPlayers = 4
     *      - expertMode off
     *      - noEntryTile not present on the island
     *      - team1 has the same influence value of the team2
     * Expected result:
     *      - tie, null is returned
     */
    @Test
    void checkInfluence_7(){

        Player winner = null;

        setUp4Players();

        island.addStudents(House.YELLOW, 2);
        island.addStudents(House.RED, 1);
        island.addStudents(House.BLUE, 2);

        island.setTowerColor(players[2].getDashboard().getTowerColor());
        island.addTowers(1);

        players[0].getDashboard().addProf(House.YELLOW);
        players[1].getDashboard().addProf(House.RED);
        players[2].getDashboard().addProf(House.BLUE);

        try{
            winner = characterCard.checkInfluence(island, expertMode, 4, players, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertNull(winner);
    }

    /**
     * Case tested:
     *      - numPlayers = 4
     *      - expertMode off
     *      - noEntryTile not present on the island
     *      - team2 has more influence value than the team1
     * Expected result:
     *      - winner is team2, represented by p2 (team2 leader)
     */
    @Test
    void checkInfluence_8(){

        Player winner = null;

        setUp4Players();

        island.addStudents(House.YELLOW, 2);
        island.addStudents(House.RED, 1);
        island.addStudents(House.BLUE, 2);

        players[1].getDashboard().addProf(House.RED);
        players[2].getDashboard().addProf(House.YELLOW);
        players[3].getDashboard().addProf(House.BLUE);

        try{
            winner = characterCard.checkInfluence(island, expertMode, 4, players, characterCardDeck);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(winner, players[2]);
    }

    private void setUp4Players(){

        Player p0 = new Player(4);
        Player p1 = new Player(4);
        Player p2 = new Player(4);
        Player p3 = new Player(4);

        p0.setTeamLeader(true);
        p1.setTeamLeader(false);
        p2.setTeamLeader(true);
        p3.setTeamLeader(false);

        players = new Player[4];

        players[0] = p0;
        players[1] = p1;
        players[2] = p2;
        players[3] = p3;

        p0.setDashboard(Color.BLACK, "p0");
        p1.setDashboard(Color.BLACK, "p1");
        p2.setDashboard(Color.GRAY, "p2");
        p3.setDashboard(Color.GRAY, "p3");
    }

    @AfterEach
    void tearDown() {
        characterCard = null;

        characterCardDeck = null;

        currentPlayer = null;
        otherPlayer = null;

        players = null;

        island = null;
    }
}