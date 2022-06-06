package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.*;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.UI;
import javafx.application.Platform;

import java.util.List;

/**
 * Gui class. It inherits from the UI interface and all its methods handle GUI scenes
 *
 * @author Dario Mazzola
 */
public class Gui extends ViewObservable implements UI {

    @Override
    public void createNewGame() {
        Platform.runLater(() -> SceneController.changeRootPane(observers, "loginScene.fxml"));
    }

    @Override
    public void updateIslands(List<ReducedIsland> islands) {

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

    }

    @Override
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    @Override
    public void notifyGameFull() {

    }

    @Override
    public void selectNickname() {

    }

    @Override
    public void selectNumPlayers() {
        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectNumPlayerScene.fxml"));
    }

    @Override
    public void selectChat() {

    }

    @Override
    public void selectExpertMode() {
        Platform.runLater(() -> SceneController.changeRootPane(observers, "SelectExpertModeScene.fxml"));
    }

    @Override
    public void selectWizard(List<Wizard> availableWizards) {

    }

    @Override
    public void selectTeam(String[] teamArray, String[] leaderArray) {

    }

    @Override
    public void selectTowerColor(List<Color> availableColors) {

    }

    @Override
    public void selectAssistantCard(List<Card> availableAssistantCard) {

    }

    @Override
    public void actionPhase(List<String> availableActions) {

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
        System.out.println("GoToLobby");
    }

    @Override
    public void selectRestoreGame() {

    }

    @Override
    public void rememberNickname(String nickname) {

    }

    @Override
    public void onChatMessageReceived(String message) {

    }

    @Override
    public void endGameDisconnection(String errorCause) {

    }
}