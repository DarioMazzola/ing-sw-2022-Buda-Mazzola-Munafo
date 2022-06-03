package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.answer.*;
import it.polimi.ingsw.messages.command.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.view.UI;

import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClientController class represents the controller of the client.
 * It processes received messages and sends command messages to the server.
 * It is an implementation of {@link ViewObserver} and {@link Observer}.
 *
 * @author Alessio Buda
 */
public class ClientController extends Observer implements ViewObserver {
    private ServerHandler client;
    private String nickname;
    private final ExecutorService taskQueue ;
    private final UI view;

    /**
     * Class constructor.
     *
     * @param view the view for this game (CLI or GUI)
     */
    public ClientController(UI view) {
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onInit(String ip, int port) {
        try {
            client = new ServerHandler(ip, port);

            client.addObserver(this);
            client.readMessage(); // Starts an asynchronous reading from the server.
            client.sendPong();
            taskQueue.execute(view::createNewGame);
        } catch (IOException e) {
            e.printStackTrace();
            taskQueue.execute(() -> view.showError(null)); //definire un tipo di errore per il messaggio di disconnessione
            client.disconnect();
        }
    }

    @Override
    public void onCreateNewGame(String nickname) {
        this.nickname = nickname;
        client.sendMessage(new NewGame(nickname));
    }

    @Override
    public void onUpdateNickname(String nickname) {
        this.nickname = nickname;
        client.sendMessage(new Nickname(nickname));
    }


    @Override
    public void onUpdateNumPlayers(int numPlayers) {
        client.sendMessage(new ChosenNumPlayers(this.nickname, numPlayers));
    }

    @Override
    public void onUpdateWizard(Wizard wizard) {
        client.sendMessage(new ChosenWizard(this.nickname, wizard));
    }

    @Override
    public void onUpdateExpertMode(boolean expertMode) {
        client.sendMessage(new ChosenExpertMode(this.nickname, expertMode));
    }

    @Override
    public void onUpdateTeam(int selectedTeam, boolean isTeamLeader) {
        client.sendMessage(new ChosenTeam(this.nickname, selectedTeam, isTeamLeader));
    }

    @Override
    public void onUpdateTowerColor(Color towerColor) {
        client.sendMessage(new ChosenTowerColor(this.nickname, towerColor));
    }

    @Override
    public void onUpdateAssistantCard(Card chosenCard) {
        client.sendMessage(new ChosenAssistantCard(this.nickname, chosenCard));
    }

    @Override
    public void onMoveStudentsToDiningHall(House chosenHouse) {
        client.sendMessage(new MoveStudentToDiningHall(this.nickname, chosenHouse));
    }

    @Override
    public void onMoveStudentsToIsland(House chosenHouse, int chosenIsland) {
        client.sendMessage(new MoveStudentToIsland(this.nickname, chosenHouse, chosenIsland));
    }

    @Override
    public void onMoveMotherNature(int moves) {
        client.sendMessage(new MoveMother(this.nickname, moves));
    }

    @Override
    public void onUpdateCloud(int chosenCloud) {
        client.sendMessage(new ChosenCloud(this.nickname, chosenCloud));
    }

    @Override
    public void onUpdateCharacterCard(int cardIndex, Map<String, Object> parameters) {
        client.sendMessage(new ChosenCharacterCard(this.nickname, cardIndex, parameters));
    }

    @Override
    public void onRestoreGame(boolean toRestore) {
        client.sendMessage(new ChosenRestoreGame(this.nickname, toRestore));
    }

    @Override
    public void onDisconnection() {
        client.disconnect();
        System.exit(-1);
    }

    @Override
    public void update(Message message) {

        switch (message.getType()) {
            case PING:
                break;
            case GAME_FULL:
                taskQueue.execute(view::notifyGameFull);
                break;
            case NACK:
                Nack nack = (Nack) message;
                String error = nack.getTypeOfError();
                taskQueue.execute(() -> view.showError(error));
                break;
            case SELECT_NICKNAME:
                taskQueue.execute(view::selectNickname);
                break;
            case SELECT_NUM_PLAYERS:
                taskQueue.execute(view::selectNumPlayers);
                break;
            case SELECT_EXPERT_MODE:
                taskQueue.execute(view::selectExpertMode);
                break;
            case SELECT_WIZARD:
                SelectWizard selectWizard = (SelectWizard) message;
                List<Wizard> availableWizards = selectWizard.getAvailableWizards();
                taskQueue.execute(() -> view.rememberNickname(this.nickname));
                taskQueue.execute(() -> view.selectWizard(availableWizards));
                break;
            case SELECT_TEAM:
                SelectTeam selectTeam = (SelectTeam) message;
                String[] teamArray = selectTeam.getTeamArray();
                String[] leaderArray = selectTeam.getLeaderArray();
                taskQueue.execute(() -> view.selectTeam(teamArray, leaderArray));
                break;
            case SELECT_COLOR_TOWER:
                SelectColorTower selectColorTower = (SelectColorTower) message;
                List<Color> availableColors = selectColorTower.getAvailableColors();
                taskQueue.execute(() -> view.selectTowerColor(availableColors));
                break;
            case SELECT_ASSISTANT_CARD:
                SelectAssistantCard selectAssistantCard = (SelectAssistantCard) message;
                List<Card> availableAssistantCards = selectAssistantCard.getAvailableAssistantCards();
                taskQueue.execute(() -> view.selectAssistantCard(availableAssistantCards));
                break;
            case ACTION_PHASE:
                ActionPhase actionPhase = (ActionPhase) message;
                List<String> availableActions = actionPhase.getAvailableActions();

                List<String> defaultActions = new ArrayList<>();
                defaultActions.add("See the details of an Island");
                defaultActions.add("See the details of a Player's dashboard");
                defaultActions.add("See the current state of clouds");

                availableActions.addAll(defaultActions);
                if (!availableActions.contains("Move students to dining hall or to island"))
                    availableActions.add("Move Mother Nature");
                taskQueue.execute(() -> view.actionPhase(availableActions));
                break;
            case GO_TO_WAITING_ROOM:
                taskQueue.execute(view::goToWaitingRoom);
                break;
            case SEND_WINNER:
                SendWinner sendWinner = (SendWinner) message;
                String winner = sendWinner.getWinner();
                taskQueue.execute(() -> view.sendWinner(winner));
                break;
            case UPDATE_ISLAND:
                UpdateIsland updateIsland = (UpdateIsland) message;
                List<ReducedIsland> islands = updateIsland.getIsland();
                taskQueue.execute(() -> view.updateIslands(islands));
                break;
            case UPDATE_DINING_HALL:
                UpdateDiningHall updateDiningHall = (UpdateDiningHall) message;
                ReducedDiningHall diningHall = updateDiningHall.getDiningHall();
                taskQueue.execute(() -> view.updateDiningHall(diningHall));
                break;
            case UPDATE_DASHBOARD:
                UpdateDashboard updateDashboard = (UpdateDashboard) message;
                ReducedDashboard dashboard = updateDashboard.getDashboard();
                taskQueue.execute(() -> view.updateDashboard(dashboard));
                break;
            case UPDATE_CLOUD:
                UpdateCloud updateCloud = (UpdateCloud) message;
                ReducedCloud[] clouds = updateCloud.getClouds();
                taskQueue.execute(() -> view.updateClouds(clouds));
                break;
            case UPDATE_MOTHER_ISLAND:
                UpdateMotherIsland updateMotherIsland = (UpdateMotherIsland) message;
                int motherIsland = updateMotherIsland.getMotherIsland();
                taskQueue.execute(() -> view.updateMotherNature(motherIsland));
                break;
            case UPDATE_CURRENT_PLAYER:
                UpdateCurrentPlayer updateCurrentPlayer = (UpdateCurrentPlayer) message;
                ReducedPlayer currentPlayer = updateCurrentPlayer.getCurrentPlayer();
                taskQueue.execute(() -> view.updateCurrentPlayer(currentPlayer));
                break;
            case UPDATE_PLAYER:
                UpdatePlayer updatePlayer = (UpdatePlayer) message;
                ReducedPlayer player = updatePlayer.getPlayer();
                taskQueue.execute(() -> view.updatePlayer(player));
                break;
            case UPDATE_GAME_MODEL:
                UpdateGameModel updateGameModel = (UpdateGameModel) message;
                ReducedGameModel gameModel = updateGameModel.getGameModel();
                taskQueue.execute(() -> view.updateGameModel(gameModel));
                break;
            case SELECT_CLOUD:
                SelectCloud selectCloud = (SelectCloud) message;
                taskQueue.execute(view::selectCloud);
                break;
            case GO_TO_LOBBY:
                GoToLobby goToLobby = (GoToLobby) message;
                taskQueue.execute(view::goToLobby);
                break;
            case SELECT_RESTORE_GAME:
                taskQueue.execute(view::selectRestoreGame);
                break;
            case REMEMBER_NICKNAME:
                RememberNickname rememberNickname = (RememberNickname) message;
                String nickname = rememberNickname.getNickname();
                taskQueue.execute(() -> view.rememberNickname(nickname));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message.getType());
        }
    }
}
