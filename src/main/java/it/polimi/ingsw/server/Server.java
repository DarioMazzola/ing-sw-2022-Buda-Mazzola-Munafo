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
     * Adds a client to be managed by the server.
     *
     * @param message the message sent by the client.
     * @param clientHandler the ClientHandler associated with the client.
     */
    public void addClient(CommandMessage message, ClientHandler clientHandler) {
        VirtualView virtualView = new VirtualView(clientHandler);

        Persistence persistence = new Persistence();
        synchronized (lock) {
            if(clientHandlerMap.isEmpty()) { //non c'è nessuno connesso, sono il primo
                if(persistence.matchExists()) {  //se c'è una partita salvata
                    turnController = persistence.restoreData(); //riprende il tc del json
                    restoredQueue = turnController.getQueue();
                    turnController.initializeVirtualViewMap();
                    restored = true;
                }
                else
                    turnController = new TurnController();  //se non c'è nessuna partita salvata
            }
        }
        if (restored) { //se la partita è stata ripristinata
            if (restoredQueue.contains(message.getNickname())) { // se lo contiene
                if (clientHandlerMap.isEmpty()) { //se non c'è ancora nessuno o sono il primo
                    clientHandlerMap.put(message.getNickname(), clientHandler);
                    turnController.loginHandler(message.getNickname(), clientHandler); //lo aggiungo alla partita
                    virtualView.selectRestoreGame();
                }
                else {
                    turnController.loginHandler(message.getNickname(), clientHandler);
                    if(!turnController.checkIfFull(restored)){
                        if(selectedRestore)
                            turnController.restore();
                        else
                            virtualView.goToLobby();
                    }
                }
            }
            else { //non era presente nella partita, gli dico che non può giocare
                virtualView.showError(GAME_RESTORED_NICKNAME_NOT_PRESENT.toString());
            }
        }

        else if (! turnController.isGameStarted()) { //se la partita ancora non è iniziata accetto giocatori.
            if (clientHandlerMap.isEmpty() || (firstHandler != null && firstHandler.equals(clientHandler))) { //se non c'è ancora nessuno o sono il primo
                if(firstHandler == null) {
                    clientHandlerMap.put(message.getNickname(), clientHandler);
                    firstHandler = clientHandler;
                }
                if (!turnController.checkLoginNickname(message.getNickname(), virtualView))
                    return;
                turnController.selectMainPhase(message, clientHandler);
            }

            else if(turnController.checkLoginNickname(message.getNickname(), virtualView)){
                turnController.loginHandler(message.getNickname(), clientHandler);

                if (! turnController.gameModelExists() || !(turnController.getVirtualViewMap().size() == turnController.getNumPlayers())) {
                    virtualView.goToLobby();
                }

                if (turnController.gameModelExists()) {
                    turnController.checkIfFull(restored);
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
            for(String n : clientHandlerMap.keySet()){
                if(! n.equals(message.getNickname()))
                    turnController.loginHandler(n, clientHandlerMap.get(n));
            }
            turnController.selectMainPhase(message, clientHandler);
            synchronized (lock) {
                restored = false;
            }
        }
        else {
            synchronized (lock) {
                selectedRestore = true;
            }
            VirtualView virtualView = new VirtualView(clientHandler);
            if(turnController.checkIfFull(restored)){
                turnController.restore();
            }
            else
                virtualView.goToLobby();
        }
    }
}

