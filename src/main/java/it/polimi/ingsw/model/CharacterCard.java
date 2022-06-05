package it.polimi.ingsw.model;

import it.polimi.ingsw.client.ReducedDashboard;
import it.polimi.ingsw.exceptions.CardNotInDeckException;
import it.polimi.ingsw.exceptions.IllegalChoiceException;
import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.messages.answer.UpdateDashboard;
import it.polimi.ingsw.observer.Observable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dario Mazzola & Gabriele Munafo'
 */
public class CharacterCard extends Observable {

    private int cost;
    private boolean neverUsed;
    private boolean inUse;
    private final String cardName;
    private final CharacterCardEnum type;

    /**
     * CharacterCard constructor
     * @param cost how much does the card cost on first use
     * @param cardName the name of the CharacterCard
     */
    public CharacterCard(int cost, String cardName, CharacterCardEnum type){
        this.cost = cost;
        neverUsed = true;
        inUse = false;
        this.cardName = cardName;
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    private void increaseCost(){
        cost++;
    }

    private void setNotNeverUsed(){
        neverUsed = false;
    }

    public boolean isNeverUsed() {
        return neverUsed;
    }

    public void setInUse (boolean isCardInUse){
        inUse = isCardInUse;
    }

    public boolean isInUse() {
        return inUse;
    }

    /**
     * Returns a description of the CharacterCard's effect
     * @return A description of the CharacterCard's effect
     */
    public String getDescription(){
        return "Standard context";
    }

    /**
     * Performs the effect of the characterCard selected.
     * If the card chosen is that of the basic context,
     * or if the game is not played in expert mode it will never be called
     * @param parameters A map that contains the objects that need to the characterCards and the objects that must be returned
     */
    public void doEffect(Map<String, Object> parameters) throws Exception{
        if(isNeverUsed()) {
            increaseCost();
            setNotNeverUsed();
        }
        setInUse(true);
    }

    @Override
    public String toString() {
        return cardName;
    }

    /**
     * Checks if a professor should be given to the current Player.
     * If the professor belongs to some other player and the current player has more students than the professor's color,
     * the professor is removed from his dashboard and assigned to the current player.
     * If the professor does not belong to anyone and the current player has at least one player,
     * then the professor is assigned to the current player
     * @param players An array that contains references to all players in the game
     * @param currentPlayer The player who is playing this turn
     * @param house The house of the professor to check
     * @throws IllegalChoiceException if the professor of the given house is not present in the dashboard from which to remove it
     */
    protected void checkProf(Player[] players, Player currentPlayer, House house) throws IllegalChoiceException {

        Player owner = null;
        Player playerWithMostStudents;
        int numPlayerI;
        int maxStudentsNumber;

        for(Player p : players){
            if (p.getDashboard().isProfPresent(house)) {
                owner = p;
                break;
            }
        }
        maxStudentsNumber = (owner == null) ? 0 : owner.getDashboard().getHouseStudents(house);
        playerWithMostStudents = owner;

        for (Player p : players) {
            if(! p.equals(owner)) {
                numPlayerI = p.getDashboard().getDiningHall().getHouseStudents(house);

                if (numPlayerI > maxStudentsNumber) {
                    playerWithMostStudents = p;
                    maxStudentsNumber = numPlayerI;
                }
            }
        }

        if(maxStudentsNumber > 0 && playerWithMostStudents.equals(currentPlayer)) {

            if(owner == null){
                currentPlayer.getDashboard().addProf(house);
            }
            else if(!owner.equals(playerWithMostStudents) &&
                        currentPlayer.getDashboard().getDiningHall().getHouseStudents(house) > owner.getDashboard().getDiningHall().getHouseStudents(house)) {
                moveProf(owner.getDashboard(), currentPlayer.getDashboard(), house);

            }
        }

    }

    /**
     * Moves a professor of a given house from a dashboard to another one
     * @param from the dashboard from which to remove the professor of the house indicated as a parameter
     * @param to the dashboard in which to add the professor of the house indicated as a parameter
     * @param house the professor's house that has to be moved
     * @throws IllegalChoiceException if the professor of the given house is not present in the dashboard from which to remove it
     */
    protected void moveProf(Dashboard from, Dashboard to, House house) throws IllegalChoiceException{
        from.removeProf(house);
        to.addProf(house);

        notifyObserver(new UpdateDashboard(new ReducedDashboard(from)));
        notifyObserver(new UpdateDashboard(new ReducedDashboard(to)));
    }

    /**
     * Calculates the influence of each player on a certain island and selects, if present, the player who has the highest influence
     * @param island the island where we calculate the influence on
     * @return the player who has the highest influence on the island, null if no one has the highest influence
     */
    public Player checkInfluence(Island island, Boolean expertMode, int numPlayers, Player[] arrayPlayers, CharacterCard[] characterCardDeck) throws Exception {

        if (expertMode && island.isNoEntryTilePresent()){
            removeNoEntryTile(island, characterCardDeck);
            return(null);
        }
        int[] influences;

        if (numPlayers == 2 || numPlayers == 3) {
            influences = new int[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                influences[i] = 0;
                for (House house : House.values()) {
                    if (arrayPlayers[i].getDashboard().isProfPresent(house)) {
                        influences[i] = influences[i] + island.getHouseStudents(house);
                    }
                }
                try{
                    if ((arrayPlayers[i].getDashboard().getTowerColor()).equals(island.getColorTower())) {
                        influences[i] = influences[i] + island.getNumTowers();
                    }
                }
                catch (IslandException e){
                    influences[i] = influences[i];
                }

            }

            int max = 0;
            boolean tie = false;

            for (int i = 1; i < numPlayers; i++) {
                if (influences[max] <= influences[i]) {
                    if (influences[max] == influences[i]) {
                        tie = true;
                    } else {
                        tie = false;
                        max = i;
                    }
                }
            }
            if (tie) {
                return (null);
            } else {
                return (arrayPlayers[max]);
            }
        }
        //four players
        else {
            influences = new int[2];
            int q = 0;
            int teamOneLeader = 0;
            int teamTwoLeader = 0;
            int[] teamOne = new int[2];
            int[] teamTwo = new int[2];

            for(int i=0; i<numPlayers; i++) {
                if(arrayPlayers[i].isTeamLeader() && q ==0) {
                    teamOneLeader = i;
                    teamOne[0] = i;
                    q++;
                }
                else if(arrayPlayers[i].isTeamLeader()) {
                    teamTwoLeader = i;
                    teamTwo[0] = i;
                    q = 0;
                    break;
                }
            }

            for(int i=0; i<numPlayers; i++) {
                if (i != teamOneLeader && i != teamTwoLeader) {
                    if (arrayPlayers[i].getDashboard().getTowerColor().equals(arrayPlayers[teamOneLeader].getDashboard().getTowerColor())) {
                        teamOne[1] = i;
                    } else {
                        teamTwo[1] = i;
                    }
                }

            }

            boolean towerAdded = false;

            for(int i=0; i<2; i++){
                influences[i] = 0;

                for(House h : House.values()) {
                    if (i == 0) {
                        if(arrayPlayers[teamOne[0]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        if(arrayPlayers[teamOne[1]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        try {
                            if (!towerAdded && arrayPlayers[teamOne[0]].getDashboard().getTowerColor().equals(island.getColorTower())) {
                                influences[i] = influences[i] + island.getNumTowers();
                                towerAdded = true;
                            }
                        }
                        catch (IslandException e){
                            influences[q] = influences[q];
                        }

                    }
                    if (i == 1) {
                        if(arrayPlayers[teamTwo[0]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        if(arrayPlayers[teamTwo[1]].getDashboard().isProfPresent(h)){
                            influences[i] += island.getHouseStudents(h);
                        }
                        try {
                            if (!towerAdded && arrayPlayers[teamTwo[0]].getDashboard().getTowerColor().equals(island.getColorTower())) {
                                influences[i] = influences[i] + island.getNumTowers();
                                towerAdded = true;
                            }
                        }
                        catch (IslandException e){
                            influences[q] = influences[q];
                        }
                    }
                }
            }

            int max = 0;
            boolean tie = false;

            if (influences[max] <= influences[1]) {
                if (influences[max] == influences[1]) {
                    tie = true;
                } else {
                    max = 1;
                }
            }
            if (tie) {
                return (null);
            } else {
                if (max == 0){
                    return (arrayPlayers[teamOneLeader]);
                } else {
                    return (arrayPlayers[teamTwoLeader]);
                }
            }
        }
    }

    void removeNoEntryTile(Island island, CharacterCard[] characterCardDeck) throws Exception{
        boolean cardPresent = false;
        HerbGranma herbGranma = null;

        for(CharacterCard c : characterCardDeck){
            if (c instanceof HerbGranma) {
                cardPresent = true;
                herbGranma = (HerbGranma) c;
                break;
            }
        }
        if(!cardPresent){
            throw new CardNotInDeckException();
        }

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("Method", "removeNoEntryTile");

        herbGranma.doEffect(parameters);
    }

    public CharacterCardEnum getType(){
        return type;
    }
}