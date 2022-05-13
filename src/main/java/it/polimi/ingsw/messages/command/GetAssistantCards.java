package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.GET_ASSISTANT_CARDS;

/**
 * GetAssistantCards class represents GetAssistantCards network message.
 * This message is sent from the client to the server to require the assistant cards in current player's deck.
 * It can be used in the planning phase but also in other circumstances when there is the need to show current player's deck.
 *
 * @author Alessio Buda
 */
public class GetAssistantCards extends CommandMessage {

    /**
     * CLass constructor.
     *
     * @param nickname player's nickname
     */
    public GetAssistantCards(String nickname) {
        super(GET_ASSISTANT_CARDS, nickname);
    }
}
