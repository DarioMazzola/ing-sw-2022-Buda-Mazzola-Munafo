package it.polimi.ingsw.observer;

import it.polimi.ingsw.messages.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable class. Classes that implements this class may be observed by class that are implementing {@link Observer} interface
 *
 * @author Dario Mazzola
 */
public class Observable {

    private final List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer to the observers' list.
     *
     * @param obs the observer to add.
     */
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * Removes an observer from the observers' list.
     *
     * @param obs the observer to remove.
     */
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    /**
     * Notifies all the observers and passes to them a message as a parameter.
     *
     * @param message the message to pass to the observers.
     */
    protected void notifyObserver(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

}
