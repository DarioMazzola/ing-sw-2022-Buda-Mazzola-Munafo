package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the insertion and removal of students and professors from the dashboard
 */
public class IllegalChoiceException extends Exception{

    public IllegalChoiceException(String message){
        super(message);
    }
}
