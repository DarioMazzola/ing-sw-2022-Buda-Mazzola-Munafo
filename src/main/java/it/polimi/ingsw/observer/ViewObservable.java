package it.polimi.ingsw.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * ViewObservable manages a list of observers of the view.
 *
 * @author Alessio Buda
 */
public class ViewObservable {
    protected final List<ViewObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param observer the observer to be added.
     */
    public void addObserver(ViewObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer.
     *
     * @param observer the observer to be removed.
     */
    public void removeObserver(ViewObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all the current observers through the lambda argument.
     *
     * @param lambda the lambda to be called on the observers.
     */
    protected void notifyObserver(Consumer<ViewObserver> lambda) {
        for (ViewObserver observer : observers) {
            lambda.accept(observer);
        }
    }
}

