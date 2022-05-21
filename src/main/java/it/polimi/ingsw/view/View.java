package it.polimi.ingsw.view;

import it.polimi.ingsw.utils.TeamInfo;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Wizard;

import java.util.List;

/**
 * Defines a generic view to be implemented by each view type (e.g. CLI, GUI in JavaFX, ...).
 *
 * @author Dario Mazzola
 */
public interface View {
    /**
     * Shows an error message received from the server to the player.
     *
     * @param errorMsg the message to show
     */
    void showError(String errorMsg);

    /**
     * Notifies that the maximum number of players has been reached and the player will be disconnected.
     */
    void notifyGameFull();

    /**
     * Asks a nickname to the player to create a new game and notifies the observers.
     */
    void createNewGame();

    /**
     * Asks a nickname to the player and notifies the observers.
     */
    void selectNickname();

    /**
     * Asks the desired number of players for this game and notifies the observers.
     */
    void selectNumPlayers();

    /**
     * Asks the player whether he/she wants to play in expert mode and notifies the observers.
     */
    void selectExpertMode();

    /**
     * Asks the player to choose a wizard among the ones not selected by other players and notifies the observers.
     *
     * @param availableWizards a list of the available wizards
     */
    void selectWizard(List<Wizard> availableWizards);

    /**
     * Shows the player already created team and asks to choose a team and whether to be team leader and notifies the observers.
     */
    void showTeamSelection(List<TeamInfo> teams);

    /**
     * Asks the player to choose a color for his/hers towers among the ones still available and notifies the observers.
     *
     * @param availableColors a list of all the tower colors still available
     */
    void selectTowerColor(List<Color> availableColors);
}
