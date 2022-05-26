package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
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
 * ThiefTest class tests Thief class.
 */
public class ThiefTest {
    private Thief thief;
    private House wantedHouse;
    private Player[] players;
    private Bag bag;

    /**
     * Setups test.
     */
    @BeforeEach
    void setup() {
        thief = new Thief();
        wantedHouse = House.YELLOW;
        players = new Player[]{new Player(2), new Player(2)};
        int i = 0;
        for (Player p : players) {
            p.setDashboard(Color.values()[i], "p");
            i++;
        }
        bag = new Bag();
    }

    /**
     * Tears down test.
     */
    @AfterEach
    void tearDown() {
        thief = null;
        wantedHouse = null;
        players = null;
        bag = null;
    }

    /**
     * Verifies that no students are removed from players' dashboard when there are no students of the selected house.
     * The number of students of the selected house in the bag remains unchanged.
     */
    @Test
    void doEffectTest_PlayersNoStudentsOfTheHouse() {
        Map<String, Object> parameters = new HashMap<>();

        for (Player p : players) {
            for (House h : House.values()) {
                if (h == wantedHouse) {
                    try {
                        p.getDashboard().getDiningHall().addStudents(h, 0);
                    } catch (StudentsTableException e) {
                        e.printStackTrace();
                        fail();
                    }
                } else
                    try {
                        p.getDashboard().getDiningHall().addStudents(h, 1);
                    } catch (StudentsTableException e) {
                        e.printStackTrace();
                        fail();
                    }
            }
        }
        Map<House, Integer> oldNumOfStudentsInBag = new HashMap<>();
        for (House h : House.values()) {
            oldNumOfStudentsInBag.put(h, bag.getHouseStudents(h));
        }

        parameters.put("wantedHouse", wantedHouse);
        parameters.put("players", players);
        parameters.put("bag", bag);
        thief.doEffect(parameters);

        for (Player p : players) {
            for (House h : House.values()) {
                if (h == wantedHouse) {
                    assertEquals(0, p.getDashboard().getDiningHall().getHouseStudents(h));
                } else {
                    assertEquals(1, p.getDashboard().getDiningHall().getHouseStudents(h));
                }
                assertEquals(oldNumOfStudentsInBag.get(h), bag.getHouseStudents(h));
            }
        }
    }

    /**
     * Verifies that when there are less than three students in players' dashboard they are all removed and
     * reinserted in the bag.
     */
    @Test
    void doEffectTest_PlayersWithLessThanThreeStudentsOfTheHouse() {
        Map<String, Object> parameters = new HashMap<>();

        for (Player p : players) {
            for (House h : House.values()) {
                if (h == wantedHouse) {
                    try {
                        p.getDashboard().getDiningHall().addStudents(h, 2);
                    } catch (StudentsTableException e) {
                        e.printStackTrace();
                        fail();
                    }
                } else
                    try {
                        p.getDashboard().getDiningHall().addStudents(h, 1);
                    } catch (StudentsTableException e) {
                        e.printStackTrace();
                        fail();
                    }
            }
        }

        for (int i = 0; i < 2 * players.length; i++) { // removes from bag students added to players' dining halls
            House pulled = null;
            do {
                try {
                    pulled = bag.pull();
                } catch (BagException e) {
                    e.printStackTrace();
                    fail();
                }
                if (pulled != wantedHouse) {
                    try {
                        bag.addStudents(pulled, 1);
                    } catch (BagException e) {
                        e.printStackTrace();
                        fail();
                    }
                }
            } while (pulled != wantedHouse);
        }

        Map<House, Integer> oldStudentsInBag = new HashMap<>();
        for (House h : House.values()) {
            oldStudentsInBag.put(h, bag.getHouseStudents(h));
        }

        parameters.put("wantedHouse", wantedHouse);
        parameters.put("players", players);
        parameters.put("bag", bag);
        thief.doEffect(parameters);

        for (Player p : players) {
            for (House h : House.values()) {
                if (h == wantedHouse) {
                    assertEquals(0, p.getDashboard().getDiningHall().getHouseStudents(h));
                    assertEquals(oldStudentsInBag.get(h) + (2 * players.length), bag.getHouseStudents(h));
                } else {
                    assertEquals(1, p.getDashboard().getDiningHall().getHouseStudents(h));
                    assertEquals(oldStudentsInBag.get(h), bag.getHouseStudents(h));
                }
            }
        }
    }

    /**
     * Verifies that when there are more than three students in the dashboard, three students are removed and
     * reinserted in the bag.
     */
    @Test
    void doEffectTest_PlayersWithMoreThanThreeStudentsOfTheHouse() {
        Map<String, Object> parameters = new HashMap<>();

        for (Player p : players) {
            for (House h : House.values()) {
                if (h == wantedHouse) {
                    try {
                        p.getDashboard().getDiningHall().addStudents(h, 4);
                    } catch (StudentsTableException e) {
                        e.printStackTrace();
                        fail();
                    }
                } else
                    try {
                        p.getDashboard().getDiningHall().addStudents(h, 1);
                    } catch (StudentsTableException e) {
                        e.printStackTrace();
                        fail();
                    }
            }
        }

        for (int i = 0; i < 4 * players.length; i++) { // removes from bag students added to players' dining halls
            House pulled = null;
            do {
                try {
                    pulled = bag.pull();
                } catch (BagException e) {
                    e.printStackTrace();
                    fail();
                }
                if (pulled != wantedHouse) {
                    try {
                        bag.addStudents(pulled, 1);
                    } catch (BagException e) {
                        e.printStackTrace();
                        fail();
                    }
                }
            } while (pulled != wantedHouse);
        }

        Map<House, Integer> oldStudentsInBag = new HashMap<>();
        for (House h : House.values()) {
            oldStudentsInBag.put(h, bag.getHouseStudents(h));
        }

        parameters.put("wantedHouse", wantedHouse);
        parameters.put("players", players);
        parameters.put("bag", bag);
        thief.doEffect(parameters);

        for (Player p : players) {
            for (House h : House.values()) {
                if (h == wantedHouse) {
                    assertEquals(1, p.getDashboard().getDiningHall().getHouseStudents(h));
                    assertEquals(oldStudentsInBag.get(h) + (3 * players.length), bag.getHouseStudents(h));
                } else {
                    assertEquals(1, p.getDashboard().getDiningHall().getHouseStudents(h));
                    assertEquals(oldStudentsInBag.get(h), bag.getHouseStudents(h));
                }
            }
        }
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "wantedHouse" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoWantedHouseParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("players", players);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> thief.doEffect(parameters));
        Assertions.assertEquals("Missing parameter wantedHouse", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "wantedHouse" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullWantedHouseParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wantedHouse", null);
        parameters.put("players", players);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> thief.doEffect(parameters));
        Assertions.assertEquals("Null parameter wantedHouse", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "players" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoPlayersParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("bag", bag);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> thief.doEffect(parameters));
        Assertions.assertEquals("Missing parameter players", thrown.getMessage());
    }

    /**
     * Verifies that an NullPointerException is thrown when "players" parameter in parameters is null.
     */
    @Test
    void doEffectTest_NullPlayersParameterGiven_ThrowsNullPointerException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("players", null);
        parameters.put("bag", bag);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> thief.doEffect(parameters));
        Assertions.assertEquals("Null parameter players", thrown.getMessage());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when "bag" parameter in parameters is missing.
     */
    @Test
    void doEffectTest_NoBagParameterGiven_ThrowsIllegalArgumentException() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("method", "move");
        parameters.put("wantedHouse", House.YELLOW);
        parameters.put("players", players);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> thief.doEffect(parameters));
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
        parameters.put("players", players);
        parameters.put("bag", null);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> thief.doEffect(parameters));
        Assertions.assertEquals("Null parameter bag", thrown.getMessage());
    }
}

