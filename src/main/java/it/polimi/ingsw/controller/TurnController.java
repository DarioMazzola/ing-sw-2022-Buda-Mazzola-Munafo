package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.ReducedGameModel;
import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.messages.answer.UpdateGameModel;
import it.polimi.ingsw.messages.command.ChatMessageClientServer;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.view.VirtualView;

import java.util.*;

import static it.polimi.ingsw.controller.GamePhase.*;
import static it.polimi.ingsw.controller.GameState.*;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the turn controller
 * @author Gabriele Munafo' & Dario Mazzola
 */
public class TurnController {
    private StartController startController;
    private ActionController actionController;
    private PlanningController planningController;
    private EndTurnController endTurnController;
    private final SetupController setupController;
    private GameState gameState;
    private GamePhase phase;
    private List<String> queue;
    private transient Map<String, VirtualView> virtualViewMap;
    private GameModel gm;
    private boolean isGameStarted;
    private int freeSpots;
    private boolean restoreDecisionTaken = false;

    public TurnController() {
        freeSpots = 0;
        isGameStarted = false;
        setupController = new SetupController();
        gameState = SETUP;
        phase = CREATE_GAME;
        queue = new ArrayList<>();
        virtualViewMap = new HashMap<>();
        gm = null;
    }

    /**
     * Gets called to manage the messages from the client
     *
     * @param message       received from the client
     * @param clientHandler related to the client
     */
    public void selectMainPhase(CommandMessage message, ClientHandler clientHandler) {
        switch (gameState) {
            case SETUP:
                System.out.println("doAction::setupController");
                setupController.doAction(message, phase, clientHandler, this);
                System.out.println("1");

                if (gameState == START) {
                    System.out.println("1.1");
                    int limit = queue.size();
                    System.out.println("limit: " + limit);
                    if (queue.size() >= gm.getNumPlayers()) {
                        limit = gm.getNumPlayers();
                    } else {
                        freeSpots = gm.getNumPlayers() - queue.size();
                    }
                    for (int i = 1; i < limit; i++) {
                        gm.getArrayPlayers()[i].setNickname(queue.get(i));
                    }
                    if (limit == gm.getNumPlayers()) {
                        for (int i = gm.getNumPlayers(); i < queue.size(); i++) {
                            virtualViewMap.get(queue.get(i)).notifyGameFull();
                            virtualViewMap.remove(queue.get(i));
                        }
                        for (int i = gm.getNumPlayers(); i < queue.size(); i++) {
                            queue.remove(i);
                        }
                        setIsGameStarted(true);
                        sendAllSelectWizard(Arrays.asList(Wizard.values()));
                    } else {
                        virtualViewMap.get(message.getNickname()).goToLobby();
                    }
                }
                System.out.println("2");

                break;

            case START:
                System.out.println("siamo in start");
                startController.doAction(message, phase, this);
                if (gameState == PLANNING) {
                    addObservers();
                    initializePlayers();
                }
                break;

            case PLANNING:
                planningController.doAction(message, this);
                break;

            case ACTION:
                actionController.doAction(message, this);
                break;

            case END_TURN:
                endTurnController.doAction(message, this);
                break;
        }
    }

    /**
     * Initialize the game model, start controller, the action controller, the planning controller and the end turn controller
     *
     * @throws EntranceException thrown by the constructor of the game model
     * @throws BagException      thrown by the constructor of the game model
     */
    public void init() throws EntranceException, BagException {
        gm = new GameModel(setupController.getNumPlayer(), setupController.isExpertMode(), setupController.getChat());

        gm.getArrayPlayers()[0].setNickname(setupController.getNickname());

        startController = new StartController(gm);
        actionController = new ActionController(gm);
        planningController = new PlanningController(gm);
        endTurnController = new EndTurnController(gm);
    }

    /**
     * Sets the game model in every controller
     */
    public void setGameModel() {
        startController.setGameModel(gm);
        actionController.setGameModel(gm);
        planningController.setGameModel(gm);
        endTurnController.setGameModel(gm);
    }

    /**
     * Sets the game state
     *
     * @param nextState the game state which is set
     */
    public void next_State(GameState nextState) {
        gameState = nextState;
    }

    /**
     * Sets the game phase
     *
     * @param nextPhase the game phase which is set
     */
    public void next_Phase(GamePhase nextPhase) {
        phase = nextPhase;
    }

    /**
     * Links the client handler to the nickname in a virtualViewMap
     *
     * @param nickname      of the client connecting to the server
     * @param clientHandler of the client connecting to the server
     */
    public void loginHandler(String nickname, ClientHandler clientHandler) {
        VirtualView virtualView = new VirtualView(clientHandler);

        virtualViewMap.put(nickname, virtualView);
        queue.add(nickname);

    }

    /**
     * Creates a new virtual view map
     */
    public void initializeVirtualViewMap(){
        virtualViewMap = new HashMap<>();
        queue = new ArrayList<>();
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setIsGameStarted(boolean value) {
        isGameStarted = value;
    }

    public Map<String, VirtualView> getVirtualViewMap() {
        return virtualViewMap;
    }

    public int getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(int newValue) {
        freeSpots = newValue;
    }

    /**
     * Sends all players a select wizard message
     */
    public void sendAllSelectWizard(List<Wizard> availableWizards) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).selectWizard(availableWizards);
        }
    }

    /**
     * Sends all players a select team message
     */
    public void sendAllSelectTeam(String[] teamArray, String[] leaderArray) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).selectTeam(teamArray, leaderArray);
        }
    }

    /**
     * Sends all players a select tower color message
     */
    public void sendAllSelectTowerColor(List<Color> availableTowers) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).selectTowerColor(availableTowers);
        }
    }

    /**
     * Sends all players a send nickname message
     */
    public void sendAllWinner(String winner) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).sendWinner(winner);
        }
    }

    /**
     * Sends all players an update message containing the model
     */
    public void sendAllModel() {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).update(new UpdateGameModel(new ReducedGameModel(gm)));
        }
    }

    /**
     * Checks if the action phase is ended
     */
    public boolean isThereNextPlayer() {
        return (!(planningController.getPosition() + 1 == gm.getNumPlayers()));
    }

    /**
     * Sets the new current planner and returns it
     */
    public int getNextPlanner() {
        planningController.setPosition(planningController.getPosition() + 1);
        System.out.println("la posizione nel ranking è " + planningController.getPosition());
        return (planningController.getRanking()[planningController.getPosition()]);
    }

    public int getFirstPlanner() {
        return planningController.getFirstPlanner();
    }


    public boolean checkLoginNickname(String nickname, ClientHandler clientHandler) {
        VirtualView view = virtualViewMap.get(nickname);
        if(view == null)
            view = new VirtualView(clientHandler);
        if (nickname.isEmpty()) {
            view.showError(EMPTY_NICKNAME.toString());
            view.selectNickname();
            return false;
        } else if (queue.contains(nickname)) {
            view.showError(NICKNAME_TAKEN.toString());
            view.selectNickname();
            return false;
        }
        return true;
    }

    public boolean gameModelExists() {
        return gm != null;
    }

    private void addObservers() {
        for (String nickname : virtualViewMap.keySet()) {
            VirtualView vv = virtualViewMap.get(nickname);

            gm.getPlayerByNickname(nickname).addObserver(vv);
            gm.getPlayerByNickname(nickname).getDashboard().addObserver(vv);
            gm.getPlayerByNickname(nickname).getDashboard().getDiningHall().addObserver(vv);

            for (Island i : gm.getIslandList())
                i.addObserver(vv);

            gm.addObserver(vv);
        }
    }

    private void initializePlayers() {
        for (Player p : gm.getArrayPlayers()) {
            try {
                gm.moveStudents(gm.getBag(), p.getDashboard(), p.getDashboard().getNumMaxStudents());
            } catch (BagException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkIfFull(boolean restore) {

        if(! restore) {
            if (queue.size() == gm.getNumPlayers()) {
                System.out.println("2.1");
                while (freeSpots != 0) {
                    gm.getArrayPlayers()[gm.getNumPlayers() - freeSpots].setNickname(queue.get(gm.getNumPlayers() - freeSpots));
                    freeSpots--;
                }
                setIsGameStarted(true);
                sendAllSelectWizard(Arrays.asList(Wizard.values()));
                return true;
            }
        }
        else return queue.size() == gm.getNumPlayers() && restoreDecisionTaken;
        return false;
    }

    public void restore(){

        for (int i=0; i< gm.getNumPlayers(); i++) {
            if (gm.getArrayPlayers()[i].getNickname().equals(gm.getCurrentPlayer().getNickname())) {
                gm.setCurrentPlayer(i);
                break;
            }
        }

        setGameModel();

        addObservers();

        sendAllModel();

        sendAllRememberNickname();

        switch (gameState){
            case PLANNING:
                if (planningController.getSelected() == gm.getNumPlayers()) {
                    List<String> availableActions = new ArrayList<>();
                    availableActions.add("Move students to dining hall or to island");
                    if (gm.isExpertMode()) {
                        availableActions.add("Select character card");
                    }
                    next_State(ACTION);
                    virtualViewMap.get(gm.getArrayPlayers()[getFirstPlanner()].getNickname()).actionPhase(availableActions);
                    break;
                }
                else {
                    virtualViewMap.get(gm.getArrayPlayers()[getFirstPlanner()].getNickname()).selectAssistantCard(Arrays.asList(Card.values()));
                }
                break;

            case ACTION:
                List<String> availableActions = new ArrayList<>();
                if (actionController.getStudentsMoved() < actionController.getMaxStudMoved()){
                    availableActions.add("Move students to dining hall or to island");
                }
                if (gm.isExpertMode() && ! actionController.isUsedCharacterCard()){
                    availableActions.add("Select character card");
                }
                virtualViewMap.get(gm.getCurrentPlayer().getNickname()).actionPhase(availableActions);
                break;

            case END_TURN:
                virtualViewMap.get(gm.getCurrentPlayer().getNickname()).selectCloud();
                break;
        }
    }

    public void setRestoreDecisionTaken() {
        this.restoreDecisionTaken = true;
    }

    public int getNumPlayers(){
        return gm.getNumPlayers();
    }

    public List<String> getQueue(){
        return queue;
    }

    /**
     * Sends all players a remember nickname message
     */
    private void sendAllRememberNickname(){
        for (String nick : virtualViewMap.keySet()){
            virtualViewMap.get(nick).rememberNickname(nick);
        }
    }

    public Boolean getChat(){
        return gm.getChat();
    }

    public GamePhase getPhase() {
        return phase;
    }

    /**
     * Handles a chat message. The message will only be sent to the teammate if the first player
     * has decided to enable chat for this match
     * @param message the message to sent to client teammate
     */
    public void chat(CommandMessage message){
        String teamMate = gm.getTeamMate(message.getNickname());
        virtualViewMap.get(teamMate).onChatMessageReceived(((ChatMessageClientServer)message).getMessage());
    }

    /**
     * Send the player identified by the nickname given as a parameter to the lobby because the game has not started yet
     * @param nickname the nickname that identifies the player
     */
    public void goToLobby(String nickname){
        virtualViewMap.get(nickname).goToLobby();
    }

    /**
     * Asks the first player whether to restore the game
     *
     * @param nickname the nickname of the player to send the message to
     */
    public void selectRestore(String nickname) {
        virtualViewMap.get(nickname). selectRestoreGame();
    }

    /**
     * Notifies an error to the player identified by the nickname given as a parameter
     *
     * @param nickname the nickname of the player to send the message to
     * @param error the error that occurred
     */
    public void showError(String nickname, String error) {
        virtualViewMap.get(nickname).showError(error);
    }

    /**
     * Notifies to the player identified by the nickname given as a parameter that
     * the game will be interrupted due to a client disconnection
     *
     * @param nickname the nickname of the player to send the message to
     * @param interruptedBy the player who caused the game to stop
     */
    public void endGameDisconnection(String nickname, String interruptedBy){
        virtualViewMap.get(nickname).endGameDisconnection(interruptedBy);
    }

    public GameModel getGameModel() {
        return gm;
    }

    public void resendActionPhase(String nickname){
        virtualViewMap.get(nickname).actionPhase(actionController.getActions());
    }
}

