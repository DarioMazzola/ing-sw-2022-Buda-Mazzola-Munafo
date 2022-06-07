package it.polimi.ingsw.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import it.polimi.ingsw.controller.TurnController;
import it.polimi.ingsw.model.*;

import java.io.*;

/**
 * Class representing the advanced functionality for persistence management.
 * The json format will be used to storage data.
 *
 * @author Dario Mazzola
 */
public class Persistence {

    Gson gson;

    /**
     * Class constructor.
     */
    public Persistence() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Saves the data belonging to this game point
     *
     * @param turnController the turn controller to save
     */
    public void saveData(TurnController turnController) {

        String controllerGson = gson.toJson(turnController);

        JsonArray toSave = new JsonArray();
        toSave.add(controllerGson);
        GameModel gm = turnController.getGameModel();

        if(gm.getCharacterCardDeck() != null) {
            for (CharacterCard c : gm.getCharacterCardDeck()) {
                switch (c.getType()) {
                    case MONK:
                        Monk monk = (Monk) c;
                        toSave.add(gson.toJson(monk));
                        break;
                    case FARMER:
                        Farmer farmer = (Farmer) c;
                        toSave.add(gson.toJson(farmer));
                        break;
                    case HERALD:
                        Herald herald = (Herald) c;
                        toSave.add(gson.toJson(herald));
                        break;
                    case MAGICAL_MAILMAN:
                        MagicalMailMan magicalMailMan = (MagicalMailMan) c;
                        toSave.add(gson.toJson(magicalMailMan));
                        break;
                    case HERB_GRANMA:
                        HerbGranma herbGranma = (HerbGranma) c;
                        toSave.add(gson.toJson(herbGranma));
                        break;
                    case CENTAUR:
                        Centaur centaur = (Centaur) c;
                        toSave.add(gson.toJson(centaur));
                        break;
                    case JOLLY:
                        Jolly jolly = (Jolly) c;
                        toSave.add(gson.toJson(jolly));
                        break;
                    case KNIGHT:
                        Knight knight = (Knight) c;
                        toSave.add(gson.toJson(knight));
                        break;
                    case MUSHROOM_HUNTER:
                        MushroomHunter mushroomHunter = (MushroomHunter) c;
                        toSave.add(gson.toJson(mushroomHunter));
                        break;
                    case MINSTREL:
                        Minstrel minstrel = (Minstrel) c;
                        toSave.add(gson.toJson(minstrel));
                        break;
                    case SPOILED_PRINCESS:
                        SpoiledPrincess spoiledPrincess = (SpoiledPrincess) c;
                        toSave.add(gson.toJson(spoiledPrincess));
                        break;
                    case THIEF:
                        Thief thief = (Thief) c;
                        toSave.add(gson.toJson(thief));
                        break;
                }
            }
        }

        File file = new File("savedData.json");
        FileOutputStream fileWriter;
        try {
            fileWriter = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error while creating storage file!");
            e.printStackTrace();
            return;
        }
        String savedData = gson.toJson(toSave);

        try {
            fileWriter.write(savedData.getBytes());
        } catch (IOException e) {
            System.err.println("Error while saving game files!");
            e.printStackTrace();
            return;
        }

        try {
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("Error while flushing data to the storage file!");
            e.printStackTrace();
            return;
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error while closing the FileOutputStream!");
            e.printStackTrace();
        }
    }

    /**
     * Restores data from the file stored on the server.
     *
     * @return the turn controller restored, null if there was an error while restoring file or the
     * file does not exist
     */
    public TurnController restoreData() {

        FileReader fileReader;
        StringBuilder saved = new StringBuilder();
        // Passing the path to the file as a parameter
        try {
            fileReader = new FileReader("savedData.json");
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        // Declaring loop variable
        int i;
        // Holds true till there is nothing to read
        try {
            while ((i = fileReader.read()) != -1)
                saved.append((char) i);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

        JsonArray jsonArray = gson.fromJson(saved.toString(), JsonArray.class);
        TurnController turnController = gson.fromJson(jsonArray.get(0).getAsString(), TurnController.class);

        if(turnController.getGameModel().isExpertMode()) {
            CharacterCard[] characterCards = new CharacterCard[3];
            int j;
            for (i = 0; i < 3; i++) {
                System.out.println(jsonArray.get(i).getAsString());
                j = i + 1;
                CharacterCard characterCard = gson.fromJson(jsonArray.get(j).getAsString(), CharacterCard.class);
                switch (characterCard.getType()) {
                    case MONK:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Monk.class);
                        break;
                    case FARMER:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Farmer.class);
                        break;
                    case HERALD:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Herald.class);
                        break;
                    case MAGICAL_MAILMAN:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), MagicalMailMan.class);
                        break;
                    case HERB_GRANMA:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), HerbGranma.class);
                        break;
                    case CENTAUR:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Centaur.class);
                        break;
                    case JOLLY:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Jolly.class);
                        break;
                    case KNIGHT:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Knight.class);
                        break;
                    case MUSHROOM_HUNTER:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), MushroomHunter.class);
                        break;
                    case MINSTREL:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Minstrel.class);
                        break;
                    case SPOILED_PRINCESS:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), SpoiledPrincess.class);
                        break;
                    case THIEF:
                        characterCards[i] = gson.fromJson(jsonArray.get(j).getAsString(), Thief.class);
                        break;
                }
            }

            turnController.getGameModel().setCharacterCardDeck(characterCards);
        }
        
        try {
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Error while closing the FileInputStream!");
            e.printStackTrace();
        }

        return turnController;
    }

    /**
     * Returns true if there is a match saved on the server
     */
    public boolean matchExists(){
        File f = new File("savedData.json");

        // Checking if the specified file exists or not
        return f.exists();
    }

    public void delete() {
        File myObj = new File("savedData.json");
        if(myObj.delete())
            System.out.println("File deleted");
    }
}
