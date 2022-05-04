package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.exceptions.EntranceException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.StudentsTableException;
import it.polimi.ingsw.model.Dashboard;
import it.polimi.ingsw.model.DiningHall;
import it.polimi.ingsw.model.House;

import java.util.Map;

public class Minstrel extends CharacterCard{

    public Minstrel(){
        super(1, "Minstrel");
    }

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
        for(House h : leaved){
            diningHall.removeStudents(h, 1);
        }

        for(House h: wanted) {
            dashboard.removeStudents(h, 1);
            diningHall.addStudents(h, 1);
        }

        for(House h : leaved){
            dashboard.addStudents(h, 1);
        }
    }

    @Override
    public String getDescription() {
        return "You may exchange up to 2 Students between your Entrance and your Dining Room";
    }
}
