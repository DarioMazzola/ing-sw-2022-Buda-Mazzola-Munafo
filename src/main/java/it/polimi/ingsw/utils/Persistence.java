package it.polimi.ingsw.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.controller.TurnController;

import java.io.*;
import java.util.Arrays;

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
        File file = new File("savedData.json");
        FileOutputStream fileWriter;
        try {
            fileWriter = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error while creating storage file!");
            e.printStackTrace();
            return;
        }

        try {
            fileWriter.write(controllerGson.getBytes());
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

        FileInputStream fileReader;
        try {
            fileReader = new FileInputStream("savedData.json");
        } catch (FileNotFoundException e) {
            System.err.println("Error while searching the storage files!");
            e.printStackTrace();
            return null;
        }

        String controllerGson;
        try {
            controllerGson = Arrays.toString(fileReader.readAllBytes());
        } catch (IOException e) {
            System.err.println("Error while parsing the input!");
            e.printStackTrace();
            return null;
        }

        TurnController turnController = gson.fromJson(controllerGson, TurnController.class);

        try {
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Error while closing the FileInputStream!");
            e.printStackTrace();
        }

        return turnController;
    }
}
