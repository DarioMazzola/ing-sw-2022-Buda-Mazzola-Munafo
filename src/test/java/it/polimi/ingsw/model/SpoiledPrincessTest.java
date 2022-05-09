package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.NotEnoughStudentsOnCardException;
import it.polimi.ingsw.exceptions.StudentsTableException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * SpoiledPrincessTest class test SpoiledPrincess class.
 *
 * @author Alessio Buda
 * @see SpoiledPrincess
 */
public class SpoiledPrincessTest {
    private static final int numPlayers = 2;
    private SpoiledPrincess spoiledPrincess;
    private Player currentPlayer;
    private Bag bag;

    /**
     * Setups test.
     */
    @BeforeEach
    void setup (){
        bag = new Bag();
        try {
            spoiledPrincess = new SpoiledPrincess(bag);
        } catch (BagException e) {
            e.printStackTrace();
            fail();
        }
        currentPlayer = new Player(numPlayers);
        currentPlayer.setDashboard(Color.WHITE);
    }

    /**
     * Tears down test.
     */
    @AfterEach
    void tearDown () {
        spoiledPrincess = null;
        currentPlayer = null;
        bag = null;
    }

    /**
     * Verifies that the students initially on the card are removed and placed in current player's dining hall.
     */
    @Test
    void doEffectTest_move() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "getStudents");
        try {
            spoiledPrincess.doEffect(parameters);
        } catch (BagException | StudentsTableException | NotEnoughStudentsOnCardException e) {
            e.printStackTrace();
            fail();
        }
        HashMap<House, Integer> studentsOnCard = new HashMap<>((HashMap<House, Integer>) parameters.get("studentsOnCard"));
        HashMap<House, Integer> oldStudentsOnCard = new HashMap<>(studentsOnCard);
        for (House h : House.values()) {
            while (studentsOnCard.get(h) > 0) {
                studentsOnCard.replace(h, studentsOnCard.get(h)-1);
                parameters.replace("method", "move");
                parameters.put("wantedHouse", h);
                parameters.put("currentPlayer", currentPlayer);
                parameters.put("bag", bag);
                try {
                    spoiledPrincess.doEffect(parameters);
                } catch (BagException | StudentsTableException | NotEnoughStudentsOnCardException e) {
                    e.printStackTrace();
                    fail();
                }
            }
        }
        for (House h : House.values()) {
            assertEquals(oldStudentsOnCard.get(h), currentPlayer.getDashboard().getDiningHall().getHouseStudents(h));
        }

    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "method" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoMethodParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("currentPlayer", currentPlayer);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> spoiledPrincess.doEffect(parameters));
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
        parameters.put("currentPlayer", currentPlayer);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> spoiledPrincess.doEffect(parameters));
        Assertions.assertEquals("Null parameter method", thrown.getMessage());
    }


    @Test
    void doEffectTest_NoWantedHouseParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("currentPlayer", currentPlayer);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> spoiledPrincess.doEffect(parameters));
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
        parameters.put("currentPlayer", currentPlayer);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> spoiledPrincess.doEffect(parameters));
        Assertions.assertEquals("Null parameter wantedHouse", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "currentPlayer" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoCurrentPlayerParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> spoiledPrincess.doEffect(parameters));
        Assertions.assertEquals("Missing parameter currentPlayer", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "currentPlayer" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullCurrentPlayerParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("currentPlayer", null);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> spoiledPrincess.doEffect(parameters));
        Assertions.assertEquals("Null parameter currentPlayer", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "bag" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoBagParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("currentPlayer", currentPlayer);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> spoiledPrincess.doEffect(parameters));
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
        parameters.put("currentPlayer", currentPlayer);
        parameters.put("bag", null);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> spoiledPrincess.doEffect(parameters));
        Assertions.assertEquals("Null parameter bag", thrown.getMessage());
    }

    /**
     * Verifies that a NotEnoughStudentsOnCard is thrown when "method" parameter in parameters is "move" and there are not enough students of a wanted house on the card.
     */
    @Test
    void doEffectTest_move_NotEnoughStudentsOnCard_ThrowsNotEnoughStudentsOnCardException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "getStudents");
        try {
            spoiledPrincess.doEffect(parameters);
        } catch (BagException | NotEnoughStudentsOnCardException | StudentsTableException e) {
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
        parameters.put("currentPlayer", currentPlayer);
        parameters.put("bag", bag);

        NotEnoughStudentsOnCardException thrown = Assertions.assertThrows(NotEnoughStudentsOnCardException.class, () -> spoiledPrincess.doEffect(parameters));
        Assertions.assertEquals("Not enough students of" + wantedHouse + " house on the card", thrown.getMessage());
    }
}
