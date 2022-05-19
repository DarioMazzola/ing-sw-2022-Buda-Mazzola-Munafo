package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.Card;

import static it.polimi.ingsw.messages.MessageType.ASSISTANT_CARDS;

/**
 * AssistantCards class represents AssistantCards network message.
 * This message is sent from the client to the server to communicate player's selected assistant card for this round.
 *
 * @author Alessio Buda
 */
public class AssistantCards extends CommandMessage {
    private final Card assistantCard;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param assistantCard the assistant card selected by the player
     */
    public AssistantCards(String nickname, Card assistantCard) {
        super(ASSISTANT_CARDS, nickname);
        this.assistantCard = assistantCard;
    }

    public Card getAssistantCard() {
        return assistantCard;
    }
}
