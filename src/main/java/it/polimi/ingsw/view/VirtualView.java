package it.polimi.ingsw.view;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.answer.*;
import it.polimi.ingsw.messages.answer.ActionPhase;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.server.ClientHandler;

import java.util.List;

/**
 * Class representing the virtual view. An instance of this class hides from the server controller
 * how the network layer is done. The server controller can use his methods to have a dialogue with
 * the client as if he had the view on the same side
 *
 * @author Dario Mazzola
 */
public class VirtualView implements View, Observer{

    private transient final ClientHandler clientHandler;

    /**
     * Class constructor.
     *
     * @param clientHandler the client handler used to send messages to the client.
     */
    public VirtualView(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    /**
     * Returns the client handler associated to a client.
     *
     * @return client handler.
     */
    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    /**
     *  Sends a message from the server to the client to notify that the player
     *  has to choose whether the match will be played in expert mode.
     */
    @Override
    public void selectExpertMode(){
        clientHandler.sendAnswerMessage(new SelectExpertMode());
    }

    @Override
    public void showError(String errorMsg) {
        clientHandler.sendAnswerMessage(new Nack(errorMsg));
    }

    @Override
    public void notifyGameFull() {
        clientHandler.sendAnswerMessage(new GameFull());
    }

    /**
     *
     */
    @Override
    public void selectNickname() {
        clientHandler.sendAnswerMessage(new SelectNickname());
    }

    /**
     * Sends a message from the server to the client to notify that the player
     * has to select the number of player for this match.
     */
    @Override
    public void selectNumPlayers() {
        clientHandler.sendAnswerMessage(new SelectNumPlayers());
    }

    /**
     * Sends a message from the server to the client to notify that the player
     * has to select the wizard for this match.
     * @param availableWizards the wizards currently available.
     */
    @Override
    public void selectWizard(List<Wizard> availableWizards){
        clientHandler.sendAnswerMessage(new SelectWizard(availableWizards));
    }

    /**
     * Sends a message from the server to the client to notify that the player
     * has to select the color of his/her towers for this match.
     *
     * @param availableTowers the tower currently available.
     */
    @Override
    public void selectTowerColor(List<Color> availableTowers){
        clientHandler.sendAnswerMessage(new SelectColorTower(availableTowers));
    }

    /**
     * Sends a message from the server to the client to notify that the player
     * has to select the assistant card he/she wants to play in this round.
     *
     * @param availableAssistantCard the assistant card that the player can use.
     */
    @Override
    public void selectAssistantCard(List<Card> availableAssistantCard) {
        clientHandler.sendAnswerMessage(new SelectAssistantCard(availableAssistantCard));
    }

    /**
     * Sends a message from the server to the client to notify that the player
     * has to select the team which the player wants to belong.
     *
     * @param teamArray the other players' preference on team choice.
     * @param leaderArray the other players' preference on team leader choice.
     */
    @Override
    public void selectTeam(String[] teamArray, String[] leaderArray){
        clientHandler.sendAnswerMessage(new SelectTeam(teamArray, leaderArray));
    }

    /**
     * Sends a message from the server to the client to notify that game
     * has finished because a player has won the match.
     *
     * @param winner the winner of the match.
     */
    @Override
    public void sendWinner(String winner){
        clientHandler.sendAnswerMessage(new SendWinner(winner));
    }

    /**
     * Sends a message from the server to the client to notify all the
     * possible actions that the player can do in this phase.
     *
     * @param availableActions all the available actions.
     */
    @Override
    public void actionPhase(List<String> availableActions){
        clientHandler.sendAnswerMessage(new ActionPhase(availableActions));
    }

    /**
     * Sends a message from the server to the client to notify that
     * the player has to select a cloud.
     *
     */
    public void selectCloud(){
        clientHandler.sendAnswerMessage(new SelectCloud());
    }

    /**
     * Sends a message from the server to the client to notify that
     * the player has to wait for other players' actions.
     */
    @Override
    public void goToWaitingRoom() {
        clientHandler.sendAnswerMessage(new GoToWaitingRoom());
    }

    @Override
    public void goToLobby() {
        clientHandler.sendAnswerMessage(new GoToLobby());
    }

    @Override
    public void selectRestoreGame() {
        clientHandler.sendAnswerMessage(new SelectRestoreGame());
    }



    /*------------------ Model - View interaction ----------------------------------*/

    /**
     * Sends a message from the server to the client notifying that there has been a change in the model
     *
     * @param message the message to send
     */
    @Override
    public void update(Message message) {
        clientHandler.sendAnswerMessage((AnswerMessage) message);
    }

}



