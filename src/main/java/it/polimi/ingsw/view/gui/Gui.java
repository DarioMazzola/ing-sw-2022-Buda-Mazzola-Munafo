package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.*;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.UI;
import it.polimi.ingsw.view.gui.scenes.ActionSceneController;
import it.polimi.ingsw.view.gui.scenes.SelectAssistantCardSceneController;
import it.polimi.ingsw.view.gui.scenes.SelectTowerColorSceneController;
import it.polimi.ingsw.view.gui.scenes.SelectWizardSceneController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

/**
 * Gui class. It inherits from the UI interface and all its methods handle GUI scenes
 *
 * @author Dario Mazzola
 */
public class Gui extends ViewObservable implements UI {

    private ReducedGameModel gm;
    private String nickname;
    private Scene root;
    private Stage waitStage;

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

        Platform.runLater(this::closeWaitForOthersMove);

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

        Platform.runLater(this::closeWaitForOthersMove);
        SelectTowerColorSceneController controller = new SelectTowerColorSceneController(availableColors);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectTowerColorScene.fxml", controller));
    }

    @Override
    public void selectAssistantCard(List<Card> availableAssistantCard) {

        Platform.runLater(this ::closeWaitForOthersMove);

        SelectAssistantCardSceneController controller = new SelectAssistantCardSceneController(gm, nickname, availableAssistantCard);

        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectAssistantCardScene.fxml", controller));
    }

    @Override
    public void actionPhase(List<String> availableActions) {

        ActionSceneController controller;

        if(! (SceneController.getActiveController() instanceof  ActionSceneController)) {
            controller = new ActionSceneController(gm, nickname);
            Platform.runLater(() -> SceneController.changeRootPane(observers, "ActionScene.fxml", controller));
        }
        else {
            controller = (ActionSceneController) SceneController.getActiveController();
            controller.setGm(gm);

            if(availableActions.contains("Move Mother Nature")){
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
        Platform.runLater(() -> displayWaitForOthersMove(move));
    }

    @Override
    public void selectRestoreGame() {

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

    private void displayWaitForOthersMove(String move) {

        root = SceneController.getActiveScene();

        root.getRoot().setEffect(new GaussianBlur());

        StackPane pauseRoot = new StackPane();

        pauseRoot.setPrefWidth(800);
        pauseRoot.setPrefHeight(600);

        Label label = new Label("Another player is choosing the "+ move +". Wait your turn");
        Font font = new Font("Baskerville Old Face", 24);

        label.setFont(font);

        pauseRoot.getChildren().add(label);
        pauseRoot.setStyle("-fx-background-color: rgba(135, 206, 250, 0.7);");

        Button resume = new Button("Ok");
        resume.setFont(font);

        resume.setOnAction((e) -> {
            root.getRoot().setEffect(null);
            waitStage.hide();
        });

        resume.setTranslateY(50);
        pauseRoot.getChildren().add(resume);

        waitStage = new Stage(StageStyle.TRANSPARENT);
        waitStage.initOwner(root.getWindow());
        waitStage.initModality(Modality.APPLICATION_MODAL);
        waitStage.setScene(new Scene(pauseRoot, javafx.scene.paint.Color.TRANSPARENT));

        waitStage.show();
    }

    private void closeWaitForOthersMove() {

        if(root != null && waitStage != null) {
            root.getRoot().setEffect(null);
            waitStage.hide();
        }
    }
}
