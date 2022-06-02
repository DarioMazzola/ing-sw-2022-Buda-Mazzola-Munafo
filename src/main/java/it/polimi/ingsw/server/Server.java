package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.TurnController;
import it.polimi.ingsw.messages.answer.SelectRestoreGame;
import it.polimi.ingsw.messages.command.ChosenRestoreGame;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.utils.Persistence;
import it.polimi.ingsw.view.VirtualView;

import java.util.*;

import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the server. It manages the connection of new players and
 * the reception of messages from different clients.
 *
 * @author Dario Mazzola
 */
public class Server {

    private TurnController turnController;
    private ClientHandler firstHandler;
    private boolean restored = false;
    private final Object lock;
    private List<String> restoredQueue;

    private final Map<String, ClientHandler> clientHandlerMap;

    /**
     * Class constructor.
     *
     * @param turnController the controller of the game.
     */
    public Server(TurnController turnController) {
        this.turnController = turnController;
        this.clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
        lock = new Object();
    }

    /**
     * Adds a client to be managed by the server.
     *
     * @param message the message sent by the client.
     * @param clientHandler the ClientHandler associated with the client.
     */
    public void addClient(CommandMessage message, ClientHandler clientHandler) {
        VirtualView virtualView = new VirtualView(clientHandler);

        Persistence persistence = new Persistence();
        synchronized (lock) {
            System.out.println("clientHandlerMap: " + clientHandlerMap.isEmpty());
            if (clientHandlerMap.isEmpty()) { //there is no player connected
                System.out.println("persistence.matchExists: " + persistence.matchExists());
                if (persistence.matchExists()) { //if there is a match saved on server
                    turnController = persistence.restoreData();
                    restoredQueue = turnController.getQueue();
                    turnController.initializeVirtualViewMap();
                    restored = true;
                } else { //creates a new game
                    turnController = new TurnController();
                }
            }
        }
        if (! turnController.isGameStarted() || restored) {
            //if the client is the first logged in
            if (clientHandlerMap.isEmpty() || (firstHandler != null && firstHandler.equals(clientHandler))) {
                if (firstHandler == null) {
                    clientHandlerMap.put(message.getNickname(), clientHandler);
                }
                if(! turnController.checkLoginNickname(message.getNickname(), virtualView))
                    return;
                firstHandler = clientHandler;
                if (!restored || !restoredQueue.contains(message.getNickname())){
                    turnController.selectMainPhase(message, clientHandler);
                    persistence.delete();
                }
                else{
                    //sends a message to the client to notify that there is
                    //a match saved on server. Asks if the player wants to restore the game or create a new one.
                    turnController.loginHandler(message.getNickname(), clientHandler);
                    virtualView.selectRestoreGame();
                }
            } else if (turnController.checkLoginNickname(message.getNickname(), virtualView)) {
                if(!restored || restoredQueue.contains(message.getNickname()))  {
                    turnController.loginHandler(message.getNickname(), clientHandler);

                    if (!turnController.gameModelExists() || !(turnController.getVirtualViewMap().size() == turnController.getNumPlayers())) {
                        virtualView.goToLobby();
                    }

                    if (turnController.gameModelExists()){
                        turnController.checkIfFull(restored);
                    }
                }
                else {
                    virtualView.showError(GAME_RESTORED_NICKNAME_NOT_PRESENT.toString());
                }
            }
        }
        else {
            virtualView.showError(GAME_ALREADY_STARTED.toString());
        }
    }

    /**
     * Forwards the message received from the client to the TurnController.
     *
     * @param message the message received.
     */
    public void receiveMessage(CommandMessage message) {
        turnController.selectMainPhase(message, clientHandlerMap.get(message.getNickname()));
    }

    /**
     * Returns the nickname associated to the client handler.
     *
     * @param clientHandler the client handler.
     * @return the nickname associated to the client handler.
     */
    private String getNicknameFromClientHandler(ClientHandler clientHandler) {
        return clientHandlerMap.entrySet()
                .stream()
                .filter(entry -> clientHandler.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public void restoreGame(CommandMessage message, ClientHandler clientHandler){
        if(! ((ChosenRestoreGame)message).getToRestore()) {
            turnController = new TurnController();
            turnController.selectMainPhase(message, clientHandler);
        }
        else {
            VirtualView virtualView = new VirtualView(clientHandler);
            virtualView.goToLobby();
        }
    }
}

