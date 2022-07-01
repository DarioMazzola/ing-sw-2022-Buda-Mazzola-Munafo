package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedCharacterCard;

import static it.polimi.ingsw.messages.MessageType.UPDATE_CHARACTER_CARD;

/**
 * Class representing messages from the server to the client to notify the change of a CharacterCard.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateCharacterCard extends AnswerMessage{

    private final ReducedCharacterCard characterCard;

    /**
     * Class constructor
     *
     * @param characterCard the characterCard to send
     */
    public UpdateCharacterCard(ReducedCharacterCard characterCard) {
        super(UPDATE_CHARACTER_CARD);
        this.characterCard = characterCard;
    }

    public ReducedCharacterCard getCharacterCard() {
        return characterCard;
    }
}
