package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.Wizard;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_WIZARD;

/**
 * Wizard class represents Wizards network message.
 * This message is sent from the client to the server to communicate the player's chosen wizard.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenWizard extends CommandMessage {
    private final Wizard wizard;

    /**
     * Class constructor
     *
     * @param nickname player's nickname
     * @param wizard the player's selected wizard for this game
     */
    public ChosenWizard(String nickname, Wizard wizard) {
        super(CHOSEN_WIZARD, nickname);
        this.wizard = wizard;
    }

    public Wizard getWizard() {
        return wizard;
    }
}
