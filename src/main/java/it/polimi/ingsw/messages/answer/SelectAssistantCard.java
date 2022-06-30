package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.Card;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.SELECT_ASSISTANT_CARD;

/**
 * AllAssistantCards class represents AllAssistantCards network message.
 * This message is sent from the server to the client to communicate the assistant cards in current player's deck.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class SelectAssistantCard extends AnswerMessage {
    private final List<Card> availableAssistantCards;

    /**
     * Class constructor.
     *
     * @param availableAssistantCard the deck of the player
     */
    public SelectAssistantCard(List<Card> availableAssistantCard) {
        super(SELECT_ASSISTANT_CARD);
        this.availableAssistantCards = availableAssistantCard;
    }

    public List<Card> getAvailableAssistantCards() {
        return availableAssistantCards;
    }
}
