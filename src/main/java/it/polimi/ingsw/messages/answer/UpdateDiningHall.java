package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedDiningHall;

import static it.polimi.ingsw.messages.MessageType.UPDATE_DINING_HALL;

/**
 * Class representing messages from the server to the client to notify the change of a Dining Hall.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateDiningHall extends AnswerMessage{

    private final ReducedDiningHall diningHall;

    /**
     * Class constructor
     *
     * @param diningHall the diningHall to send
     */
    public UpdateDiningHall(ReducedDiningHall diningHall) {
        super(UPDATE_DINING_HALL);
        this.diningHall = diningHall;
    }

    public ReducedDiningHall getDiningHall() {
        return diningHall;
    }
}
