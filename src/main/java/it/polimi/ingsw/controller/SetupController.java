package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.messages.command.*;

import static it.polimi.ingsw.controller.GamePhase.*;
import static it.polimi.ingsw.controller.GameState.*;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the setup controller
 *
 * @author Gabriele Munafo'
 */
public class SetupController {
    private int numPlayer;
    private boolean expertMode;
    private Boolean chat;
    private String nickname;


    public SetupController() {
        this.chat = null;
        this.expertMode = false;
        this.numPlayer = 2;
    }

    /**
     * Gets called to manage the messages from the client
     *
     * @param messageReceived received from the client
     * @param phase           which selects the right case of the switch
     */
    public void doAction(CommandMessage messageReceived, GamePhase phase, TurnController tc) {
        System.out.println("\n" + messageReceived.getNickname() + " is in setup state\n");

        switch (phase) {
            case CREATE_GAME:
                this.nickname = messageReceived.getNickname();
                tc.next_Phase(NUM_PLAYERS);
                tc.getVirtualViewMap().get(nickname).selectNumPlayers();
                break;

            case NUM_PLAYERS:
                int num = ((ChosenNumPlayers) messageReceived).getNumPlayers();
                if (num < 2 || num > 4) {
                    tc.getVirtualViewMap().get(nickname).showError(INVALID_NUM_PLAYERS.toString());
                    tc.getVirtualViewMap().get(nickname).selectNumPlayers();
                    break;
                }
                this.numPlayer = num;
                if (num == 4) {
                    tc.next_Phase(CHAT);
                    tc.getVirtualViewMap().get(nickname).selectChat();
                    break;
                }
                tc.next_Phase(EXPERT_MODE);
                tc.getVirtualViewMap().get(nickname).selectExpertMode();
                break;

            case CHAT:
                this.chat = ((ChosenChat) messageReceived).isChat();
                tc.next_Phase(EXPERT_MODE);
                tc.getVirtualViewMap().get(nickname).selectExpertMode();
                break;

            case EXPERT_MODE:
                this.expertMode = ((ChosenExpertMode) messageReceived).isExpertMode();
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

    public int getNumPlayer() {
        return numPlayer;
    }

    public boolean isExpertMode() {
        return expertMode;
    }

    public String getNickname() {
        return nickname;
    }

    public Boolean getChat() {
        return chat;
    }
}
