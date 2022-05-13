package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.messages.MessageType;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.ALL_ASSISTANT_CARD;

/**
 * AllAssistantCards class represents AllAssistantCards network message.
 * This message is sent from the server to the client to communicate the assistant cards in current player's deck.
 *
 * @author Alessio Buda
 */
public class AllAssistantCards extends AnswerMessage {
    private final List<AllAssistantCards> playersDeck;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param playersDeck the deck of the player
     */
    public AllAssistantCards(String nickname, List<AllAssistantCards> playersDeck) {
        super(ALL_ASSISTANT_CARD, nickname);
        this.playersDeck = playersDeck;
    }

    public List<AllAssistantCards> getPlayersDeck() {
        return playersDeck;
    }
}
