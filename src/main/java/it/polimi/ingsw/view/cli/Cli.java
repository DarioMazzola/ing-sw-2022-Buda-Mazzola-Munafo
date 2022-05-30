package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.ClientMain;
import it.polimi.ingsw.client.*;
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

    /**
     * Class constructor.
     */
    public Cli() {
        scanner = new Scanner(System.in);
    }


    public void setGm(ReducedGameModel gm) {
        this.gm = gm;
    }

    /**
     * Initiates the communication with the server.
     */
    public void start() {

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
            System.out.println("IP address of the server? [default: localhost]");
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
        String nickname = scanner.nextLine();
        notifyObserver(observers -> observers.onCreateNewGame(nickname));
    }

    @Override
    public void selectNickname() {
        System.out.print("Please, enter your nickname: ");
        String nickname = scanner.nextLine();
        notifyObserver(observers -> observers.onUpdateNickname(nickname));
    }

    @Override
    public void selectNumPlayers() {
        System.out.println("How many players want to play? (enter a number between 2 and 4):");
        int numPlayers = inputInRange(2, 4, "select a valid number of player for the game");
        notifyObserver(observers -> observers.onUpdateNumPlayers(numPlayers));
    }

    @Override
    public void selectExpertMode() {
        clearCli();
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
            System.out.println("TEAM " + (i + 1) + " :" + "Team leader: " + (leaderArray[i] != null ? leaderArray[i] : "this team does not have a leader yet! ") + "Other member: " + (teamArray[i] != null ? teamArray[i] : "this team has no other members!"));
        }

        boolean isValidInput;
        int numTeam = 0;
        System.out.println("Select a team (" + (numPlayersTeam1 >= 2 ? "" : "1") + (numPlayersTeam2 >= 2 ? "" : "/2") + "): ");
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
        System.out.println("Waiting for other players ...");
    }

    @Override
    public void goToLobby() {
        System.out.println("You're now in the lobby! Waiting for other players to join ...");
    }

    @Override
    public void selectAssistantCard(List<Card> availableCards) {
        List<Card> playersDeck = gm.getCurrentPlayer().getDeck();

        Set<Card> result = availableCards.stream().distinct().filter(gm.getPlayerByNickname(gm.getCurrentPlayer().getNickname()).
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
        System.out.println("Chose a card by entering a number between 1 and " + (i-1));
        do {
            isValidInput = true;
            chosenCard = inputInRange(1, i-1, "to select a valid card");
            if (!result.contains(playersDeck.get(chosenCard-1)) && result.size() > 0) {
                isValidInput = false;
                System.out.println("You can't chose this card, it has already been chosen by another player!\nChose another card: ");
            }
        } while (!isValidInput);
        int finalChosenCard = chosenCard;
        notifyObserver(observers -> observers.onUpdateAssistantCard(playersDeck.get(finalChosenCard-1)));
    }

    @Override
    public void actionPhase(List<String> availableActions) {
        boolean stop = false;
        do {
            System.out.println("Here's a list of all the available actions:");
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
                    moveStudents();
                    stop = true;
                    break;
                case "Move Mother Nature":
                    moveMotherNature();
                    stop = true;
                    break;
                case "Select character card":
                    useCharacterCard();
                    break;
            }
        } while (!stop);
    }

    /**
     * Asks the player to select an island and shows the details (students, towers, no entry tiles) of the chosen island.
     */
    private void focusOnIsland() {
        boolean isValidInput;
        int numIsland = 0;
        System.out.println("Enter the number of the island you want to see (between 1 and " + gm.getIslandList().size() + " ):");
        do {
            isValidInput = true;
            try {
                numIsland = Integer.parseInt(scanner.nextLine());
                if (!(numIsland >= 1 && numIsland <= gm.getIslandList().size())) {
                    isValidInput = false;
                    System.out.println("Invalid input! Enter a number between 1 and " + gm.getIslandList().size());
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Invalid input! Please insert a number:");
            }
        }
        while (!isValidInput);
        System.out.println("Showing details of Island " + numIsland + "...");
        System.out.println(gm.getIslandList().get(numIsland - 1));
    }

    /**
     * Asks the player to select a player and shows the details of his/hers dashboard.
     */
    private void focusOnDashboard() {
        boolean isValidInput;
        System.out.println("Which player do you want to see the dashboard of?");
        int i = 1;
        for (ReducedPlayer p : gm.getArrayPlayers()) {
            System.out.println(i + " ) " + ((p.equals(gm.getCurrentPlayer())) ? "YOU" : p.getNickname()));
            i++;
        }
        int player = 0;
        do {
            isValidInput = true;
            try {
                player = Integer.parseInt(scanner.nextLine());
                if (!(player >= 1 && player <= gm.getArrayPlayers().length)) {
                    isValidInput = false;
                    System.out.println("Invalid input! Enter a number between 1 and " + gm.getArrayPlayers().length);
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Invalid input! Please insert a number:");
            }
        }
        while (!isValidInput);
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
    private void moveStudents() {
            System.out.println("Do you want to move students to your dining hall (1) or to an island (2)?");
            int chosen = 0;
            boolean isValidInput;
            do {
                isValidInput = true;
                try {
                    chosen = Integer.parseInt(scanner.nextLine());
                    if (!(chosen == 1 || chosen == 2)) {
                        isValidInput = false;
                        System.out.println("Invalid input! Please enter '1' or '2':");
                    }
                } catch (NumberFormatException e) {
                    isValidInput = false;
                    System.out.println("Invalid input! Please enter a number:");
                }
            } while (!isValidInput);
            switch (chosen) {
                case 1:
                    moveStudentsToDiningHall();
                    break;
                case 2:
                    moveStudentsToIsland();
                    break;
            }

    }

    /**
     * Manages the selection of students to move from the player's dashboard to his/hers dining hall.
     */
    private void moveStudentsToDiningHall() {
        // checking whether the dining hall is full
        boolean fullDiningHall = true;
        for (House h : House.values()) {
            if (!(gm.getCurrentPlayer().getDashboard().getDiningHall().getHouseStudents(h) >= 10)) {
                fullDiningHall = false;
            }
        }
        if (fullDiningHall) {
            System.out.println("Your dining hall is full, you cannot add any students!");
            return;
        }

        System.out.println("This is your dashboard: \n" + gm.getCurrentPlayer().getDashboard());
        // receiving and checking input
        System.out.println("Enter the house of the student you want to move:");
        House chosenHouse = selectHouse();
        notifyObserver(observers -> observers.onMoveStudentsToDiningHall(chosenHouse));
    }

    /**
     * Manages the selection of students to move from the player's dashboard to an island.
     */
    private void moveStudentsToIsland() {
        System.out.println("Enter the house of the student you want to move:");
        boolean isValidInput;
        House chosenHouse;
        do {
            isValidInput = true;
            chosenHouse = selectHouse();
            if (gm.getCurrentPlayer().getDashboard().getHouseStudents(chosenHouse) <= 0) {
                isValidInput = false;
                System.out.println("You don't have any students of the " + chosenHouse + " house, please chose another house:");
            }
        } while (!isValidInput);

        System.out.println("Enter the island where you want to move the student (1 - " + gm.getIslandList().size() + "):");
        int chosenIsland = selectIsland();
        House finalChosenHouse = chosenHouse;
        notifyObserver(observers -> observers.onMoveStudentsToIsland(finalChosenHouse, chosenIsland - 1));
    }

    /**
     * Asks the player of how many steps he/she wants to move mother nature.
     */
    private void moveMotherNature() {
        System.out.println("How many steps you want to move mother nature of? (1" + (gm.getCurrentPlayer().getMaxMoves() == 1 ? ")" : " - " + gm.getCurrentPlayer().getMaxMoves() + ")"));
        boolean isValidInput;
        int chosenMoves = -1;
        do {
            isValidInput = true;
            try {
                chosenMoves = Integer.parseInt(scanner.nextLine());
                if (!(chosenMoves >= 0 && chosenMoves <= gm.getCurrentPlayer().getMaxMoves())) {
                    isValidInput = false;
                    System.out.println("Invalid input! Please enter a number between 0 and " +  gm.getCurrentPlayer().getMaxMoves() + " :");
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Invalid input! Please insert a number:");
            }
        } while (!isValidInput);
        int finalChosenMoves = chosenMoves;
        notifyObserver(observers -> observers.onMoveMotherNature(finalChosenMoves) );
    }

    /**
     * Asks the player to select a character card to use and the parameters required by that card.
     */
    private void useCharacterCard() {
        System.out.println("Here's a list of the character cards available in this game!");
        printList(Arrays.asList(gm.getCharacterCardDeck()));

        System.out.println("Enter a number between 1 and " + gm.getCharacterCardDeck().length + " to select a card:");
        boolean isValidInput;
        int chosenCard = 0;
        do { // TODO: estrarne metodo per prendere input in un intervallo di valori
            isValidInput = true;
            chosenCard = inputInRange(0, gm.getCharacterCardDeck().length, "select a valid card");
            if (chosenCard == 0)
                return;
            if (gm.getCurrentPlayer().getCoins() < gm.getCharacterCardDeck()[chosenCard-1].getCost()) {
                isValidInput = false;
                System.out.println("You haven't got enough money to use the selected card, choose another card");
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
                do {
                    isValidInput = true;
                    chosenHouse = selectHouse();
                    if (gm.getCharacterCardDeck()[chosenCard - 1].getHouseMap().get(chosenHouse) <= 0) {
                        isValidInput = false;
                        System.out.println("There are no students of " + chosenCard + " house on the card");
                    }
                } while (!isValidInput);
                parameters.put("wantedHouse", chosenHouse);

                // destinationIsland
                System.out.println("Select the island where you want to put the selected student");
                chosenIsland = selectIsland();
                parameters.put("destinationIsland", chosenIsland);

                break;

            case HERALD: // needed: - Island
            case HERB_GRANMA: // needed: - island
                chosenIsland = selectIsland();
                parameters.put("island", chosenIsland);

                break;

            case JOLLY: // needed: - wantedStudents, - returnedStudents
                Map<House, Integer> wantedStudents = new HashMap<>();
                for (House h : House.values()) {
                    wantedStudents.put(h, 0);
                }
                System.out.println("Select the houses of the students you want to take from the card:");
                for (int i = 0; i < 3; i++) {
                    do {
                        isValidInput = true;
                        chosenHouse = selectHouse();
                        if (gm.getCharacterCardDeck()[chosenCard - 1].getHouseMap().get(chosenHouse) - wantedStudents.get(chosenHouse) <= 0) {
                            isValidInput = false;
                            System.out.println("There are no students of the " + chosenHouse + " house on the card");
                        }
                    } while (!isValidInput);
                    wantedStudents.replace(chosenHouse, wantedStudents.get(chosenHouse)+1);

                    if (i != 2) {
                        System.out.println("Do you want to move another student? ('Y'/'N'):");
                        if (!YNInput("you want move another student"))
                            break;
                    }
                }

                System.out.println("Select the houses of the students you want to return from your Entrance:");
                Map<House, Integer> returnedStudents = new HashMap<>();
                for (House h : House.values()) {
                    returnedStudents.put(h, 0);
                }
                for (int i = 0; i < 3; i++) {
                    do {
                        isValidInput = true;
                        chosenHouse = selectHouse();
                        if (gm.getCharacterCardDeck()[chosenCard - 1].getHouseMap().get(chosenHouse) - returnedStudents.get(chosenHouse) <= 0) {
                            isValidInput = false;
                            System.out.println("There are no students of the " + chosenHouse + " house in your Entrance");
                        }
                    } while (!isValidInput);
                    returnedStudents.replace(chosenHouse, wantedStudents.get(chosenHouse)+1);

                    if (i != 2) {
                        System.out.println("Do you want to move another student? ('Y'/'N'):");
                        if (!YNInput("you want move another student"))
                            break;
                    }
                }

                parameters.put("wantedStudents", wantedStudents);
                parameters.put("returnedStudents", returnedStudents);
                break;

            case MINSTREL: // needed: -fromDashboard, - fromDiningHall
                House[] fromDashboard = new House[2];
                for (int i = 0; i < 2; i++) {
                    do {
                        isValidInput = true;
                        chosenHouse = selectHouse();
                        if (gm.getCurrentPlayer().getDashboard().getHouseStudents(chosenHouse) <= 0) {
                            isValidInput = false;
                            System.out.println("There are no students of the " + chosenHouse + " house in your entrance! Select another house:");
                        }
                    } while (!isValidInput);
                    fromDashboard[i] = chosenHouse;

                    if (i != 1) {
                        System.out.println("Do you want to move another student? ('Y'/'N'):");
                        if (!YNInput("you want move another student"))
                            break;
                    }
                }

                House[] fromDiningHall = new House[2];
                for (int i = 0; i < 2; i++) {
                    do {
                        isValidInput = true;
                        chosenHouse = selectHouse();
                        if (gm.getCurrentPlayer().getDashboard().getDiningHall().getHouseStudents(chosenHouse) <= 0) {
                            isValidInput = false;
                            System.out.println("There are no students of the " + chosenHouse + " house in your dining hall! Select another house:");
                        }
                    } while (!isValidInput);
                    fromDiningHall[i] = chosenHouse;

                    if (i != 1) {
                        System.out.println("Do you want to move another student? ('Y'/'N'):");
                        if (!YNInput("you want move another student"))
                            break;
                    }
                }

                parameters.put("fromDashboard", fromDashboard);
                parameters.put("fromDiningHall", fromDiningHall);
                break;

            case MUSHROOM_HUNTER:
                System.out.println("Choose the house you want not be considered during the evaluation of influence:");
                chosenHouse = selectHouse();
                parameters.put("house", chosenHouse);
                break;

            case SPOILED_PRINCESS:
                System.out.println("Choose the house of the student you want to take from the card:");
                do {
                    isValidInput = true;
                    chosenHouse = selectHouse();
                    if (gm.getCharacterCardDeck()[chosenCard - 1].getHouseMap().get(chosenHouse) <= 0) {
                        isValidInput = false;
                        System.out.println("There are no students of the " + chosenHouse + " house on the card");
                    }
                } while (!isValidInput);
                parameters.put("wantedHouse", chosenHouse);
                break;

            case THIEF:
                System.out.println("Select the house of the students you want to removed from player's dashboards:");
                chosenHouse = selectHouse();
                parameters.put("house", chosenHouse);
                break;

            case KNIGHT:
            case MAGICAL_MAILMAN:
            case FARMER:
            case CENTAUR:
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + gm.getCharacterCardDeck()[chosenCard - 1].getType());
        }

        int finalChosenCard = chosenCard;
        notifyObserver(observers -> observers.onUpdateCharacterCard(finalChosenCard-1, parameters));
    }



    @Override
    public void selectCloud() {
        System.out.println("You should now select a cloud...");
        List<ReducedCloud> availableClouds = Arrays.stream(gm.getArrayClouds()).filter(ReducedCloud::isFull).collect(Collectors.toList());
        printList(availableClouds);
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

    @Override
    public void sendWinner(String winner) {
        System.out.println("GAME ENDED! " + winner + " won the game! Thank you for playing!");
    }

    // <------ UpdateMethods -------->

    @Override
    public void updateIslands(List<ReducedIsland> islands) {
        System.out.println("Received updateIslands:");
        for (ReducedIsland i : islands) {
            System.out.println(i);
        }
        gm.setIslandList(islands);

    }

    @Override
    public void updateDiningHall(ReducedDiningHall diningHall) {
        System.out.println("Received updateDiningHall");
        System.out.println(diningHall);
        gm.getPlayerByNickname(diningHall.getNickname()).getDashboard().setDiningHall(diningHall);
    }

    @Override
    public void updateDashboard(ReducedDashboard dashboard) {
        System.out.println("Received updateDashboard");
        System.out.println(dashboard);
        gm.getPlayerByNickname(dashboard.getNickname()).setDashboard(dashboard);
    }

    @Override
    public void updatePlayer(ReducedPlayer player) {
        System.out.println(("Received updatePlayer"));
        System.out.println(player.getNickname());
        int index = Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getPlayerByNickname(player.getNickname()));
        gm.setPlayer(index, player);
    }

    @Override
    public void updateClouds(ReducedCloud[] clouds) {
        System.out.println("Received updateClouds");
        for (ReducedCloud c : clouds) {
            System.out.println(c);
        }
        gm.setArrayClouds(clouds);
    }

    @Override
    public void updateTotalCoins(int totCoins) {
        gm.setTotalCoins(totCoins);
    }

    @Override
    public void updateMotherNature(int motherIsland) {
        System.out.println("Received updateMotherNature");
        System.out.println(motherIsland);
        gm.setMotherIsland(motherIsland);
    }

    @Override
    public void updateCurrentPlayer(ReducedPlayer currentPlayer) {
        System.out.println("Received updateCurrentPlayer");
        System.out.println(currentPlayer);
        gm.setCurrentPlayer(currentPlayer);
    }

    @Override
    public void updateGameModel(ReducedGameModel gameModel) {
        System.out.println("Received updateGameModel");
        System.out.println(gameModel);
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
            System.out.println(i + " - " + t.toString());
            i++;
        }
    }

    /**
     * Asks the player to select a house and checks the validity of the input.
     *
     * @return the house selected by the player
     */
    private House selectHouse () {
        System.out.println("Houses:");
        printList(Arrays.asList(House.values()));
        System.out.println("Select an house by entering a number between 1 and " + House.values().length + ":");
        int chosenHouse = inputInRange(1, House.values().length, "select a valid house");
        return House.values()[chosenHouse-1];
    }

    /**
     * Asks the player to select an island and checks the validity of the input.
     *
     * @return the index of the island selected by the player
     */
    private int selectIsland() {
        System.out.println("Select an island (1 - " + gm.getIslandList().size() + "):");
        printList(gm.getIslandList());
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
