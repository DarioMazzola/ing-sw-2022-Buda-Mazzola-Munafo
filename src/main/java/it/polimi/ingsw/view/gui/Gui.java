package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.*;
import it.polimi.ingsw.messages.TypeOfError;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.UI;
import it.polimi.ingsw.view.gui.scenes.*;
import javafx.application.Platform;

import java.util.Arrays;
import java.util.List;

/**
 * Gui class. It inherits from the UI interface and all its methods handle GUI scenes.
 * Gui is made up of many scenes that are changed and updated by messages arriving from the server.
 *
 * @author Dario Mazzola & Alessio Buda
 */
public class Gui extends ViewObservable implements UI {

    private ReducedGameModel gm;
    private String nickname;
    private final SceneController sceneController;

    public Gui() {
        this.sceneController = new SceneController();
    }

    /**
     * Shows the player the scene to log into the game.
     */
    @Override
    public void createNewGame() {

        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "loginScene.fxml"));
    }

    /**
     * Show the player the scene to select the number of players in the game.
     */
    @Override
    public void selectNumPlayers() {

        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectNumPlayerScene.fxml"));
    }

    /**
     * shows the player the scene to select whether to play expert mode or not.
     */
    @Override
    public void selectExpertMode() {

        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectExpertModeScene.fxml"));
    }

    /**
     * Shows the player the scene to select the wizard that identifies him for this game
     *
     * @param availableWizards a list of the available wizards
     */
    @Override
    public void selectWizard(List<Wizard> availableWizards) {

        Platform.runLater(sceneController::hidePopUp);

        SelectWizardSceneController controller = new SelectWizardSceneController(availableWizards);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectWizardScene.fxml", controller));
    }

    @Override
    public void selectNickname() {

    }

    /**
     * Shows the player the scene to select whether to give the possibility to use the chat or not.
     */
    @Override
    public void selectChat() {
        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectChatScene.fxml"));
    }

    /**
     * Shows the player, if in 4 players, the scene to select which team to take part in
     *
     * @param teamArray   players who have already made their choice
     * @param leaderArray players who want to be team leaders
     */
    @Override
    public void selectTeam(String[] teamArray, String[] leaderArray) {
        Platform.runLater(sceneController::hidePopUp);

        SelectTeamController controller = new SelectTeamController(teamArray, leaderArray);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectTeamScene.fxml", controller));
    }

    /**
     * Shows the player the scene to choose the color of the towers on his dashboard
     *
     * @param availableColors a list of all the tower colors still available
     */
    @Override
    public void selectTowerColor(List<Color> availableColors) {

        Platform.runLater(sceneController::hidePopUp);

        SelectTowerColorSceneController controller = new SelectTowerColorSceneController(availableColors);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectTowerColorScene.fxml", controller));
    }

    /**
     * Shows the player the scene to choose the assistant card for the next game turn.
     *
     * @param availableAssistantCard the assistant card available
     */
    @Override
    public void selectAssistantCard(List<Card> availableAssistantCard) {

        Platform.runLater(sceneController::hidePopUp);

        SelectAssistantCardSceneController controller = new SelectAssistantCardSceneController(gm, nickname, availableAssistantCard);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectAssistantCardScene.fxml", controller));
    }

    /**
     * Shows the player the main scene. If the player was already in the action phase,
     * the GUI controller does not create the scene from scratch but updates it.
     *
     * @param availableActions all the available actions
     */
    @Override
    public void actionPhase(List<String> availableActions) {
        Platform.runLater(sceneController::hidePopUp);

        ActionSceneController controller;

        Platform.runLater(sceneController::hidePopUp);
        if (!(sceneController.getActiveController() instanceof ActionSceneController)) {
            controller = new ActionSceneController(gm, nickname);
            Platform.runLater(() -> sceneController.changeRootPane(observers, "ActionScene.fxml", controller));
        } else {
            controller = (ActionSceneController) sceneController.getActiveController();
            controller.setGameModel(gm);
        }

        Platform.runLater(() -> controller.setSuggestions(availableActions, "actionPhase"));

        if (availableActions.contains("Move Mother Nature")) {
            Platform.runLater(controller::setMoveMother);
        }
        Platform.runLater(controller::initializeEvents);
    }

    /**
     * Show the player a PopUp to notify that the player passed as parameter won the match.
     * The player will be asked if he wants to continue playing or stop the game.
     *
     * @param winner the player who won the match
     */
    @Override
    public void sendWinner(String winner) {
        if (gm.getNumPlayers() == 4) {
            String winnerTeamMate = gm.getTeamMate(winner);
            winner += " and " + winnerTeamMate;
        }
        Platform.runLater(sceneController::hidePopUp);
        WinnerSceneController controller = new WinnerSceneController(winner, nickname);
        Platform.runLater(() -> sceneController.displayPopUp(observers, "WinnerScene.fxml", controller));
    }

    /**
     * Shows the player the ability to choose the clouds.
     */
    @Override
    public void selectCloud() {
        ActionSceneController controller;

        if (!(sceneController.getActiveController() instanceof ActionSceneController)) {
            controller = new ActionSceneController(gm, nickname);
            Platform.runLater(() -> sceneController.changeRootPane(observers, "ActionScene.fxml", controller));
        } else {
            controller = (ActionSceneController) sceneController.getActiveController();
        }

        Platform.runLater(() -> controller.setSuggestions(null, "selectCloud"));

        Platform.runLater(controller::initializeClouds);
        Platform.runLater(controller::setCloudSelectable);
    }

    /**
     * Notifies the player that his turn has ended and that another player is playing. The player will be
     * taken to a waiting room where he can continue to observe the progress of the game without being able to
     * interact with the board.
     */
    @Override
    public void goToWaitingRoom() {

        Platform.runLater(sceneController::hidePopUp);

        ActionSceneController controller = new ActionSceneController(gm, nickname);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "ActionScene.fxml", controller));

        Platform.runLater(() -> controller.setSuggestions(null, ""));
    }

    /**
     * Notifies the player that they have successfully logged into the game but that the server
     * is waiting for other players to connect before starting.
     */
    @Override
    public void goToLobby() {

        Platform.runLater(sceneController::hidePopUp);
        Platform.runLater(() -> sceneController.changeRootPane(observers, "LobbyScene.fxml"));
    }

    /**
     * Notifies the player that he has made his choice correctly and is waiting for another player to choose
     * the color of the towers, the assistant card or the wizard.
     *
     * @param move the choice that the player is waiting for
     */
    @Override
    public void waitForOthersMoves(String move) {

        Platform.runLater(sceneController::hidePopUp);
        WaitForOthersMoveSceneController controller = new WaitForOthersMoveSceneController(move);
        Platform.runLater(() -> sceneController.displayPopUp("WaitForOthersMoveScene.fxml", controller));
    }

    /**
     * Notifies to the player that he/she has to decide whether to resume a saved game or start a new one.
     */
    @Override
    public void selectRestoreGame() {

        Platform.runLater(() -> sceneController.displayPopUp(observers, "SelectRestoreGameScene.fxml"));
    }

    /**
     * Remembers the nickname to the player after a disconnection.
     *
     * @param nickname the nickname of the player
     */
    @Override
    public void rememberNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Updates the chat based on the message received from the teammate.
     *
     * @param message the message received from the teammate
     */
    @Override
    public void onChatMessageReceived(String message) {
        ActionSceneController controller;
        if (sceneController.getActiveController() instanceof ActionSceneController) {
            controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateChat(message));
        }
    }

    /**
     * Shows to the player the alert that a player has logged out.
     *
     * @param errorCause the alert cause, the player who disconnected.
     */
    @Override
    public void endGameDisconnection(String errorCause) {
        Platform.runLater(() -> sceneController.displayError(errorCause, observers));
    }

    @Override
    public void updateIslands(List<ReducedIsland> islands) {
        gm.setIslandList(islands);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(controller::updateIslands);
        }
    }

    /**
     * Updates the player received as a parameters and modifies its representation in current scene.
     *
     * @param player the updated version of the player
     */
    @Override
    public void updatePlayer(ReducedPlayer player) {
        int index = Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getPlayerByNickname(player.getNickname()));
        gm.setPlayer(index, player);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updatePlayer(player));
        }
    }

    /**
     * Updates the clouds and modifies their representation in current scene.
     *
     * @param clouds the updated version of the array of clouds
     */
    @Override
    public void updateClouds(ReducedCloud[] clouds) {
        gm.setArrayClouds(clouds);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(controller::updateClouds);
        }
    }

    /**
     * Updates the number of coins in the Game Model
     *
     * @param totCoins the updated number of coins
     */
    @Override
    public void updateTotalCoins(int totCoins) {
        gm.setTotalCoins(totCoins);
        // totCoins not shown in ActionScene, no update needed
    }

    /**
     * Updates mother nature and modifies its representation in current scene.
     *
     * @param motherIsland the updated island on which mother nature is located
     */
    @Override
    public void updateMotherNature(int motherIsland) {
        gm.setMotherIsland(motherIsland);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(controller::updateMotherNature);
        }
    }

    /**
     * Updates the player received as a parameters and modifies its representation in current scene.
     *
     * @param currentPlayer the updated current player
     */
    @Override
    public void updateCurrentPlayer(ReducedPlayer currentPlayer) {
        gm.setCurrentPlayer(currentPlayer);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateCurrentPlayer(currentPlayer));
        }
    }

    /**
     * Updates the diningHall received as a parameters and modifies its representation in current scene.
     *
     * @param diningHall the updated version of the dining hall
     */
    @Override
    public void updateDiningHall(ReducedDiningHall diningHall) {
        gm.getPlayerByNickname(diningHall.getNickname()).getDashboard().setDiningHall(diningHall);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateDiningHall(diningHall));
        }
    }

    /**
     * Updates the dashboard received as a parameters and modifies its representation in current scene.
     *
     * @param dashboard the updated version of the dining hall
     */
    @Override
    public void updateDashboard(ReducedDashboard dashboard) {
        gm.getPlayerByNickname(dashboard.getNickname()).setDashboard(dashboard);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateDashboard(dashboard));
        }
    }

    /**
     * Updates the game model received as a parameters and modifies its representation in current scene.
     *
     * @param gameModel the updated version of the game model
     */
    @Override
    public void updateGameModel(ReducedGameModel gameModel) {
        this.gm = gameModel;

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(controller::initialize);
        }
    }

    @Override
    public void setStop(boolean stop) {

    }

    /**
     * Shows an alert to the client with the error message specified as a parameter.
     *
     * @param errorMsg the message to show
     */
    @Override
    public void showError(String errorMsg) {
        Platform.runLater(() -> sceneController.displayError(errorMsg, observers));
    }

    /**
     * Shows a warning to the client saying that the game is already full and the player cannot join the game.
     */
    @Override
    public void notifyGameFull() {
        Platform.runLater(() -> sceneController.displayError(TypeOfError.GAME_FULL.toString(), observers));
    }

    /**
     * Updates game model in the actionScene.
     */
    private void updateActionSceneGM() {
        ActionSceneController controller;

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            controller = (ActionSceneController) sceneController.getActiveController();
            controller.setGameModel(gm);
        }
    }
}
