package it.polimi.ingsw.exceptions;

public class noEntryTileException extends Exception{
    public noEntryTileException(String message){
        if (message.equals("True")){System.out.println("NoEntryTile is already true");}
        else if (message.equals("False")){System.out.println("NoEntryTile is already false");}
    }
}
