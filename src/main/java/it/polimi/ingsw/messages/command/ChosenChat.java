package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.MessageType;

/**
 * ChosenChat message is sent from the client to the server
 * to communicate whether the player wants to allow communication between team members.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenChat extends CommandMessage {
    private final boolean chat;

    /**
     * Class constructor
     *
     * @param nickname the player's nickname
     * @param chat true if the player wants to allow communication between team members
     */
    public ChosenChat(String nickname, boolean chat) {
        super(MessageType.CHOSEN_CHAT, nickname);
        this.chat = chat;
    }

    public boolean isChat() {
        return chat;
    }
}