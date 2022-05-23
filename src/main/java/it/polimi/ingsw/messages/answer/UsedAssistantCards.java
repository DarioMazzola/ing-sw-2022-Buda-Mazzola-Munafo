package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.Card;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.USED_ASSISTANT_CARDS;

/**
 * UsedAssistantCards class represents UsedAssistantCards network message.
 * This message is sent from the server to the client to communicate the assistant cards used by other players in this round.
 * With this information the client controller is able to check whether the card selected by the player can or cannot be used.
 *
 * @author Alessio Buda
 */
public class UsedAssistantCards extends AnswerMessage {
    private final List<Card> usedCards;

    /**
     * Class constructor.
     *
     * @param usedCards cards selected by other players in this turn
     */
    public UsedAssistantCards(List<Card> usedCards) {
        super(USED_ASSISTANT_CARDS);
        this.usedCards = usedCards;
    }

    public List<Card> getUsedCards() {
        return usedCards;
    }
}
