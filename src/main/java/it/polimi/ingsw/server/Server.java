package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.TurnController;
import it.polimi.ingsw.messages.answer.GoToWaitingRoom;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.view.VirtualView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.messages.TypeOfError.GAME_ALREADY_STARTED;

/**
 * Class representing the server. It manages the connection of new players and
 * the reception of messages from different clients.
 *
 * @author Dario Mazzola
 */
public class Server {

    private final TurnController turnController;
    private ClientHandler firstHandler;

    private final Map<String, ClientHandler> clientHandlerMap;

    /**
     * Class constructor.
     * @param turnController the controller of the game.
     */
    public Server(TurnController turnController) {
        this.turnController = turnController;
        this.clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Adds a client to be managed by the server.
     *
     * @param message the message sent by the client.
     * @param clientHandler the ClientHandler associated with the client.
     */
    public void addClient(CommandMessage message, ClientHandler clientHandler) {
        VirtualView virtualView = new VirtualView(clientHandler);

        if(! turnController.isGameStarted()) {
            if (clientHandlerMap.isEmpty() || firstHandler.equals(clientHandler)) { //if the client is the first logged in
                firstHandler = clientHandler;
                clientHandlerMap.put(message.getNickname(), clientHandler);
                turnController.selectMainPhase(message, clientHandler);
            } else if (turnController.checkLoginNickname(message.getNickname(), virtualView)) {
                turnController.loginHandler(message.getNickname(), clientHandler);

                if (!turnController.gameModelExists() || !(turnController.getVirtualViewMap().size() == turnController.getNumPlayers())) {
                    virtualView.goToWaitingRoom();
                }
                if(turnController.gameModelExists()) {
                    turnController.checkIfFull();
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
}

