package it.polimi.ingsw.view;

import it.polimi.ingsw.client.*;

import java.util.List;

public interface UI extends View{

    /**
     * Asks a nickname to the player to create a new game and notifies the observers.
     */
    void createNewGame();

    /**
     * Updates the list of islands in the game model according to the changes communicated by the server.
     *
     * @param islands a list of updated islands
     */
    void updateIslands(List<ReducedIsland> islands);

    /**
     * Updates the given player with an updated version.
     *
     * @param player the updated version of the player
     */
    void updatePlayer(ReducedPlayer player);

    /**
     * Updates the array of clouds in the game model with an updated version.
     *
     * @param clouds the updated version of the array of clouds
     */
    void updateClouds(ReducedCloud[] clouds);

    /**
     * Updates the total number of coins in the game model.
     *
     * @param totCoins the updated number of coins
     */
    void updateTotalCoins(int totCoins);

    /**
     * Updates the position of mother nature.
     *
     * @param motherIsland the updated island on which mother nature is located
     */
    void updateMotherNature(int motherIsland);

    /**
     * Updates current player in game model.
     *
     * @param currentPlayer the updated current player
     */
    void updateCurrentPlayer(ReducedPlayer currentPlayer);

    /**
     * Updates the dining hall of the player identified by the nickname (attribute of dining hall).
     *
     * @param diningHall the updated version of the dining hall
     */
    void updateDiningHall(ReducedDiningHall diningHall);

    /**
     * Updates the dashboard of the player identified by the nickname (attribute of dashboard).
     *
     * @param dashboard the updated version of the dining hall
     */
    void updateDashboard(ReducedDashboard dashboard);

    /**
     * Updates the game model.
     *
     * @param gameModel the updated version of the game model
     */
    void updateGameModel(ReducedGameModel gameModel);

    /**
     * Sets player's nickname in Cli or Gui
     * 
     * @param nickname the nickname of the player
     */
    void setNickname(String nickname);
}
