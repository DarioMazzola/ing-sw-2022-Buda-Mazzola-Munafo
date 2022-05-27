package it.polimi.ingsw.model;

import it.polimi.ingsw.client.ReducedPlayer;
import it.polimi.ingsw.exceptions.CardNotInDeckException;
import it.polimi.ingsw.messages.answer.UpdatePlayer;
import it.polimi.ingsw.observer.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Player class represents the player.
 *
 * @author Alessio Buda
 */
public class Player extends Observable {
    private String nickname;
    private Wizard wizard;
    private Boolean teamLeader;
    private final List<Card> deck;
    private Card cardInUse;
    private int maxMoves;
    private Card graveyard;
    private int coins;
    private Dashboard dashboard;
    private final int numPlayers;

    /**
     * Class constructor, initializes Player with the standard number of coins.
     * To set nickname, wizard and teamLeader attributes use the correspondent setter methods.
     */
    public Player(int numPlayers) {
        this.nickname = null;
        this.wizard = null;
        this.teamLeader = null;
        this.cardInUse = null;
        this.maxMoves = 0;
        this.graveyard = null;
        this.dashboard = null;

        this.deck = new ArrayList<>();
        this.deck.addAll(Arrays.asList(Card.values()));
        this.coins = 1;
        if (numPlayers<2 || numPlayers>4)
            throw new IllegalArgumentException("Number of players must between 2 and 4");
        this.numPlayers = numPlayers;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    /**
     * Sets nickname with the given nickname only if it hasn't been already set,
     * in this case player's nickname is not changed.
     *
     * @param nickname player's nickname
     */
    public void setNickname(String nickname) {
        if (nickname == null)
            throw new NullPointerException("Player nickname cannot be null");
        this.nickname = this.nickname == null ? nickname : this.nickname;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    public String getNickname() {
        return nickname;
    }

    /**
     * Sets wizard with the given wizard only if it hasn't been already set,
     * in this case player's wizard is not changed.
     *
     * @param wizard player's wizard
     */
    public void setWizard(Wizard wizard) {
        if (wizard == null)
            throw new NullPointerException("Player wizard cannot be null");
        this.wizard = this.wizard == null ? wizard : this.wizard;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    public Wizard getWizard() {
        return wizard;
    }

    /**
     * Sets teamLeader attribute with the given value only if it hasn't been already set.
     * In this case teamLeader attribute is not changed.
     *
     * @param teamLeader indicates whether the player is teamLeader
     */
    public void setTeamLeader(boolean teamLeader) {
        this.teamLeader = this.teamLeader == null ? teamLeader : this.teamLeader;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    public Boolean isTeamLeader() {
        return teamLeader;
    }

    public List<Card> getDeck() {
        return deck;
    }

    /**
     * Returns number of cards in deck.
     *
     * @return number of cards in deck
     */
    public int getNumCards() {
        return deck.size();
    }


    /**
     * Removes card from deck.
     * Updates cardInUse with the given card and maxMoves with the number of moves associated to the card.
     *
     * @param card card to be removed from deck
     * @throws CardNotInDeckException when the card to be used is not in deck
     */
    public void useCard (Card card) throws CardNotInDeckException {
        if (card == null) {
            throw new NullPointerException("Card cannot be null");
        }
        if (deck.contains(card)) {
            deck.remove(card);
            cardInUse = card;
            maxMoves = card.getMoves();
        } else {
            throw new CardNotInDeckException();
        }

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    public Card getCardInUse() {
        return cardInUse;
    }

    public void setMaxMoves(int maxMoves) {
        this.maxMoves = maxMoves;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public void setGraveyard() {
        this.graveyard = cardInUse;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    public Card getGraveyard() {
        return graveyard;
    }

    public int getCoins() {
        return coins;
    }

    /** Increments the player's number of coins
     *
     * @param numCoins number of coins added to the player's coins
     */
    public void addCoins(int numCoins) {
        if (numCoins < 0) {
            throw new IllegalArgumentException("Number of coins cannot be negative");
        }
        coins+= numCoins;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    /** Decrements the player's number of coins
     *
     * @param numCoins number of coins removed from the player's coins
     */
    public void removeCoins(int numCoins) {
        if (numCoins < 0) {
            throw new IllegalArgumentException("Number of coins cannot be negative");
        }
        coins-= numCoins;

        notifyObserver(new UpdatePlayer(new ReducedPlayer(this)));
    }

    /**Sets player's dashboard
     *
     * @param towerColor player's tower color
     */
    public void setDashboard(Color towerColor, String nickname) {
        if (this.dashboard != null) {
            return;
        }
        int numMaxStudents;
        int numMaxTowers;
        switch (numPlayers) {
            case 2: { numMaxStudents = 7;
                    numMaxTowers = 8;
                    break; }
            case 3: { numMaxStudents = 9;
                    numMaxTowers = 6;
                    break; }
            case 4: { numMaxStudents = 7;
                    if (teamLeader)
                        numMaxTowers = 8;
                    else
                        numMaxTowers = 0;
                    break; }
            default: { numMaxStudents = 0;
                     numMaxTowers = 0; }
        }

        this.dashboard = new Dashboard(towerColor, numMaxStudents, numMaxTowers, nickname);
    }

    public Dashboard getDashboard () {
        return dashboard;
    }

}
