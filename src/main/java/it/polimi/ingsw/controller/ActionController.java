package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.messages.command.ChosenCharacterCard;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.messages.command.MoveMother;
import it.polimi.ingsw.messages.command.MoveStudentToIsland;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.controller.GameState.*;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the action controller
 * @author Gabriele Munafo'
 */
public class ActionController {
    private final GameModel gm;
    private final TurnController tc;
    private int studentsMoved;
    private final int maxStudMoved;
    private final List<String> availableActions;
    private boolean usedCharacterCard;
    private Player winner;
    private boolean isEnded;

    public ActionController(GameModel gm, TurnController turnController){
        this.isEnded = false;
        this.winner = null;
        this.gm = gm;
        this.tc = turnController;
        this.studentsMoved = 0;
        if (gm.getNumPlayers() == 2 || gm.getNumPlayers() == 4){
            this.maxStudMoved = 3;
        }
        else {
            this.maxStudMoved = 4;
        }
        this.availableActions = new ArrayList<>();
        resetActions();

        this.usedCharacterCard = false;
    }

    /**
     * Gets called to manage the messages from the client
     * @param messageReceived received from the client
     */
    public void doAction(CommandMessage messageReceived) {
        MessageType type = messageReceived.getType();

        switch (type) {
            case MOVE_STUDENT_TO_ISLAND:
                if (studentsMoved == maxStudMoved){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(MAX_STUDENTS_MOVED.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                    break;
                }
                moveStudentsToIslandHandler(messageReceived);

                studentsMoved++;

                if(studentsMoved == maxStudMoved){
                    availableActions.remove("Move");
                }
                tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                break;

            case MOVE_STUDENT_TO_DINING_HALL:
                if (studentsMoved == maxStudMoved){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(MAX_STUDENTS_MOVED.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                    break;
                }

                moveStudentsToDiningHallHandler(messageReceived);

                studentsMoved++;

                if(studentsMoved == maxStudMoved){
                    availableActions.remove("Move");
                }
                tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                break;

            case CHOSEN_CHARACTER_CARD:
                if (usedCharacterCard){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(ALREADY_USED_CHARACTER_CARD.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                    break;
                }
                //selectCharacterCardHandler(messageReceived);

                usedCharacterCard = true;
                availableActions.remove("SelectCharacterCard");
                tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                break;

            case MOVE_MOTHER:
                if (studentsMoved != maxStudMoved){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(STUDENTS_NOT_MOVED.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                }
                moveMotherHandler(messageReceived);

                //reinizializzo variabili di controllo
                resetActions();
                usedCharacterCard = false;
                studentsMoved = 0;

                GameEnded();

                if (isEnded){
                    if (winner != null){
                        tc.sendAllWinner(winner.getNickname());
                    }
                    else {
                        tc.sendAllWinner(null);
                    }
                    break;
                }

                tc.next_State(END_TURN);
                tc.getVirtualViewMap().get(messageReceived.getNickname()).selectCloud(tc.getAvailableClouds());
                break;
        }
    }

    //todo rivedere tutte le funzioni

    public String toString(){
        return "Action Controller";
    }

    /**
     * Uses the character card selected
     * @param card the character card used
     * @param map the map containing parameters useful for the character card
     */
    private void chooseCharacterCard(CharacterCard card, Map<String, Object> map){
        Map<String, Object> parameters = new HashMap<>();

        CharacterCardEnum cardType = card.getType();

        switch (cardType) {
            case MONK:
                if(map.get("method") == null)
                    throw new IllegalArgumentException("Method to use not indicated");
                if((Integer)map.get("method") == 1){
                    parameters.put("method", "getStudents");
                }
                else {
                    if(map.get("wantedHouse") == null)
                        throw new IllegalArgumentException("Wanted house not indicated");
                    if(map.get("destinationIsland") == null)
                        throw new IllegalArgumentException("Destination island not indicated");
                    parameters.put("method", "move");
                    parameters.put("wantedHouse", map.get("wantedHouse"));
                    parameters.put("destinationIsland", map.get("island"));
                    parameters.put("bag", gm.getBag());
                }
                break;
            case HERALD:
                if(map.get("island") == null)
                    throw new IllegalArgumentException("Island in which to calculate the influence not indicated");
                parameters.put("Island", map.get("island"));
                parameters.put("ArrayPlayers", gm.getArrayPlayers());
                parameters.put("ExpertMode", gm.isExpertMode());
                parameters.put("NumPlayers", gm.getNumPlayers());
                parameters.put("CharacterCardDeck", gm.getCharacterCardDeck());
                break;
            case HERB_GRANMA:
                if(map.get("island") == null)
                    throw new IllegalArgumentException("Island in which to place the NoEntryTile not indicated");
                parameters.put("Island", map.get("island"));
                break;
            case JOLLY:
                if(map.get("method") == null)
                    throw new IllegalArgumentException("Method to use not indicated");
                if((Integer)map.get("method") == 1){
                    parameters.put("method", "getStudents");
                }
                else {
                    if(map.get("wantedStudents") == null)
                        throw new IllegalArgumentException("Wanted house not indicated");
                    if(map.get("returnedStudents") == null)
                        throw new IllegalArgumentException("Destination island not indicated");

                    parameters.put("method", "move");
                    parameters.put("wantedStudents", map.get("wantedStudents"));
                    parameters.put("returnedStudents", map.get("returnedStudents"));
                    parameters.put("playerDashboard", gm.getCurrentPlayer().getDashboard());
                }
            case KNIGHT:
                parameters.put("CurrentPlayer", gm.getCurrentPlayer());
                break;
            case MAGICAL_MAILMAN:
                parameters.put("currentPlayer", gm.getCurrentPlayer());
                break;
            case MINSTREL:
                if(map.get("fromDashboard") == null)
                    throw new IllegalArgumentException("Students from Dashboard not indicated");
                if(map.get("fromDiningHall") == null)
                    throw new IllegalArgumentException("Students from Dining Hall not indicated");
                parameters.put("Dashboard", gm.getCurrentPlayer().getDashboard());
                parameters.put("DiningHall", gm.getCurrentPlayer().getDashboard().getDiningHall());
                parameters.put("fromDashboard", map.get("fromDashboard"));
                parameters.put("fromDiningHall", map.get("fromDiningHall"));
                break;
            case MUSHROOM_HUNTER:
                if(map.get("house") == null)
                    throw new IllegalArgumentException("House not indicated");
                parameters.put("House", map.get("house"));
                break;
            case SPOILED_PRINCESS:
                if(map.get("method") == null)
                    throw new IllegalArgumentException("Method to use not indicated");
                if((Integer)map.get("method") == 1){
                    parameters.put("method", "getStudents");
                }
                else {
                    if(map.get("wantedStudents") == null)
                        throw new IllegalArgumentException("Wanted students not indicated");

                    parameters.put("method", "move");
                    parameters.put("wantedHouse", map.get("wantedHouse"));
                    parameters.put("currentPlayer", gm.getCurrentPlayer());
                    parameters.put("bag", gm.getBag());
                }
            case THIEF:

                if(map.get("wantedHouse") == null)
                    throw new IllegalArgumentException("Wanted house not indicated");

                parameters.put("wantedHouse", map.get("wantedHouse"));
                parameters.put("players", gm.getArrayPlayers());
                parameters.put("bag", gm.getBag());
                break;

        }
    }

    /**
     * Moves the students from the entrance to the island
     * @param message received from the client
     */
    private void moveStudentsToIslandHandler(CommandMessage message) {
        Island island = ((MoveStudentToIsland)message).getIsland();
        House house = ((MoveStudentToIsland)message).getHouse();

        if (island == null || house == null || !gm.getIslandList().contains(island)){
            tc.getVirtualViewMap().get(message.getNickname()).showError(INVALID_STUDENT_OR_ISLAND.toString());
            //tc.getVirtualViewMap().get(message.getNickname()).actionPhase(availableActions);
        }

        try {
            gm.moveStudents(gm.getCurrentPlayer().getDashboard(), island, house, 1);
        } catch (Exception e) {
            e.printStackTrace();

        }
        //todo l'eccezione va lanciata?

    }

    /**
     * Moves the students from the entrance to the dining hall
     * @param message received from the client
     */
    private void moveStudentsToDiningHallHandler(CommandMessage message){

        House house = ((MoveStudentToIsland)message).getHouse();

        if(house == null){
            tc.getVirtualViewMap().get(message.getNickname()).showError(INVALID_STUDENT.toString());
            //tc.getVirtualViewMap().get(message.getNickname()).actionPhase(availableActions);
        }
        try {
            gm.moveStudents(gm.getCurrentPlayer().getDashboard(), gm.getCurrentPlayer().getDashboard().getDiningHall(), house, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void selectCharacterCardHandler(CommandMessage message){
//        todo sto get map non esiste
//        Map<String, Object> map = ((ChosenCharacterCard)message).getMap();
//
//        int numCCard = ((ChosenCharacterCard)message).getCardIndex();
//        CharacterCard card = gm.getCharacterCardDeck()[numCCard];
//
//        chooseCharacterCard(card, map);
//        try {
//            gm.useCharacterCard(numCCard, map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Moves the mother nature
     * @param message received from the client
     */
    private void moveMotherHandler(CommandMessage message){
        gm.setMotherIsland(((MoveMother)message).getMotherMoves());
        try {
            gm.moveTowers(gm.checkInfluence(gm.getIslandList().get(gm.getMotherIsland())).getDashboard(), gm.getIslandList().get(gm.getMotherIsland()), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetActions(){
        availableActions.clear();
        availableActions.add("Move");
        availableActions.add("SelectCharacterCard");
    }

    /**
     * Checks if the game is ended
     */
    private void GameEnded(){
        GameEnded_Towers();
        if (!isEnded || winner == null) {
            GameEnded_Island();
            if (isEnded && winner == null){
                GameEnded_OtherCriteria();
            }
        }
    }

    /**
     * Checks if the game is ended due to the low number of island
     */
    private void GameEnded_Island(){ //todo ricontrollare
        if (gm.getNumIslands() < 4) {
            isEnded = true;
            List<Integer> numTowersOfPlayer = new ArrayList<>();
            for (int i = 0; i < gm.getNumPlayers(); i++) {
                numTowersOfPlayer.add(gm.getArrayPlayers()[i].getDashboard().getNumTowers());
            }

            int min = numTowersOfPlayer.stream().max((a,b)-> a<b ? a : b).get();

            int numMinValues = 0;
            for (int i : numTowersOfPlayer) {
                if (i == min)
                    numMinValues++;
            }
            if (numMinValues > 1)
                winner = null;
            else
                winner = gm.getArrayPlayers()[numTowersOfPlayer.indexOf(min)];
        }
    }

    /**
     * Checks if the game is ended due to finishing the towers
     */
    private void GameEnded_Towers (){ //todo ricontrollare
        boolean tie = false;

        for (int i=0; i<gm.getNumPlayers(); i++){
            if (gm.getArrayPlayers()[i].getDashboard().getNumTowers()<1) {
                if (winner != null) {
                    tie = true;
                }
                winner = gm.getArrayPlayers()[i];
                isEnded = true;
            }
        }
        if (tie){
            winner = null;
            isEnded = true;
        }
    }

    /**
     * Checks if there is a winner, when the game is ended
     */
    private void GameEnded_OtherCriteria(){ //todo ricontrollare
        List<Integer> numProfOfPlayer = new ArrayList<>();
        int numProf;
        for (int i = 0; i < gm.getNumPlayers(); i++) {
            numProf = 0;
            for (House h : House.values())
            {
                if (gm.getArrayPlayers()[i].getDashboard().isProfPresent(h)){
                    numProf++;
                }
            }
            numProfOfPlayer.add(numProf);
        }

        int max = numProfOfPlayer.stream().max((a,b)-> a<b ? b : a).get();

        int numMaxValues = 0;
        for (int i : numProfOfPlayer) {
            if (i == max)
                numMaxValues++;
        }
        if (numMaxValues > 1)
            winner = null;
        else
            winner = gm.getArrayPlayers()[numProfOfPlayer.indexOf(max)];
    }
}