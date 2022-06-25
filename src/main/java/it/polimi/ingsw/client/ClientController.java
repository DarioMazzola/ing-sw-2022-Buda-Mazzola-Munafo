package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.TypeOfError;
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
import java.util.concurrent.*;

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
    private final ExecutorService showTaskQueue;
    private final ExecutorService updateTaskQueue;
    private final UI view;

    /**
     * Class constructor.
     *
     * @param view the view for this game (CLI or GUI)
     */
    public ClientController(UI view) {
        this.view = view;
        showTaskQueue = Executors.newSingleThreadExecutor();
        updateTaskQueue = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onInit(String ip, int port) {
        try {
            client = new ServerHandler(ip, port);

            client.addObserver(this);
            client.readMessage(); // Starts an asynchronous reading from the server.
            client.sendPong();
            showTaskQueue.execute(view::createNewGame);
        } catch (IOException e) {
            update(new EndGameDisconnection(TypeOfError.SERVER_UNREACHBLE.toString()));
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
    public void onUpdateChat(boolean chat) {
        client.sendMessage(new ChosenChat(this.nickname, chat));
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
    public void onSendMessage(String message) {
        client.sendMessage(new ChatMessageClientServer(this.nickname, message));
    }

    @Override
    public void onDisconnection() {
        try{
            client.disconnect();
        }catch (NullPointerException ignored){}
        showTaskQueue.shutdown();
        System.exit(-12);
    }

    @Override
    public void waitForMessage(){
        client.sendMessage(new ReloadMessages(this.nickname));
    }

    @Override
    public void update(Message message) {

        switch (message.getType()) {
            case PING:
                break;
            case GAME_FULL:
                showTaskQueue.execute(view::notifyGameFull);
                break;
            case NACK:
                Nack nack = (Nack) message;
                String error = nack.getTypeOfError();
                showTaskQueue.execute(() -> view.showError(error));
                break;
            case SELECT_NICKNAME:
                showTaskQueue.execute(view::selectNickname);
                break;
            case SELECT_NUM_PLAYERS:
                showTaskQueue.execute(view::selectNumPlayers);
                break;
            case SELECT_CHAT:
                showTaskQueue.execute(view::selectChat);
                break;
            case SELECT_EXPERT_MODE:
                showTaskQueue.execute(view::selectExpertMode);
                break;
            case SELECT_WIZARD:
                SelectWizard selectWizard = (SelectWizard) message;
                List<Wizard> availableWizards = selectWizard.getAvailableWizards();
                showTaskQueue.execute(() -> view.rememberNickname(this.nickname));
                showTaskQueue.execute(() -> view.selectWizard(availableWizards));
                break;
            case SELECT_TEAM:
                SelectTeam selectTeam = (SelectTeam) message;
                String[] teamArray = selectTeam.getTeamArray();
                String[] leaderArray = selectTeam.getLeaderArray();
                showTaskQueue.execute(() -> view.selectTeam(teamArray, leaderArray));
                break;
            case SELECT_COLOR_TOWER:
                SelectColorTower selectColorTower = (SelectColorTower) message;
                List<Color> availableColors = selectColorTower.getAvailableColors();
                showTaskQueue.execute(() -> view.selectTowerColor(availableColors));
                break;
            case SELECT_ASSISTANT_CARD:
                SelectAssistantCard selectAssistantCard = (SelectAssistantCard) message;
                List<Card> availableAssistantCards = selectAssistantCard.getAvailableAssistantCards();
                view.setStop(true);
                showTaskQueue.execute(() -> view.selectAssistantCard(availableAssistantCards));
                break;
            case ACTION_PHASE:
                ActionPhase actionPhase = (ActionPhase) message;
                List<String> availableActions = actionPhase.getAvailableActions();

                List<String> defaultActions = new ArrayList<>();
                defaultActions.add("See the details of an Island");
                defaultActions.add("See the details of a Player's dashboard");
                defaultActions.add("See the current state of clouds");
                defaultActions.add("See Mother Nature position");

                availableActions.addAll(defaultActions);
                if (!availableActions.contains("Move students to dining hall or to island"))
                    availableActions.add("Move Mother Nature");
                view.setStop(true);
                showTaskQueue.execute(() -> view.actionPhase(availableActions));
                break;
            case CHAT_MESSAGE_SERVER_CLIENT:
                ChatMessageServerClient chatMessageServerClient = (ChatMessageServerClient) message;
                String chatMessage = chatMessageServerClient.getMessage();
                showTaskQueue.execute(() -> view.onChatMessageReceived(chatMessage));
                break;
            case GO_TO_WAITING_ROOM:
                showTaskQueue.execute(view::goToWaitingRoom);
                break;
            case SEND_WINNER:
                SendWinner sendWinner = (SendWinner) message;
                String winner = sendWinner.getWinner();
                view.setStop(true);
                showTaskQueue.execute(() -> view.sendWinner(winner));
                break;
            case UPDATE_ISLAND:
                UpdateIsland updateIsland = (UpdateIsland) message;
                List<ReducedIsland> islands = updateIsland.getIsland();
                updateTaskQueue.execute(() -> view.updateIslands(islands));
                break;
            case UPDATE_DINING_HALL:
                UpdateDiningHall updateDiningHall = (UpdateDiningHall) message;
                ReducedDiningHall diningHall = updateDiningHall.getDiningHall();
                updateTaskQueue.execute(() -> view.updateDiningHall(diningHall));
                break;
            case UPDATE_DASHBOARD:
                UpdateDashboard updateDashboard = (UpdateDashboard) message;
                ReducedDashboard dashboard = updateDashboard.getDashboard();
                updateTaskQueue.execute(() -> view.updateDashboard(dashboard));
                break;
            case UPDATE_CLOUD:
                UpdateCloud updateCloud = (UpdateCloud) message;
                ReducedCloud[] clouds = updateCloud.getClouds();
                updateTaskQueue.execute(() -> view.updateClouds(clouds));
                break;
            case UPDATE_MOTHER_ISLAND:
                UpdateMotherIsland updateMotherIsland = (UpdateMotherIsland) message;
                int motherIsland = updateMotherIsland.getMotherIsland();
                showTaskQueue.execute(() -> view.updateMotherNature(motherIsland));
                break;
            case UPDATE_CURRENT_PLAYER:
                UpdateCurrentPlayer updateCurrentPlayer = (UpdateCurrentPlayer) message;
                ReducedPlayer currentPlayer = updateCurrentPlayer.getCurrentPlayer();
                updateTaskQueue.execute(() -> view.updateCurrentPlayer(currentPlayer));
                break;
            case UPDATE_PLAYER:
                UpdatePlayer updatePlayer = (UpdatePlayer) message;
                ReducedPlayer player = updatePlayer.getPlayer();
                updateTaskQueue.execute(() -> view.updatePlayer(player));
                break;
            case UPDATE_GAME_MODEL:
                UpdateGameModel updateGameModel = (UpdateGameModel) message;
                ReducedGameModel gameModel = updateGameModel.getGameModel();
                updateTaskQueue.execute(() -> view.updateGameModel(gameModel));
                break;
            case SELECT_CLOUD:
                view.setStop(true);
                showTaskQueue.execute(view::selectCloud);
                break;
            case GO_TO_LOBBY:
                showTaskQueue.execute(view::goToLobby);
                break;
            case SELECT_RESTORE_GAME:
                showTaskQueue.execute(view::selectRestoreGame);
                break;
            case END_GAME_DISCONNECTION:
                EndGameDisconnection endGameDisconnection = (EndGameDisconnection) message;
                view.setStop(true);
                showTaskQueue.execute(() -> view.endGameDisconnection(endGameDisconnection.getErrorCause()));
                break;
            case REMEMBER_NICKNAME:
                RememberNickname rememberNickname = (RememberNickname) message;
                String nickname = rememberNickname.getNickname();
                showTaskQueue.execute(() -> view.rememberNickname(nickname));
                break;
            case WAIT_FOR_OTHERS_MOVES:
                String move = ((WaitForOthersMoves)message).getMove();
                showTaskQueue.execute(() -> view.waitForOthersMoves(move));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message.getType());
        }
    }
}
