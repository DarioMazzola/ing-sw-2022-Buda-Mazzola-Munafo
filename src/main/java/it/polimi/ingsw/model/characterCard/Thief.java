package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.StudentsTableException;
import it.polimi.ingsw.model.Bag;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Thief extends CharacterCard{

    /**
     * Class constructor, initializes card with name and initial cost.
     *
     */
    public Thief() {
        super(3, "Thief");
    }

    /**
     * Receives parameters, saves them and calls the correct method to perform the wanted operation.
     *
     * @param parameters map containing the necessary parameters to perform card operations
     * @throws IllegalArgumentException missing parameter in parameters
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws IllegalArgumentException{
        House wantedHouse;
        Player[] players;
        Bag bag;
        List<String> expectedParameters = new ArrayList<>();
        expectedParameters.add("wantedHouse");
        expectedParameters.add("players");
        expectedParameters.add("bag");

        for (String s : expectedParameters) {
            if (!parameters.containsKey(s))
                throw new IllegalArgumentException("Missing parameter " + s);
            if (parameters.get(s) == null)
                throw new NullPointerException("Null parameter " + s);
        }

        wantedHouse = (House) parameters.get("wantedHouse");
        players = (Player[]) parameters.get("players");
        bag = (Bag) parameters.get("bag");
        this.stealStudents(wantedHouse, players, bag);
    }

    /**
     * Removes 3 students of the selected house from every player's dining hall.
     * If less of 3 students are present, all the present students are removed
     *
     * @param wantedHouse house whose students will be removed from players' dining halls
     * @param players array of players
     * @param bag bag where the removed students will be added
     */
    private void stealStudents(House wantedHouse, Player[] players, Bag bag){
        for (Player p : players) {
            int numTotStudents = p.getDashboard().getDiningHall().getHouseStudents(wantedHouse);
            int numStudentsToRemove = Math.min(numTotStudents, 3);
            try {
                if (numStudentsToRemove != 0)
                    p.getDashboard().getDiningHall().removeStudents(wantedHouse, numStudentsToRemove);
            } catch (StudentsTableException e) {
                e.printStackTrace();
            }
            try {
                bag.addStudents(wantedHouse, numStudentsToRemove);
            } catch (BagException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getDescription() {
        return "Choose a type a student, every player (including yourself) must return 3 students of that type from their Dining Room the the bag. " +
                "If any player has fewer than 3 students of that type, return as many students as they have";
    }
}
