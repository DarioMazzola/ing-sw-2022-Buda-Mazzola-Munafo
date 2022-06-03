package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.ReducedPlayer;
import it.polimi.ingsw.exceptions.CardNotInDeckException;
import it.polimi.ingsw.messages.answer.UpdateCurrentPlayer;
import it.polimi.ingsw.messages.command.ChosenAssistantCard;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.utils.Persistence;
import it.polimi.ingsw.view.VirtualView;

import java.util.*;
import java.util.stream.Collectors;

import static it.polimi.ingsw.controller.GameState.ACTION;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the planning controller
 * @author Gabriele Munafo'
 */
public class PlanningController {
    private int firstPlanner;
    private final List<Card> cardList;
    private int[] ranking;
    private GameModel gm;
    private final List<Card> availableAssistantCards;
    private int selected;
    private int position;
    private transient final Persistence persistence;

    public PlanningController (GameModel gm){
        persistence = new Persistence();
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
     * @param messageReceived received from the client
     */
    public void doAction(CommandMessage messageReceived, TurnController tc) {
        System.out.println(messageReceived.getNickname() + "is in planning state");

        Card card = ((ChosenAssistantCard) messageReceived).getAssistantCard();

        System.out.println(card.toString());

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

            List<String> availableActions = new ArrayList<>();
            availableActions.add("Move students to dining hall or to island");
            if (gm.isExpertMode()) {
                availableActions.add("Select character card");
            }
            setPosition(0);
            gm.setCurrentPlayer(ranking[0]);
            persistence.saveData(tc);
            tc.next_State(ACTION);

            for (int i=0; i<gm.getNumPlayers(); i++){
                if (!gm.getArrayPlayers()[i].equals(gm.getCurrentPlayer())){
                    tc.getVirtualViewMap().get(gm.getArrayPlayers()[i].getNickname()).goToWaitingRoom();
                }
            }

            tc.getVirtualViewMap().get(gm.getArrayPlayers()[ranking[0]].getNickname()).actionPhase(availableActions);
        }
        else {
            tc.getVirtualViewMap().get(messageReceived.getNickname()).goToWaitingRoom();

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
        for (int i=0; i< gm.getNumPlayers(); i++){
            gm.getArrayPlayers()[i].setGraveyard();
        }
    }

    /**
     * Creates the ranking of the planning phase
     * @param cardList parameters on which the ranking is based on
     * @param numPlayers the number of players
     */
    private int[] createRanking (List<Card> cardList, int numPlayers){ //todo non funziona????

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
     * @param messageReceived the message from the client
     */
    private boolean notOtherCards(CommandMessage messageReceived){
        Set<Card> result = availableAssistantCards.stream().distinct().filter(gm.getPlayerByNickname(messageReceived.getNickname()).getDeck()::contains).collect(Collectors.toSet());
        return result.isEmpty();
    }

    private void resetCards(){
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

