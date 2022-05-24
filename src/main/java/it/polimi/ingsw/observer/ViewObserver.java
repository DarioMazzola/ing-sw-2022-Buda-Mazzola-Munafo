package it.polimi.ingsw.observer;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Wizard;

/**
 * Class representing the observer for view elements.
 *
 * @author Dario Mazzola & Alessio Buda
 */

public interface ViewObserver {

    /**
     * Called when a client expresses an intention to connect to the server to play.
     *
     * @param ip   the IP address to which the client wants to connect
     * @param port the port number the client wants to connect to.
     */
    void onInit(String ip, int port);

    /**
     * Method that represents the start of a game.
     *
     * @param nickname the nickname chosen by the client in this game.
     */
    void onCreateNewGame(String nickname);

    /**
     * Method representing the client sending the nickname. This method is invoked if
     * the nickname, previously sent, is wrong (empty, already in use, ...).
     *
     * @param nickname the new nickname chosen by the client in this game.
     */
    void onUpdateNickname(String nickname);

    /**
     * Method that represents the sending, by the client who created the game first, of the number
     * of players who will be able to take part in the game.
     *
     * @param numPlayers the number of players in this game.
     */
    void onUpdateNumPlayers(int numPlayers);

    /**
     * Method that represents the sending of the wizard that the player wants to use in this game.
     *
     * @param wizard the wizard chosen by the player.
     */
    void onUpdateWizard(Wizard wizard);

    /**
     * Method invoked by the player who first created the game. Used for choosing
     * whether to play the game in expertMode or not.
     *
     * @param expertMode true if the game will be played in expert mode, false otherwise.
     */
    void onUpdateExpertMode(boolean expertMode);

    /**
     * Method used to specify which team the player wants to belong to and whether
     * the player wants to be the team leader or not,
     *
     * @param selectedTeam the team chosen.
     * @param isTeamLeader true if the player wants to be the team leader, false otherwise.
     */
    void onUpdateTeam(int selectedTeam, boolean isTeamLeader);

    /**
     * Method used to specify the tower color that the player wants to own in this game.
     *
     * @param towerColor the tower color chosen.
     */
    void onUpdateTowerColor(Color towerColor);
}