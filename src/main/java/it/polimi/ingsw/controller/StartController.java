package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.command.*;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.polimi.ingsw.controller.GamePhase.*;
import static it.polimi.ingsw.controller.GameState.*;
import static it.polimi.ingsw.messages.TypeOfError.*;

/**
 * Class representing the start controller
 * @author Gabriele Munafo'
 */
public class StartController {
    private final GameModel gm;
    private final TurnController tc;
    private final String[] teamArray;
    private final String[] leaderArray;
    private final List<Wizard> availableWizards;
    private final List<Color> availableTowers;


    public StartController(GameModel gm, TurnController turnController) {
        this.teamArray = new String[2];
        this.leaderArray = new String[2];
        for (int i = 0; i < 2; i++) {
            this.teamArray[i] = null;
            this.leaderArray[i] = null;
        }
        this.availableWizards = new ArrayList<>();
        availableWizards.addAll(Arrays.asList(Wizard.values()));

        this.availableTowers = new ArrayList<>();
        availableTowers.addAll(Arrays.asList(Color.values()));

        this.tc = turnController;
        this.gm = gm;

    }

    /**
     * Gets called to manage the messages from the client
     * @param messageReceived received from the client
     * @param phase which selects the right case of the switch
     */
    public void doAction(CommandMessage messageReceived, GamePhase phase) {
        System.out.println(messageReceived.getNickname() + " is in start state");
        Player p;

        switch (phase) {
            case WIZARD:
                p = gm.getPlayerByNickname(messageReceived.getNickname());
                Wizard wizard = ((ChosenWizard) messageReceived).getWizard();

                if (wizardTaken(wizard)) {
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(WIZARD_TAKEN.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).selectWizard(availableWizards);
                    break;
                }

                p.setWizard(wizard);
                availableWizards.remove(wizard);

                if (4 - availableWizards.size() == gm.getNumPlayers()) {
                    if (gm.getNumPlayers() == 4) {
                        tc.next_Phase(SELECT_TEAM);
                        tc.sendAllSelectTeam(teamArray, leaderArray);
                    } else {
                        tc.next_Phase(SELECT_COLOR_TOWER);
                        tc.sendAllSelectTowerColor(availableTowers);
                    }
                } else {
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).goToWaitingRoom();
                }
                break;

            case SELECT_TEAM:
                boolean isTeamLeader = ((ChosenTeam) messageReceived).isTeamLeader();
                int team = (((ChosenTeam) messageReceived).getSelectedTeam()) - 1;

                if (team != 0 && team != 1) {
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(INVALID_TEAM.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).selectTeam(teamArray, leaderArray);
                    break;
                }

                if (isTeamLeader) {
                    if (this.leaderArray[team] == null) {
                        this.leaderArray[team] = messageReceived.getNickname();
                    } else if (this.teamArray[team] == null) {
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(LEADER_TAKEN.toString());
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).selectTeam(teamArray, leaderArray);
                        break;
                    } else {
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(TEAM_FULL.toString());
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).selectTeam(teamArray, leaderArray);
                        break;
                    }
                } else {
                    if (this.teamArray[team] == null) {
                        this.teamArray[team] = messageReceived.getNickname();
                    } else if (this.leaderArray[team] == null) {
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(TEAM_TAKEN.toString());
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).selectTeam(teamArray, leaderArray);
                        break;
                    } else {
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(TEAM_FULL.toString());
                        tc.getVirtualViewMap().get(messageReceived.getNickname()).selectTeam(teamArray, leaderArray);
                        break;
                    }
                }
                if (teamFull()) {
                    tc.next_Phase(SELECT_COLOR_TOWER);
                    tc.getVirtualViewMap().get(leaderArray[0]).selectTowerColor(availableTowers);
                    tc.getVirtualViewMap().get(leaderArray[1]).selectTowerColor(availableTowers);
                }
                break;

            case SELECT_COLOR_TOWER:
                System.out.println("stiamo scegliendo il colore delle torri");
                Color colorTower = ((ChosenTowerColor) messageReceived).getTowerColor();

                if (colorTowerTaken(colorTower)) {
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).showError(COLOR_TOWER_TAKEN.toString());
                    tc.getVirtualViewMap().get(messageReceived.getNickname()).selectTowerColor(availableTowers);
                    break;
                } else {
                    if (gm.getNumPlayers() == 4) {
                        if (leaderArray[0].equals(messageReceived.getNickname())) {
                            gm.getPlayerByNickname(leaderArray[0]).setDashboard(colorTower, leaderArray[0]);
                            gm.getPlayerByNickname(teamArray[0]).setDashboard(colorTower, teamArray[0]);
                        } else {
                            gm.getPlayerByNickname(leaderArray[1]).setDashboard(colorTower, leaderArray[1]);
                            gm.getPlayerByNickname(teamArray[1]).setDashboard(colorTower, teamArray[1]);
                        }
                    } else {
                        gm.getPlayerByNickname(messageReceived.getNickname()).setDashboard(colorTower, messageReceived.getNickname());
                    }
                    availableTowers.remove(colorTower);
                }
                if (colorFull()) {
                    tc.next_State(PLANNING);

                    try {
                        gm.refillClouds();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    tc.sendAllModel();
                    tc.getVirtualViewMap().get(gm.getArrayPlayers()[0].getNickname()).selectAssistantCard(Arrays.asList(Card.values()));
                }
                break;

            default:
                System.err.println("Error in StartController switch");
        }
    }

    public String toString() {
        return "Start Controller";
    }

    /**
     * Checks if the wizard is already been taken by another player
     * @param wizard that has to be checked
     */
    private boolean wizardTaken(Wizard wizard) {
        for (int i = 0; i < gm.getNumPlayers(); i++) {
            if (wizard.equals(gm.getArrayPlayers()[i].getWizard())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the color tower is already been taken by another player
     * @param color that has to be checked
     */
    private boolean colorTowerTaken(Color color) {
        for (int i = 0; i < gm.getNumPlayers(); i++) {
            if (gm.getArrayPlayers()[i].getDashboard() != null && color.equals(gm.getArrayPlayers()[i].getDashboard().getTowerColor())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the teams are full
     */
    private boolean teamFull() {
        for (int i = 0; i < 2; i++) {
            if (leaderArray[i] == null || teamArray[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if every player has selected the color
     */
    private boolean colorFull() {
        for (int i = 0; i < gm.getNumPlayers(); i++) {
            if (gm.getArrayPlayers()[i].getDashboard() == null) {
                return false;
            }
        }
        return true;
    }
}
