package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.messages.command.ChosenCloud;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.GameModel;

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
    private final TurnController tc;

    public EndTurnController(GameModel gm, TurnController turnController){
        this.gm = gm;
        this.tc = turnController;
    }

    /**
     * Gets called to manage the messages from the client
     * @param messageReceived received from the client
     */
    public void doAction(CommandMessage messageReceived) {
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
            tc.next_State(GameState.ACTION);

            List<String> availableActions = new ArrayList<String>();
            availableActions.add("Move");
            availableActions.add("SelectCharacterCard");

            tc.getVirtualViewMap().get(gm.getArrayPlayers()[tc.getNextPlanner()].getNickname()).actionPhase(availableActions);
        }
        else {
            tc.next_State(GameState.PLANNING);
            tc.getVirtualViewMap().get(gm.getArrayPlayers()[tc.getFirstPlanner()].getNickname()).selectAssistantCard(Arrays.asList(Card.values()));
        }
    }

    public String toString(){
        return "End Turn Controller";
    }
}
