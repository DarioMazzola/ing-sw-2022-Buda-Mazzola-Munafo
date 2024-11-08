package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.MessageType;

/**
 * ReloadMessages message is sent from the client to the server.
 * It asks the server to set a timer and to send, at the expiring of the timer, an ActionPhase or GoToWaitingRoom message to the server.
 * This allows the Cli to show messages potentially received while managing the action phase or waiting room.
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ReloadMessages extends CommandMessage {

    /**
     * Class constructor
     *
     * @param nickname the player's nickname
     */
    public ReloadMessages(String nickname) {
        super(MessageType.RELOAD_MESSAGES, nickname);
    }
}
