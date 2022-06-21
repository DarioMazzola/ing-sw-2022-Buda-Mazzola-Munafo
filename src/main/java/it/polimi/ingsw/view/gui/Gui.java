package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.*;
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
 * Gui class. It inherits from the UI interface and all its methods handle GUI scenes
 *
 * @author Dario Mazzola
 */
public class Gui extends ViewObservable implements UI {

    private ReducedGameModel gm;
    private String nickname;

    @Override
    public void createNewGame() {

        Platform.runLater(SceneController::hidePopUp);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "loginScene.fxml"));
    }

    @Override
    public void selectNumPlayers() {

        Platform.runLater(SceneController::hidePopUp);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectNumPlayerScene.fxml"));
    }

    @Override
    public void selectExpertMode() {

        Platform.runLater(SceneController::hidePopUp);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectExpertModeScene.fxml"));
    }

    @Override
    public void selectWizard(List<Wizard> availableWizards) {

        Platform.runLater(SceneController::hidePopUp);

        SelectWizardSceneController controller = new SelectWizardSceneController(availableWizards);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectWizardScene.fxml", controller));
    }

    @Override
    public void selectNickname() {

    }

    @Override
    public void selectChat() {
        Platform.runLater(SceneController::hidePopUp);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectChatScene.fxml"));
    }

    @Override
    public void selectTeam(String[] teamArray, String[] leaderArray) {
        Platform.runLater(SceneController::hidePopUp);

        SelectTeamController controller = new SelectTeamController(teamArray, leaderArray);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectTeamScene.fxml", controller));
    }

    @Override
    public void selectTowerColor(List<Color> availableColors) {

        Platform.runLater(SceneController::hidePopUp);

        SelectTowerColorSceneController controller = new SelectTowerColorSceneController(availableColors);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectTowerColorScene.fxml", controller));
    }

    @Override
    public void selectAssistantCard(List<Card> availableAssistantCard) {

        Platform.runLater(SceneController::hidePopUp);

        SelectAssistantCardSceneController controller = new SelectAssistantCardSceneController(gm, nickname, availableAssistantCard);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectAssistantCardScene.fxml", controller));
    }

    @Override
    public void actionPhase(List<String> availableActions) {
        Platform.runLater(SceneController::hidePopUp);

        ActionSceneController controller;

        Platform.runLater(SceneController::hidePopUp);
        if (!(SceneController.getActiveController() instanceof ActionSceneController)) {
            controller = new ActionSceneController(gm, nickname);
            Platform.runLater(() -> SceneController.changeRootPane(observers, "ActionScene.fxml", controller));
        } else {
            controller = (ActionSceneController) SceneController.getActiveController();
            controller.setGameModel(gm);
        }

        if (availableActions.contains("Move Mother Nature")) {
            Platform.runLater(controller::setMoveMother);
        }
        Platform.runLater(controller::initializeEvents);
    }

    @Override
    public void sendWinner(String winner) {

    }

    @Override
    public void selectCloud() {
        ActionSceneController controller;

        if (!(SceneController.getActiveController() instanceof ActionSceneController)) {
            controller = new ActionSceneController(gm, nickname);
            Platform.runLater(() -> SceneController.changeRootPane(observers, "ActionScene.fxml", controller));
        } else {
            controller = (ActionSceneController) SceneController.getActiveController();
        }

        Platform.runLater(controller::initializeClouds);
        Platform.runLater(controller::setCloudSelectable);
    }

    @Override
    public void goToWaitingRoom() {
        System.out.println("GoToWaitingRoom");

        ActionSceneController controller = new ActionSceneController(gm, nickname);
        Platform.runLater(() -> SceneController.changeRootPane(observers, "ActionScene.fxml", controller));


    }

    @Override
    public void goToLobby() {

        Platform.runLater(SceneController::hidePopUp);
        Platform.runLater(() -> SceneController.changeRootPane(observers, "LobbyScene.fxml"));
    }

    @Override
    public void waitForOthersMoves(String move) {

        Platform.runLater(SceneController::hidePopUp);
        WaitForOthersMoveSceneController controller = new WaitForOthersMoveSceneController(move);
        Platform.runLater(() -> SceneController.displayPopUp("WaitForOthersMoveScene.fxml", controller));
    }

    @Override
    public void selectRestoreGame() {

        Platform.runLater(() -> SceneController.displayPopUp(observers, "SelectRestoreGameScene.fxml"));
    }

    @Override
    public void rememberNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void onChatMessageReceived(String message) {
        ActionSceneController controller;
        if (SceneController.getActiveController() instanceof ActionSceneController) {
            controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(() -> controller.updateChat(message));
        }
    }

    @Override
    public void endGameDisconnection(String errorCause) {

    }

    @Override
    public void updateIslands(List<ReducedIsland> islands) {
        gm.setIslandList(islands);

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(controller::updateIslands);
        }
    }

    @Override
    public void updatePlayer(ReducedPlayer player) {
        int index = Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getPlayerByNickname(player.getNickname()));
        gm.setPlayer(index, player);

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(() -> controller.updatePlayer(player));
        }
    }

    @Override
    public void updateClouds(ReducedCloud[] clouds) {
        gm.setArrayClouds(clouds);

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(controller::updateClouds);
        }
    }

    @Override
    public void updateTotalCoins(int totCoins) {
        gm.setTotalCoins(totCoins);
        // totCoins not shown in ActionScene, no update needed
    }

    @Override
    public void updateMotherNature(int motherIsland) {
        gm.setMotherIsland(motherIsland);

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(controller::updateMotherNature);
        }
    }

    @Override
    public void updateCurrentPlayer(ReducedPlayer currentPlayer) {
        gm.setCurrentPlayer(currentPlayer);

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(() -> controller.updateCurrentPlayer(currentPlayer));
        }
    }

    @Override
    public void updateDiningHall(ReducedDiningHall diningHall) {
        gm.getPlayerByNickname(diningHall.getNickname()).getDashboard().setDiningHall(diningHall);

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(() -> controller.updateDiningHall(diningHall));
        }
    }

    @Override
    public void updateDashboard(ReducedDashboard dashboard) {
        gm.getPlayerByNickname(dashboard.getNickname()).setDashboard(dashboard);

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(() -> controller.updateDashboard(dashboard));
        }
    }

    @Override
    public void updateGameModel(ReducedGameModel gameModel) {
        this.gm = gameModel;

        updateActionSceneGM();

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) SceneController.getActiveController();
            Platform.runLater(controller::initialize);
        }
    }

    @Override
    public void setStop(boolean stop) {

    }

    @Override
    public void showError(String errorMsg) {
        Platform.runLater(() -> SceneController.displayError(errorMsg));
    }

    @Override
    public void notifyGameFull() {

    }

    private void updateActionSceneGM() {
        ActionSceneController controller;

        if (SceneController.getActiveController() instanceof ActionSceneController) {
            controller = (ActionSceneController) SceneController.getActiveController();
            controller.setGameModel(gm);
        }
    }
}
