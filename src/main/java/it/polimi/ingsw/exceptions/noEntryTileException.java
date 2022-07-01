package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack of no entry tile on an island
 */
public class noEntryTileException extends Exception{
    public noEntryTileException(String message){
        super(message);
    }
}
