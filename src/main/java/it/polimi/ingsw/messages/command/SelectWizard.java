package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.Wizard;

import static it.polimi.ingsw.messages.MessageType.WIZARD;

/**
 * Wizard class represents Wizards network message.
 * This message is sent from the client to the server to communicate the player's chosen wizard.
 *
 * @author Alessio Buda
 */
public class SelectWizard extends CommandMessage {
    private final Wizard wizard;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param wizard the player's selected wizard for this game
     */
    public SelectWizard(String nickname, Wizard wizard) {
        super(WIZARD, nickname);
        this.wizard = wizard;
    }

    public Wizard getWizard() {
        return wizard;
    }
}