package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.AnswerMessage;
import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.messages.command.AssistantCards;

import java.util.List;

/**
 * UsedAssistantCards class represents UsedAssistantCards network message.
 * This message is sent from the server to the client to communicate the assistant cards used by other players in this round.
 * With this information the client controller is able to check whether the card selected by the player can or cannot be used.
 *
 * @author Alessio Buda
 */
public class UsedAssistantCards extends AnswerMessage {
    private final List<AssistantCards> usedCards;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param usedCards cards selected by other players in this turn
     */
    public UsedAssistantCards(String nickname, List<AssistantCards> usedCards) {
        super(MessageType.USED_ASSISTANT_CARDS, nickname);
        this.usedCards = usedCards;
    }

    public List<AssistantCards> getUsedCards() {
        return usedCards;
    }
}
