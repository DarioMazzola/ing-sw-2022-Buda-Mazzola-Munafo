package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Player;

import java.util.Map;

/**
 * Farmer class represents farmer character card.
 *
 * @author Dario Mazzola
 */
public class Farmer extends CharacterCard {

    /**
     * Class constructor, initializes card with name and initial cost.
     */
    public Farmer() {
        super(2, "Farmer");
    }

    /**
     * Checks if a professor should be given to the current Player.
     * If the professor belongs to some other player and the current player has at least the number of students
     * of the professor's color, the professor is removed from his dashboard and assigned to the current player.
     * If the professor does not belong to anyone and the current player has at least one player,
     * then the professor is assigned to the current player
     */
    @Override
    protected void checkProf(Player[] players, Player currentPlayer, House house) throws IllegalChoiceException {
        Player owner = null;
        Player playerWithMostStudents = null;
        int numPlayerI;
        int maxStudentsNumber = 0;

        //finds the player with the most students for that given house as a parameter
        for (Player p : players) {
            numPlayerI = p.getDashboard().getHouseStudents(house);

            if (numPlayerI > maxStudentsNumber) {
                playerWithMostStudents = p;
                maxStudentsNumber = numPlayerI;
            }
            if (p.getDashboard().isProfPresent(house))
                owner = p;
        }

        if(maxStudentsNumber > 0 && playerWithMostStudents.equals(currentPlayer)) {
            if(owner == null){
                currentPlayer.getDashboard().addProf(house);
            }
            else if(!owner.equals(currentPlayer) &&
                    currentPlayer.getDashboard().getHouseStudents(house) >= playerWithMostStudents.getDashboard().getHouseStudents(house)) {
                moveProf(owner.getDashboard(), currentPlayer.getDashboard(), house);
            }
        }

    }

    @Override
    public String getDescription() {
        return "During this turn, you take control of any number of Professors even if you have " +
                "the same number of Students as the player who currently controls them";
    }
}
