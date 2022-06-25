package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.MessageType;
import it.polimi.ingsw.messages.answer.AnswerMessage;
import it.polimi.ingsw.messages.answer.AnswerSerializer;
import it.polimi.ingsw.messages.answer.Ping;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.messages.command.CommandSerializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class representing the handler of a client. Each instance of this class is associated with a specific client and
 * manages the reception, transmission of messages and pings and client disconnection.
 *
 * @author Dario Mazzola
 */
public class ClientHandler implements Runnable {
    private transient final Socket client;
    private transient final SocketServer socketServer;

    private transient boolean connected;
    private transient final ScheduledExecutorService pingHandler = Executors.newSingleThreadScheduledExecutor();

    private transient ObjectOutputStream output;
    private transient ObjectInputStream input;

    private transient int timer;
    private transient boolean enableTimer;
    private transient final Object lock;
    private transient String nickname;

    /**
     * Class constructor.
     *
     * @param socketServer the SocketServer that handles connection for the server.
     * @param client the socket related to the client.
     */
    public ClientHandler(SocketServer socketServer, Socket client) {
        this.socketServer = socketServer;
        this.client = client;
        this.connected = true;

        this.lock = new Object();

        try {
            this.output = new ObjectOutputStream(client.getOutputStream());
            this.input = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Method that manages the launch of the thread that deals with
     * ping and the method for handling messages.
     */
    @Override
    public void run() {
        sendPing();
        try {
            handleClientConnection();
        } catch (IOException e) {
            System.err.println("Client " + client.getInetAddress() + " connection dropped, client handler");
            if(socketServer.belongsToTheGame(this))
                disconnect();
            pingHandler.shutdownNow();
        }
    }

    /**
     * Handles the connection of a new client and keep listening to the socket for
     * new messages from the specific client related.
     *
     * @throws IOException any of the usual Input/Output related exceptions.
     */
    private void handleClientConnection() throws IOException {
        System.err.println("Client connected from " + client.getInetAddress());

        try {
            while (!Thread.currentThread().isInterrupted()) {
                String msg = (String) input.readObject();

                CommandMessage message = CommandSerializer.deserialize(msg);

                if (message != null && message.getType() != MessageType.PONG) {
                    System.out.println(message);

                    if (message.getType() == MessageType.NEW_GAME || message.getType() == MessageType.NICKNAME) {
                        socketServer.addClient(message, this);
                    }
                    else if(message.getType() == MessageType.CHOSEN_RESTORE_GAME){
                        socketServer.restoreGame(message, this);
                    }
                    else if(message.getType() == MessageType.CHAT_MESSAGE_CLIENT_SERVER){
                        socketServer.chat(message);
                    }
                    else if(message.getType() == MessageType.RELOAD_MESSAGES) {
                        synchronized (lock) {
                            enableTimer = true;
                            timer = 0;
                        }
                    }
                    else {
                        socketServer.receiveMessage(message);
                        if(nickname == null)
                            nickname = message.getNickname();
                    }
                }

            }
        } catch (ClassCastException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Invalid stream from client");
            disconnect();
        }
        catch (SocketException e) {
            System.err.println("Client disconnected");
            if(socketServer.belongsToTheGame(this))
                disconnect();
            pingHandler.shutdownNow();
        }
        client.close();
    }

    /**
     * Handles the disconnection of the client related to this instance of ClientHandler.
     * Closes the client connection and interrupts the thread associated.
     */
    public void disconnect() {

        pingHandler.shutdownNow();
        if (connected) {
            try {
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("Client disconnected");
            }
            connected = false;
            Thread.currentThread().interrupt();
        }
        socketServer.onDisconnection(this);
    }

    /**
     * Sends a message to the client via the socket. Before being sent,
     * the message must be serialized according to the chosen communication mechanic
     *
     * @param message the message to be sent.
     */
    public void sendAnswerMessage(AnswerMessage message) {
        try {

            String msg = AnswerSerializer.serialize(message);
            output.writeObject(msg);
            output.reset();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossible to sent: " + message);
            System.err.println(e.getMessage());
            disconnect();
        }
    }

    /**
     * Method that deals with sending the ping at certain intervals. The method uses a separate
     * thread to not suspend the execution of the main thread while waiting for the message to be sent
     */
    private void sendPing() {
        pingHandler.scheduleAtFixedRate(() -> {

            String message = AnswerSerializer.serialize(new Ping());

            try {
                output.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
                disconnect();
            }
            synchronized (lock) {
                if (enableTimer)
                    timer++;
                if (timer > 3) {
                    socketServer.resendAvailableActions(nickname);
                    enableTimer = false;
                    timer = 0;
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
}


