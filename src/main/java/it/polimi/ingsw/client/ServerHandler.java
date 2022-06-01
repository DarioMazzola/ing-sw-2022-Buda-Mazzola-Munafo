package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.answer.AnswerMessage;
import it.polimi.ingsw.messages.answer.AnswerSerializer;
import it.polimi.ingsw.messages.answer.EndGameDisconnection;
import it.polimi.ingsw.messages.command.CommandMessage;
import it.polimi.ingsw.messages.command.CommandSerializer;
import it.polimi.ingsw.messages.command.Pong;
import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

import static it.polimi.ingsw.messages.MessageType.*;

/**
 * Class representing the network handler for the client. Instances of this class are observed by the Client Controller.
 *
 * @author Dario Mazzola
 */
public class ServerHandler extends Observable {

    private final Socket socket;

    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final ExecutorService readTask;
    private final ScheduledExecutorService pingHandler;


    /**
     * Class constructor.
     *
     * @param address the ip address chosen by the client
     * @param port the port number chosen by the client
     * @throws IOException if there is an exception while reading from output and input buffer
     */
    public ServerHandler(String address, int port) throws IOException {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(address, port));

        //sets a timeout for the socket on client
        socket.setSoTimeout(10000);

        //takes the input and output stream from the socket
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());

        //creates 2 task to handle ping and the message reading phase
        this.readTask = Executors.newSingleThreadExecutor();
        this.pingHandler = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Reads a message from the ObjectOutput stream related to the socket connection with the server
     * and notifies the message received to the ServerHandler observer.
     */
    public void readMessage() {
        readTask.execute(() -> {

            while (!readTask.isShutdown()) {
                AnswerMessage messageReceived;
                try {

                    messageReceived = AnswerSerializer.deserialize((String) input.readObject());

                    //notifies all the observer of the ServerHandler
                    if (messageReceived != null && ! messageReceived.getType().equals(PING)) {
                            notifyObserver(messageReceived);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    disconnect();
                    readTask.shutdownNow();
                }

            }
        });

    }

    /**
     * Sends a message to the server using the socket.
     *
     * @param message the message to send.
     */
    public void sendMessage(CommandMessage message) {
        try {
            String msg = CommandSerializer.serialize(message);
            output.writeObject(msg);
            output.reset();
        } catch (IOException e) {
            disconnect();
            e.printStackTrace();
        }
    }

    /**
     * Disconnect the socket from the server.
     */
    public void disconnect() {
        try {
            if (!socket.isClosed()) {
                readTask.shutdownNow();
                pingHandler.shutdownNow();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a pong message to the server to keep alive the connection with the server.
     */
    public void sendPong() {

        pingHandler.scheduleAtFixedRate(() -> {
            CommandMessage message = new Pong(null);
            String msg = CommandSerializer.serialize(message);

            try {
                output.writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
                disconnect();
            }
        }, 0, 3, TimeUnit.SECONDS);

    }

}
