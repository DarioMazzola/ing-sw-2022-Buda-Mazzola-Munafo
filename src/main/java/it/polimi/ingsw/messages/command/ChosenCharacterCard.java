package it.polimi.ingsw.messages.command;

import java.util.Map;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_CHARACTER_CARD;

/**
 * This message is sent from the client to the server to request the character cards in use in this game.
 *
 * @author Dario Mazzola
 */
public class ChosenCharacterCard extends CommandMessage{

    private final int cardIndex;
    private final Map<String, Object> parameters;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param cardIndex The index of the CharacterCard chosen by the player
     */
    public ChosenCharacterCard(String nickname, int cardIndex, Map<String, Object> parameters) {
        super(CHOSEN_CHARACTER_CARD, nickname);
        this.cardIndex = cardIndex;
        this.parameters = parameters;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public Map<String, Object> getMap() {
        return parameters;
    }
}
