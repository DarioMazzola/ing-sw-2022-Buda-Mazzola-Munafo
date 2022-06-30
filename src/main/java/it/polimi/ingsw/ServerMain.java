package it.polimi.ingsw;

import it.polimi.ingsw.controller.TurnController;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.SocketServer;

import java.util.Scanner;

public class ServerMain {
    
    private static final int defaultPort = 1234;

    public static void main(String[] args) {

        Server server;

        Scanner scanner = new Scanner(System.in);
        int serverPort = 0;

        System.out.println("Server port? [default: 1234]");
        try{
            String input = scanner.nextLine();
            if (input.equals(""))
                serverPort = defaultPort;
            else
                serverPort = Integer.parseInt(input);
        }catch (NumberFormatException e){
            System.out.println("Error while reading port number. Please try again!");
            ServerMain.main(null);
        }

        while(serverPort < 1024){
            System.out.println("Port number chosen is not valid. Please try again!");
            System.out.println("Server port?");
            try{
                serverPort = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Error while reading port number. Please try again!");
                ServerMain.main(null);
            }
        }

        TurnController gameController = new TurnController();
        server = new Server(gameController);

        SocketServer socketServer = new SocketServer(server, serverPort);
        Thread thread = new Thread(socketServer, "socketserver_");
        thread.start();
    }
}
