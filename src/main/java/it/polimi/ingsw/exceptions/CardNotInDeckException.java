package it.polimi.ingsw.exceptions;

/**
 * Thrown to manage the lack fo a card in a deck
 */
public class CardNotInDeckException extends Exception{
    public CardNotInDeckException() {
     super("Card not in deck");
    }

}
