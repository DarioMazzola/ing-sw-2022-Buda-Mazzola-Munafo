package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.messages.TypeOfError;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.observer.ViewObserver;
import it.polimi.ingsw.view.gui.scenes.SceneInterface;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Class representing the general controller for GUI scenes.
 *
 * @author Dario Mazzola
 */
public class SceneController extends ViewObservable {

    private static Scene activeScene;
    private static SceneInterface activeController;
    private static Stage waitStage;

    /**
     * Sets the scene as active starting from the filename passed as a parameter. The observers
     * passed as a parameter are added controller associated with the scene.
     * Controller is created as a generic type <T> so that it can be used as a ViewObservable and SceneInterface
     *
     * @param observerList the list of observer to add to the controller
     * @param fileName     the name of the file that points to the scene to load
     * @param <T>          the generic type of the controller
     * @return the controller associated to the scene.
     */
    public <T> T changeRootPane(List<ViewObserver> observerList, String fileName) {
        T controller = null;

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fileName));
            Parent root = loader.load();
            controller = loader.getController();

            for (ViewObserver o : observerList)
                ((ViewObservable) controller).addObserver(o);

            activeController = (SceneInterface) controller;
            activeScene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

    /**
     * Sets the scene as active starting from the filename passed as a parameter. The observers
     * passed as a parameter are added controller associated with the scene.
     * Controller passed as a parameter is set dynamically into the scene.
     *
     * @param observerList the list of observer to add to the controller
     * @param fileName     the name of the file that points to the scene to load
     * @param controller   the controller to set for this scene.
     */
    public void changeRootPane(List<ViewObserver> observerList, String fileName, SceneInterface controller) {

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

    private void setActiveScene(Scene scene) {
        activeScene = scene;
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public SceneInterface getActiveController() {
        return activeController;
    }

    /**
     * Sets the scene as active starting from the filename passed as a parameter. The observers
     * passed as a parameter are added controller associated with the scene.
     * Controller is created as a generic type <T> so that it can be used as a ViewObservable and SceneInterface
     *
     * @param observerList the list of observer to add to the controller
     * @param event        the event fired
     * @param fileName     the name of the file that points to the scene to load
     * @param <T>          the generic type of the controller
     * @return the controller associated to the scene.
     */
    public <T> T changeRootPane(List<ViewObserver> observerList, Event event, String fileName) {
        Scene scene = ((Node) event.getSource()).getScene();
        setActiveScene(scene);
        return changeRootPane(observerList, fileName);
    }

    /**
     * Show as Pop-up the scene pointed to by the filename provided as a parameter.
     *
     * @param fileName   the name of the file that points to the scene to load
     * @param controller the controller to set to the scene
     */
    public void displayPopUp(String fileName, SceneInterface controller) {

        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fileName));
            loader.setController(controller);
            Parent root = loader.load();

            activeScene.getRoot().setEffect(new GaussianBlur());

            waitStage = new Stage(StageStyle.TRANSPARENT);
            waitStage.initOwner(activeScene.getWindow());
            waitStage.initModality(Modality.APPLICATION_MODAL);
            waitStage.setScene(new Scene(root, javafx.scene.paint.Color.TRANSPARENT));

            waitStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show as Pop-up the scene pointed to by the filename provided as a parameter. The observers
     * passed as a parameter are added controller associated with the scene.
     * Controller is created as a generic type <T> so that it can be used as a ViewObservable and SceneInterface
     *
     * @param observerList the list of observer to add to the controller
     * @param fileName     the name of the file that points to the scene to load
     * @param <T>          the generic type of the controller
     * @return the controller associated to the scene.
     */
    public <T> T displayPopUp(List<ViewObserver> observerList, String fileName) {

        T controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fileName));
            Parent root = loader.load();

            controller = loader.getController();

            for (ViewObserver o : observerList)
                ((ViewObservable) controller).addObserver(o);

            activeController = (SceneInterface) controller;

            activeScene.getRoot().setEffect(new GaussianBlur());

            waitStage = new Stage(StageStyle.TRANSPARENT);
            waitStage.initOwner(activeScene.getWindow());
            waitStage.initModality(Modality.APPLICATION_MODAL);
            waitStage.setScene(new Scene(root, javafx.scene.paint.Color.TRANSPARENT));

            waitStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

    /**
     * Show as Pop-up the scene pointed to by the filename provided as a parameter. The observers
     * passed as a parameter are added controller associated with the scene.
     *
     * @param observerList the list of observer to add to the controller
     * @param fileName     the name of the file that points to the scene to load
     * @param controller   the controller associated to the scene
     */
    public void displayPopUp(List<ViewObserver> observerList, String fileName, SceneInterface controller) {
        for (ViewObserver o : observerList) {
            ((ViewObservable) controller).addObserver(o);
        }
        displayPopUp(fileName, controller);
    }

    /**
     * Hides the Pop-up and removes all effects placed on the main stage
     */
    public void hidePopUp() {

        if (activeScene != null && waitStage != null) {
            activeScene.getRoot().setEffect(null);
            waitStage.hide();
        }
    }

    /**
     * Shows the client the error message passed as a parameter. Adds the observer passed as a parameter
     * in case after the error message it is necessary to handle a disconnection.
     *
     * @param errorMessage the error message to show
     * @param observerList the list of observer to add to the controller
     */
    public void displayError(String errorMessage, List<ViewObserver> observerList) {

        activeScene.getRoot().setEffect(new GaussianBlur());

        Alert alert = new Alert(ERROR, "", ButtonType.OK);
        alert.setHeaderText(errorMessage);
        alert.initOwner(activeScene.getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();

        activeScene.getRoot().setEffect(null);

        if (errorMessage.endsWith(TypeOfError.DISCONNECTED.toString()) || errorMessage.endsWith(TypeOfError.GAME_FULL.toString())
                || errorMessage.endsWith(TypeOfError.GAME_ALREADY_STARTED.toString()) || errorMessage.endsWith(TypeOfError.SERVER_UNREACHABLE.toString())) {
            for (ViewObserver observer : observerList)
                addObserver(observer);

            notifyObserver(ViewObserver::onDisconnection);
            System.out.println("disconnected");


            for (ViewObserver observer : observerList)
                removeObserver(observer);

        }
    }
}
