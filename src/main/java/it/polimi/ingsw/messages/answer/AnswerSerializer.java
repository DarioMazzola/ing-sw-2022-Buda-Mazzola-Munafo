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
     *
     * @param msg The answer message to be serialized
     * @return The message serialized
     * @throws IllegalArgumentException If the message, that the user wants to serialize, does not exist
     */
    public static String serialize(AnswerMessage msg) throws IllegalArgumentException {
        List<String> list = new ArrayList<>();

        Gson gson = new Gson();

        MessageType messageType = msg.getType();
        String gsonMessage = gson.toJson(msg);

        switch (messageType) {
            case ACTION_PHASE:
                list.add(ACTION_PHASE.toString());
                break;
            case END_GAME_DISCONNECTION:
                list.add(END_GAME_DISCONNECTION.toString());
                break;
            case GAME_FULL:
                list.add(GAME_FULL.toString());
                break;
            case GO_TO_WAITING_ROOM:
                list.add(GO_TO_WAITING_ROOM.toString());
                break;
            case GO_TO_LOBBY:
                list.add(GO_TO_LOBBY.toString());
                break;
            case NACK:
                list.add(NACK.toString());
                break;
            case PING:
                list.add(PING.toString());
                break;
            case SELECT_ASSISTANT_CARD:
                list.add(SELECT_ASSISTANT_CARD.toString());
                break;
            case SELECT_CHARACTER_CARD:
                list.add(SELECT_CHARACTER_CARD.toString());
                break;
            case SELECT_CLOUD:
                list.add(SELECT_CLOUD.toString());
                break;
            case SELECT_COLOR_TOWER:
                list.add(SELECT_COLOR_TOWER.toString());
                break;
            case SELECT_EXPERT_MODE:
                list.add(SELECT_EXPERT_MODE.toString());
                break;
            case SELECT_NICKNAME:
                list.add(SELECT_NICKNAME.toString());
                break;
            case SELECT_NUM_PLAYERS:
                list.add(SELECT_NUM_PLAYERS.toString());
                break;
            case SELECT_TEAM:
                list.add(SELECT_TEAM.toString());
                break;
            case SELECT_WIZARD:
                list.add(SELECT_WIZARD.toString());
                break;
            case SEND_WINNER:
                list.add(SEND_WINNER.toString());
                break;
            case SELECT_RESTORE_GAME:
                list.add(SELECT_RESTORE_GAME.toString());
                break;
            case UPDATE_CHARACTER_CARD:
                list.add(UPDATE_CHARACTER_CARD.toString());
                break;
            case UPDATE_CLOUD:
                list.add(UPDATE_CLOUD.toString());
                break;
            case UPDATE_DASHBOARD:
                list.add(UPDATE_DASHBOARD.toString());
                break;
            case UPDATE_DINING_HALL:
                list.add(UPDATE_DINING_HALL.toString());
                break;
            case UPDATE_GAME_MODEL:
                list.add(UPDATE_GAME_MODEL.toString());
                break;
            case UPDATE_ISLAND:
                list.add(UPDATE_ISLAND.toString());
                break;
            case UPDATE_PLAYER:
                list.add(UPDATE_PLAYER.toString());
                break;
            case UPDATE_CURRENT_PLAYER:
                list.add(UPDATE_CURRENT_PLAYER.toString());
                break;
            case UPDATE_MOTHER_ISLAND:
                list.add(UPDATE_MOTHER_ISLAND.toString());
                break;
            case REMEMBER_NICKNAME:
                list.add(REMEMBER_NICKNAME.toString());
                break;
            case SELECT_CHAT:
                list.add(SELECT_CHAT.toString());
                break;
            case CHAT_MESSAGE_SERVER_CLIENT:
                list.add(CHAT_MESSAGE_SERVER_CLIENT.toString());
                break;
            case WAIT_FOR_OTHERS_MOVES:
                list.add(WAIT_FOR_OTHERS_MOVES.toString());
                break;
            default:
                throw new IllegalArgumentException("Message (" + messageType + ") is not an answer message");
        }

        list.add(gsonMessage);

        return gson.toJson(list);
    }

    /**
     * Static method that represents a deserializer for an answer message. It receives the string received by the
     * network layer and deserializes it, from the network communication protocol to AnswerMessage.
     *
     * @param messageReceived The message too be deserialized
     * @return The answer message deserialized
     * @throws IllegalArgumentException If the message received is not valid or not an answer message
     */
    public static AnswerMessage deserialize(String messageReceived) {

        Gson gson = new Gson();

        Type listOfMyClassObject = new TypeToken<ArrayList<String>>() {
        }.getType();

        List<String> msg = gson.fromJson(messageReceived, listOfMyClassObject);

        AnswerMessage answer;
        MessageType messageType = gson.fromJson(msg.get(0), MessageType.class);

        String gsonMessage = msg.get(1);

        switch (messageType) {
            case ACTION_PHASE:
                answer = gson.fromJson(gsonMessage, ActionPhase.class);
                break;
            case END_GAME_DISCONNECTION:
                answer = gson.fromJson(gsonMessage, EndGameDisconnection.class);
                break;
            case GAME_FULL:
                answer = gson.fromJson(gsonMessage, GameFull.class);
                break;
            case GO_TO_WAITING_ROOM:
                answer = gson.fromJson(gsonMessage, GoToWaitingRoom.class);
                break;
            case GO_TO_LOBBY:
                answer = gson.fromJson(gsonMessage, GoToLobby.class);
                break;
            case NACK:
                answer = gson.fromJson(gsonMessage, Nack.class);
                break;
            case PING:
                answer = gson.fromJson(gsonMessage, Ping.class);
                break;
            case SELECT_ASSISTANT_CARD:
                answer = gson.fromJson(gsonMessage, SelectAssistantCard.class);
                break;
            case SELECT_CLOUD:
                answer = gson.fromJson(gsonMessage, SelectCloud.class);
                break;
            case SELECT_COLOR_TOWER:
                answer = gson.fromJson(gsonMessage, SelectColorTower.class);
                break;
            case SELECT_EXPERT_MODE:
                answer = gson.fromJson(gsonMessage, SelectExpertMode.class);
                break;
            case SELECT_NICKNAME:
                answer = gson.fromJson(gsonMessage, SelectNickname.class);
                break;
            case SELECT_NUM_PLAYERS:
                answer = gson.fromJson(gsonMessage, SelectNumPlayers.class);
                break;
            case SELECT_RESTORE_GAME:
                answer = gson.fromJson(gsonMessage, SelectRestoreGame.class);
                break;
            case SELECT_TEAM:
                answer = gson.fromJson(gsonMessage, SelectTeam.class);
                break;
            case SELECT_WIZARD:
                answer = gson.fromJson(gsonMessage, SelectWizard.class);
                break;
            case SEND_WINNER:
                answer = gson.fromJson(gsonMessage, SendWinner.class);
                break;
            case UPDATE_CHARACTER_CARD:
                answer = gson.fromJson(gsonMessage, UpdateCharacterCard.class);
                break;
            case UPDATE_CLOUD:
                answer = gson.fromJson(gsonMessage, UpdateCloud.class);
                break;
            case UPDATE_DASHBOARD:
                answer = gson.fromJson(gsonMessage, UpdateDashboard.class);
                break;
            case UPDATE_DINING_HALL:
                answer = gson.fromJson(gsonMessage, UpdateDiningHall.class);
                break;
            case UPDATE_GAME_MODEL:
                answer = gson.fromJson(gsonMessage, UpdateGameModel.class);
                break;
            case UPDATE_ISLAND:
                answer = gson.fromJson(gsonMessage, UpdateIsland.class);
                break;
            case UPDATE_PLAYER:
                answer = gson.fromJson(gsonMessage, UpdatePlayer.class);
                break;
            case UPDATE_CURRENT_PLAYER:
                answer = gson.fromJson(gsonMessage, UpdateCurrentPlayer.class);
                break;
            case UPDATE_MOTHER_ISLAND:
                answer = gson.fromJson(gsonMessage, UpdateMotherIsland.class);
                break;
            case REMEMBER_NICKNAME:
                answer = gson.fromJson(gsonMessage, RememberNickname.class);
                break;
            case SELECT_CHAT:
                answer = gson.fromJson(gsonMessage, SelectChat.class);
                break;
            case CHAT_MESSAGE_SERVER_CLIENT:
                answer = gson.fromJson(gsonMessage, ChatMessageServerClient.class);
                break;
            case WAIT_FOR_OTHERS_MOVES:
                answer = gson.fromJson(gsonMessage, WaitForOthersMoves.class);
                break;
            default:
                throw new IllegalArgumentException("Message (" + messageType + ") is not an answer message");
        }

        return answer;
    }
}
