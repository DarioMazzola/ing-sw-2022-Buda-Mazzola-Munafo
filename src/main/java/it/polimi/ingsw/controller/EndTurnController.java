package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.ReducedPlayer;
import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.messages.answer.UpdateCurrentPlayer;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.messages.command.ChosenCloud;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.utils.Persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the end turn controller
 * @author Gabriele Munafo'
 */
public class EndTurnController{
    private final GameModel gm;

    public EndTurnController(GameModel gm){

        this.gm = gm;
    }

    /**
     * Gets called to manage the messages from the client
     * @param messageReceived received from the client
     */
    public void doAction(CommandMessage messageReceived, TurnController tc) {
        Persistence persistence = new Persistence();
        System.out.println(messageReceived.getNickname() + "is in end turn state");

        int numCloud = ((ChosenCloud)messageReceived).getCloud();

        if (gm.getArrayClouds()[numCloud].isFull()) {
            try {
                gm.refillFromCloud(gm.getArrayClouds()[numCloud]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(INVALID_CLOUD.toString());
            tc.getVirtualViewMap().get(messageReceived.getNickname()).selectCloud();
            return;
        }

        if (tc.isThereNextPlayer()){
            tc.getVirtualViewMap().get(messageReceived.getNickname()).goToWaitingRoom();

            tc.next_State(GameState.ACTION);

            gm.setCurrentPlayer(tc.getNextPlanner());

            List<String> availableActions = new ArrayList<>();
            availableActions.add("Move students to dining hall or to island");

            if (gm.isExpertMode()) {
                availableActions.add("Select character card");
            }

            System.out.println("andiamo avanti");

            tc.getVirtualViewMap().get(gm.getCurrentPlayer().getNickname()).actionPhase(availableActions);
        }
        else {
            tc.next_State(GameState.PLANNING);
            setAllGraveyard();
            try {
                gm.refillClouds();
            } catch (IllegalChoiceException | BagException e) {
                e.printStackTrace();
            }
            tc.getVirtualViewMap().get(gm.getArrayPlayers()[tc.getFirstPlanner()].getNickname()).selectAssistantCard(Arrays.asList(Card.values()));
        }

        persistence.saveData(tc);
    }

    public String toString(){
        return "End Turn Controller";
    }

    private void setAllGraveyard(){
        for (Player p : gm.getArrayPlayers()){
            p.setGraveyard();
        }
    }
}
