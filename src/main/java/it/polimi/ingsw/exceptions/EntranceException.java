package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage students presence or absence in the hall of the school of magic
 */

public class EntranceException extends Exception{


    public EntranceException(String message){
        super(message);
    }
}
