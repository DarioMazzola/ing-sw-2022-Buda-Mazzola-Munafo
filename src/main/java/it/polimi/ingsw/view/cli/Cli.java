package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.ClientMain;
import it.polimi.ingsw.client.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.view.UI;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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

        boolean isValidInput;
        int numPlayers = 0;
        System.out.println("How many players want to play? (enter a number between 2 and 4): ");
        do {
            try {
                isValidInput = true;
                numPlayers = Integer.parseInt(scanner.nextLine());
                if (numPlayers < 2 || numPlayers > 4)
                    isValidInput = false;
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
            if (!isValidInput)
                System.out.println("The given number of players is not valid. Please, enter a number between 2 and 4. \nNumber of players: ");
        } while (!isValidInput);
        int finalNumPlayers = numPlayers;
        notifyObserver(observers -> observers.onUpdateNumPlayers(finalNumPlayers));
    }

    @Override
    public void selectExpertMode() {
        clearCli();
        boolean isValidInput;
        String input;
        System.out.println("Do you want to play in expert mode? (Y/N)");
        do {
            isValidInput = true;
            input = scanner.nextLine();
            if (!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N"))) {
                isValidInput = false;
                System.out.println("The given input is not valid.\nPlease, enter 'Y' if you want to play in expert mode, 'N' otherwise: ");
            }
        } while (!isValidInput);
        boolean expertMode;
        expertMode = input.equalsIgnoreCase("Y");
        notifyObserver(observers -> observers.onUpdateExpertMode(expertMode));
    }

    public void selectWizard(List<Wizard> availableWizards) {
        clearCli();
        System.out.println("Now select your wizard for this game!\nHere's a list of available wizards: ");
        int i = 1;
        for (Wizard w : availableWizards) {
            System.out.println(i + " - " + w.toString());
            i++;
        }
        boolean isValidInput;
        int wizard = 0;
        do {
            try {
                isValidInput = true;
                wizard = Integer.parseInt(scanner.nextLine());
                if (wizard < 1 || wizard > i)
                    isValidInput = false;
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
            if (!isValidInput)
                System.out.println("The given input is not valid.\nPlease enter a number between 1 and " + i + " to select a valid wizard:");
        } while (!isValidInput);
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

        System.out.println("Now select the color of the towers in your dashboard!\nHere's a list of available colors: ");
        int i = 1;
        for (Color c : colorTowersAvailable) {
            System.out.println(i + " - " + c.toString());
            i++;
        }
        boolean isValidInput;
        int color = 0;
        do {
            try {
                isValidInput = true;
                color = Integer.parseInt(scanner.nextLine());
                if (color < 1 || color > i)
                    isValidInput = false;
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
            if (!isValidInput)
                System.out.println("The given input is not valid.\nPlease enter a number between 1 and " + i + " to select a valid color:");
        } while (!isValidInput);
        Color selectedColor = colorTowersAvailable.get(color - 1);
        notifyObserver(observers -> observers.onUpdateTowerColor(selectedColor));
    }

    @Override
    public void goToWaitingRoom() {
        System.out.println("You are now in the lobby! Waiting for other players ...");
    }

    @Override
    public void selectAssistantCard(List<Card> availableCards) {

        List<Card> playersDeck = gm.getCurrentPlayer().getDeck();

        Set<Card> result = availableCards.stream().distinct().filter(gm.getPlayerByNickname(gm.getCurrentPlayer().getNickname()).
                getDeck()::contains).collect(Collectors.toSet());

        System.out.println("It's your turn, chose an assistant card for this round!");
        System.out.println("Here's a list of the cards in your deck, the cards marked with an 'X' have already been chosen by another player. If you have other cards in your deck you can't use them in this round.");
        int i = 1;
        for (Card c : playersDeck) {
            System.out.println(i + ") " + c + (!result.contains(c) ? " X" : ""));
            i++;
        }
        boolean isValidInput;
        int chosenCard = 0;
        System.out.println("Chose a card by entering a number between 1 and " + i);
        do {
            isValidInput = true;
            try {
                chosenCard = Integer.parseInt(scanner.nextLine());
                if (!(chosenCard >= 1 && chosenCard <= i)) {
                    isValidInput = false;
                    System.out.println("Please, enter a number between 1 and " + i + " :");
                } else {
                    if (!result.contains(playersDeck.get(chosenCard)) && result.size() > 0) {
                        isValidInput = false;
                        System.out.println("You can't chose this card, it has already been chosen by another player!\nChose another card: ");
                    }
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Invalid input, please enter a number:");
            }
        } while (!isValidInput);
        int finalChosenCard = chosenCard;
        notifyObserver(observers -> observers.onUpdateAssistantCard(playersDeck.get(finalChosenCard)));
    }

    @Override
    public void actionPhase(List<String> availableActions) {
        System.out.println("Here's a list of all the available actions:");
        int i = 1;
        for (String s : availableActions) {
            System.out.println(i + ") " + s);
            i++;
        }
        boolean isValidInput;
        int chosenAction = 0;
        do {
            isValidInput = true;
            try {
                chosenAction = Integer.parseInt(scanner.nextLine());
                if (!(chosenAction >= 1 && chosenAction <= i)) {
                    isValidInput = false;
                    System.out.println("Please, enter a number between 1 and " + i + " :");
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Invalid input, please enter a number:");
            }
        } while (!isValidInput);
        switch (availableActions.get(chosenAction - 1)) {
            case "See the details of an Island":
                focusOnIsland();
                break;
            case "See the details of a Player's dashboard":
                focusOnDashboard();
                break;
            case "See the current stat of clouds":
                focusOnClouds();
                break;
            case "move":
                moveStudents();
                break;
            case "move Mother Nature":
                moveMotherNature();
                break;
            case "Use character card":
                useCharacterCard();
                break;
        }
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
        int i = 1;
        for (ReducedCloud c : gm.getArrayClouds()) {
            System.out.println( i + " )" + c.toString());
            i++;
        }
    }

    /**
     * Manages the selection of students to move from the player's dashboard to his/hers dining hall or an island.
     */
    private void moveStudents() {
        for (int i = 0; i < 3; i++) {
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

        // receiving and checking input
        System.out.println("Enter the house of the student you want to move:");
        boolean isValidInput;
        House chosenHouse = null;
        do {
            isValidInput = true;
            String input = scanner.nextLine();
            for (House h : House.values()) {
                if (input.equalsIgnoreCase(h.toString())) {
                    chosenHouse = h;
                    break;
                }
            }
            if (chosenHouse == null) {
                isValidInput = false;
                System.out.println("Invalid input! Please enter an existing house:");
            } else if (gm.getCurrentPlayer().getDashboard().getHouseStudents(chosenHouse) <= 0) {
                isValidInput = false;
                System.out.println("You don't have any students of the " + chosenHouse + " house, please chose another house:");
            }
            else if (gm.getCurrentPlayer().getDashboard().getDiningHall().getHouseStudents(chosenHouse) >= 10) {
                isValidInput = false;
                System.out.println("Your dining hall for the house " + chosenHouse + " is full, please chose another house: ");
            }
        } while (!isValidInput);
        House finalChosenHouse = chosenHouse;
        notifyObserver(observers -> observers.onMoveStudentsToDiningHall(finalChosenHouse));
    }

    /**
     * Manages the selection of students to move from the player's dashboard to an island.
     */
    private void moveStudentsToIsland() {
        System.out.println("Enter the house of the student you want to move:");
        boolean isValidInput;
        House chosenHouse = null;
        do {
            isValidInput = true;
            String input = scanner.nextLine();
            for (House h : House.values()) {
                if (input.equalsIgnoreCase(h.toString())) {
                    chosenHouse = h;
                    break;
                }
            }
            if (chosenHouse == null) {
                isValidInput = false;
                System.out.println("Invalid input! Please enter an existing house:");
            } else if (gm.getCurrentPlayer().getDashboard().getHouseStudents(chosenHouse) <= 0) {
                isValidInput = false;
                System.out.println("You don't have any students of the " + chosenHouse + " house, please chose another house:");
            }
        } while (!isValidInput);

        System.out.println("Enter the island where you want to move the student (1 - " + gm.getIslandList().size() + " :");
        int chosenIsland = 0;
        do {
            isValidInput = true;
            try {
                chosenIsland = Integer.parseInt(scanner.nextLine());
                if (!(chosenIsland >= 1 && chosenIsland <= gm.getIslandList().size())) {
                    isValidInput = false;
                    System.out.println("The island you chose does not exist! Please enter a number between 1 and " + gm.getIslandList().size());
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Invalid input! Please enter a number:");
            }
        } while (!isValidInput);
        House finalChosenHouse = chosenHouse;
        int finalChosenIsland = chosenIsland;
        int finalChosenIsland1 = chosenIsland;
        notifyObserver(observers -> observers.onMoveStudentsToIsland(finalChosenHouse, finalChosenIsland1 - 1));
    }

    /**
     * Asks the player of how many steps he/she wants to move mother nature.
     */
    private void moveMotherNature() {
        System.out.println("How many steps you want to move mother nature of? (0 - " + gm.getCurrentPlayer().getMaxMoves() + " )");
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
        // to be implemented
    }

    @Override
    public void selectCloud() {
        System.out.println("You should now select a cloud...");
        focusOnClouds();
        boolean isValidInput;
        int chosenCloud = 0;
        do {
            isValidInput = true;
            try {
                chosenCloud = Integer.parseInt(scanner.nextLine());
                if (!(chosenCloud >= 1 && chosenCloud <= gm.getArrayClouds().length)) {
                    isValidInput = false;
                    System.out.println("Please, enter a number between 1 and " + gm.getArrayClouds().length + " :");
                } else if (!gm.getArrayClouds()[chosenCloud-1].isFull()) {
                    isValidInput = false;
                    System.out.println("The cloud you have selected has already been chosen by another player! Please select another one:");
                }
            } catch (NumberFormatException e) {
                isValidInput = false;
                System.out.println("Invalid input, please enter a number:");
            }
        } while (!isValidInput);
        int finalChosenCloud = chosenCloud;
        notifyObserver(observers -> observers.onUpdateCloud(finalChosenCloud));

    }

    @Override
    public void sendWinner(String winner) {
        System.out.println("GAME ENDED! " + winner + " won the game! Thank you for playing!");
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