package it.polimi.ingsw.messages.command;

import java.util.Map;

import static it.polimi.ingsw.messages.MessageType.SELECT_CHARACTER_CARD;

/**
 * This message is sent from the client to the server to select the character card the player wants to use.
 *
 * @author Dario Mazzola
 */
public class SelectCharacterCard extends CommandMessage{

    private final int cardIndex;
    private final Map<String, Object> parametersMap;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param cardIndex The index of the card selected by the player
     */
    public SelectCharacterCard(String nickname, int cardIndex, Map<String, Object> map) {
        super(SELECT_CHARACTER_CARD, nickname);
        this.cardIndex = cardIndex;
        this.parametersMap = map;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public Map<String, Object> getMap() {
        return parametersMap;
    }
}
