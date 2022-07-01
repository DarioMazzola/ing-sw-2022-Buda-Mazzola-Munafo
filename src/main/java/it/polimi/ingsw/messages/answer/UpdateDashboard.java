package it.polimi.ingsw.messages.answer;


import it.polimi.ingsw.client.ReducedDashboard;

import static it.polimi.ingsw.messages.MessageType.UPDATE_DASHBOARD;

/**
 * Class representing messages from the server to the client to notify the change of a Dashboard.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateDashboard extends AnswerMessage{

    private final ReducedDashboard dashboard;

    /**
     * Class constructor
     *
     * @param dashboard the dashboard to send
     */
    public UpdateDashboard(ReducedDashboard dashboard) {
        super(UPDATE_DASHBOARD);
        this.dashboard = dashboard;
    }

    public ReducedDashboard getDashboard() {
        return dashboard;
    }
}
