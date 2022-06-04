package it.polimi.ingsw.model;

import it.polimi.ingsw.client.ReducedDashboard;
import it.polimi.ingsw.client.ReducedGameModel;
import it.polimi.ingsw.client.ReducedIsland;
import it.polimi.ingsw.client.ReducedPlayer;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.messages.answer.*;
import it.polimi.ingsw.model.interfaces.StudentAdderInterface;
import it.polimi.ingsw.model.interfaces.StudentModifierInterface;
import it.polimi.ingsw.observer.Observable;

import java.util.*;

import static it.polimi.ingsw.model.House.*;

/**
 * Class that represents the GameModel
 * @author Gabriele Munafo'
 */
public class GameModel extends Observable {
    private int motherIsland;
    private final int numPlayers;
    private final boolean expertMode;
    private final List<Island> islandList;
    private final CharacterCard[] characterCardDeck;
    private final Bag bag;
    private final Player[] arrayPlayers;
    private final Cloud[] arrayClouds;
    private int totalCoins;
    private Player currentPlayer;
    private ContextCharacterCard context;
    private final Boolean chat;

    /**
     * Class constructor, initializes the GameModel based on the number of players and the mode chosen
     * @param numPlayers the number of players chosen
     * @param expertMode specifies the mode chosen
     */
    public GameModel(int numPlayers, boolean expertMode, Boolean chat) throws EntranceException, BagException {
        this.chat = chat;
        this.numPlayers = numPlayers;
        this.expertMode = expertMode;
        this.motherIsland = 0;
        this.totalCoins = 0;
        islandList = new ArrayList<>();
        int numIsland = 12;
        for (int i = 0; i < numIsland; i++) islandList.add(new Island(expertMode));

        Map<House,Integer> houseMap = new HashMap<>();
        houseMap.put(YELLOW, 2);
        houseMap.put(BLUE, 2);
        houseMap.put(GREEN, 2);
        houseMap.put(RED, 2);
        houseMap.put(PINK, 2);

        for (int i=1; i<numIsland; i++) {
            if (i!=6) {
                Random rand = new Random();
                int value;
                House chosen;
                ArrayList<House> houses = new ArrayList<>(Arrays.asList(values()));

                do {
                    value = rand.nextInt(houses.toArray().length);
                    chosen = houses.remove(value);
                } while (houseMap.get(chosen) == 0);

                houseMap.replace(chosen, houseMap.get(chosen) - 1);

                islandList.get(i).addStudents(chosen, 1);
            }
        }
        bag = new Bag();
        arrayClouds = new Cloud[numPlayers];
        arrayPlayers = new Player[numPlayers];
        for (int i=0; i<numPlayers; i++){
            arrayClouds[i] = new Cloud(numPlayers);
            arrayPlayers[i] = new Player(numPlayers);
        }

        if (expertMode){
            totalCoins = 20 - numPlayers;
            characterCardDeck = new CharacterCard[3];
            CharacterCardFactory characterCardFactory = new CharacterCardFactory();
            ArrayList<CharacterCardEnum> charList = new ArrayList<>(Arrays.asList(CharacterCardEnum.values()));
            Random rand = new Random();
            int randomNum;
            CharacterCardEnum cardType;

            for (int i=0; i<3; i++){
                randomNum = rand.nextInt(charList.size());
                cardType = charList.get(randomNum);
                charList.remove(randomNum);

                characterCardDeck[i] = characterCardFactory.getCharacterCard(cardType, bag);
            }
        }
        else {
            characterCardDeck = null;
        }

        currentPlayer = arrayPlayers[0];

        context = new ContextCharacterCard(new CharacterCard(0, "Base", null));
    }

    /**
     * Initialize the dashboard of a certain player with the chosen tower color
     * @param player the player whose dashboard will be initialized
     * @param towerColor the tower color that has to be set
     * @throws BagException when the bag.pull fails
     * @throws EntranceException when the dashboard is already full
     */
    public void initializeDashboard(Player player, Color towerColor) throws BagException, EntranceException {
        player.setDashboard(towerColor, player.getNickname());

        int numRep;
        if (numPlayers == 2 || numPlayers == 4){
            numRep = 7;
        }
        else {
            numRep = 9;
        }

        for (int j=0; j<numRep; j++) {
            player.getDashboard().addStudents(bag.pull(), 1);
        }
    }

    /**
     * Calculates the influence of each player on a certain island and selects, if present, the player who has the highest influence
     * @param island the island where we calculate the influence on
     * @return the player who has the highest influence on the island
     */
    public Player checkInfluence(Island island) throws Exception {
        return(context.checkInfluence(island, expertMode, numPlayers, arrayPlayers, characterCardDeck));
    }

    /**
     * Sets the right context for the character card played
     * @param card the index of the character card the player wants to use
     * @param map contains the parameters used by the character cards
     * @throws IllegalArgumentException when the player hasn't got enough coins or when the card used does not exist
     */
    public void useCharacterCard(int card, Map<String, Object> map) throws Exception {
        if (card < 0 || card > 3){
            throw new IllegalArgumentException("This character card doesn't exists!");
        }
        if (currentPlayer.getCoins() < characterCardDeck[card].getCost()){
            throw new IllegalArgumentException("The player doesn't have enough coins to play this card!");
        }
        currentPlayer.removeCoins(characterCardDeck[card].getCost());
        context = new ContextCharacterCard(characterCardDeck[card]);

        context.doEffect(map);

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Sets the basic context
     */
    public void setBaseContext(){
        context = new ContextCharacterCard(new CharacterCard(0, "Base", null));

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Checks the correct position of a certain professor
     * @param house the kind of prof to check
     */
    public void checkProf(House house) throws IllegalChoiceException{
        context.checkProf(arrayPlayers, currentPlayer, house);
    }

    /**
     * Adds a certain number of coins to totalCoins
     * @param numCoins the number of coins added
     * @throws TotalCoinsException if the number of coins exceeds the maximum number of coins acceptable
     */
    public void addCoins(int numCoins) throws TotalCoinsException {
        if (this.totalCoins == 20){
            throw new TotalCoinsException("The number of coins is already the maximum possible");
        }
        else if (this.totalCoins + numCoins > 20){
            throw new TotalCoinsException("Cannot add so many coins");
        }
        this.totalCoins = this.totalCoins + numCoins;

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Removes a certain number of coins to totalCoins
     * @param numCoins the number of coins removed
     * @throws TotalCoinsException if the number of coins is below the number requested
     */
    public void removeCoins(int numCoins) throws TotalCoinsException{
        if (this.totalCoins < numCoins){
            throw new TotalCoinsException("There aren't enough coins available");
        }
        this.totalCoins = this.totalCoins - numCoins;

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Moves a certain number of students belonging to a certain house from a position to another
     * @param from the StudentModifierInterface where we remove the students
     * @param to the StudentAdderInterface where we add the students
     * @param house the house where the students belongs
     * @param numStudents number of students moved
     * @throws NullPointerException when at least one among from, to and house are null
     */
    public void moveStudents (StudentModifierInterface from, StudentAdderInterface to, House house, int numStudents) throws Exception {
        if (from == null || to == null || house == null){
            throw new NullPointerException();
        }

        from.removeStudents(house, numStudents);

        try {
            to.addStudents(house, numStudents);
        } catch (Exception e){
            from.addStudents(house, numStudents);
            throw new Exception();
        }

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Moves a certain number of students belonging to a certain house from a position to another. If the game is in expert mode, it may add coins to the current player if needed
     * @param from the StudentModifierInterface where we remove the students
     * @param to the StudentModifierInterface where we add the students
     * @param house the house where the students belongs
     * @param numStudents number of students moved
     */
    public void moveStudents (StudentModifierInterface from, StudentModifierInterface to, House house, int numStudents) throws NullPointerException, IllegalChoiceException {
        if (from == null || to == null || house == null) {
            throw new NullPointerException();
        }
        try {
            from.removeStudents(house, numStudents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (expertMode) {
            if (from instanceof Dashboard && to instanceof DiningHall) {
                try {
                    if ((to.getHouseStudents(house) + numStudents) % 3 == 0) {
                        removeCoins(((to.getHouseStudents(house) + numStudents) / 3) - (to.getHouseStudents(house) / 3));
                        currentPlayer.addCoins(((to.getHouseStudents(house) + numStudents) / 3) - (to.getHouseStudents(house) / 3));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            to.addStudents(house, numStudents);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkProf(house);

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Moves a certain number of students belonging to a certain house from a position to another
     * @param from the Bag from where we pull the students
     * @param to the StudentAdderInterface where we add the students
     * @param numStudents number of students moved
     */
    public void moveStudents (Bag from, StudentAdderInterface to, int numStudents) throws NullPointerException, BagException {
        if (from == null || to == null) {
            throw new NullPointerException();
        }
        House pulled;
        for (int i=0; i<numStudents; i++) {
            pulled = from.pull();

            try {
                to.addStudents(pulled, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Moves a certain number of students belonging to a certain house from a position to another
     * @param from the Bag from where we pull the students
     * @param to the StudentModifierInterface where we add the students
     * @param numStudents number of students moved
     */
    public void moveStudents (Bag from, StudentModifierInterface to, int numStudents) throws NullPointerException, BagException {
        if (from == null || to == null) {
            throw new NullPointerException();
        }
        House pulled;
        for (int i=0; i<numStudents; i++) {
            pulled = from.pull();
            try {
                to.addStudents(pulled, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        notifyObserver(new UpdateGameModel(new ReducedGameModel(this)));
    }

    /**
     * Moves the cloud's students in the player's dashboard
     * @param cloud from where the students are taken
     * @throws NullPointerException when the cloud passed is null
     */
    public void refillFromCloud(Cloud cloud) throws NullPointerException, IllegalChoiceException {
        if (cloud == null){
            throw new NullPointerException();
        }
        int numStud;
        for (House house : House.values()){
            numStud = cloud.getHouseStudents(house);
            if (numStud > 0) {
                moveStudents(cloud, currentPlayer.getDashboard(), house, numStud);
            }
        }

        List<ReducedIsland> reducedIslands = new ArrayList<>();

        for(Island i : islandList)
            reducedIslands.add(new ReducedIsland(i));

        notifyObserver(new UpdateIsland(reducedIslands));
    }

    /**
     * Moves a certain number of towers from a dashboard to an island
     * @param dashboard the player's dashboard where we remove the towers
     * @param island the island where we add the towers
     * @param numTowers number of towers moved
     */
    public void moveTowers(Dashboard dashboard, Island island, int numTowers) throws NullPointerException, TowerAreaException {
        if (dashboard == null || island == null) {
            throw new NullPointerException();
        }
        if (numTowers > dashboard.getNumTowers()) {
            throw new TowerAreaException("There are not enough towers in the dashboard");
        }
        for (int i = 0; i < numTowers; i++) {
            dashboard.removeTower();
        }

        try {
            Color color = island.getColorTower();
            if (!color.equals(dashboard.getTowerColor())){
                island.setTowerColor(dashboard.getTowerColor());
            }
        } catch (IslandException e){
            island.setTowerColor(dashboard.getTowerColor());
            island.addTowers(numTowers);
        }

        for (int i = 0; i < getNumIslands(); i++) {
            if (islandList.get(i).equals(island)) {
                try {
                    if (i < getNumIslands() - 1 && islandList.get(i).getColorTower().equals(islandList.get(i + 1).getColorTower())) {
                        merge(i, i + 1);
                    } else if (i == getNumIslands() - 1 && islandList.get(i).getColorTower().equals(islandList.get(0).getColorTower())) {
                        merge(0, i);
                        i = 0;
                    }
                } catch (IslandException ignored) {}

                try {
                    if (i > 0 && islandList.get(i).getColorTower().equals(islandList.get(i - 1).getColorTower())) {
                        merge(i - 1, i);
                    } else if (i == 0 && islandList.get(i).getColorTower().equals(islandList.get(getNumIslands() - 1).getColorTower())) {
                        merge(i, getNumIslands() - 1);
                    }
                } catch (IslandException ignored) {}
                break;
            }
        }

        notifyObserver(new UpdateDashboard(new ReducedDashboard(dashboard)));

        List<ReducedIsland> reducedIslands = new ArrayList<>();

        for(Island i : islandList)
            reducedIslands.add(new ReducedIsland(i));

        notifyObserver(new UpdateIsland(reducedIslands));
    }

    /**
     * Merges two island in one, copying the parameters of the second one in the first one. It also moves the mother accordingly
     * @param island1 the resulting island after the operation
     * @param island2 the island that got merged, which won't exist anymore in the list
     */
    private void merge(int island1, int island2){
        if (motherIsland == island2) {
            motherIsland = island1;
        }
        for (House house : House.values()) {
            islandList.get(island1).addStudents(house, islandList.get(island2).getHouseStudents(house));
        }
        islandList.get(island1).addTowers(islandList.get(island2).getNumTowers());
        if (islandList.get(island2).isNoEntryTilePresent()) {
            for (int i = 0; i < islandList.get(island2).getNoEntryTile(); i++) {
                islandList.get(island1).addNoEntryTile();
            }
        }
        islandList.remove(island2);

        List<ReducedIsland> reducedIslands = new ArrayList<>();

        for(Island i : islandList)
            reducedIslands.add(new ReducedIsland(i));

        notifyObserver(new UpdateIsland(reducedIslands));
        notifyObserver(new UpdateMotherIsland(motherIsland));
    }

    /**
     * Refills all the clouds getting students from the bag
     */
    public void refillClouds() throws IllegalChoiceException, BagException {
        int numCloud = 3;
        if (numPlayers == 3){
            numCloud = 4;
        }
        for (int i=0; i<numPlayers; i++){
            moveStudents(bag, arrayClouds[i], numCloud);
        }

        List<ReducedIsland> reducedIslands = new ArrayList<>();

        for(Island i : islandList)
            reducedIslands.add(new ReducedIsland(i));

        notifyObserver(new UpdateIsland(reducedIslands));
    }

    /**
     * Modifies the position of the mother. If it exceeds the 11th island, it starts over from the 0 one
     * @param moves how many steps mother has to take
     */
    public void setMotherIsland (int moves){
        if (moves < 1 || moves > currentPlayer.getMaxMoves()){
            throw new IllegalArgumentException("You can't move mother nature in this way!");
        }
        this.motherIsland = (this.getMotherIsland()+moves)%this.getNumIslands();

        notifyObserver(new UpdateMotherIsland(motherIsland));
    }

    /**
     * Checks that all professors are in the correct place. It is used mainly for character cards
     */
    public void checkAllProf(){
        for (House h: House.values()){
            try {
                checkProf(h);
            } catch (IllegalChoiceException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the current player
     * @param player the player that has to be the current player
     */
    public void setCurrentPlayer(int player) throws IllegalArgumentException{
        if (player < 0 || player >= numPlayers){
            throw new IllegalArgumentException("This player does not exit!");
        }
        currentPlayer = arrayPlayers[player];

        notifyObserver(new UpdateCurrentPlayer(new ReducedPlayer(currentPlayer)));
    }

    public ContextCharacterCard getContext(){
        return context;
    }

    public int getNumPlayers(){
        return(this.numPlayers);
    }

    public boolean isExpertMode(){
        return(this.expertMode);
    }

    public int getNumIslands(){
        return(islandList.size());
    }

    public int getMotherIsland (){
        return(motherIsland);
    }

    public Player[] getArrayPlayers() {
        Player[] result = new Player[getNumPlayers()];
        System.arraycopy(arrayPlayers, 0, result, 0, getNumPlayers());
        return result;
    }

    public List<Island> getIslandList(){
        return (new ArrayList<>(islandList));
    }

    public Bag getBag(){
        return (bag);
    }

    public Cloud[] getArrayClouds(){
        return (arrayClouds);
    }

    public int getTotalCoins(){
        return (totalCoins);
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public CharacterCard[] getCharacterCardDeck() {
        return (characterCardDeck);
    }

    public Player getPlayerByNickname(String nickname){
        return Arrays.stream(getArrayPlayers()).
                filter((x)-> x.getNickname().equals(nickname)).
                findFirst().orElse(null);
    }

    public Boolean getChat(){
        return chat;
    }
}