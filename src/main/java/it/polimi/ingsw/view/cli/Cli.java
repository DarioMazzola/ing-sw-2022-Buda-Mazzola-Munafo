package it.polimi.ingsw.view.cli;

import com.google.gson.Gson;
import it.polimi.ingsw.ClientMain;
import it.polimi.ingsw.client.*;
import it.polimi.ingsw.messages.TypeOfError;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.view.UI;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Math.min;

/**
 * This class offers a User Interface to the user via terminal. It is an implementation of the {@link UI}.
 *
 * @author Alessio Buda
 */
public class Cli extends ViewObservable implements UI {
    private final Scanner scanner;
    private ReducedGameModel gm;
    private static final int defaultPort = 1234;
    private String nickname;
    private boolean stop;

    /**
     * Class constructor
     */
    public Cli() {
        scanner = new Scanner(System.in);
        stop = false;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Initiates the communication with the server
     */
    public void start() {
        clearCli();

        System.out.println(" " +
                " /$$$$$$$$           /$$                       /$$                        \n" +
                "| $$_____/          |__/                      | $$                        \n" +
                "| $$        /$$$$$$  /$$  /$$$$$$  /$$$$$$$  /$$$$$$   /$$   /$$  /$$$$$$$\n" +
                "| $$$$$    /$$__  $$| $$ |____  $$| $$__  $$|_  $$_/  | $$  | $$ /$$_____/\n" +
                "| $$__/   | $$  \\__/| $$  /$$$$$$$| $$  \\ $$  | $$    | $$  | $$|  $$$$$$ \n" +
                "| $$      | $$      | $$ /$$__  $$| $$  | $$  | $$ /$$| $$  | $$ \\____  $$\n" +
                "| $$$$$$$$| $$      | $$|  $$$$$$$| $$  | $$  |  $$$$/|  $$$$$$$ /$$$$$$$/\n" +
                "|________/|__/      |__/ \\_______/|__/  |__/   \\___/   \\____  $$|_______/ \n" +
                "                                                       /$$  | $$          \n" +
                "                                                      |  $$$$$$/          \n" +
                "                                                       \\______/           \n" +
                "\n");

        System.out.println("Welcome to the game!");

        String ipAddress;
        int serverPort = 0;

        do {
            System.out.println("\nIP address of the server? [default: localhost]");
            ipAddress = scanner.nextLine();

            if(ipAddress != null && ipAddress.equals("")){
                ipAddress = "127.0.0.1";
            }

        } while (!checkIP(ipAddress));

        System.out.println("Server port? [default: 1234]");
        try {
            String input = scanner.nextLine();
            if (input.equals(""))
                serverPort = defaultPort;
            else
                serverPort = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("Error while reading port number. Please try again!");
            ClientMain.main(null);
        }

        while (serverPort < 1024) {
            System.out.println("Port number chosen is not valid. Please try again!");
            System.out.println("Server port? [default: 1234]");
            try {
                serverPort = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Error while reading port number. Please try again!");
                ClientMain.main(null);
            }
        }

        int finalServerPort = serverPort;
        String finalIpAddress = ipAddress;

        notifyObserver(observer -> observer.onInit(finalIpAddress, finalServerPort));

    }

    @Override
    public void showError(String errorMsg) {
        System.out.println(errorMsg);

        if(errorMsg.endsWith(TypeOfError.DISCONNECTED.toString()) || errorMsg.endsWith(TypeOfError.GAME_FULL.toString())
                || errorMsg.endsWith(TypeOfError.GAME_ALREADY_STARTED.toString()) || errorMsg.endsWith(TypeOfError.SERVER_UNREACHABLE.toString())) {
            notifyObserver(ViewObserver::onDisconnection);
        }
    }

    /**
     * Notifies that the game is full and the player will be disconnected.
     */
    public void notifyGameFull() {
        System.out.println("We are sorry, the game you're trying to join has already reached the maximum number of players.\nYou'll be disconnected...");
        notifyObserver(ViewObserver::onDisconnection);
    }

    @Override
    public void createNewGame() {
        System.out.println("Enter your nickname to start a new game: ");
        boolean isValidInput;
        String nickname;
        do {
            isValidInput = true;
            nickname = scanner.nextLine();
            if (nickname.equals("")) {
                isValidInput = false;
                System.out.println("Empty nickname! Please insert a nickname that is at least 1 character long:");
            }
        } while (!isValidInput);
        String finalNickname = nickname;
        notifyObserver(observers -> observers.onCreateNewGame(finalNickname));
    }

    @Override
    public void selectRestoreGame() {
        System.out.println("There's a game saved on the server. Do you want to restore the game ('Y') or start a new one ('N')?");
        boolean toRestore = YNInput("restore the game");
        notifyObserver(observers -> observers.onRestoreGame(toRestore));
    }

    @Override
    public void rememberNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void onChatMessageReceived(String message) {
        stop = false;
        String teamMate = gm.getTeamMate(nickname);
        System.out.println("Message from " + (teamMate == null ? "your team mate" : teamMate));
        System.out.println("\n<< " + message + " >>\n");
    }

    @Override
    public void endGameDisconnection(String errorCause) {
        System.err.println(errorCause);
        notifyObserver(ViewObserver::onDisconnection);
    }

    @Override
    public void selectNickname() {
        System.out.print("Please, enter your nickname: ");
        boolean isValidInput;
        String nickname;
        do {
            isValidInput = true;
            nickname = scanner.nextLine();
            if (nickname.equals("")) {
                isValidInput = false;
                System.out.println("Empty nickname! Please insert a nickname that is at least 1 character long:");
            }
        } while (!isValidInput);
        String finalNickname = nickname;
        notifyObserver(observers -> observers.onUpdateNickname(finalNickname));
    }

    @Override
    public void selectNumPlayers() {
        System.out.println("How many players want to play? (enter a number between 2 and 4):");
        int numPlayers = inputInRange(2, 4, "select a valid number of player for the game");
        notifyObserver(observers -> observers.onUpdateNumPlayers(numPlayers));
    }

    @Override
    public void selectChat() {
        System.out.println("Do you want team members to communicate with each other? (Y/N)");
        boolean chat = YNInput("you want to allow communication between team members");
        notifyObserver(observers -> observers.onUpdateChat(chat));
    }

    @Override
    public void selectExpertMode() {
        System.out.println("Do you want to play in expert mode? (Y/N)");
        boolean expertMode = YNInput("you want to play in expert mode");
        notifyObserver(observers -> observers.onUpdateExpertMode(expertMode));
    }

    @Override
    public void selectWizard(List<Wizard> availableWizards) {
        clearCli();
        System.out.println("Now select your wizard for this game!\nHere's a list of available wizards:");
        printList(availableWizards);
        int wizard = inputInRange(1, Wizard.values().length, "select a valid wizard");
        Wizard selectedWizard = availableWizards.get(wizard - 1);
        notifyObserver(observers -> observers.onUpdateWizard(selectedWizard));
    }

    @Override
    public void selectTeam(String[] teamArray, String[] leaderArray) {
        int numPlayersTeam1 = (teamArray[0] == null ? 0 : 1) + (leaderArray[0] == null ? 0 : 1);
        int numPlayersTeam2 = (teamArray[1] == null ? 0 : 1) + (leaderArray[1] == null ? 0 : 1);
        boolean hasLeader1 = (leaderArray[0] != null);
        boolean hasLeader2 = (leaderArray[1] != null);

        System.out.println("There are 4 players connected, these are the teams now: ");
        for (int i = 0; i < min(teamArray.length, leaderArray.length); i++) {
            System.out.println("TEAM " + (i + 1) + " :" + "\nTeam leader: " + (leaderArray[i] != null ? leaderArray[i] : "this team does not have a leader yet!") + "\nOther member: " + (teamArray[i] != null ? teamArray[i] : "this team has no other members!"));
        }

        boolean isValidInput;
        int numTeam = 0;
        System.out.println("Select a team (" + (numPlayersTeam1 >= 2 ? "" : "1") + ((numPlayersTeam1 >= 2 || numPlayersTeam2 >=2) ? "" : "/")  + (numPlayersTeam2 >= 2 ? "" : "2") + "): ");
        do {
            isValidInput = true;
            try {
                numTeam = Integer.parseInt(scanner.nextLine());
                if (numTeam != 1 && numTeam != 2) {
                    isValidInput = false;
                    System.out.println("The given input is not valid.\nPlease, enter '1' if you want to play in team n.1, '2' to play in team n.2: ");
                }
                if ((numTeam == 1 && numPlayersTeam1 >= 2) || (numTeam == 2 && numPlayersTeam2 >= 2)) {
                    isValidInput = false;
                    System.out.println("The given input is not valid. The team you have selected is already full\nPlease, select another team: ");
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Please insert a number!\nSelect a team (" + (numPlayersTeam1 >= 2 ? "" : "1") + (numPlayersTeam2 >= 2 ? "" : "/2") + "): ");
            }
        } while (!isValidInput);
        String input;
        System.out.println("Do you want to be leader of the team? (Y/N): ");
        do {
            isValidInput = true;
            input = scanner.nextLine();
            if (!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N"))) {
                isValidInput = false;
                System.out.println("The given input is not valid.\nPlease, enter 'Y' if you want to be team leader, 'N' otherwise: ");
            }
            if (input.equalsIgnoreCase("Y")) {
                if (numTeam == 1) {
                    if (hasLeader1) {
                        isValidInput = false;
                        System.out.println("The selected team has already a team leader, you cannot be leader! Please choose 'N': ");
                    }
                }
                if (numTeam == 2) {
                    if (hasLeader2) {
                        isValidInput = false;
                        System.out.println("The selected team has already a team leader, you cannot be leader! Please choose 'N': ");
                    }
                }
            }
            if (input.equalsIgnoreCase("N")) {
                if (numTeam == 1 && numPlayersTeam1 == 1) {
                    if (!hasLeader1) {
                        isValidInput = false;
                        System.out.println("The selected team does not have a team leader, you have to be leader of the team! Please choose 'Y': ");
                    }
                }
                if (numTeam == 2 && numPlayersTeam2 == 1) {
                    if (!hasLeader2) {
                        isValidInput = false;
                        System.out.println("The selected team does not have a team leader, you have to be leader of the team! Please choose 'Y': ");
                    }
                }
            }
        } while (!isValidInput);
        boolean isTeamLeader;
        isTeamLeader = input.equalsIgnoreCase("Y");
        int finalNumTeam = numTeam;
        notifyObserver(observers -> observers.onUpdateTeam(finalNumTeam, isTeamLeader));

    }

    @Override
    public void selectTowerColor(List<Color> colorTowersAvailable) {
        System.out.println("Select the color of the towers in your dashboard!\nHere's a list of available colors: ");
        printList(colorTowersAvailable);
        int color = inputInRange(1, colorTowersAvailable.size(), "select a valid tower color");
        Color selectedColor = colorTowersAvailable.get(color - 1);
        notifyObserver(observers -> observers.onUpdateTowerColor(selectedColor));
    }

    @Override
    public void goToWaitingRoom() {
        clearCli();
        stop = false;
        System.out.println("Waiting for other players to make their choice or move ...");
        if (gm != null) {
            List<String> defaultActions = new ArrayList<>();
            defaultActions.add("See if the other player has finished his/hers turn");
            defaultActions.add("See the details of an Island");
            defaultActions.add("See the details of a Player's dashboard");
            defaultActions.add("See the current state of clouds");
            defaultActions.add("See Mother Nature position");

            if (gm.getNumPlayers() == 4 && gm.isChat()) {
                defaultActions.add("Send a message to your team mate");
                defaultActions.add("See received messages");
            }
            while (!stop) {
                System.out.println("\nThe other player hasn't finished his/hers turn, you need to be patient ...");

                System.out.println("\nWhile you're waiting, here's a list of available actions:");
                printList(defaultActions);
                int action = inputInRange(1, defaultActions.size(), "select a valid action");
                switch (defaultActions.get(action - 1)) {
                    case "See if the other player has finished his/hers turn":
                        break;
                    case "See the details of an Island":
                        focusOnIsland();
                        break;
                    case "See the details of a Player's dashboard":
                        focusOnDashboard();
                        break;
                    case "See the current state of clouds":
                        focusOnClouds();
                        break;
                    case "See Mother Nature position":
                        focusOnMotherNature();
                        break;
                    case "Send a message to your team mate":
                        sendMessage();
                        break;
                    case "See received messages":
                        stop = true;
                        System.out.println("\nSearching for new messages ...\n");
                        notifyObserver(ViewObserver::waitForMessage);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + defaultActions.get(action));
                }
                if (stop && defaultActions.get(action-1).equals("See if the other player has finished his/hers turn"))
                    System.out.println("\nThe other player has finished his/hers turn!");
            }
        }
    }

    @Override
    public void goToLobby() {
        System.out.println("You're now in the lobby! Waiting for other players to join ...");
    }

    @Override
    public void waitForOthersMoves(String move) {
        System.out.println("Another player is choosing the "+ move +". Wait your turn");
    }

    @Override
    public void selectAssistantCard(List<Card> availableCards) {
        stop = false;
        List<Card> playersDeck = gm.getPlayerByNickname(this.nickname).getDeck();


        Set<Card> result = availableCards.stream().distinct().filter(gm.getPlayerByNickname(this.nickname).
                getDeck()::contains).collect(Collectors.toSet());
        System.out.println("It's your turn, choose an assistant card for this round!");
        System.out.println("Here's a list of the cards in your deck, the cards marked with an 'X' have already been chosen by another player.\nIf you have other cards in your deck, you can't use them in this round.");
        int i = 1;
        for (Card c : playersDeck) {
            System.out.println(i + " - " + c + (!result.contains(c) ? " X" : ""));
            i++;
        }
        boolean isValidInput;
        int chosenCard;
        System.out.println("Choose a card by entering a number between 1 and " + (i-1));
        do {
            isValidInput = true;
            chosenCard = inputInRange(1, playersDeck.size(), "to select a valid card");
            if (!result.contains(playersDeck.get(chosenCard-1)) && result.size() > 0) {
                isValidInput = false;
                System.out.println("You can't choose this card, it has already been chosen by another player!\nChoose another card: ");
            }
        } while (!isValidInput);
        int finalChosenCard = chosenCard;
        notifyObserver(observers -> observers.onUpdateAssistantCard(playersDeck.get(finalChosenCard-1)));
    }

    @Override
    public void actionPhase(List<String> availableActions) {
        boolean stop = false;
        do {
            System.out.println("It's up to you! Here's a list of all the available actions:");
            printList(availableActions);
            int chosenAction = inputInRange(1, availableActions.size(), "to select a valid action");
            switch (availableActions.get(chosenAction - 1)) {
                case "See the details of an Island":
                    focusOnIsland();
                    break;
                case "See the details of a Player's dashboard":
                    focusOnDashboard();
                    break;
                case "See the current state of clouds":
                    focusOnClouds();
                    break;
                case "Move students to dining hall or to island":
                    boolean movedStudents = moveStudents();
                    if (movedStudents)
                        stop = true;
                    break;
                case "Move Mother Nature":
                    boolean movedMother = moveMotherNature();
                    if (movedMother)
                        stop = true;
                    break;
                case "Select character card":
                    boolean usedCard = useCharacterCard();
                    if (usedCard)
                        stop = true;
                    break;
                case "See Mother Nature position":
                    focusOnMotherNature();
                    break;
                case "Send a message to your team mate":
                    sendMessage();
                    break;
                case "See received messages":
                    stop = true;
                    System.out.println("\nSearching for new messages ...\n");
                    notifyObserver(ViewObserver::waitForMessage);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + availableActions.get(chosenAction - 1));
            }
        } while (!stop);
    }

    /**
     * Asks the player to select an island and shows the details (students, towers, no entry tiles) of the chosen island.
     */
    private void focusOnIsland() {
        System.out.println("Enter the number of the island you want to see (between 1 and " + gm.getIslandList().size() + ", 0 to go back):");
        int numIsland = inputInRange(0, gm.getIslandList().size(),"select a valid island" );
        if (numIsland == 0)
            return;
        System.out.println("Showing details of Island " + numIsland + "...");
        System.out.println(gm.getIslandList().get(numIsland - 1));
    }

    /**
     * Asks the player to select a player and shows the details of his/hers dashboard.
     */
    private void focusOnDashboard() {
        System.out.println("Which player do you want to see the dashboard of? (0 to go back)");
        printList(Arrays.asList(gm.getArrayPlayers()));
        int player = inputInRange(0, gm.getNumPlayers(), "select an existing player");
        if (player == 0)
            return;
        System.out.println("Showing details of " + gm.getArrayPlayers()[player - 1].getNickname() + "'s dashboard...");
        System.out.println(gm.getArrayPlayers()[player - 1].getDashboard());
    }

    /**
     * Shows the current state of the clouds.
     */
    private void focusOnClouds() {
        System.out.println("Current state of clouds:");
        printList(Arrays.asList(gm.getArrayClouds()));
    }

    /**
     * Manages the selection of students to move from the player's dashboard to his/hers dining hall or an island.
     */
    private boolean moveStudents() {
            System.out.println("Do you want to move students to your dining hall (1) or to an island (2) (0 to go back)?");
            int chosen = inputInRange(0, 2, "select a valid action");
            if (chosen == 0)
                return false;
            boolean moved;
            switch (chosen) {
                case 1:
                    moved = moveStudentsToDiningHall();
                    break;
                case 2:
                    moveStudentsToIsland();
                    moved = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + chosen);
            }
            return moved;
    }

    /**
     * Manages the selection of students to move from the player's dashboard to his/hers dining hall.
     */
    private boolean moveStudentsToDiningHall() {
        // checking whether the dining hall is full
        boolean fullDiningHall = true;
        for (House h : House.values()) {
            if (!(gm.getCurrentPlayer().getDashboard().getDiningHall().getHouseStudents(h) >= 10)) {
                fullDiningHall = false;
            }
        }
        if (fullDiningHall) {
            System.out.println("Your dining hall is full, you cannot add any students!");
            return false;
        }

        System.out.println("This is your dashboard: \n" + gm.getCurrentPlayer().getDashboard());
        // receiving and checking input
        System.out.println("Enter the house of the student you want to move:");

        List<House> availableHouses = new ArrayList<>();
        for (House h : House.values()) {
            if (gm.getCurrentPlayer().getDashboard().getHouseStudents(h) > 0)
                availableHouses.add(h);
        }
        House chosenHouse = selectHouse(availableHouses);
        notifyObserver(observers -> observers.onMoveStudentsToDiningHall(chosenHouse));
        return true;
    }

    /**
     * Manages the selection of students to move from the player's dashboard to an island.
     */
    private void moveStudentsToIsland() {
        System.out.println("Enter the house of the student you want to move:");
        List<House> availableHouses = new ArrayList<>();
        for (House h : House.values()) {
            if (gm.getCurrentPlayer().getDashboard().getHouseStudents(h) > 0)
                availableHouses.add(h);
        }
        boolean isValidInput;
        House chosenHouse;
        do {
            isValidInput = true;
            chosenHouse = selectHouse(availableHouses);
            if (gm.getCurrentPlayer().getDashboard().getHouseStudents(chosenHouse) <= 0) {
                isValidInput = false;
                System.out.println("You don't have any students of the " + chosenHouse + " house, please choose another house:");
            }
        } while (!isValidInput);

        int chosenIsland = selectIsland();
        House finalChosenHouse = chosenHouse;
        notifyObserver(observers -> observers.onMoveStudentsToIsland(finalChosenHouse, chosenIsland - 1));
    }

    /**
     * Asks the player of how many steps he/she wants to move mother nature.
     */
    private boolean moveMotherNature() {
        boolean isValidInput;
        int chosenMoves;
        do {
            isValidInput = true;
            System.out.println("How many steps you want to move mother nature of? (1" + (gm.getCurrentPlayer().getMaxMoves() == 1 ? ", 0 to go back)" : " - " + gm.getCurrentPlayer().getMaxMoves() + ", 0 to go back)"));
            chosenMoves = inputInRange(0, gm.getCurrentPlayer().getMaxMoves(), "select a valid a number of moves");
            if (chosenMoves == 0)
                return false;
            if ((gm.getMotherIsland() + chosenMoves) % gm.getIslandList().size() == gm.getMotherIsland()) {
                isValidInput = false;
                System.out.println("With the selected number of moves Mother Nature would remain on the same island. Please select a different number of moves");
            }
        } while (!isValidInput);
        int finalChosenMoves = chosenMoves;
        notifyObserver(observers -> observers.onMoveMotherNature(finalChosenMoves));
        return true;
    }

    /**
     * Asks the player to select a character card to use and the parameters required by that card.
     */
    private boolean useCharacterCard() {
        System.out.println("Here's a list of the character cards available in this game! You have " + gm.getPlayerByNickname(this.nickname).getCoins() + " coins to spend");
        printList(Arrays.asList(gm.getCharacterCardDeck()));

        Gson gson = new Gson();

        boolean poorPlayer = true;
        for (ReducedCharacterCard c : gm.getCharacterCardDeck()) {
            if (c.getCost() <= gm.getCurrentPlayer().getCoins()) {
                poorPlayer = false;
                break;
            }
        }
        if (poorPlayer) {
            System.out.println("You don't have enough money to use any of this cards!");
            return false;
        }

        System.out.println("Enter a number between 1 and " + gm.getCharacterCardDeck().length + " to select a card (0 to go back):");
        boolean isValidInput;
        int chosenCard;
        do {
            isValidInput = true;
            chosenCard = inputInRange(0, gm.getCharacterCardDeck().length, "select a valid card");
            if (chosenCard == 0)
                return false;
            if (gm.getCurrentPlayer().getCoins() < gm.getCharacterCardDeck()[chosenCard-1].getCost()) {
                isValidInput = false;
                System.out.println("You haven't got enough money to use the selected card, choose another card (0 to go back):");
            }
        } while (!isValidInput);

        // adding the parameters to be sent to the server
        Map<String, Object> parameters = new HashMap<>();
        int chosenIsland;
        House chosenHouse;
        switch (gm.getCharacterCardDeck()[chosenCard - 1].getType()) {

            case MONK: // needed: - wantedHouse, - destinationIsland
                // wantedHouse
                System.out.println("Select the house of the student you want to take from this card");

                List<House> availableHouses = new ArrayList<>();
                for (House h : House.values()) {
                    if (gm.getCharacterCardDeck()[chosenCard-1].getHouseMap().get(h) > 0)
                        availableHouses.add(h);
                }

                chosenHouse = selectHouse(availableHouses);

                parameters.put("wantedHouse", chosenHouse);

                // destinationIsland
                System.out.println("\nSelect the island where you want to put the selected student");
                chosenIsland = selectIsland();
                parameters.put("destinationIsland", chosenIsland-1);

                break;

            case HERALD: // needed: - Island
                System.out.println("You should now choose an island to resolve as if Mother Nature has ended her movement there");
                chosenIsland = selectIsland();
                parameters.put("island", chosenIsland-1);

                break;

            case HERB_GRANMA: // needed: - island
                System.out.println("You should now choose an island where to put a no entry tile");
                chosenIsland = selectIsland();
                parameters.put("island", chosenIsland-1);

                break;

            case JOLLY: // needed: - wantedStudents, - returnedStudents
                // wantedStudents
                Map<House, Integer> wantedStudents = new HashMap<>();
                for (House h : House.values()) {
                    wantedStudents.put(h, 0);
                }

                System.out.println("Select the houses of the students you want to take from the card:");
                availableHouses = new ArrayList<>();
                int numChosenStudents = 0;
                for (int i = 0; i < 3; i++) {
                    availableHouses.clear();
                    for (House h : House.values()) {
                        if (gm.getCharacterCardDeck()[chosenCard-1].getHouseMap().get(h) - wantedStudents.get(h) > 0)
                            availableHouses.add(h);
                    }

                    chosenHouse = selectHouse(availableHouses);
                    wantedStudents.replace(chosenHouse, wantedStudents.get(chosenHouse)+1);
                    numChosenStudents++;

                    if (i != 2) {
                        System.out.println("Do you want to move another student? ('Y'/'N'):");
                        if (!YNInput("you want move another student"))
                            break;
                    }
                }

                // returnedStudents
                System.out.println("Select the houses of the students you want to return from your Entrance:");
                Map<House, Integer> returnedStudents = new HashMap<>();
                for (House h : House.values()) {
                    returnedStudents.put(h, 0);
                }
                availableHouses = new ArrayList<>();

                for (int i = 0; i < numChosenStudents; i++) {
                    availableHouses.clear();
                    for (House h : House.values()) {
                        if (gm.getCurrentPlayer().getDashboard().getHouseStudents(h) - returnedStudents.get(h) > 0)
                            availableHouses.add(h);
                    }
                    chosenHouse = selectHouse(availableHouses);
                    returnedStudents.replace(chosenHouse, returnedStudents.get(chosenHouse)+1);
                }
                parameters.put("wantedStudents", gson.toJson(wantedStudents));
                parameters.put("returnedStudents", gson.toJson(returnedStudents));

                break;

            case MINSTREL: // needed: -fromDashboard, - fromDiningHall

                // fromDashboard
                System.out.println("Select up to two students to move from your Entrance");
                Map<House, Integer> fromDashboardMap = new HashMap<>();
                for (House h : House.values()) {
                    fromDashboardMap.put(h, 0);
                }
                availableHouses = new ArrayList<>();
                numChosenStudents = 0;

                for (int i = 0; i < 2; i++) {
                    availableHouses.clear();
                    for (House h : House.values()) {
                        if (gm.getCurrentPlayer().getDashboard().getHouseStudents(h) - fromDashboardMap.get(h) > 0)
                            availableHouses.add(h);
                    }
                    chosenHouse = selectHouse(availableHouses);

                    fromDashboardMap.put(chosenHouse, fromDashboardMap.get(chosenHouse)+1);
                    numChosenStudents++;

                    if (i != 1) {
                        System.out.println("Do you want to move another student? ('Y'/'N'):");
                        if (!YNInput("you want move another student"))
                            break;
                    }
                }
                House[] fromDashboard = new House[numChosenStudents];
                int index = 0;
                for (House h : House.values()) {
                    for (int i = 0; i < fromDashboardMap.get(h);  i++) {
                        if (index < numChosenStudents) {
                            fromDashboard[index] = h;
                            index++;
                        }
                    }
                }


                // fromDiningHall
                System.out.println("Select up to two students to move from your dining hall");
                Map<House, Integer> fromDiningHallMap = new HashMap<>();
                for (House h : House.values()) {
                    fromDiningHallMap.put(h, 0);
                }
                availableHouses = new ArrayList<>();

                for (int i = 0; i < numChosenStudents; i++) {
                    availableHouses.clear();
                    for (House h : House.values()) {
                        if (gm.getCurrentPlayer().getDashboard().getDiningHall().getHouseStudents(h) - fromDiningHallMap.get(h) > 0)
                            availableHouses.add(h);
                    }
                    chosenHouse = selectHouse(availableHouses);
                    fromDiningHallMap.replace(chosenHouse, fromDiningHallMap.get(chosenHouse)+1);
                }
                House[] fromDiningHall = new House[numChosenStudents];
                index = 0;
                for (House h : House.values()) {
                    for (int i = 0; i < fromDiningHallMap.get(h);  i++) {
                        if (index < numChosenStudents) {
                            fromDiningHall[index] = h;
                            index++;
                        }
                    }
                }

                parameters.put("fromDashboard", gson.toJson(fromDashboard));
                parameters.put("fromDiningHall", gson.toJson(fromDiningHall));

                break;

            case MUSHROOM_HUNTER:

                System.out.println("Choose the house you want not be considered during the evaluation of influence:");
                chosenHouse = selectHouse(Arrays.asList(House.values()));
                parameters.put("house", chosenHouse);

                break;

            case SPOILED_PRINCESS:

                availableHouses = new ArrayList<>();
                boolean fullDiningHall = true;
                for (House h : House.values()) {
                    if (gm.getCharacterCardDeck()[chosenCard-1].getHouseMap().get(h) > 0)
                        availableHouses.add(h);
                    if (gm.getPlayerByNickname(this.nickname).getDashboard().getDiningHall().getHouseStudents(h) < 10)
                        fullDiningHall = false;
                }
                if (fullDiningHall) {
                    System.out.println("\nYour dining hall is full, you cannot you this card!\n2");
                    return false;
                }
                System.out.println("Choose the house of the student you want to take from the card:");
                do {
                    isValidInput = true;
                    chosenHouse = selectHouse(availableHouses);
                    if (gm.getPlayerByNickname(this.nickname).getDashboard().getDiningHall().getHouseStudents(chosenHouse) == 10) {
                        isValidInput = false;
                        System.out.println("Your dining hall for the selected house is full, you cannot add any other student. Select another house!");
                    }
                } while (!isValidInput);
                parameters.put("wantedHouse", chosenHouse);

                break;

            case THIEF:
                System.out.println("Select the house of the students you want to removed from player's dashboards:");
                chosenHouse = selectHouse(Arrays.asList(House.values()));
                parameters.put("wantedHouse", chosenHouse);

                break;

            case KNIGHT:
                System.out.println("You have selected " + CharacterCardEnum.KNIGHT + ", during the influence calculation this turn, you count as having 2 more influence");

                break;

            case MAGICAL_MAILMAN:
                System.out.println("You have selected " + CharacterCardEnum.MAGICAL_MAILMAN + ", you'll be able to move Mother Nature up to 2 additional Islands");

                break;

            case FARMER:
                System.out.println("You have selected " + CharacterCardEnum.FARMER + ", during this turn, you take control of any number of Professors even if you have " +
                "the same number of Students as the player who currently controls them");

                break;

            case CENTAUR:
                System.out.println("You have selected " + CharacterCardEnum.CENTAUR + ", when resolving a Conquering on an Island, Towers will not count towards influence.");

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + gm.getCharacterCardDeck()[chosenCard - 1].getType());
        }

        int finalChosenCard = chosenCard;
        notifyObserver(observers -> observers.onUpdateCharacterCard(finalChosenCard-1, parameters));
        return true;
    }

    private void focusOnMotherNature () {
        System.out.println("Mother nature is currently on island " + (gm.getMotherIsland()+1));
    }

    @Override
    public void selectCloud() {
        stop = false;
        System.out.println("You should now select a cloud...");
        List<ReducedCloud> availableClouds = Arrays.stream(gm.getArrayClouds()).filter(ReducedCloud::isFull).collect(Collectors.toList());
        printList(availableClouds);
        System.out.println("Select a cloud (1" + (availableClouds.size() == 1 ? ")" : "-" + availableClouds.size() + "):"));
        boolean isValidInput;
        int chosenCloud;
        do {
            isValidInput = true;
                chosenCloud = inputInRange(1, availableClouds.size(), "select a valid cloud");
                if (!availableClouds.get(chosenCloud-1).isFull()) {
                    isValidInput = false;
                    System.out.println("The cloud you have selected has already been chosen by another player! Please select another one:");
                }
        } while (!isValidInput);
        int finalChosenCloud = chosenCloud;
        notifyObserver(observers -> observers.onUpdateCloud(Arrays.asList(gm.getArrayClouds()).indexOf(availableClouds.get(finalChosenCloud -1))));
    }

    private void sendMessage() {
        System.out.println("Enter the message you want to send to your teammate (0 to go back):");
        boolean isValidInput;
        String message;
        do {
            isValidInput = true;
            message = scanner.nextLine();
            if (message.isEmpty()) {
                isValidInput = false;
                System.out.println("You should write something ...\nWrite your message here:");
            }
        } while (!isValidInput);

        if (message.equals("0"))
            return;
        String finalMessage = message;
        notifyObserver(observers -> observers.onSendMessage(finalMessage));
    }
    @Override
    public void sendWinner(String winner) {
        if (gm.getNumPlayers() == 4) {
            String winnerTeamMate = gm.getTeamMate(winner);
            System.out.println("GAME ENDED! " + winner + " and " + winnerTeamMate + " won the game! Thank you for playing!");
        } else {
            System.out.println("GAME ENDED! " + winner + " won the game! Thank you for playing!");
        }

        System.out.println("Do you want to play another game? (Y/N)");
        boolean newGame = YNInput("you want to play again");
        if (newGame)
            notifyObserver(observers -> observers.onCreateNewGame(nickname));
        else
            System.exit(0);
    }

    // <------ UpdateMethods -------->

    @Override
    public void updateIslands(List<ReducedIsland> islands) {
        gm.setIslandList(islands);
    }

    @Override
    public void updateDiningHall(ReducedDiningHall diningHall) {
        gm.getPlayerByNickname(diningHall.getNickname()).getDashboard().setDiningHall(diningHall);
    }

    @Override
    public void updateDashboard(ReducedDashboard dashboard) {
        gm.getPlayerByNickname(dashboard.getNickname()).setDashboard(dashboard);
    }

    @Override
    public void updatePlayer(ReducedPlayer player) {
        int index = Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getPlayerByNickname(player.getNickname()));
        gm.setPlayer(index, player);
    }

    @Override
    public void updateClouds(ReducedCloud[] clouds) {
        gm.setArrayClouds(clouds);
    }

    @Override
    public void updateTotalCoins(int totCoins) {
        gm.setTotalCoins(totCoins);
    }

    @Override
    public void updateMotherNature(int motherIsland) {
        gm.setMotherIsland(motherIsland);
    }

    @Override
    public void updateCurrentPlayer(ReducedPlayer currentPlayer) {
        gm.setCurrentPlayer(currentPlayer);
    }

    @Override
    public void updateGameModel(ReducedGameModel gameModel) {
        this.gm = gameModel;
    }

    // <--------- Utility methods --------->

    /**
     * Prints the given list adding, before each element, an index from 1 to the size of the list.
     *
     * @param listToPrint the list to print
     * @param <T> the type of the list elements
     */
    private <T> void printList (List<T> listToPrint) {
        int i = 1;
        for (T t : listToPrint) {
            if (t instanceof House)
                System.out.println(i + " - " + ((House) t).getColouredHouse());
            else if (t instanceof ReducedPlayer) {
                System.out.println(i + " - " + ((ReducedPlayer) t).getNickname());
            } else
                System.out.println(i + " - " + t.toString());
            i++;
        }
    }

    /**
     * Asks the player to select a house and checks the validity of the input.
     *
     * @return the house selected by the player
     */
    private House selectHouse (List<House> availableHouses) {
        System.out.println("Houses:");
        printList(availableHouses);
        System.out.println("Select an house by entering a number between 1 and " + availableHouses.size() + ":");
        int chosenHouse = inputInRange(1, availableHouses.size(), "select a valid house");
        return availableHouses.get(chosenHouse-1);
    }

    /**
     * Asks the player to select an island and checks the validity of the input.
     *
     * @return the index of the island selected by the player
     */
    private int selectIsland() {
        System.out.println(("Islands:"));
        printList(gm.getIslandList());
        System.out.println("Select an island (1 - " + gm.getIslandList().size() + "):");
        return inputInRange(1, gm.getIslandList().size(), "select a valid island");
    }

    /**
     * Manages input of the type Y/N (Yes or No)
     *
     * @param action a string describing the action to perform if the player selects 'Y'
     * @return true if the player selects 'Y', false otherwise
     */
    private boolean YNInput (String action) {
        boolean isValidInput;
        String input;
        do {
            isValidInput = true;
            input = scanner.nextLine();
            if (!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N"))) {
                isValidInput = false;
                System.out.println("The given input is not valid.\nPlease, enter 'Y' if " + action + ", 'N' otherwise: ");
            }
        } while (!isValidInput);
        return input.equalsIgnoreCase("Y");
    }

    /**
     * Reads as input from the user a number between min and max, uses the message to print a specific error message.
     *
     * @param min the minimum accepted value
     * @param max the maximum accepted value
     * @param message a message used to print a specific error message
     * @return the value entered by the user
     */
    private int inputInRange (int min, int max, String message) {
        boolean isValidInput;
        int input = 0;
        do {
            try {
                isValidInput = true;
                input = Integer.parseInt(scanner.nextLine());
                if (!(input >= min && input <= max))
                    isValidInput = false;
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
            if (!isValidInput)
                System.out.println("Invalid input! Insert " + (min == max ? min + ":" : "a number between " + min + " and " + max + ", to " + message + ":"));
        } while (!isValidInput);
        return input;
    }

    /**
     * Checks that the given ip address is a valid ip address.
     *
     * @param ip the ip address to validate
     * @return true if the given ip is valid, false otherwise
     */
    private static boolean checkIP(String ip) {

        String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";

        String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;

        Pattern p = Pattern.compile(regex);

        if (ip == null) {
            return false;
        }

        Matcher m = p.matcher(ip);

        return m.matches();
    }

    /**
     * Clears terminal.
     */
    private void clearCli() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
