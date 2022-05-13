package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.IslandException;

import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.KNIGHT;

/**
 * Class that represent the Knight character card
 * @author Gabriele Munafò
 */
public class Knight extends CharacterCard {

    private Player currentPlayer = null;

    public Knight() {
        super(2, "Knight", KNIGHT);
    }

    /**
     * Calculates the influence on a certain island, using a different version of checkInfluence
     * @param parameters the map containing the island where to calculate the influence
     * @throws NullPointerException when parameters is null or when island is null
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception{
        super.doEffect(parameters);

        if (parameters == null){
            throw new NullPointerException();
        }
        currentPlayer = (Player) parameters.get("CurrentPlayer");
        if (currentPlayer == null){
            throw new NullPointerException();
        }
    }



    /**
     * Calculates the influence on a certain island, giving 2 extra points to the player who played the card
     * @param island where to calculate the influence
     * @param expertMode the mode the game is in
     * @param numPlayers the number of players
     * @param arrayPlayers the array of players
     * @return the player with highest influence
     */
    @Override
    public Player checkInfluence(Island island, Boolean expertMode, int numPlayers, Player[] arrayPlayers, CharacterCard[] characterCardDeck) throws Exception {
        if (expertMode && island.isNoEntryTilePresent()){
            removeNoEntryTile(island, characterCardDeck);
            return(null);
        }
        int[] influences;

            if (numPlayers == 2 || numPlayers == 3) {
            influences = new int[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                influences[i] = 0;
                if (arrayPlayers[i].equals(currentPlayer)) {
                    influences[i] = influences[i] + 2;
                }
                for (House house : House.values()) {
                    if (arrayPlayers[i].getDashboard().isProfPresent(house)) {
                        influences[i] = influences[i] + island.getHouseStudents(house);
                    }
                }
                try{
                    if ((arrayPlayers[i].getDashboard().getTowerColor()).equals(island.getColorTower())) {
                        influences[i] = influences[i] + island.getNumTowers();
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
            boolean added = false;

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
                                influences[i] = influences[i] + island.getNumTowers();
                                towerAdded = true;
                            }
                        }
                        catch (IslandException e){
                            influences[q] = influences[q];
                        }

                        if (!added && (arrayPlayers[teamOne[0]].equals(currentPlayer) || arrayPlayers[teamOne[1]].equals(currentPlayer))) {
                            influences[i] = influences[i] + 2;
                            added = true;
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
                                influences[i] = influences[i] + island.getNumTowers();
                                towerAdded = true;
                            }
                        }
                        catch (IslandException e){
                            influences[q] = influences[q];
                        }
                        if (!added && (arrayPlayers[teamTwo[0]].equals(currentPlayer) || arrayPlayers[teamTwo[1]].equals(currentPlayer))) {
                            influences[i] = influences[i] + 2;
                            added = true;
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

    @Override
    public String getDescription() {
        return "During the influence calculation this turn, you count as having 2 more influence";
    }

    @Override
    public CharacterCardEnum getType() {
        return KNIGHT;
    }

}

