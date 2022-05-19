package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.model.Wizard;

import java.util.List;

import static it.polimi.ingsw.messages.MessageType.SELECT_WIZARD;

/**
 * This message is sent from the server to the client to communicate that the player
 * has to choose the wizard for this match
 *
 * @author Dario Mazzola
 */
public class SelectWizard extends AnswerMessage{
        private final List<Wizard> availableWizards;

        /**
         * Class constructor.
         *
         * @param nickname player's nickname
         * @param availableWizards list of all the wizard that have not been selected by other players
         */
        public SelectWizard(String nickname, List<Wizard> availableWizards) {
            super(SELECT_WIZARD, nickname);
            this.availableWizards = availableWizards;
        }

        public List<Wizard> getAvailableWizards() {
            return availableWizards;
        }
}