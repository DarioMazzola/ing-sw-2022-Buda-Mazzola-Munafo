package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.messages.command.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.utils.Persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.controller.GameState.*;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the action controller
 * @author Gabriele Munafo' & Dario Mazzola
 */
public class ActionController {
    private final GameModel gm;
    private int studentsMoved;
    private final int maxStudMoved;
    private final List<String> availableActions;
    private boolean usedCharacterCard;
    private Player winner;
    private boolean isEnded;

    public ActionController(GameModel gm){
        this.isEnded = false;
        this.winner = null;
        this.gm = gm;
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
    public void doAction(CommandMessage messageReceived, TurnController tc) {
        MessageType type = messageReceived.getType();

        Persistence persistence = new Persistence();

        switch (type) {
            case MOVE_STUDENT_TO_ISLAND:
                if (studentsMoved == maxStudMoved){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(MAX_STUDENTS_MOVED.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                    break;
                }
                moveStudentsToIslandHandler(messageReceived, tc);

                studentsMoved++;

                if(studentsMoved == maxStudMoved){
                    availableActions.remove("Move students to dining hall or to island");
                }
                tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                break;

            case MOVE_STUDENT_TO_DINING_HALL:
                if (studentsMoved == maxStudMoved){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(MAX_STUDENTS_MOVED.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                    break;
                }

                moveStudentsToDiningHallHandler(messageReceived, tc);

                studentsMoved++;

                if(studentsMoved == maxStudMoved){
                    availableActions.remove("Move students to dining hall or to island");
                }
                tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                break;

            case CHOSEN_CHARACTER_CARD:
                if (usedCharacterCard){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(ALREADY_USED_CHARACTER_CARD.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                    break;
                }

                selectCharacterCardHandler(messageReceived);

                usedCharacterCard = true;
                availableActions.remove("Select character card");
                tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                break;

            case MOVE_MOTHER:
                if (studentsMoved != maxStudMoved){
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(STUDENTS_NOT_MOVED.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).actionPhase(availableActions);
                }
                moveMotherHandler(messageReceived);

                //initialize variables for next turn
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
                tc.getVirtualViewMap().get(messageReceived.getNickname()).selectCloud();
                break;

            default:
                System.err.println("Error in ActionController switch");
        }
        persistence.saveData(tc);
    }


    public String toString(){
        return "Action Controller";
    }

    private void selectCharacterCardHandler(CommandMessage message){

        Map<String, Object> map = ((ChosenCharacterCard)message).getMap();

        int numCCard = ((ChosenCharacterCard)message).getCardIndex();
        CharacterCard card = gm.getCharacterCardDeck()[numCCard];

        Map<String, Object> parameters = chooseCharacterCard(card, map);

        try {
            gm.useCharacterCard(numCCard, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the character card selected
     * @param card the character card used
     * @param map the map containing parameters useful for the character card
     */
    private Map<String, Object> chooseCharacterCard(CharacterCard card, Map<String, Object> map){
        Map<String, Object> parameters = new HashMap<>();
        CharacterCardEnum cardType = card.getType();

        switch (cardType) {
            case MONK:
                if(map.get("method") == null)
                    throw new IllegalArgumentException("Method to use not indicated");
                if((Integer)map.get("method") == 1){
                    parameters.put("method", "getStudents");
                }
                else if((Integer)map.get("method") == 2){
                    if(map.get("wantedHouse") == null)
                        throw new IllegalArgumentException("Wanted house not indicated");
                    if(map.get("destinationIsland") == null)
                        throw new IllegalArgumentException("Destination island not indicated");
                    parameters.put("method", "move");
                    parameters.put("wantedHouse", map.get("wantedHouse"));
                    parameters.put("destinationIsland", gm.getIslandList().get((int) map.get("island")));
                    parameters.put("bag", gm.getBag());
                }
                else
                    throw new IllegalArgumentException("Not valid method given");
                break;

            case HERALD:
                if(map.get("island") == null)
                    throw new IllegalArgumentException("Island in which to calculate the influence not indicated");
                parameters.put("Island", gm.getIslandList().get((int)map.get("island")));
                parameters.put("ArrayPlayers", gm.getArrayPlayers());
                parameters.put("NumPlayers", gm.getNumPlayers());
                parameters.put("CharacterCardDeck", gm.getCharacterCardDeck());
                break;

            case HERB_GRANMA:
                if(map.get("island") == null)
                    throw new IllegalArgumentException("Island in which to place the NoEntryTile not indicated");
                if((Integer)map.get("method") == 1)
                    parameters.put("method", "addNoEntryTile");
                else if((Integer)map.get("method") == 2)
                    parameters.put("method", "removeNoEntryTile");
                else if((Integer)map.get("method") == 3)
                    parameters.put("method", "getNoEntryTileNumber");
                else
                    throw new IllegalArgumentException("Not valid method given");

                parameters.put("Island", gm.getIslandList().get((int) map.get("island")));
                break;

            case JOLLY:
                if(map.get("method") == null)
                    throw new IllegalArgumentException("Method to use not indicated");
                if((Integer)map.get("method") == 1){
                    parameters.put("method", "getStudents");
                }
                else if((Integer)map.get("method") == 2){
                    if(map.get("wantedStudents") == null)
                        throw new IllegalArgumentException("Wanted house not indicated");
                    if(map.get("returnedStudents") == null)
                        throw new IllegalArgumentException("Destination island not indicated");

                    parameters.put("method", "move");
                    parameters.put("wantedStudents", map.get("wantedStudents"));
                    parameters.put("returnedStudents", map.get("returnedStudents"));
                    parameters.put("playerDashboard", gm.getCurrentPlayer().getDashboard());
                }
                else
                    throw new IllegalArgumentException("Not valid method given");
                break;

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
                parameters.put("fromDashboard", gm.getArrayPlayers()[(int) map.get("fromDashboard")].getDashboard());
                parameters.put("fromDiningHall", gm.getArrayPlayers()[(int) map.get("fromDiningHall")].getDashboard().getDiningHall());
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
                else if((Integer)map.get("method") == 2){
                    parameters.put("method", "move");
                    if(map.get("wantedStudents") == null)
                        throw new IllegalArgumentException("Wanted students not indicated");

                    parameters.put("method", "move");
                    parameters.put("wantedHouse", map.get("wantedHouse"));
                    parameters.put("currentPlayer", gm.getCurrentPlayer());
                    parameters.put("bag", gm.getBag());
                }
                else
                    throw new IllegalArgumentException("Not valid method given");
                break;

            case THIEF:
                if(map.get("wantedHouse") == null)
                    throw new IllegalArgumentException("Wanted house not indicated");

                parameters.put("wantedHouse", map.get("wantedHouse"));
                parameters.put("players", gm.getArrayPlayers());
                parameters.put("bag", gm.getBag());
                break;

            default:
                System.err.println("Error in chooseCharacterCard of ActionController switch");
        }

        return (parameters);
    }

    /**
     * Moves the students from the entrance to the island
     * @param message received from the client
     */
    private void moveStudentsToIslandHandler(CommandMessage message, TurnController tc) {
        int island = ((MoveStudentToIsland)message).getIsland();
        House house = ((MoveStudentToIsland)message).getHouse();

        if (house == null || island >= gm.getNumIslands()){
            tc.getVirtualViewMap().get(message.getNickname()).showError(INVALID_STUDENT_OR_ISLAND.toString());
            tc.getVirtualViewMap().get(message.getNickname()).actionPhase(availableActions);
            return;
        }

        try {
            gm.moveStudents(gm.getCurrentPlayer().getDashboard(), gm.getIslandList().get(island) , house, 1);
        } catch (Exception e) {
            e.printStackTrace();
            tc.getVirtualViewMap().get(message.getNickname()).showError(INVALID_STUDENT_OR_ISLAND.toString());
            tc.getVirtualViewMap().get(message.getNickname()).actionPhase(availableActions);
        }
    }

    /**
     * Moves the students from the entrance to the dining hall
     * @param message received from the client
     */
    private void moveStudentsToDiningHallHandler(CommandMessage message, TurnController tc){
        House house = ((MoveStudentToDiningHall)message).getHouse();

        if (house == null){
            tc.getVirtualViewMap().get(message.getNickname()).showError(INVALID_STUDENT.toString());
            tc.getVirtualViewMap().get(message.getNickname()).actionPhase(availableActions);
            return;
        }

        try {
            gm.moveStudents(gm.getCurrentPlayer().getDashboard(), gm.getCurrentPlayer().getDashboard().getDiningHall(), house, 1);
        } catch (Exception e) {
            e.printStackTrace();
            tc.getVirtualViewMap().get(message.getNickname()).showError(INVALID_STUDENT.toString());
            tc.getVirtualViewMap().get(message.getNickname()).actionPhase(availableActions);
        }
    }

    /**
     * Moves the mother nature
     * @param message received from the client
     */
    private void moveMotherHandler(CommandMessage message){
        int motherMoves = (((MoveMother)message).getMotherMoves());
        gm.setMotherIsland(motherMoves);

        Player p = null;
        try {
            p = gm.checkInfluence(gm.getIslandList().get(gm.getMotherIsland()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (p != null) { //pensare se mettere un messaggio che annuncia chi a vinto
            try {
                gm.moveTowers(p.getDashboard(), gm.getIslandList().get(gm.getMotherIsland()), 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //pensare se mettere un messaggio che annuncia che c'è stato un pareggio (in else)
    }

    private void resetActions(){
        availableActions.clear();
        availableActions.add("Move students to dining hall or to island");
        if (gm.isExpertMode()) {
            availableActions.add("Select character card");
        }
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
    private void GameEnded_Island(){
        if (gm.getNumIslands() < 4) {
            isEnded = true;

            List<Integer> numTowersOfPlayer = new ArrayList<>();

            for (int i = 0; i < gm.getNumPlayers(); i++) {
                numTowersOfPlayer.add(gm.getArrayPlayers()[i].getDashboard().getNumTowers());
            }

            Integer min = numTowersOfPlayer.stream().reduce((a,b)-> a < b ? a : b).orElse(null);

            if (min == null){
                winner = null;
                return;
            }

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
    private void GameEnded_Towers (){
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
    private void GameEnded_OtherCriteria(){

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

        Integer max = numProfOfPlayer.stream().reduce((a,b)-> a > b ? a : b).orElse(null);

        if (max == null){
            winner = null;
        }

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

    public int getStudentsMoved(){
        return studentsMoved;
    }

    public boolean isUsedCharacterCard(){
        return usedCharacterCard;
    }

    public int getMaxStudMoved(){
        return maxStudMoved;
    }
}