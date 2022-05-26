package it.polimi.ingsw.view;

public interface UI extends View{

    /**
     * Asks a nickname to the player to create a new game and notifies the observers.
     */
    void createNewGame();
}
