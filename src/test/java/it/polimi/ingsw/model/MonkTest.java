package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.NotEnoughStudentsOnCardException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * MonkTest class tests Monk class.
 *
 * @author Alessio Buda
 * @see Monk
 */
public class MonkTest {
    private Monk monk;
    private Island destinationIsland;
    private Bag bag;

    /**
     *  Setups test.
     */
    @BeforeEach
    void setup (){
        bag = new Bag();
        try {
            monk = new Monk(bag);
        } catch (BagException e) {
            e.printStackTrace();
            fail();
        }
        boolean expertMode = true;
        destinationIsland = new Island(expertMode);
    }

    /**
     *  Tears down test.
     */
    @AfterEach
    void tearDown () {
        monk = null;
        destinationIsland = null;
        bag = null;
    }

    /**
     * Verifies the correct functioning of doEffect method with "move" parameter.
     * It removes students initially on the card and places them on an island.
     * Finally, it checks that students on the island correspond to those previously on the card.
     */
    @Test
    void doEffectTest_move() {
        // gets students on card
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "getStudents");
        try {
            monk.doEffect(parameters);
        } catch (BagException | NotEnoughStudentsOnCardException e) {
            e.printStackTrace();
            fail();
        }

        // saves students on card and a copy
        HashMap<House, Integer> studentsOnCard = new HashMap<>((HashMap<House, Integer>) parameters.get("studentsOnCard"));
        HashMap<House, Integer> oldStudentsOnCard = new HashMap<>(studentsOnCard);

        // sets parameters
        parameters.replace("method", "move");
        parameters.put("destinationIsland", destinationIsland);
        parameters.put("bag", bag);
        for (House h : House.values()) {
            while (studentsOnCard.get(h) > 0) {
                parameters.put("wantedHouse", h);
                studentsOnCard.replace(h, studentsOnCard.get(h)-1);
                try {
                    monk.doEffect(parameters);
                } catch (BagException | NotEnoughStudentsOnCardException e) {
                    e.printStackTrace();
                    fail();
                }
            }
        }
        for (House h : House.values()) {
            assertEquals(oldStudentsOnCard.get(h), destinationIsland.getHouseStudents(h));
        }
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "method" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoMethodParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("destinationIsland", destinationIsland);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Missing parameter method", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "method" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullMethodParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", null);
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("destinationIsland", destinationIsland);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Null parameter method", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "wantedHouse" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoWantedHouseParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("destinationIsland", destinationIsland);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Missing parameter wantedHouse", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "wantedHouse" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullWantedHouseParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", null);
        parameters.put("destinationIsland", destinationIsland);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Null parameter wantedHouse", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "destinationIsland" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoDestinationIslandParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Missing parameter destinationIsland", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "destinationIsland" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullDestinationIslandParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("destinationIsland", null);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Null parameter destinationIsland", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "bag" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoBagParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("destinationIsland", destinationIsland);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Missing parameter bag", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "bag" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullBagParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("destinationIsland", destinationIsland);
        parameters.put("bag", null);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Null parameter bag", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalStateException is thrown when "method" parameter in parameters has an unexpected value (different from "getStudents" or "move").
     */
    @Test
    void doEffectTest_WrongMethodParameterGiven_ThrowsIllegalStateException() {
        Map<String, Object> parameters = new HashMap<>();
        String method = "otherMethod";
        parameters.put("method", method);
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("destinationIsland", destinationIsland);

        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Unexpected value: " + method, thrown.getMessage());
    }

    /**
     * Verifies that a NotEnoughStudentsOnCard is thrown when "method" parameter in parameters is "move" and there are not enough students of a wanted house on the card.
     */
    @Test
    void doEffectTest_move_NotEnoughStudentsOnCard_ThrowsNotEnoughStudentsOnCardException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "getStudents");
        try {
            monk.doEffect(parameters);
        } catch (BagException | NotEnoughStudentsOnCardException e) {
            e.printStackTrace();
            fail();
        }
        HashMap<House, Integer> studentsOnCard = new HashMap<>((HashMap<House, Integer>) parameters.get("studentsOnCard"));
        int i = 0;
        House wantedHouse = null;
        boolean emptyHouseFound = false;
        do {
            House h = House.values()[i];
            if (studentsOnCard.get(h) == 0) {
                emptyHouseFound = true;
                wantedHouse = h;
            }
            i++;
        } while (!emptyHouseFound);
        parameters.replace("method", "move");
        parameters.put("wantedHouse", wantedHouse);
        parameters.put("destinationIsland", destinationIsland);
        parameters.put("bag", bag);

        NotEnoughStudentsOnCardException thrown = Assertions.assertThrows(NotEnoughStudentsOnCardException.class, () -> monk.doEffect(parameters));
        Assertions.assertEquals("Not enough students of" + wantedHouse + " house on the card", thrown.getMessage());
    }
}
