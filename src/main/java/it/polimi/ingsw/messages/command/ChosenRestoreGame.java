package it.polimi.ingsw.messages.command;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_RESTORE_GAME;

/**
 * This message is sent from the client to the server to communicate whether to resume the saved game or not.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenRestoreGame extends CommandMessage{

    private final boolean toRestore;

    /**
     * @param nickname The nickname of the player sending the message
     */
    public ChosenRestoreGame(String nickname, boolean toRestore) {
        super(CHOSEN_RESTORE_GAME, nickname);
        this.toRestore = toRestore;
    }

    public boolean getToRestore(){
        return toRestore;
    }

}
