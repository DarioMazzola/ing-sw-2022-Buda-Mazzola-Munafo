package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.CardNotInDeckException;
import it.polimi.ingsw.messages.command.ChosenAssistantCard;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.GameModel;

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
    int[] ranking;
    private final GameModel gm;
    private final List<Card> availableAssistantCards;
    private final TurnController tc;
    private int selected;
    private int position;

    public PlanningController (GameModel gm, TurnController turnController){
        this.position = 0;
        this.selected = 0;
        this.firstPlanner = 0;
        this.gm = gm;
        this.tc = turnController;

        this.cardList = new ArrayList<>();

        this.availableAssistantCards = new ArrayList<>();
        availableAssistantCards.addAll(Arrays.asList(Card.values()));
    }

    /**
     * Gets called to manage the messages from the client
     * @param messageReceived received from the client
     */
    public void doAction(CommandMessage messageReceived) {
        System.out.println(messageReceived.getNickname() + "is in planning state");

        Card card = ((ChosenAssistantCard) messageReceived).getAssistantCard();

        for (int i = 0; i < gm.getNumPlayers(); i++) {
            if (gm.getArrayPlayers()[i].getCardInUse() != null && card.equals(gm.getArrayPlayers()[i].getCardInUse())) {
                if (!notOtherCards(messageReceived)) {
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(ASSISTANT_CARD_TAKEN.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).selectAssistantCard(availableAssistantCards);
                    return;
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
        position = 0;

        if(selected == gm.getNumPlayers()){
            selected = 0;
            UpdateRanking();
            resetCards();
            tc.next_State(ACTION);

            List<String> availableActions = new ArrayList<>();
            availableActions.add("Move");
            availableActions.add("SelectCharacterCard");

            tc.getVirtualViewMap().get(gm.getArrayPlayers()[ranking[position]].getNickname()).actionPhase(availableActions);
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
    private int[] createRanking (List<Card> cardList, int numPlayers){ //todo da ricontrollare

        Map<Card, Integer> map = new HashMap<>();

        Card[] cardArray = (Card[]) cardList.toArray();

        for (int i=0; i<numPlayers; i++){
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

        int[] ranking = new int[numPlayers];

        for (int i=0; i<numPlayers; i++){
            ranking[i] = (map.get(cardArray[i])+firstPlanner)%numPlayers;
        }
        return(ranking);
    }

    public int[] getRanking(){
        return ranking;
    }

    public String toString(){
        return "Planning Controller";
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
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int newValue){
        position = newValue;
    }

    public int getFirstPlanner(){
        return firstPlanner;
    }
}
