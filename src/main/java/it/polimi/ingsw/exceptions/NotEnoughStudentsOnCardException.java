package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack of students on a character card
 */
public class NotEnoughStudentsOnCardException extends Exception{

    public NotEnoughStudentsOnCardException(String message) {
        super(message);
    }
}
