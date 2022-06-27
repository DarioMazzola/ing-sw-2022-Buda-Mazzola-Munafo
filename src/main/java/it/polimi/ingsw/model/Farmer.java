package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.IllegalChoiceException;

import static it.polimi.ingsw.model.CharacterCardEnum.FARMER;

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
        super(2, "Farmer", FARMER);
    }

    /**
     * Checks if a professor should be given to the current Player.
     * If the professor belongs to some other player and the current player has at least the number of students
     * of the professor's color, the professor is removed from his dashboard and assigned to the current player.
     * If the professor does not belong to anyone and the current player has at least one player,
     * then the professor is assigned to the current player
     */
    protected void checkProf(Player[] players, Player currentPlayer, House house) throws IllegalChoiceException {

        Player owner = null;
        Player playerWithMostStudents;
        int numPlayerI;
        int maxStudentsNumber;

        for(Player p : players){
            if (p.getDashboard().isProfPresent(house)) {
                owner = p;
                break;
            }
        }
        maxStudentsNumber = (owner == null) ? 0 : owner.getDashboard().getHouseStudents(house);
        playerWithMostStudents = owner;

        for (Player p : players) {
            if(! p.equals(owner)) {
                numPlayerI = p.getDashboard().getDiningHall().getHouseStudents(house);

                if (numPlayerI > maxStudentsNumber) {
                    playerWithMostStudents = p;
                    maxStudentsNumber = numPlayerI;
                }
            }
        }

        if(maxStudentsNumber > 0 && playerWithMostStudents.equals(currentPlayer)) {

            if(owner == null){
                currentPlayer.getDashboard().addProf(house);
            }
            else if(!owner.equals(playerWithMostStudents) &&
                    currentPlayer.getDashboard().getDiningHall().getHouseStudents(house) >= owner.getDashboard().getDiningHall().getHouseStudents(house)) {
                moveProf(owner.getDashboard(), currentPlayer.getDashboard(), house);
            }
        }

    }

    @Override
    public String getDescription() {
        return "During this turn, you take control of any number of Professors even if you have \n" +
                "the same number of Students as the player who currently controls them";
    }

    @Override
    public CharacterCardEnum getType() {
        return FARMER;
    }
}
