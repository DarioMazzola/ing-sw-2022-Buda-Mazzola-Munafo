package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.NEW_GAME;

/**
 * This message is sent from the client to the server when a player wants to start a game
 *
 * @author Dario Mazzola
 */
public class NewGame extends CommandMessage{

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     */
    protected NewGame(String nickname) {
        super(NEW_GAME, nickname);
    }

}
