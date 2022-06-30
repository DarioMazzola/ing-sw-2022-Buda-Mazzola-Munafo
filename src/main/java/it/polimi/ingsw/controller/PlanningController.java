package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.CardNotInDeckException;
import it.polimi.ingsw.messages.command.ChosenAssistantCard;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.utils.Persistence;

import java.util.*;
import java.util.stream.Collectors;

import static it.polimi.ingsw.controller.GameState.ACTION;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the planning controller
 *
 * @author Gabriele Munafo'
 */
public class PlanningController {
    private int firstPlanner;
    private final List<Card> cardList;
    private int[] ranking;
    private transient GameModel gm;
    private final List<Card> availableAssistantCards;
    private int selected;
    private int position;

    public PlanningController (GameModel gm){

        this.selected = 0;
        this.firstPlanner = 0;
        this.gm = gm;
        position = 1;

        this.cardList = new ArrayList<>();

        this.availableAssistantCards = new ArrayList<>();
        availableAssistantCards.addAll(Arrays.asList(Card.values()));
    }

    /**
     * Gets called to manage the messages from the client
     *
     * @param messageReceived received from the client
     * @param tc the turn controller in use
     */
    public void doAction(CommandMessage messageReceived, TurnController tc) {
        Persistence persistence = new Persistence();
        System.out.println("\n" + messageReceived.getNickname() + " is in planning state\n");

        Card card = ((ChosenAssistantCard) messageReceived).getAssistantCard();

        for (int i = 0; i < gm.getNumPlayers(); i++) {
            if (gm.getArrayPlayers()[i].getCardInUse() != null && card.equals(gm.getArrayPlayers()[i].getCardInUse())) {
                if (!notOtherCards(messageReceived)) {
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(ASSISTANT_CARD_TAKEN.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).selectAssistantCard(availableAssistantCards);
                    return;
                } else {
                    break;
                }
            }
        }

        try {
            gm.getPlayerByNickname(messageReceived.getNickname()).useCard(card);
        } catch (CardNotInDeckException e) {
            e.printStackTrace();
            tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(ASSISTANT_CARD_NOT_AVAILABLE.toString());
            tc.getVirtualViewMap().get(messageReceived.getNickname()).selectAssistantCard(availableAssistantCards);
            return;
        }

        cardList.add(card);
        availableAssistantCards.remove(card);
        selected++;

        if(selected == gm.getNumPlayers()){
            selected = 0;

            UpdateRanking();

            resetCards();

            tc.sendAllModel();

            List<String> availableActions = new ArrayList<>();
            availableActions.add("Move students to dining hall or to island");
            if (gm.isExpertMode()) {
                availableActions.add("Select character card");
            }
            if(gm.getChat() != null && gm.getChat()) {
                availableActions.add("Send a message to your team mate");
                availableActions.add("See received messages");
            }

            setPosition(0);
            gm.setCurrentPlayer(ranking[0]);
            tc.next_State(ACTION);

            persistence.saveData(tc);

            for (int i=0; i<gm.getNumPlayers(); i++){
                if (!gm.getArrayPlayers()[i].equals(gm.getCurrentPlayer())){
                    tc.getVirtualViewMap().get(gm.getArrayPlayers()[i].getNickname()).goToWaitingRoom();
                }
            }

            tc.getVirtualViewMap().get(gm.getArrayPlayers()[ranking[0]].getNickname()).actionPhase(availableActions);
        }
        else {
            tc.getVirtualViewMap().get(messageReceived.getNickname()).waitForOthersMoves("assistant card");

            int i;
            for (i=0; i<gm.getNumPlayers(); i++){
                if (gm.getArrayPlayers()[i].getNickname().equals(messageReceived.getNickname())){
                    break;
                }
            }
            tc.getVirtualViewMap().get(gm.getArrayPlayers()[(i + 1)% gm.getNumPlayers()].getNickname()).selectAssistantCard(availableAssistantCards);
        }
    }

    /**
     * Updates the ranking of the planning phase
     */
    private void UpdateRanking(){
        ranking = createRanking(cardList, gm.getNumPlayers());
        firstPlanner = ranking[0];
    }

    /**
     * Creates the ranking of the planning phase
     *
     * @param cardList parameters on which the ranking is based on
     * @param numPlayers the number of players
     */
    private int[] createRanking (List<Card> cardList, int numPlayers){

        Map<Card, Integer> map = new HashMap<>();

        Card[] cardArray = cardList.toArray(new Card[cardList.size()]);

        for (int i = 0; i < numPlayers; i++) {
            map.put(cardArray[i], i);
        }

        int j;
        Card tmp;

        for (int i = 0; i < numPlayers; i++) {
            j = i;
            for (int k = i + 1; k < numPlayers; k++) {
                if (cardArray[k].getValue() < cardArray[j].getValue()) {
                    j = k;
                }
            }
            tmp = cardArray[j];
            cardArray[j] = cardArray[i];
            cardArray[i] = tmp;
        }

        List<Card> duplicates = null;

        for (int i = 0; i < numPlayers - 1; i++) {
            if (cardArray[i].getValue() == cardArray[i + 1].getValue()) {
                if (duplicates == null) {
                    duplicates = new ArrayList<>();
                }
                duplicates.add(cardArray[i]);
            }
        }

        if (duplicates != null) {
            duplicates = duplicates.stream().distinct().collect(Collectors.toList());
        }

        int[] ranking = new int[numPlayers];

        if (duplicates == null) {
            for (int i = 0; i < numPlayers; i++) {
                ranking[i] = (map.get(cardArray[i]) + firstPlanner) % numPlayers;
            }
        } else {
            for (int i = 0; i < numPlayers; i++) {
                Card same = null;
                for (Card duplicate : duplicates) {
                    if (duplicate.equals(cardArray[i])) {
                        same = cardArray[i];
                        break;
                    }
                }

                if (same == null) {
                    ranking[i] = (map.get(cardArray[i]) + firstPlanner) % numPlayers;
                } else {
                    int times = 0;
                    for (int q = i; q < numPlayers; q++) {
                        if (cardArray[q].equals(cardArray[i])) {
                            times++;
                        }
                    }
                    for (int q = i; q < i + times; q++) {
                        int position = cardList.indexOf(cardArray[q]);
                        cardList.set(position, null);
                        ranking[q] = (position + firstPlanner) % numPlayers;
                    }
                    i = i + times - 1;
                }
            }
        }
        return(ranking);
    }

    public int[] getRanking(){
        return ranking;
    }

    public String toString(){
        return "Planning Controller";
    }

    public void setGameModel(GameModel gm){
        this.gm = gm;
    }

    /**
     * Checks if the player has other cards to play
     *
     * @param messageReceived the message from the client
     */
    private boolean notOtherCards(CommandMessage messageReceived){
        Set<Card> result = availableAssistantCards.stream().distinct().filter(gm.getPlayerByNickname(messageReceived.getNickname()).getDeck()::contains).collect(Collectors.toSet());
        return result.isEmpty();
    }

    /**
     * Resets the available actions list
     */
    public void resetCards(){
        this.availableAssistantCards.clear();
        availableAssistantCards.addAll(Arrays.asList(Card.values()));
        this.cardList.clear();
    }

    public int getFirstPlanner(){
        return firstPlanner;
    }

    public void setPosition(int value){
        position = value;
    }

    public int getPosition(){
        return position;
    }

    public int getSelected(){
        return selected;
    }
}

