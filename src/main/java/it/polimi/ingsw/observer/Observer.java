package it.polimi.ingsw.observer;

import it.polimi.ingsw.messages.Message;

/**
 * Observer interface. Classes that implement this interface observes classes that are extending Observable interface.
 *
 * @author Dario Mazzola
 */
public class Observer {

    /**
     * Update method of the observer pattern.
     * Notifies all observers that a message has been received
     *
     * @param message the message received
     */
    public void update(Message message) {
    }
}
