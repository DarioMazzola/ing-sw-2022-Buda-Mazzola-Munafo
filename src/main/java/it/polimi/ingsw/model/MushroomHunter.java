package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.IllegalChoiceException;

import java.util.Map;

/**
 * Class that represent the MushroomHunter character card
 * @author Gabriele Munafò
 */
public class MushroomHunter extends CharacterCard{

    private House houseToIgnore = null;

    public MushroomHunter() {
        super(3, "MushroomHunter");
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

        Player player = super.checkInfluence(island, expertMode, numPlayers, arrayPlayers);

        if (useful) {
            arrayPlayers[giveBack].getDashboard().addProf(houseToIgnore);
        }
        return (player);
    }

    @Override
    public String getDescription() {
        return "Choose a color of Student: during the influence calculation this, that color adds no influence";
    }
}