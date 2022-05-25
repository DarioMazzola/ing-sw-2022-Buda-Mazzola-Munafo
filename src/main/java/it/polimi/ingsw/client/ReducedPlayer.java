package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the ReducedPlayer
 * @author Gabriele Munafo'
 */
public class ReducedPlayer {
    private final String nickname;
    private final Wizard wizard;
    private final Boolean teamLeader;
    private final List<Card> deck;
    private final Card cardInUse;
    private final int maxMoves;
    private final Card graveyard;
    private final int coins;
    private final ReducedDashboard dashboard;

    public ReducedPlayer(Player p){
        nickname = p.getNickname();

        wizard = p.getWizard();

        teamLeader = p.isTeamLeader();

        deck = new ArrayList<>();
        deck.addAll(p.getDeck());

        cardInUse = p.getCardInUse();

        maxMoves = p.getMaxMoves();

        graveyard = p.getGraveyard();

        coins = p.getCoins();

        dashboard = new ReducedDashboard(p.getDashboard());
    }

    public String getNickname() {
        return nickname;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public boolean isTeamLeader() {
        return teamLeader;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public int getNumCards() {
        return deck.size();
    }

    public Card getCardInUse() {
        return cardInUse;
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    public Card getGraveyard() {
        return graveyard;
    }

    public int getCoins() {
        return coins;
    }

    public ReducedDashboard getDashboard () {
        return dashboard;
    }
}
