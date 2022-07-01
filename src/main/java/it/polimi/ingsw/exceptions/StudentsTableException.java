package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack or excess of students in the Dining Hall
 */
public class StudentsTableException extends Exception{

    public StudentsTableException(String message){
        super(message);
    }
}
