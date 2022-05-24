package it.polimi.ingsw.messages.answer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.messages.MessageType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.messages.MessageType.*;

/**
 * Class representing a serializer for answer messages, sent from the server to the client. It is a part
 * of logic that hides the network layer to the client controller. So it does not have to know how the messages are sent
 * and how they are deserialized
 *
 * @author Dario Mazzola
 */
public class AnswerSerializer {

    /**
     * Static method that represents a serializer for an answer message. It receives an answer message and serializes it
     * into the network communication protocol.
     * @param msg The answer message to be serialized
     * @return The message serialized
     * @throws IllegalArgumentException If the message, that the user wants to serialize, does not exist
     */
    public static String serialize(AnswerMessage msg) throws IllegalArgumentException{
        List<String> list = new ArrayList<>();

        Gson gson = new Gson();

        MessageType messageType = msg.getType();
        String gsonMessage = gson.toJson(msg);

        switch (messageType) {
            case NACK:
                list.add(NACK.toString());
                break;
            case PING:
                list.add(PING.toString());
                break;
            case SELECT_NICKNAME:
                list.add(SELECT_NICKNAME.toString());
                break;
            case SELECT_NUM_PLAYERS:
                list.add(SELECT_NUM_PLAYERS.toString());
                break;
            case SELECT_EXPERT_MODE:
                list.add(SELECT_EXPERT_MODE.toString());
                break;
            case SELECT_WIZARD:
                list.add(SELECT_WIZARD.toString());
                break;
            case SELECT_COLOR_TOWER:
                list.add(SELECT_COLOR_TOWER.toString());
                break;
            case SELECT_TEAM:
                list.add(SELECT_TEAM.toString());
                break;
            default:
                throw new IllegalArgumentException("Message is not an answer message");
        }

        list.add(gsonMessage);

        return gson.toJson(list);
    }

    /**
     * Static method that represents a deserializer for an answer message. It receives the string received by the
     * network layer and deserializes it, from the network communication protocol to AnswerMessage.
     * @param messageReceived The message too be deserialized
     * @return The answer message deserialized
     * @throws IllegalArgumentException If the message received is not valid or not an answer message
     */
    public static AnswerMessage deserialize(String messageReceived) {

        Gson gson = new Gson();

        Type listOfMyClassObject = new TypeToken<ArrayList<String>>() {}.getType();

        List<String> msg = gson.fromJson(messageReceived, listOfMyClassObject);

        AnswerMessage answer;
        MessageType messageType = gson.fromJson(msg.get(0), MessageType.class);

        String gsonMessage = msg.get(1);

        switch (messageType) {
            case NACK:
                answer = gson.fromJson(gsonMessage, Nack.class);
                break;
            case PING:
                answer = gson.fromJson(gsonMessage, Ping.class);
                break;
            case SELECT_NICKNAME:
                answer = gson.fromJson(gsonMessage, SelectNickname.class);
                break;
            case SELECT_NUM_PLAYERS:
                answer = gson.fromJson(gsonMessage, SelectNumPlayers.class);
                break;
            case SELECT_EXPERT_MODE:
                answer = gson.fromJson(gsonMessage, SelectExpertMode.class);
                break;
            case SELECT_WIZARD:
                answer = gson.fromJson(gsonMessage, SelectWizard.class);
                break;
            case SELECT_COLOR_TOWER:
                answer = gson.fromJson(gsonMessage, SelectColorTower.class);
                break;
            case SELECT_TEAM:
                answer = gson.fromJson(gsonMessage, SelectTeam.class);
                break;

            default:
                throw new IllegalArgumentException("Message is not an answer message");
        }

        return answer;
    }
}