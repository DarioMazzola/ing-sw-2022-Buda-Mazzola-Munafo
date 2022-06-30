package it.polimi.ingsw.messages.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.model.Island;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_CHARACTER_CARD;

/**
 * This message is sent from the client to the server to request the character cards in use in this game.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenCharacterCard extends CommandMessage{

    private final int cardIndex;
    private final String parameters;

    /**
     * Message constructor
     *
     * @param nickname The nickname of the player sending the message
     * @param cardIndex The index of the CharacterCard chosen by the player
     */
    public ChosenCharacterCard(String nickname, int cardIndex, Map<String, Object> parameters) {
        super(CHOSEN_CHARACTER_CARD, nickname);
        this.cardIndex = cardIndex;
        Gson gson;gson = new Gson();
        this.parameters = gson.toJson(parameters);
    }

    public int getCardIndex() {
        return cardIndex;
    }

    /**
     * Method that returns the Map <String, Object> built with all the parameters necessary for
     * the functioning of the character cards starting from the card that was used to send the message.
     *
     * @return the map with the all parameters necessary to use the CharacterCard
     */
    public Map<String, Object> getMap() {
        Type listOfMyClassObject = new TypeToken<HashMap<String, String>>() {}.getType();
        Gson gson = new Gson();

        Map<String, Object> map = gson.fromJson(parameters, listOfMyClassObject);
        Map<String, Object> newMap = new HashMap<>();

        if(map != null) {
            for (String key : map.keySet()) {
                if (key.equals("method")) {
                    newMap.put(key, map.get(key));
                } else if (key.equals("wantedHouse") || key.equals("house")) {
                    newMap.put(key, gson.fromJson((String) map.get(key), House.class));
                } else if (key.equals("destinationIsland")) {
                    newMap.put(key, gson.fromJson((String) map.get(key), Double.class));
                } else if (key.equals("island")) {
                    newMap.put(key, gson.fromJson((String) map.get(key), Integer.class));
                } else if (key.equals("wantedStudents") || key.equals("returnedStudents")) {
                    Type type = new TypeToken<HashMap<House, Integer>>() {
                    }.getType();
                    newMap.put(key, gson.fromJson((String) map.get(key), type));
                } else if (key.equals("fromDashboard") || key.equals("fromDiningHall")) {
                    Type type = new TypeToken<House[]>() {
                    }.getType();
                    newMap.put(key, gson.fromJson((String) map.get(key), type));
                }
            }
        }

        return newMap;
    }
}
