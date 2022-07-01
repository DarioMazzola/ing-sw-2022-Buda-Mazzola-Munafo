package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack of towers on an island
 */
public class IslandException extends Exception{
    public IslandException(String message) {
        super(message);
    }
}
