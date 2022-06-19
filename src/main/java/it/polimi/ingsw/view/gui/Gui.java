package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.*;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.UI;
import it.polimi.ingsw.view.gui.scenes.*;
import javafx.application.Platform;

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
        Platform.runLater(() -> SceneController.changeRootPane(observers, "loginScene.fxml"));
    }

    @Override
    public void selectNumPlayers() {
        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectNumPlayerScene.fxml"));
    }

    @Override
    public void selectExpertMode() {
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

    }

    @Override
    public void selectTeam(String[] teamArray, String[] leaderArray) {

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

        ActionSceneController controller;

        if (!(SceneController.getActiveController() instanceof ActionSceneController)) {
            controller = new ActionSceneController(gm, nickname);
            Platform.runLater(() -> SceneController.changeRootPane(observers, "ActionScene.fxml", controller));
        } else {
            controller = (ActionSceneController) SceneController.getActiveController();
            controller.setGm(gm);

            if (availableActions.contains("Move Mother Nature")) {
                controller.setMoveMother();
            }
            Platform.runLater(controller::initialize);
        }
    }

    @Override
    public void sendWinner(String winner) {

    }

    @Override
    public void selectCloud() {

    }

    @Override
    public void goToWaitingRoom() {
        System.out.println("GoToWaitingRoom");
    }

    @Override
    public void goToLobby() {
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
        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectRestoreGameScene.fxml"));
    }

    @Override
    public void rememberNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void onChatMessageReceived(String message) {

    }

    @Override
    public void endGameDisconnection(String errorCause) {

    }

    @Override
    public void updateIslands(List<ReducedIsland> islands) {
        gm.setIslandList(islands);
    }

    @Override
    public void updatePlayer(ReducedPlayer player) {

    }

    @Override
    public void updateClouds(ReducedCloud[] clouds) {

    }

    @Override
    public void updateTotalCoins(int totCoins) {

    }

    @Override
    public void updateMotherNature(int motherIsland) {

    }

    @Override
    public void updateCurrentPlayer(ReducedPlayer currentPlayer) {

    }

    @Override
    public void updateDiningHall(ReducedDiningHall diningHall) {

    }

    @Override
    public void updateDashboard(ReducedDashboard dashboard) {

    }

    @Override
    public void updateGameModel(ReducedGameModel gameModel) {
        this.gm = gameModel;
    }

    @Override
    public void setStop(boolean stop) {

    }

    @Override
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    @Override
    public void notifyGameFull() {

    }
}
