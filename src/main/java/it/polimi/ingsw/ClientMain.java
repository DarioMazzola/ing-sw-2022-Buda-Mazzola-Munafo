package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientController;
import it.polimi.ingsw.view.cli.Cli;
import it.polimi.ingsw.view.gui.MainGui;
import javafx.application.Application;

import java.util.Scanner;

/**
 * Represents the finishing class for a player. The Client is asked if he wants to play with the CLI or the GUI.
 *
 * @author Alessio Buda, Dario Mazzola & Gabriele Munafò
 */
public class ClientMain {

    public static void main(String[] args) {

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

        System.out.println("Welcome to the Eriantys board game");
        System.out.println("\n\n < Do you want to play with CLI or GUI? >");
        System.out.println(" 1 - CLI");
        System.out.println(" 2 - GUI");

        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception ignored) {}

        } while (choice != 1 && choice != 2);

        if (choice == 1) {
            Cli cli = new Cli();
            ClientController clientcontroller = new ClientController(cli);
            cli.addObserver(clientcontroller);
            cli.start();
        }
        else {
            System.setProperty("prism.allowhidpi", "false");
            Application.launch(MainGui.class);
        }
    }
}

