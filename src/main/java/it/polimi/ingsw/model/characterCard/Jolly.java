package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.JollyException;
import it.polimi.ingsw.model.Bag;
import it.polimi.ingsw.model.Dashboard;
import it.polimi.ingsw.model.House;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;

/**
 * Jolly class represents jolly character card.
 *
 * @author Alessio Buda
 */
public class Jolly extends CharacterCard{
    private final Map<House, Integer> houseMap;

    /**
     * Class constructor, initializes card with name, initial cost and required students.
     *
     * @param bag bag from which students are pulled to initialize card
     */
    public Jolly(Bag bag) throws BagException {
        super(1, "Jolly");
        houseMap = new HashMap<>();
        for (House h : House.values()){
            houseMap.put(h, 0);
        }
        for (int i=0; i<6; i++) {
            House h = bag.pull();
            houseMap.replace(h, houseMap.get(h) + 1);
        }
    }

    /**
     * Receives parameters, saves them and calls the correct method to perform the wanted operation.
     *
     * @param parameters A map that contains the objects that need to the characterCards and the objects that must be returned
     * @throws IllegalArgumentException when a parameter is missing
     * @throws JollyException when the number of wanted students is not on the card
     * @throws EntranceException when students cannot be added to the dashboard's entrance because it is full (method "move")
     * @throws IllegalChoiceException when students cannot be removed from the entrance
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception {
        super.doEffect(parameters);

        HashMap<House, Integer> wantedStudents;
        HashMap<House, Integer> returnedStudents;
        Dashboard playerDashboard;

        List<String> moveParameters = new ArrayList<>();
        moveParameters.add("wantedStudents");
        moveParameters.add("returnedStudents");
        moveParameters.add("playerDashboard");

        if (!parameters.containsKey("method"))
            throw new IllegalArgumentException("Missing parameter method");
        if (parameters.get("method") == null)
            throw new NullPointerException("Null parameter method");
        String method = (String) parameters.get("method");
        switch (method) {
            case "getStudents":
                parameters.put("studentsOnCard", getStudents());
                break;
            case "move":
                for (String s : moveParameters) {
                    if (!parameters.containsKey(s))
                        throw new IllegalArgumentException("Missing parameter " + s);
                    if (parameters.get(s) == null)
                        throw new NullPointerException("Null parameter " + s);
                }

                wantedStudents = (HashMap<House, Integer>) parameters.get("wantedStudents");
                returnedStudents = (HashMap<House, Integer>) parameters.get("returnedStudents");
                playerDashboard = (Dashboard) parameters.get("playerDashboard");
                this.move(wantedStudents, returnedStudents, playerDashboard);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }

    }

    /**
     * Returns a map with the number of students on the card for each house.
     *
     * @return map containing the number of students on the card for each house
     */
    private Map<House, Integer> getStudents(){
        return new HashMap<>(houseMap);
    }

    /**
     * Moves students from card to player's dashboard and adds students removed from dashboard to the card.
     *
     * @param wantedStudents wantedStudents students the player wants to pick from the card
     * @param returnedStudents returnedStudents students the player wants to return to the card
     * @param dashboard dashboard player's dashboard
     * @throws JollyException when the number of students to swap is greater than 3 or the number of wanted students is different from the number of returned students.
     * @throws EntranceException when students cannot be added to the dashboard's entrance because it is full
     * @throws IllegalChoiceException when students cannot be removed from the entrance
     */
    private void move(Map<House, Integer> wantedStudents, Map<House, Integer> returnedStudents, Dashboard dashboard) throws JollyException, EntranceException, IllegalChoiceException {
        int numWantedStudents = 0;
        int numReturnedStudents = 0;

        // checks
        for (House h : House.values()) {
            if (houseMap.get(h) < wantedStudents.get(h))
                throw new JollyException("Not enough students of" + h + "house on the card");
            numWantedStudents+= wantedStudents.get(h);
            numReturnedStudents+= returnedStudents.get(h);
        }
        if (numReturnedStudents>3 || numWantedStudents>3 || numReturnedStudents!=numWantedStudents)
            throw new IllegalArgumentException("Number of students to swap is not legal");

        for (House h :House.values()) {
            if (returnedStudents.get(h) != 0) {
                dashboard.removeStudents(h, returnedStudents.get(h));
            }
            dashboard.addStudents(h, wantedStudents.get(h));
            houseMap.replace(h, houseMap.get(h) - wantedStudents.get(h));
            houseMap.replace(h, houseMap.get(h) + returnedStudents.get(h));
        }
    }

    @Override
    public String getDescription() {
        return "You may take up to 3 Students from this card and replace them with the same number of Students" +
                " from your Entrance";
    }
}
