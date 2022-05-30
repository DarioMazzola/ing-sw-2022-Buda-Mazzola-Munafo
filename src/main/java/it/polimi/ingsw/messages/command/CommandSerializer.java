package it.polimi.ingsw.messages.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.messages.MessageType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.messages.MessageType.*;

/**
 * Class representing a serializer for command messages, sent from the client to the server. It is a part
 * of logic that hides the network layer to the client controller. So it does not have to know how the messages are sent
 * and how they are serialized
 *
 * @author Dario Mazzola
 */
public class CommandSerializer {

    /**
     * Static method that represents a serializer for a command message. It receives a command message and serializes it
     * into the network communication protocol.
     * @param msg The command message to be serialized
     * @return The message serialized
     * @throws IllegalArgumentException If the message, that the user wants to serialize, does not exist
     */
    public static String serialize(CommandMessage msg) throws IllegalArgumentException{
        List<String> list = new ArrayList<>();

        Gson gson = new Gson();

        MessageType messageType = msg.getType();
        String gsonMessage = gson.toJson(msg);

        switch (messageType) {
            case CHOSEN_ASSISTANT_CARD:
                list.add(CHOSEN_ASSISTANT_CARD.toString());
                break;
            case CHOSEN_CHARACTER_CARD:
                list.add(CHOSEN_CHARACTER_CARD.toString());
                break;
            case CHOSEN_CLOUD:
                list.add(CHOSEN_CLOUD.toString());
                break;
            case CHOSEN_TEAM:
                list.add(CHOSEN_TEAM.toString());
                break;
            case CHOSEN_TOWER_COLOR:
                list.add(CHOSEN_TOWER_COLOR.toString());
                break;
            case NEW_GAME:
                list.add(NEW_GAME.toString());
                break;
            case NICKNAME:
                list.add(NICKNAME.toString());
                break;
            case CHOSEN_NUM_PLAYERS:
                list.add(CHOSEN_NUM_PLAYERS.toString());
                break;
            case CHOSEN_EXPERT_MODE:
                list.add(CHOSEN_EXPERT_MODE.toString());
                break;
            case CHOSEN_RESTORE_GAME:
                list.add(CHOSEN_RESTORE_GAME.toString());
                break;
            case CHOSEN_WIZARD:
                list.add(MessageType.CHOSEN_WIZARD.toString());
                break;
            case MOVE_STUDENT_TO_DINING_HALL:
                list.add(MOVE_STUDENT_TO_DINING_HALL.toString());
                break;
            case MOVE_STUDENT_TO_ISLAND:
                list.add(MOVE_STUDENT_TO_ISLAND.toString());
                break;
            case PONG:
                list.add(PONG.toString());
                break;
            case MOVE_MOTHER:
                list.add(MOVE_MOTHER.toString());
                break;
            default:
                throw new IllegalArgumentException("Message received (" + messageType +") is not a command message");
        }

        list.add(gsonMessage);

        return gson.toJson(list);
    }

    /**
     * Static method that represents a deserializer for a command message. It receives the string received by the
     * network layer and deserializes it, from the network communication protocol to CommandMessage.
     * @param messageReceived The message too be deserialized
     * @return The command message deserialized
     * @throws IllegalArgumentException If the message received is not valid or not a command message
     */
    public static CommandMessage deserialize(String messageReceived) throws IllegalArgumentException {

        Gson gson = new Gson();

        Type listOfMyClassObject = new TypeToken<ArrayList<String>>() {}.getType();

        List<String> msg = gson.fromJson(messageReceived, listOfMyClassObject);

        CommandMessage command;

        MessageType messageType = gson.fromJson(msg.get(0), MessageType.class);

        String messageContent = msg.get(1);

        switch (messageType) {
            case PONG:
                command = gson.fromJson(messageContent, Pong.class);
                break;
            case NEW_GAME:
                command = gson.fromJson(messageContent, NewGame.class);
                break;
            case NICKNAME:
                command = gson.fromJson(messageContent, Nickname.class);
                break;
            case CHOSEN_NUM_PLAYERS:
                command = gson.fromJson(messageContent, ChosenNumPlayers.class);
                break;
            case CHOSEN_EXPERT_MODE:
                command = gson.fromJson(messageContent, ChosenExpertMode.class);
                break;
            case CHOSEN_RESTORE_GAME:
                command = gson.fromJson(messageContent, ChosenRestoreGame.class);
                break;
            case CHOSEN_WIZARD:
                command = gson.fromJson(messageContent, ChosenWizard.class);
                break;
            case CHOSEN_TEAM:
                command = gson.fromJson(messageContent, ChosenTeam.class);
                break;
            case CHOSEN_TOWER_COLOR:
                command = gson.fromJson(messageContent, ChosenTowerColor.class);
                break;
            case CHOSEN_ASSISTANT_CARD:
                command = gson.fromJson(messageContent, ChosenAssistantCard.class);
                break;
            case MOVE_STUDENT_TO_DINING_HALL:
                command = gson.fromJson(messageContent, MoveStudentToDiningHall.class);
                break;
            case MOVE_STUDENT_TO_ISLAND:
                command = gson.fromJson(messageContent, MoveStudentToIsland.class);
                break;
            case MOVE_MOTHER:
                command = gson.fromJson(messageContent, MoveMother.class);
                break;
            case CHOSEN_CLOUD:
                command = gson.fromJson(messageContent, ChosenCloud.class);
                break;
            default:
                throw new IllegalArgumentException("Message received (" + messageType + ") is not a command message");
        }

        return command;
    }
}
