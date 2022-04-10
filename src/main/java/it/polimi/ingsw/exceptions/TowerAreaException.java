package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the insertion and removal of towers in the tower area
 */
public class TowerAreaException extends Exception{

    public TowerAreaException(String message){
        super(message);
    }
}
