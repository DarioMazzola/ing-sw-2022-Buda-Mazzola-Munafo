package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.view.gui.scenes.SceneInterface;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;

public class SceneController extends ViewObservable {

    private static Scene activeScene;

    public static <T> T changeRootPane(List<ViewObserver> observerList, String fileName) {
        T controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fileName));
            Parent root = loader.load();
            controller = loader.getController(); //prende il controller associato alla scena

            for(ViewObserver o : observerList)
                ((ViewObservable) controller).addObserver(o);

            SceneInterface activeController = (SceneInterface) controller;
            activeScene.setRoot(root);
        } catch (IOException e) {
            System.err.println("Error");
        }
        return controller;
    }

    private static void setActiveScene(Scene scene){
        activeScene = scene;
    }

    public static <T> T changeRootPane(List<ViewObserver> observerList, Event event, String fileName) {
        Scene scene = ((Node) event.getSource()).getScene();
        setActiveScene(scene);
        return changeRootPane(observerList, fileName);
    }

}
