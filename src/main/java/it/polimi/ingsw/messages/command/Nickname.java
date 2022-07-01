package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.NICKNAME;

/**
 * Nickname class represents Nickname network message.
 * This message is sent from the client to the server to communicate the player's chosen nickname.
 * Input validity must be checked by the server.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class Nickname extends CommandMessage {

    /**
     * Class constructor
     *
     * @param nickname player's chosen nickname
     */
    public Nickname(String nickname) {
        super(NICKNAME, nickname);
    }
}
