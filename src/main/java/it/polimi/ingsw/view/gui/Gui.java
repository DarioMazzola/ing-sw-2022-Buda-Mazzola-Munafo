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
 * Gui class. It inherits from the UI interface and all its methods handle GUI scenes
 *
 * @author Dario Mazzola
 */
public class Gui extends ViewObservable implements UI {

    private ReducedGameModel gm;
    private String nickname;
    private final SceneController sceneController;

    public Gui(){
        this.sceneController = new SceneController();
    }

    @Override
    public void createNewGame() {

        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "loginScene.fxml"));
    }

    @Override
    public void selectNumPlayers() {

        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectNumPlayerScene.fxml"));
    }

    @Override
    public void selectExpertMode() {

        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectExpertModeScene.fxml"));
    }

    @Override
    public void selectWizard(List<Wizard> availableWizards) {

        Platform.runLater(sceneController::hidePopUp);

        SelectWizardSceneController controller = new SelectWizardSceneController(availableWizards);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectWizardScene.fxml", controller));
    }

    @Override
    public void selectNickname() {

    }

    @Override
    public void selectChat() {
        Platform.runLater(sceneController::hidePopUp);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectChatScene.fxml"));
    }

    @Override
    public void selectTeam(String[] teamArray, String[] leaderArray) {
        Platform.runLater(sceneController::hidePopUp);

        SelectTeamController controller = new SelectTeamController(teamArray, leaderArray);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectTeamScene.fxml", controller));
    }

    @Override
    public void selectTowerColor(List<Color> availableColors) {

        Platform.runLater(sceneController::hidePopUp);

        SelectTowerColorSceneController controller = new SelectTowerColorSceneController(availableColors);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectTowerColorScene.fxml", controller));
    }

    @Override
    public void selectAssistantCard(List<Card> availableAssistantCard) {

        Platform.runLater(sceneController::hidePopUp);

        SelectAssistantCardSceneController controller = new SelectAssistantCardSceneController(gm, nickname, availableAssistantCard);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "SelectAssistantCardScene.fxml", controller));
    }

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

        Platform.runLater(()->controller.setSuggestions(availableActions, "actionPhase"));

        if (availableActions.contains("Move Mother Nature")) {
            Platform.runLater(controller::setMoveMother);
        }
        Platform.runLater(controller::initializeEvents);
    }

    @Override
    public void sendWinner(String winner) {
        if (gm.getNumPlayers() == 4) {
            String winnerTeamMate = gm.getTeamMate(winner);
            winner+= " and " + winnerTeamMate;
        }
        Platform.runLater(sceneController::hidePopUp);
        WinnerSceneController controller = new WinnerSceneController(winner, nickname);
        Platform.runLater(() -> sceneController.displayPopUp("WinnerScene.fxml", controller));
    }

    @Override
    public void selectCloud() {
        ActionSceneController controller;

        if (!(sceneController.getActiveController() instanceof ActionSceneController)) {
            controller = new ActionSceneController(gm, nickname);
            Platform.runLater(() -> sceneController.changeRootPane(observers, "ActionScene.fxml", controller));
        } else {
            controller = (ActionSceneController) sceneController.getActiveController();
        }

        Platform.runLater(()->controller.setSuggestions(null, "selectCloud"));

        Platform.runLater(controller::initializeClouds);
        Platform.runLater(controller::setCloudSelectable);
    }

    @Override
    public void goToWaitingRoom() {
        System.out.println("GoToWaitingRoom");

        Platform.runLater(sceneController::hidePopUp);

        ActionSceneController controller = new ActionSceneController(gm, nickname);

        Platform.runLater(() -> sceneController.changeRootPane(observers, "ActionScene.fxml", controller));

        Platform.runLater(()->controller.setSuggestions(null, ""));
    }

    @Override
    public void goToLobby() {

        Platform.runLater(sceneController::hidePopUp);
        Platform.runLater(() -> sceneController.changeRootPane(observers, "LobbyScene.fxml"));
    }

    @Override
    public void waitForOthersMoves(String move) {

        Platform.runLater(sceneController::hidePopUp);
        WaitForOthersMoveSceneController controller = new WaitForOthersMoveSceneController(move);
        Platform.runLater(() -> sceneController.displayPopUp("WaitForOthersMoveScene.fxml", controller));
    }

    @Override
    public void selectRestoreGame() {

        Platform.runLater(() -> sceneController.displayPopUp(observers, "SelectRestoreGameScene.fxml"));
    }

    @Override
    public void rememberNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void onChatMessageReceived(String message) {
        ActionSceneController controller;
        if (sceneController.getActiveController() instanceof ActionSceneController) {
            controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateChat(message));
        }
    }

    @Override
    public void endGameDisconnection(String errorCause) {
        System.out.println(errorCause);

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

    @Override
    public void updateClouds(ReducedCloud[] clouds) {
        gm.setArrayClouds(clouds);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
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

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(controller::updateMotherNature);
        }
    }

    @Override
    public void updateCurrentPlayer(ReducedPlayer currentPlayer) {
        gm.setCurrentPlayer(currentPlayer);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateCurrentPlayer(currentPlayer));
        }
    }

    @Override
    public void updateDiningHall(ReducedDiningHall diningHall) {
        gm.getPlayerByNickname(diningHall.getNickname()).getDashboard().setDiningHall(diningHall);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateDiningHall(diningHall));
        }
    }

    @Override
    public void updateDashboard(ReducedDashboard dashboard) {
        gm.getPlayerByNickname(dashboard.getNickname()).setDashboard(dashboard);

        updateActionSceneGM();

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            ActionSceneController controller = (ActionSceneController) sceneController.getActiveController();
            Platform.runLater(() -> controller.updateDashboard(dashboard));
        }
    }

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

    @Override
    public void showError(String errorMsg) {
        Platform.runLater(() -> sceneController.displayError(errorMsg, observers));
    }

    @Override
    public void notifyGameFull() {
        Platform.runLater(() -> sceneController.displayError(TypeOfError.GAME_FULL.toString(), observers));
//        notifyObserver(ViewObserver::onDisconnection);
    }

    private void updateActionSceneGM() {
        ActionSceneController controller;

        if (sceneController.getActiveController() instanceof ActionSceneController) {
            controller = (ActionSceneController) sceneController.getActiveController();
            controller.setGameModel(gm);
        }
    }
}
