package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.command.CommandMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket of the server that manages the connection of all clients.
 *
 * @author Dario Mazzola
 */
public class SocketServer implements Runnable {
    private final Server server;
    private final int port;

    public SocketServer(Server server, int port) {
        this.server = server;
        this.port = port;
    }

    /**
     * Method that opens a socket to the port indicated in the constructor. The handler will then wait
     * for the connection of new clients. Sets a timer that will expire if the handler does not receive
     * messages from a certain client, causing it to disconnect
     */
    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Socket server started on port " + port + ".");
        } catch (IOException e) {
            System.err.println("Server could not start!");
            return;
        }

        while(! Thread.currentThread().isInterrupted()) {
            try {
                Socket client = serverSocket.accept();
                client.setSoTimeout(5000);

                ClientHandler clientHandler = new ClientHandler(this, client);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                System.err.println("Connection dropped");

            }
        }
    }

    /**
     * Handles the connection of a new client.
     *
     * @param  message the message received from the client
     * @param clientHandler the ClientHandler of the new client.
     */
    public void addClient(CommandMessage message, ClientHandler clientHandler) {
        server.addClient(message, clientHandler);
    }

    /**
     * Forwards a received message from the client to the Server.
     *
     * @param message the message received.
     */
    public void receiveMessage(CommandMessage message) {
       server.receiveMessage(message);
    }
}
