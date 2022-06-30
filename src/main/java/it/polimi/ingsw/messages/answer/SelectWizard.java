package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.Wizard;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.SELECT_WIZARD;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to choose the wizard for this match
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class SelectWizard extends AnswerMessage{
        private final List<Wizard> availableWizards;

        /**
         * Class constructor.
         *
         * @param availableWizards list of all the wizard that have not been selected by other players
         */
        public SelectWizard(List<Wizard> availableWizards) {
            super(SELECT_WIZARD);
            this.availableWizards = availableWizards;
        }

        public List<Wizard> getAvailableWizards() {
            return availableWizards;
        }
}