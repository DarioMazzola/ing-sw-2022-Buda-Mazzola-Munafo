package it.polimi.ingsw.model;

import it.polimi.ingsw.client.ReducedCloud;
import it.polimi.ingsw.client.ReducedDiningHall;
import it.polimi.ingsw.exceptions.CloudException;
import it.polimi.ingsw.messages.answer.UpdateCloud;
import it.polimi.ingsw.messages.answer.UpdateDiningHall;
import it.polimi.ingsw.model.interfaces.StudentModifierInterface;
import it.polimi.ingsw.observer.Observable;


import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.House.*;

/**
 * Class that represents a cloud
 * @author Gabriele Munafo'
 */
public class Cloud extends Observable implements StudentModifierInterface {

    private final Map<House,Integer> houseMap;
    private final int numMaxStud;
    private boolean full;

    /**
     * Class constructor, initializes the Cloud based on the number of players
     * @param numPlayers the number of players chosen
     */
    public Cloud(int numPlayers){
        houseMap = new HashMap<>();
        houseMap.put(YELLOW, 0);
        houseMap.put(BLUE, 0);
        houseMap.put(GREEN, 0);
        houseMap.put(RED, 0);
        houseMap.put(PINK, 0);

        if (numPlayers == 2 || numPlayers == 4) {
            numMaxStud = 3;}
        else {
            numMaxStud = 4;}
        full = false;

        notifyObserver(new UpdateCloud(new ReducedCloud(this)));
    }

    /**
     * @throws NullPointerException when the house passed is null
     */
    @Override
    public int getHouseStudents (House house){
        if (house == null){
            throw new NullPointerException("house = null can't be used as a parameter");
        }
        return(houseMap.get(house));
    }

    @Override
    public Map<House, Integer> getStudents (){
        return (houseMap);
    }

    /**
     * @throws CloudException when the cloud is already full
     * @throws NullPointerException when the house passed is null
     * @throws IllegalArgumentException when the number of students passed is negative
     */
    @Override
    public void addStudents(House house, int numStudents) throws CloudException{
        if (house == null){
            throw new NullPointerException("house = null can't be used as a parameter");
        }
        if (numStudents < 0){
            throw new IllegalArgumentException("Number of students can't be negative");
        }
        if (full){
            throw new CloudException("The cloud has too many students already");
        }
        houseMap.replace(house, houseMap.get(house) + numStudents);
        int sum = 0;
        for (House h : House.values()) {
            sum = sum + houseMap.get(h);
        }
        if (sum == numMaxStud) {
            full = true;
        }

        notifyObserver(new UpdateCloud(new ReducedCloud(this)));
    }

    /**
     * @throws CloudException when the cloud is empty
     * @throws NullPointerException when the house passed is null
     * @throws IllegalArgumentException when the number of students passed is negative
     */
    @Override
    public void removeStudents(House house, int numStudents) throws CloudException{
        if (house == null){
            throw new NullPointerException("house = null can't be used as a parameter");
        }
        if (numStudents < 0){
            throw new IllegalArgumentException("Number of students can't be negative");
        }
        int sum = 0;
        for (House h : House.values()) {
            sum = sum + houseMap.get(h);
        }
        if (sum == 0){
            throw new CloudException("The cloud hasn't got enough students");
        }
        if (houseMap.get(house) == 0){
            throw new CloudException("There isn't any student of that kind in the cloud");
        }
        houseMap.replace(house, houseMap.get(house) - numStudents);
        full = false;

        notifyObserver(new UpdateCloud(new ReducedCloud(this)));
    }

    public boolean isFull(){
        return(full);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Students on the cloud: ");
        for (House h : House.values()) {
            string.append("\n").append(h).append(": ").append(getHouseStudents(h));
        }
        return string.toString();
    }
}