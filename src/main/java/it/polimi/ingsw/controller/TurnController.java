package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.EntranceException;
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
 * @author Gabriele Munafo'
 */
public class TurnController {
    private StartController startController;
    private ActionController actionController;
    private PlanningController planningController;
    private EndTurnController endTurnController;
    private final SetupController setupController;
    private GameState gameState;
    private GamePhase phase;
    private final List<String> queue;
    private final Map<String, VirtualView> virtualViewMap;
    private GameModel gm;
    private boolean isGameStarted;
    private int freeSpots;

    public TurnController() {
        freeSpots = 0;
        isGameStarted = false;
        setupController = new SetupController(this);
        gameState = SETUP;
        phase = CREATE_GAME;
        queue = new ArrayList<>();
        virtualViewMap = new HashMap<>();
        gm = null;
    }

    /**
     * Gets called to manage the messages from the client
     * @param message received from the client
     * @param clientHandler related to the client
     */
    public void selectMainPhase(CommandMessage message, ClientHandler clientHandler) {
        switch (gameState) {
            case SETUP:
                System.out.println("doAction::setupController");
                setupController.doAction(message, phase, clientHandler);
                System.out.println("1");
                if (gameState == START) {
                    System.out.println("1.1");
                    int limit = queue.size();
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
                    }
                }
                System.out.println("2");
                if (phase == WIZARD) {
                    System.out.println("2.1");
                    System.out.println("mando select wizard");
                    setIsGameStarted(true);
                    sendAllSelectWizard(Arrays.asList(Wizard.values()));
                }
                System.out.println("3");

                break;

            case START:
                System.out.println("siamo in start");
                startController.doAction(message, phase);
                break;

            case PLANNING:
                planningController.doAction(message);
                break;

            case ACTION:
                actionController.doAction(message);
                break;

            case END_TURN:
                endTurnController.doAction(message);
                break;
        }
    }

    /**
     * Initialize the game model, start controller, the action controller, the planning controller and the end turn controller
     * @throws EntranceException thrown by the constructor of the game model
     * @throws BagException thrown by the constructor of the game model
     */
    public void init() throws EntranceException, BagException {
        gm = new GameModel(setupController.getNumPlayer(), setupController.isExpertMode());
        gm.getArrayPlayers()[0].setNickname(setupController.getNickname());

        startController = new StartController(gm, this);
        actionController = new ActionController(gm, this);
        planningController = new PlanningController(gm, this);
        endTurnController = new EndTurnController(gm, this);
    }

    /**
     * Sets the game state
     * @param nextState the game state which is set
     */
    public void next_State(GameState nextState) {
        gameState = nextState;
    }

    /**
     * Sets the game phase
     * @param nextPhase the game phase which is set
     */
    public void next_Phase(GamePhase nextPhase) {
        phase = nextPhase;
    }

    /**
     * Links the client handler to the nickname in a virtualViewMap
     * @param nickname of the client connecting to the server
     * @param clientHandler of the client connecting to the server
     */
    public void loginHandler(String nickname, ClientHandler clientHandler) {
        VirtualView virtualView = new VirtualView(clientHandler);

        virtualViewMap.put(nickname, virtualView);
        queue.add(nickname);
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

    public void sendAllSelectWizard(List<Wizard> availableWizards) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).selectWizard(availableWizards);
        }
    }

    public void sendAllSelectTeam(String[] teamArray, String[] leaderArray) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).selectTeam(teamArray, leaderArray);
        }
    }

    public void sendAllSelectTowerColor(List<Color> availableTowers) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).selectTowerColor(availableTowers);
        }
    }

    public void sendAllWinner(String winner) {
        for (String nickname : virtualViewMap.keySet()) {
            virtualViewMap.get(nickname).sendWinner(winner);
        }
    }

    public List<Integer> getAvailableClouds() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < gm.getArrayClouds().length; i++) {
            if (gm.getArrayClouds()[i].isFull()) {
                result.add(i);
            }
        }
        return result;
    }

    public boolean isThereNextPlayer() {
        return (!(planningController.getPosition() == planningController.getRanking().length));
    }

    public int getNextPlanner() {
        planningController.setPosition(planningController.getPosition() + 1);
        return (planningController.getRanking()[planningController.getPosition()]);
    }

    public int getFirstPlanner() {
        return planningController.getFirstPlanner();
    }

    public boolean checkLoginNickname(String nickname, VirtualView view) {
        if (nickname.isEmpty()) {
            view.showError(EMPTY_NICKNAME.toString());
            view.selectNickname();
            return false;
        }
         else if (queue.contains(nickname)) {
            view.showError(NICKNAME_TAKEN.toString());
            view.selectNickname();
            return false;
        }
        return true;
    }

    public boolean gameModelExists(){
        return gm != null;
    }
}

