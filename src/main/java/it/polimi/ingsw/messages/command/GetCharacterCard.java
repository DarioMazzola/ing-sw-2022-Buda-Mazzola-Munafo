package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.GET_CHARACHTER_CARD;

/**
 * This message is sent from the client to the server to request the character cards in use in this game.
 *
 * @author Dario Mazzola
 */
public class GetCharacterCard extends CommandMessage{

    private final int cardIndex;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param cardIndex The index of the CharacterCard chosen by the player
     */
    protected GetCharacterCard(String nickname, int cardIndex) {
        super(GET_CHARACHTER_CARD, nickname);
        this.cardIndex = cardIndex;
    }

    public int getCardIndex() {
        return cardIndex;
    }
}
