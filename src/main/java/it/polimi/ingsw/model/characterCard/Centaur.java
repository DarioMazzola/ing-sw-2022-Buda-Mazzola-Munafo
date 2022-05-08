package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Player;

/**
 * Class that represent the Centaur character card
 * @author Gabriele Munafò
 */
public class Centaur extends CharacterCard {

    public Centaur() {
        super(3, "Centaur");
    }

    /**
     * Calculates the influence on a certain island, not considering the presence of towers
     * @param island where to calculate the influence
     * @param expertMode the mode the game is in
     * @param numPlayers the number of players
     * @param arrayPlayers the array of players
     * @return the player with highest influence
     */
    @Override
    public Player checkInfluence(Island island, Boolean expertMode, int numPlayers, Player[] arrayPlayers) {
        if (expertMode && island.isNoEntryTilePresent()) {
            return (null);
        }
        int[] influences;

        if (numPlayers == 2 || numPlayers == 3) {
            influences = new int[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                influences[i] = 0;
                for (House house : House.values()) {
                    if (arrayPlayers[i].getDashboard().isProfPresent(house)) {
                        influences[i] = influences[i] + island.getHouseStudents(house);
                    }
                }
            }

            int max = 0;
            boolean tie = false;

            for (int i = 1; i < numPlayers; i++) {
                if (influences[max] <= influences[i]) {
                    if (influences[max] == influences[i]) {
                        tie = true;
                    } else {
                        tie = false;
                        max = i;
                    }
                }
            }
            if (tie) {
                return (null);
            } else {
                return (arrayPlayers[max]);
            }
        }
        else {
            influences = new int[2];
            int q = 0;
            int teamOneLeader = 0;
            int teamTwoLeader = 0;
            int[] teamOne = new int[2];
            int[] teamTwo = new int[2];

            for (int i = 0; i < numPlayers; i++) {
                if (arrayPlayers[i].isTeamLeader() && q == 0) {
                    teamOneLeader = i;
                    teamOne[0] = i;
                    q++;
                } else if (arrayPlayers[i].isTeamLeader()) {
                    teamTwoLeader = i;
                    teamTwo[0] = i;
                    q = 0;
                    break;
                }
            }

            for (int i = 0; i < numPlayers; i++) {
                if (i != teamOneLeader && i != teamTwoLeader) {
                    if (arrayPlayers[i].getDashboard().getTowerColor().equals(arrayPlayers[teamOneLeader].getDashboard().getTowerColor())) {
                        teamOne[1] = i;
                    } else {
                        teamTwo[1] = i;
                    }
                }

            }

            for (int i = 0; i < 2; i++) {
                influences[i] = 0;

                for (House h : House.values()) {
                    if (i == 0) {
                        if (arrayPlayers[teamOne[0]].getDashboard().isProfPresent(h)) {
                            influences[i] += island.getHouseStudents(h);
                        }
                        if (arrayPlayers[teamOne[1]].getDashboard().isProfPresent(h)) {
                            influences[i] += island.getHouseStudents(h);
                        }
                    }
                    if (i == 1) {
                        if (arrayPlayers[teamTwo[0]].getDashboard().isProfPresent(h)) {
                            influences[i] += island.getHouseStudents(h);
                        }
                        if (arrayPlayers[teamTwo[1]].getDashboard().isProfPresent(h)) {
                            influences[i] += island.getHouseStudents(h);
                        }
                    }
                }
            }

            int max = 0;
            boolean tie = false;

            if (influences[max] <= influences[1]) {
                if (influences[max] == influences[1]) {
                    tie = true;
                } else {
                    max = 1;
                }
            }
            if (tie) {
                return (null);
            } else {
                if (max == 0) {
                    return (arrayPlayers[teamOneLeader]);
                } else {
                    return (arrayPlayers[teamTwoLeader]);
                }
            }
        }
    }
}
