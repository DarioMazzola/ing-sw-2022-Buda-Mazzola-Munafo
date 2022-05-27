package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.messages.command.*;
import it.polimi.ingsw.server.ClientHandler;

import static it.polimi.ingsw.controller.GamePhase.*;
import static it.polimi.ingsw.controller.GameState.*;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the setup controller
 * @author Gabriele Munafo'
 */
public class SetupController{
    private int numPlayer;
    private boolean expertMode;
    private String nickname;
    private final TurnController tc;


    public SetupController(TurnController turnController){
        this.tc = turnController;
        this.expertMode = false; //valori di default
        this.numPlayer = 2; //valori di default
    }

    /**
     * Gets called to manage the messages from the client
     * @param messageReceived received from the client
     * @param phase which selects the right case of the switch
     * @param clientHandler related to the client
     */
    public void doAction(CommandMessage messageReceived, GamePhase phase, ClientHandler clientHandler) {
        System.out.println(messageReceived.getNickname() + "is in setup state");

        switch (phase) {
            case CREATE_GAME:
                this.nickname = messageReceived.getNickname();
                tc.loginHandler(nickname, clientHandler);
                tc.next_Phase(NUM_PLAYERS);
                tc.getVirtualViewMap().get(nickname).selectNumPlayers();
                break;

            case NUM_PLAYERS:
                int num = ((ChosenNumPlayers)messageReceived).getNumPlayers();
                if (num<2 || num>4){
                    tc.getVirtualViewMap().get(nickname).showError(INVALID_NUM_PLAYERS.toString());
                    tc.getVirtualViewMap().get(nickname).selectNumPlayers();
                    break;
                }
                this.numPlayer = num;
                tc.next_Phase(EXPERT_MODE);
                tc.getVirtualViewMap().get(nickname).selectExpertMode();
                break;

            case EXPERT_MODE:
                this.expertMode = ((ChosenExpertMode)messageReceived).isExpertMode();
                try {
                    tc.init();
                } catch (EntranceException | BagException e) {
                    e.printStackTrace();
                }
                tc.next_State(START);
                tc.next_Phase(WIZARD);
                break;

            default:
                System.err.println("Error in SetupController switch");
        }
    }

    public int getNumPlayer(){
        return numPlayer;
    }

    public boolean isExpertMode(){
        return expertMode;
    }

    public String getNickname(){
        return nickname;
    }
}
