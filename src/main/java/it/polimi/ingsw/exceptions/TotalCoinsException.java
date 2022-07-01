package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack or excess of total coins
 */
public class TotalCoinsException extends Exception {
    public TotalCoinsException(String message) {
        super(message);
    }
}
