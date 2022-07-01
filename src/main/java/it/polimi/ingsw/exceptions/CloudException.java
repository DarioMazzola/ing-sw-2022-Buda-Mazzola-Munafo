package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack or excess of students on a cloud
 */
public class CloudException extends Exception {
    public CloudException(String message) {
        super(message);
    }
}
