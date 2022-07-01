package it.polimi.ingsw.model;

import it.polimi.ingsw.client.ReducedDashboard;
import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.TowerAreaException;
import it.polimi.ingsw.messages.answer.UpdateDashboard;
import it.polimi.ingsw.model.interfaces.StudentModifierInterface;
import it.polimi.ingsw.observer.Observable;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;

/**
 * Describes the Dashboard
 *
 * @author Dario Mazzola
 */
public class Dashboard extends Observable implements StudentModifierInterface {

    private final Map<House, Integer> houseMap;
    private final Map<House, Boolean> profMap;
    private final Color towerColor;
    private final int numMaxStudents;
    private int numStudentsIn;
    private final int numMaxTowers;
    private int numTowersIn;
    private final String owner;

    private final DiningHall diningHall;

    /**
     * Dashboard constructor: sets the number of students owned by the player to zero.
     * The player at the creation of the dashboard does not have any professors and
     * has all the tower placed in the tower area
     *
     * @param towerColor     Color of the towers chosen by the player
     * @param numMaxStudents Maximum number of students present at the entrance
     * @param numMaxTowers   Maximum number of towers present at the Tower Area
     * @throws NullPointerException If the color of the towers given as parameter is a null value
     */
    public Dashboard(Color towerColor, int numMaxStudents, int numMaxTowers, String nickname) throws NullPointerException {

        if (towerColor == null)
            throw new NullPointerException("The tower color given is null");

        houseMap = new HashMap<>();

        profMap = new HashMap<>();

        for (House h : values()) {
            houseMap.put(h, 0);
            profMap.put(h, false);
        }

        this.numMaxStudents = numMaxStudents;
        numStudentsIn = 0;

        this.numMaxTowers = numMaxTowers;
        numTowersIn = numMaxTowers;
        this.towerColor = towerColor;

        this.diningHall = new DiningHall(nickname);
        this.owner = nickname;

    }

    /**
     * Returns the students' number of each house present in the entrance
     */
    public int getNumStudentsIn() {
        return numStudentsIn;
    }

    /**
     * @throws NullPointerException If the house given as a parameter is a null value
     */
    @Override
    public int getHouseStudents(House house) throws NullPointerException {

        if (house == null)
            throw new NullPointerException("The house given is null");
        return houseMap.get(house);
    }

    @Override
    public Map<House, Integer> getStudents() {
        return houseMap;
    }

    /**
     * @throws EntranceException        If the entrance is already full
     * @throws EntranceException        If the number of students to add exceed the maximum students' number
     * @throws NullPointerException     If the house given as a parameter is a null value
     * @throws IllegalArgumentException if the number of students given as a parameter is negative
     */
    @Override
    public void addStudents(House house, int numStudents, boolean notify) throws EntranceException, NullPointerException, IllegalArgumentException {

        if (house == null)
            throw new NullPointerException("The house given is null");

        if (numStudents < 0) {
            throw new IllegalArgumentException("You cannot add a negative number of students!");
        }

        if (numStudentsIn == numMaxStudents)
            throw new EntranceException("You cannot add any students to the entrance, it is already full");

        if (numStudentsIn + numStudents > numMaxStudents)
            throw new EntranceException("You cannot add so many students to the entrance");

        houseMap.replace(house, houseMap.get(house) + numStudents);
        numStudentsIn += numStudents;

        if (notify)
            notifyObserver(new UpdateDashboard(new ReducedDashboard(this)));

    }

    /**
     * @throws IllegalChoiceException   If there is no professor of that house in the dashboard
     * @throws IllegalChoiceException   If the students' number that the player wants to remove is greater than the number of available students
     * @throws NullPointerException     If the house given as a parameter is a null value
     * @throws IllegalArgumentException if the number of students given as a parameter is negative
     */
    @Override
    public void removeStudents(House house, int numStudents, boolean notify) throws IllegalChoiceException, NullPointerException, IllegalArgumentException {

        if (house == null)
            throw new NullPointerException("The house given is null");

        if (numStudents < 0) {
            throw new IllegalArgumentException("You cannot remove a negative number of students!");
        }

        if (houseMap.get(house) == 0)
            throw new IllegalChoiceException("You cannot remove any students from the entrance, it is already empty");

        if (houseMap.get(house) - numStudents < 0)
            throw new IllegalChoiceException("You cannot remove so many students from the entrance");

        houseMap.replace(house, houseMap.get(house) - numStudents);

        numStudentsIn -= numStudents;

        notifyObserver(new UpdateDashboard(new ReducedDashboard(this)));
    }

    public int getNumTowers() {
        return numTowersIn;
    }

    /**
     * Adds tower to the Tower Area
     *
     * @throws TowerAreaException If the Tower Area is already full
     */
    public void addTower() throws TowerAreaException {

        if (numTowersIn == numMaxTowers)
            throw new TowerAreaException("You cannot add a tower to your Towers' Area, it is already full");

        numTowersIn++;

        notifyObserver(new UpdateDashboard(new ReducedDashboard(this)));
    }

    /**
     * Removes one tower from the Tower Area
     *
     * @throws TowerAreaException If the Tower Area is already empty
     */
    public void removeTower() throws TowerAreaException {

        if (numTowersIn == 0)
            throw new TowerAreaException("You cannot remove a tower from your Towers' Area, it is already empty");
        numTowersIn--;

        notifyObserver(new UpdateDashboard(new ReducedDashboard(this)));
    }

    /**
     * Returns the color of the towers on the dashboard
     */
    public Color getTowerColor() {
        return towerColor;
    }

    /**
     * Returns true if the professor of the house given is present on the dashboard
     *
     * @param house The house of the professor who will be verified
     * @throws NullPointerException If the house given as a parameter is a null value
     */
    public boolean isProfPresent(House house) throws NullPointerException {
        if (house == null)
            throw new NullPointerException("The house given is null");
        return profMap.get(house);
    }

    /**
     * Adds a professor into the professors' table
     *
     * @param house house of the professor to add
     * @throws NullPointerException If the house given as a parameter is a null value
     */
    public void addProf(House house) throws NullPointerException {

        if (house == null)
            throw new NullPointerException("The house given is null");
        if (!profMap.get(house))
            profMap.replace(house, true);

        notifyObserver(new UpdateDashboard(new ReducedDashboard(this)));
    }

    /**
     * Removes a professor from the professors'table
     *
     * @param house House of the professor to remove
     * @throws IllegalChoiceException If there is no professor of that house sitting at professors' table
     * @throws NullPointerException   If the house given as a parameter is a null value
     */
    public void removeProf(House house) throws IllegalChoiceException, NullPointerException {

        if (house == null)
            throw new NullPointerException("The house given is null");

        if (!profMap.get(house))
            throw new IllegalChoiceException("This prof is not present in this dashboard");
        else {
            profMap.replace(house, false);
        }

        notifyObserver(new UpdateDashboard(new ReducedDashboard(this)));
    }

    /**
     * Returns an instance of the dining hall related to this dashboard
     */
    public DiningHall getDiningHall() {
        return diningHall;
    }

    public int getNumMaxStudents() {
        return numMaxStudents;
    }

    public Map<House, Boolean> getProfMap() {
        return profMap;
    }

    public int getNumMaxTowers() {
        return numMaxTowers;
    }

    public String getOwner() {
        return owner;
    }
}
