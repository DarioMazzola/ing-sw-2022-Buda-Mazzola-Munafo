package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.BagException;
import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.NotEnoughStudentsOnCardException;
import it.polimi.ingsw.model.Bag;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Dashboard;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.characterCard.Jolly;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Jolly test class tests Jolly class.
 *
 * @author Alessio Buda
 * @see Jolly
 */
public class JollyTest {
    private Jolly jolly;
    private Map<House, Integer> wantedStudents;
    private Map<House, Integer> returnedStudents;
    private Dashboard playerDashboard;
    private static final Color towerColor = Color.WHITE;
    private static final int numMaxStudents = 7;
    private static final int numMaxTowers = 8;

    /**
     * Setups test.
     */
    @BeforeEach
    void setup() {
        try {
            jolly = new Jolly(new Bag());
        } catch (BagException e) {
            e.printStackTrace();
        }
        wantedStudents = new HashMap<>();
        for (House h : House.values()) {
            wantedStudents.put(h, 0);
        }
        returnedStudents = new HashMap<>();
        for (House h : House.values()) {
            returnedStudents.put(h, 0);
        }
        playerDashboard = new Dashboard(towerColor, numMaxStudents, numMaxTowers);
    }

    /**
     * Tears down test.
     */
    @AfterEach
    void tearDown () {
        jolly = null;
        wantedStudents = null;
        returnedStudents = null;
        playerDashboard = null;
    }

    /**
     * Verifies the correct functioning of doEffect method with "getStudents" parameter.
     * The test removes students pulled from bag to initialize the card and replaces them with six students of a known house.
     * It then verifies that the students on the card are the ones expected.
     */
    @Test
    void doEffectTest_get() {
        // getting students on card, they will be removed and changed with 6 students of a fixed house (in this example YELLOW)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "getStudents");
        try {
            jolly.doEffect(parameters);
        } catch (Exception e) {
            fail("Exception thrown during get method");
        }
        Map<House, Integer> studentsOnCard = new HashMap<>((HashMap<House, Integer>) parameters.get("studentsOnCard"));

        // preparing dashboard
        try {
            playerDashboard.addStudents(House.YELLOW, 6);
        } catch (EntranceException e) {
            e.printStackTrace();
            fail();
        }

        returnedStudents.replace(House.YELLOW, 3);
        for (int i = 0; i < 2; i++) { // 2 = numStudentsOnCard / 3
            int addedStudents = 0;
            for (House h : House.values()) {
                wantedStudents.replace(h, 0);
            }
            for (House h : House.values()) {
                while (addedStudents < 3 && studentsOnCard.get(h) > 0) {
                    studentsOnCard.replace(h, studentsOnCard.get(h) - 1);
                    wantedStudents.replace(h, wantedStudents.get(h) + 1);
                    addedStudents++;
                }
            }

            parameters.replace("method", "move");
            parameters.put("wantedStudents", wantedStudents);
            parameters.put("returnedStudents", returnedStudents);
            parameters.put("playerDashboard", playerDashboard);
            try {
                jolly.doEffect(parameters);
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        parameters.replace("method", "getStudents");
        try {
            jolly.doEffect(parameters);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
        studentsOnCard = (HashMap<House, Integer>) parameters.get("studentsOnCard");
        Map<House, Integer> expectedStudents = new HashMap<>();
        for (House h : House.values()){
            expectedStudents.put(h, 0);
        }
        expectedStudents.replace(House.YELLOW, 6);
        assertEquals(expectedStudents, studentsOnCard);
    }

    /**
     * Verifies the correct functioning of doEffect method with "move" parameter.
     * It checks that students initially on the card and the ones in player's dashboard have been actually swapped.
     * */
    @Test
    void doEffectTest_move() {
        // getting students on card, they will be removed and changed with 6 students of a fixed house (in this example YELLOW)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "getStudents");
        try {
            jolly.doEffect(parameters);
        } catch (Exception e) {
            fail("Exception thrown during get method");
        }
        Map<House, Integer> studentsOnCard = new HashMap<>((HashMap<House, Integer>) parameters.get("studentsOnCard"));
        Map<House, Integer> oldStudentsOnCard = new HashMap<>(studentsOnCard);
        // preparing dashboard
        try {
            playerDashboard.addStudents(House.YELLOW, 6);
        } catch (EntranceException e) {
            e.printStackTrace();
            fail();
        }

        returnedStudents.replace(House.YELLOW, 3);
        for (int i = 0; i < 2; i++) { // 2 = numStudentsOnCard / 3
            int addedStudents = 0;
            for (House h : House.values()) {
                wantedStudents.replace(h, 0);
            }
            for (House h : House.values()) {
                while (addedStudents < 3 && studentsOnCard.get(h) > 0) {
                    studentsOnCard.replace(h, studentsOnCard.get(h) - 1);
                    wantedStudents.replace(h, wantedStudents.get(h) + 1);
                    addedStudents++;
                }
            }

            parameters.replace("method", "move");
            parameters.put("wantedStudents", wantedStudents);
            parameters.put("returnedStudents", returnedStudents);
            parameters.put("playerDashboard", playerDashboard);
            try {
                jolly.doEffect(parameters);
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        parameters.replace("method", "getStudents");
        try {
            jolly.doEffect(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        Map<House, Integer> newStudentsOnCard = new HashMap<>((HashMap<House, Integer>) parameters.get("studentsOnCard"));
        Map<House, Integer> expected = new HashMap<>();
        for (House h : House.values()) {
            if (h == House.YELLOW)
                expected.put(h, 6);
            else
                expected.put(h, 0);
        }
        assertEquals(expected, newStudentsOnCard);
        assertEquals(oldStudentsOnCard, playerDashboard.getStudents());

    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "method" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoMethodParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", playerDashboard);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Missing parameter method", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "method" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullMethodParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", null);
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", playerDashboard);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Null parameter method", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "wantedStudents" parameter in parameters is missing and "method" is "move".
     */
    @Test
    void doEffectTest_NoWantedStudentsParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", playerDashboard);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Missing parameter wantedStudents", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "wantedStudents" parameter in parameters is null and "method" is "move".
     */
    @Test
    void doEffectTest_NullWantedStudentsParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedStudents", null);
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", returnedStudents);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Null parameter wantedStudents", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "returnedStudents" parameter in parameters is missing and "method" is "move".
     */
    @Test
    void doEffectTest_NoReturnedStudentsParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("playerDashboard", playerDashboard);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Missing parameter returnedStudents", thrown.getMessage());
    }

    /**
     * Verifies that a NullPointerException is thrown when "returnedStudents" parameter in parameters is null and "method" is "move".
     */
    @Test
    void doEffectTest_NullReturnedStudentsParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("returnedStudents", null);
        parameters.put("playerDashboard", returnedStudents);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Null parameter returnedStudents", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "playerDashboard" parameter in parameters is missing and "method" is "move".
     */
    @Test
    void doEffectTest_NoPlayerDashboardParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("returnedStudents", returnedStudents);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Missing parameter playerDashboard", thrown.getMessage());
    }

    /**
     * Verifies that a NullPointerException is thrown when "playerDashboard" parameter in parameters is null and "method" is "move".
     */
    @Test
    void doEffectTest_NullPlayerDashboardParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", null);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Null parameter playerDashboard", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalStateException is thrown when "method" parameter in parameters has an unexpected value (different from "getStudents" or "move").
     */
    @Test
    void doEffectTest_WrongMethodParameterGiven_ThrowsIllegalStateException() {
        Map<String, Object> parameters = new HashMap<>();
        String method = "otherMethod";
        parameters.put("method", method);
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", playerDashboard);

        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Unexpected value: " + method, thrown.getMessage());
    }

    /**
     * Verifies that a NotEnoughStudentsOnCard is thrown when "method" parameter in parameters is "move" and there are not enough students of a wanted house on the card.
     */
    @Test
    void doEffectTest_MoveWithNotEnoughStudents_ThrowsJollyException() {
        Map<String, Object> parameters = new HashMap<>();
        wantedStudents.put(House.YELLOW, 7);

        parameters.put("method", "move");
        parameters.put("wantedStudents", wantedStudents);
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", playerDashboard);
        NotEnoughStudentsOnCardException thrown = Assertions.assertThrows(NotEnoughStudentsOnCardException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Not enough students of" + House.YELLOW + "house on the card", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when the number of students to swap is greater than 3
     * or the number of wanted students is different from the number of returned students.
     */
    @Test
    void doEffectTest_MoveWithIllegalNumberOfStudents_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedStudents", wantedStudents);
        returnedStudents.put(House.YELLOW, 4);
        parameters.put("returnedStudents", returnedStudents);
        parameters.put("playerDashboard", playerDashboard);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> jolly.doEffect(parameters));
        Assertions.assertEquals("Number of students to swap is not legal", thrown.getMessage());
    }

}