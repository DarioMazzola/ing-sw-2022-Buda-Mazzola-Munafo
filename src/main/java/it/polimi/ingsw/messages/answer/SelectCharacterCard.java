package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.CharacterCard;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.SELECT_CHARACTER_CARD;

/**
 * This message is sent from the server to the client to communicate all the character cards in use in this game.
 *
 * @author Dario Mazzola
 */
public class SelectCharacterCard extends AnswerMessage{

    private final List<CharacterCard> characterCards;
    /**
     * Message constructor
     *
     * @param characterCards The list of CharacterCards available for this match
     */
    public SelectCharacterCard(List<CharacterCard> characterCards) {
        super(SELECT_CHARACTER_CARD);
        this.characterCards = characterCards;
    }

    public List<CharacterCard> getCharacterCards() {
        return characterCards;
    }
}
