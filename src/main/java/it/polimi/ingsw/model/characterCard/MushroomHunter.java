package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Player;

import java.util.Map;

/**
 * Class that represent the MushroomHunter character card
 * @author Gabriele Munafò
 */
public class MushroomHunter extends CharacterCard{

    private House houseToIgnore = null;

    public MushroomHunter(int cost) {
        super(cost, "MushroomHunter");
    }

    /**
     * Temporary removes a specified professor from a player, calculates the influence and then gives the professor back
     * @param parameters the map containing the house to ignore, the island where to calculate the influence, the number of players and the array of players
     * @throws NullPointerException when parameters is null or when house, arrayPlayers or island are null
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception {
        super.doEffect(parameters);

        if (parameters == null) {
            throw new NullPointerException();
        }

        houseToIgnore = (House) parameters.get("House");

        if (houseToIgnore == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Calculates the influence on a certain island, removing a certain professor
     * @param island where to calculate the influence
     * @param expertMode the mode the game is in
     * @param numPlayers the number of players
     * @param arrayPlayers the array of players
     * @return the player with highest influence
     */
    @Override
    public Player checkInfluence(Island island, Boolean expertMode, int numPlayers, Player[] arrayPlayers) {
        boolean useful = false;
        int giveBack = 0;

        for (int i = 0; i < numPlayers; i++) {
            if (arrayPlayers[i].getDashboard().isProfPresent(houseToIgnore)) {
                useful = true;
                giveBack = i;
                try {
                    arrayPlayers[i].getDashboard().removeProf(houseToIgnore);
                } catch (IllegalChoiceException e) {
                    e.printStackTrace();
                }
            }
        }

        Player player = checkInf(island, expertMode, numPlayers, arrayPlayers);

        if (useful) {
            arrayPlayers[giveBack].getDashboard().addProf(houseToIgnore);
        }
        return (player);
    }

    private Player checkInf(Island island, boolean expertMode, int numPlayers, Player[] arrayPlayers){
        if (expertMode && island.isNoEntryTilePresent()){
            return(null);
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
                try{
                    if ((arrayPlayers[i].getDashboard().getTowerColor()).equals(island.getColorTower())) {
                        influences[i] = influences[i] + island.getNumTower();
                    }
                }
                catch (IslandException e){
                    influences[i] = influences[i];
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
        //four players
        else {
            influences = new int[2];
            int q = 0;
            int teamOneLeader = 0;
            int teamTwoLeader = 0;
            int[] teamOne = new int[2];
            int[] teamTwo = new int[2];

            for(int i=0; i<numPlayers; i++) {
                if(arrayPlayers[i].isTeamLeader() && q ==0) {
                    teamOneLeader = i;
                    teamOne[0] = i;
                    q++;
                }
                else if(arrayPlayers[i].isTeamLeader()) {
                    teamTwoLeader = i;
                    teamTwo[0] = i;
                    q = 0;
                    break;
                }
            }

            for(int i=0; i<numPlayers; i++) {
                if (i != teamOneLeader && i != teamTwoLeader) {
                    if (arrayPlayers[i].getDashboard().getTowerColor().equals(arrayPlayers[teamOneLeader].getDashboard().getTowerColor())) {
                        teamOne[1] = i;
                    } else {
                        teamTwo[1] = i;
                    }
                }

            }

            boolean towerAdded = false;

            for(int i=0; i<2; i++){
                influences[i] = 0;

                for(House h : House.values()) {
                    if (i == 0) {
                        if(arrayPlayers[teamOne[0]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        if(arrayPlayers[teamOne[1]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        try {
                            if (!towerAdded && arrayPlayers[teamOne[0]].getDashboard().getTowerColor().equals(island.getColorTower())) {
                                influences[i] = influences[i] + island.getNumTower();
                                towerAdded = true;
                            }
                        }
                        catch (IslandException e){
                            influences[q] = influences[q];
                        }

                    }
                    if (i == 1) {
                        if(arrayPlayers[teamTwo[0]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        if(arrayPlayers[teamTwo[1]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        try {
                            if (!towerAdded && arrayPlayers[teamTwo[0]].getDashboard().getTowerColor().equals(island.getColorTower())) {
                                influences[i] = influences[i] + island.getNumTower();
                                towerAdded = true;
                            }
                        }
                        catch (IslandException e){
                            influences[q] = influences[q];
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
                if (max == 0){
                    return (arrayPlayers[teamOneLeader]);
                } else {
                    return (arrayPlayers[teamTwoLeader]);
                }
            }
        }
    }
}