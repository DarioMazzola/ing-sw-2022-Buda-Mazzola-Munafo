package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Card;
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
     * Asks a nickname to the player and notifies the observers.
     */
    void selectNickname();

    /**
     * Asks the desired number of players for this game and notifies the observers.
     */
    void selectNumPlayers();

    /**
     * Asks the player whether he wants team members to communicate with each other and notifies the observers.
     */
    void selectChat ();

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
    void selectTeam(String[] teamArray, String[] leaderArray);

    /**
     * Asks the player to choose a color for his/hers towers among the ones still available and notifies the observers.
     *
     * @param availableColors a list of all the tower colors still available
     */
    void selectTowerColor(List<Color> availableColors);

    /**
     * Asks the player to choose the assistant among the ones still available and notifies the observers.
     *
     * @param availableAssistantCard the assistant card available
     */
    void selectAssistantCard(List<Card> availableAssistantCard);

    /**
     * Shows all the available actions that the player can do.
     *
     * @param availableActions all the available actions
     */
    void actionPhase(List<String> availableActions);

    /**
     * Shows the player who won the match
     *
     * @param winner the player who won the match
     */
    void sendWinner(String winner);

    /**
     * Asks the player to choose a cloud among the ones still available and notifies the observers.
     *
     */
    void selectCloud();

    /**
     * Puts the player in the waiting room.
     */
    void goToWaitingRoom();

    /**
     * Puts the player in the lobby.
     */
    void goToLobby();

    /**
     * Asks the player whether he/she wants to restore a previously saved game.
     */
    void selectRestoreGame();

    void rememberNickname(String nickname);

    void onMessageReceived(String message);
}
