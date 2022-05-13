package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.CharacterCard;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.ALL_CHARACTER_CARDS;

/**
 * This message is sent from the server to the client to communicate all the character cards in use in this game.
 */
public class AllCharacterCards extends AnswerMessage{

    private final List<CharacterCard> characterCards;
    /**
     * Message constructor
     *
     * @param nickname The nickname of the nickname of the player to who is sent the message
     * @param characterCards The list of CharacterCards available for this match
     */
    protected AllCharacterCards(String nickname, List<CharacterCard> characterCards) {
        super(ALL_CHARACTER_CARDS, nickname);
        this.characterCards = characterCards;
    }

    public List<CharacterCard> getCharacterCards() {
        return characterCards;
    }
}
