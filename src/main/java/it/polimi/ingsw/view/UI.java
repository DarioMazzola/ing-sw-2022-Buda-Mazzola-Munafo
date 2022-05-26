package it.polimi.ingsw.view;

import it.polimi.ingsw.client.ReducedCharacterCard;
import it.polimi.ingsw.client.ReducedCloud;
import it.polimi.ingsw.client.ReducedIsland;
import it.polimi.ingsw.client.ReducedPlayer;

import java.util.Arrays;
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
     * Updates the given character card with an updated version.
     *
     * @param characterCard the updated version of the character card
     */
    void updateCharacterCard(ReducedCharacterCard characterCard);

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
    void updateCloud (ReducedCloud[] clouds);

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
     * @param player the updated current player
     */
    void updateCurrentPlayer(ReducedPlayer player);
}
