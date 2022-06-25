package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GamePhase;
import it.polimi.ingsw.controller.TurnController;
import it.polimi.ingsw.messages.command.ChosenRestoreGame;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.utils.Persistence;

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
    private boolean selectedRestore = false;
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
     * Adds a client to the game.
     * If the game was saved on the server, the player must be part of the saved game otherwise
     * he/she will be asked to wait for the saved game to finish or to reconnect with the nickname he had
     * previously chosen. The first player who connects, if he was part of the saved game, is asked if he/she
     * wants to restore the game or start a new one.
     * If, on the other hand, the client is not the first, then the player will be placed in a queue in which
     * he/she will wait for the decisions made by the first player about the initialization of the game or
     * on resuming the game or not.
     *
     * @param message the message sent by the client.
     * @param clientHandler the ClientHandler associated with the client.
     */
    public void addClient(CommandMessage message, ClientHandler clientHandler) {
        String sender = message.getNickname();

        Persistence persistence = new Persistence();
        //if the player is the first, tries to restore the game
        synchronized (lock) {
            if(clientHandlerMap.isEmpty()) {
                if(persistence.matchExists()) {
                    turnController = persistence.restoreData();
                    restoredQueue = turnController.getQueue();
                    turnController.initializeVirtualViewMap();
                    restored = true;
                }
                else
                    turnController = new TurnController();  //se non c'è nessuna partita salvata
            } else if (turnController.reset()){
                turnController = new TurnController();
            }
        }
        if (restored) { //if the game has been restored
            if (restoredQueue.contains(sender)) { //if the player belongs to the previous game
                if (clientHandlerMap.isEmpty()) { //if the player is the first
                    clientHandlerMap.put(sender, clientHandler);
                    turnController.loginHandler(sender, clientHandler);
                    //asks the player whether to restore the game
                    turnController.selectRestore(sender);
                }
                else { //if the player is not the first
                    if(turnController.checkLoginNickname(sender, clientHandler)) {
                        clientHandlerMap.put(sender, clientHandler);
                        turnController.loginHandler(sender, clientHandler);
                        //if the player is the last
                        if (turnController.checkIfFull(restored)) {
                            synchronized (lock) {
                                //if the first player wants to resume the game or not
                                if (selectedRestore) {
                                    turnController.restore();
                                } else {
                                    turnController.goToLobby(sender);
                                }
                            }
                        } else
                            turnController.goToLobby(sender);
                    }
                    else
                        turnController.showError(sender, NICKNAME_TAKEN.toString());
                }
            }
            else { // the player was not present in the saved game, he/she cannot play
                turnController.loginHandler(sender, clientHandler);
                turnController.showError(sender, GAME_RESTORED_NICKNAME_NOT_PRESENT.toString());
                turnController.sendNickname(sender);
            }
        }

        // if the game has not already started
        else if (! turnController.isGameStarted()) {
            // if he/she is the first player
            if (clientHandlerMap.isEmpty() || (firstHandler != null && firstHandler.equals(clientHandler))) {
                if(firstHandler == null) {
                    clientHandlerMap.put(sender, clientHandler);
                    firstHandler = clientHandler;
                }
                if (!turnController.checkLoginNickname(sender, clientHandler))
                    return;
                if(turnController.getPhase() == GamePhase.CREATE_GAME)
                    turnController.loginHandler(sender, clientHandler);
                turnController.selectMainPhase(message, clientHandler);
            }
            // if he/she is not the first player
            else if(turnController.checkLoginNickname(sender, clientHandler)){
                turnController.loginHandler(sender, clientHandler);
                clientHandlerMap.put(sender, clientHandler);

                // if the game model has not been created or the maximum number of players has not been reached
                if (! turnController.gameModelExists() || !(turnController.getVirtualViewMap().size() == turnController.getNumPlayers())) {
                    turnController.goToLobby(sender);
                }
                // if the game model has been created
                else if (turnController.gameModelExists()) {
                    turnController.checkIfFull(restored);
                }
            }
        }
        else {
            turnController.showError(sender, GAME_ALREADY_STARTED.toString());
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

    /**
     * Manages the first player's decision if there is a game saved on the server memory.
     * If the player has decided not to resume the saved game then a new one will be created,
     * otherwise the game will resume where it left off.
     * @param message the message sent by the client that represents his/her decision.
     * @param clientHandler the client handler associated to the client
     */
    public void restoreGame(CommandMessage message, ClientHandler clientHandler){
        synchronized (lock){
            turnController.setRestoreDecisionTaken();
        }
        String sender = message.getNickname();
        //if the player does not want to restore the game
        if(! ((ChosenRestoreGame)message).getToRestore()) {

            Persistence persistence = new Persistence();
            persistence.delete();
            synchronized (lock) {
                restored = false;
            }
            turnController = new TurnController();

            turnController.loginHandler(sender, clientHandler);

            for(String n : clientHandlerMap.keySet()){
                if(! n.equals(sender))
                    turnController.loginHandler(n, clientHandlerMap.get(n));
            }
            turnController.selectMainPhase(message, clientHandler);
        }
        //if the player wants to restore the game
        else {
            synchronized (lock) {
                selectedRestore = true;
                if (turnController.checkIfFull(restored)) {
                    turnController.restore();
                } else
                    turnController.goToLobby(sender);
            }
        }
    }

    public void chat(CommandMessage message){
        turnController.chat(message);
    }

    public void onDisconnection(ClientHandler clientHandler){
        String playerDisconnected = getNicknameFromClientHandler(clientHandler);
        for(String nickname : clientHandlerMap.keySet()) {
            if(! nickname.equals(playerDisconnected)) {
                String interruptedBy = playerDisconnected + " has disconnected. The game will be stopped and saved on the server. " +
                        "The server will be interrupted but you will be able to resume the game at a later time";
                turnController.endGameDisconnection(nickname, interruptedBy);
            }
        }
    }

    public void resendAvailableActions(String nickname){
        turnController.resendActionPhase(nickname);
    }
}

