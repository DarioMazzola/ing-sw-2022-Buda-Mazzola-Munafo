package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents the ReducedGameModel
 * @author Gabriele Munafo'
 */
public class ReducedGameModel {

    private int motherIsland;
    private List<ReducedIsland> islandList;
    private final ReducedCharacterCard[] characterCardDeck;
    private final ReducedPlayer[] arrayPlayers;
    private ReducedCloud[] arrayClouds;
    private ReducedPlayer currentPlayer;
    private final int numPlayers;
    private int totalCoins;

    public ReducedGameModel(GameModel gm){
        totalCoins = gm.getTotalCoins();

        motherIsland = gm.getMotherIsland();

        numPlayers = gm.getNumPlayers();

        islandList = new ArrayList<>();
        for (Island i : gm.getIslandList()){
            ReducedIsland island = new ReducedIsland(i);
            islandList.add(island);
        }

        arrayPlayers = new ReducedPlayer[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayPlayers[i] = new ReducedPlayer(gm.getArrayPlayers()[i]);
        }

        arrayClouds = new ReducedCloud[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayClouds[i]  = new ReducedCloud(gm.getArrayClouds()[i]);
        }

        currentPlayer = new ReducedPlayer(gm.getCurrentPlayer());

        if (gm.isExpertMode()){
            characterCardDeck = new ReducedCharacterCard[3];
            characterCardDeck[0] = new ReducedCharacterCard(gm.getCharacterCardDeck()[0]);
            characterCardDeck[1] = new ReducedCharacterCard(gm.getCharacterCardDeck()[1]);
            characterCardDeck[2] = new ReducedCharacterCard(gm.getCharacterCardDeck()[2]);
        }
        else {
            characterCardDeck = null;
        }
    }

    public void setMotherIsland(int value){
        motherIsland = value;
    }

    public void setPlayer (int position, ReducedPlayer p){
        arrayPlayers[position] = p;
    }

    public void setIslandList (List<ReducedIsland> islands){
        islandList = islands;
    }

    public void setCharacterCard (int position, ReducedCharacterCard c){
        characterCardDeck[position] = c;
    }

    public void setArrayClouds(ReducedCloud[] clouds){
        arrayClouds = clouds;
    }

    public int getMotherIsland(){
        return motherIsland;
    }

    public List<ReducedIsland> getIslandList(){
        return islandList;
    }

    public ReducedPlayer[] getArrayPlayers(){
        return arrayPlayers;
    }

    public ReducedCloud[] getArrayClouds(){
        return arrayClouds;
    }

    public ReducedCharacterCard[] getCharacterCardDeck(){
        return characterCardDeck;
    }

    public ReducedPlayer getCurrentPlayer(){
        return currentPlayer;
    }

    public ReducedPlayer getPlayerByNickname(String nickname){
        return Arrays.stream(getArrayPlayers()).
                filter((x)-> x.getNickname().equals(nickname)).
                findFirst().orElse(null);
    }

    public int getNumPlayers(){
        return numPlayers;
    }

    public void setCurrentPlayer(ReducedPlayer p){
        currentPlayer = p;
    }

    public int getTotalCoins(){
        return totalCoins;
    }

    public void setTotalCoins(int value){
        totalCoins = value;
    }
}