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
    private static SceneInterface activeController;

    public static <T> T changeRootPane(List<ViewObserver> observerList, String fileName) {
        T controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fileName));
            Parent root = loader.load();
            controller = loader.getController(); //prende il controller associato alla scena

            for (ViewObserver o : observerList)
                ((ViewObservable) controller).addObserver(o);

            activeController = (SceneInterface) controller;
            activeScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

    public static void changeRootPane(List<ViewObserver> observerList, String fileName, SceneInterface controller) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fileName));
            loader.setController(controller);
            Parent root = loader.load();

            for (ViewObserver o : observerList)
                ((ViewObservable) controller).addObserver(o);

            activeController = controller;
            activeScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void setActiveScene(Scene scene){
        activeScene = scene;
    }

    public static SceneInterface getActiveController() {
        return activeController;
    }

    public static <T> T changeRootPane(List<ViewObserver> observerList, Event event, String fileName) {
        Scene scene = ((Node) event.getSource()).getScene();
        setActiveScene(scene);
        return changeRootPane(observerList, fileName);
    }

}
