package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.NotEnoughStudentsOnCardException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.MONK;

/**
 * Monk class represents monk character card.
 *
 * @author Alessio Buda
 */
public class Monk extends CharacterCard {
    private final Map<House, Integer> houseMap;

    /**
     * Class constructor, initializes card with name, initial cost and required students.
     *
     * @param bag bag from which students are pulled to initialize card
     */
    public Monk(Bag bag) throws BagException {
        super(1, "Monk", MONK);

        houseMap = new HashMap<>();
        for (House h : House.values()){
            houseMap.put(h, 0);
        }
        for (int i=0; i<4; i++) {
            House h = bag.pull();
            houseMap.replace(h, houseMap.get(h) + 1);
        }
    }

    /**
     * Receives parameters, saves them and calls the correct method  to perform the wanted operation.
     *
     * @param parameters map containing the necessary parameters to perform card operations
     * @throws IllegalArgumentException missing parameter in parameters
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws IllegalArgumentException, BagException, NotEnoughStudentsOnCardException {
        House wantedHouse;
        Island destinationIsland;
        Bag bag;

        List<String> moveParameters = new ArrayList<>();
        moveParameters.add("wantedHouse");
        moveParameters.add("destinationIsland");
        moveParameters.add("bag");

        if (!parameters.containsKey("method"))
            throw new IllegalArgumentException("Missing parameter method");
        if (parameters.get("method") == null)
            throw new NullPointerException("Null parameter method");
        String method = (String) parameters.get("method");
        switch (method) {
            case "getStudents":
                Map<House, Integer> studentsOnCard = new HashMap<>(this.getStudents());
                parameters.put("studentsOnCard", studentsOnCard);
                break;
            case "move":
                for (String s : moveParameters) {
                    if (!parameters.containsKey(s))
                        throw new IllegalArgumentException("Missing parameter " + s);
                    if (parameters.get(s) == null)
                        throw new NullPointerException("Null parameter " + s);
                }

                wantedHouse = (House) parameters.get("wantedHouse");
                destinationIsland = (Island) parameters.get("destinationIsland");
                bag = (Bag) parameters.get("bag");
                this.move(wantedHouse, destinationIsland, bag);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
    }

    /**
     * Returns a map with the number of students on the card for each house.
     *
     * @return map containing the number of students on the card for each house.
     */
    private Map<House, Integer> getStudents(){
        return new HashMap<>(houseMap);
    }
    /**
     * Removes the wanted student from the card, places it on the desired Island and refills the card from the Bag.
     *
     * @param  wantedHouse house of the students to be removed from card
     * @param destinationIsland island where the student will be placed
     * @param bag bag needed to refill the card
     */
    private void move(House wantedHouse, Island destinationIsland, Bag bag) throws BagException, NotEnoughStudentsOnCardException {
        if (houseMap.get(wantedHouse) < 1)
            throw new NotEnoughStudentsOnCardException("Not enough students of" + wantedHouse + " house on the card");
        House pulled = bag.pull();
        houseMap.replace(wantedHouse, houseMap.get(wantedHouse) - 1);
        destinationIsland.addStudents(wantedHouse, 1);
        houseMap.replace(pulled, houseMap.get(pulled) + 1);
    }

    @Override
    public String getDescription() {
        return "Take 1 student from this card and place on the island of your choice. Then draw a new Student from the Bag and place it on this card";
    }

    @Override
    public CharacterCardEnum getType() {
        return MONK;
    }
}
