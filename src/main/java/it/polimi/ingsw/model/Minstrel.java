package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.StudentsTableException;

import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.MINSTREL;

/**
 * Minstrel class represents minstrel character card.
 *
 * @author Dario Mazzola
 */
public class Minstrel extends CharacterCard{

    /**
     * Class constructor, initializes card with name and initial cost.
     */
    public Minstrel(){
        super(1, "Minstrel", MINSTREL);
    }

    /**
     * Receives parameters, saves them and calls the correct method to perform the wanted operation.
     *
     * @param parameters A map that contains the objects that need to the characterCards and the objects that must be returned
     * @throws IllegalChoiceException when a parameter is missing
     * @throws StudentsTableException when the number of wanted students is not on the card
     * @throws EntranceException when students cannot be added to the dashboard's entrance because it is full (method "move")
     */
    @Override
    public void doEffect(Map<String, Object> parameters) throws Exception {

       super.doEffect(null);

        swapStudents((Dashboard) parameters.get("Dashboard"), (DiningHall) parameters.get("DiningHall"),
                (House[]) parameters.get("fromDashboard"), (House[]) parameters.get("fromDiningHall") );

    }

    private void swapStudents(Dashboard dashboard, DiningHall diningHall, House[] wanted, House[] leaved)
            throws IllegalChoiceException, StudentsTableException, EntranceException {

        if(wanted.length != leaved.length)
            throw new IllegalArgumentException("The number of students taken from the dashboard does not match " +
                                                    "the number of students taken from the diningHall");
        int numStudent = 0;

        for(House h : leaved){
            if(numStudent == leaved.length - 1)
                diningHall.removeStudents(h, 1, true);
            else {
                diningHall.removeStudents(h, 1, false);
                numStudent++;
            }
        }

        int added = 0;
        for(House h: wanted) {
            dashboard.removeStudents(h, 1, false);
            diningHall.addStudents(h, 1, false);
        }

        for(House h : leaved){
            if(added == wanted.length - 1)
                dashboard.addStudents(h, 1, true);
            else{
                added++;
                dashboard.addStudents(h, 1, false);
            }
        }
    }

    @Override
    public String getDescription() {
        return "You may exchange up to 2 Students between your Entrance and your Dining Room";
    }

    @Override
    public CharacterCardEnum getType() {
        return MINSTREL;
    }
}
