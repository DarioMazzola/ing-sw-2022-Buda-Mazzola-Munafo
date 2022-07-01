package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack or excess of students in the bag
 */
public class BagException extends Exception{

    public BagException(String message){
        super(message);
    }
}
