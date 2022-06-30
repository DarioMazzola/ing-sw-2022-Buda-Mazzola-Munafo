package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.gui.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Game arrival scene controller. The scene asks the player if he wants to play or quit the game.
 *
 * @author Dario Mazzola
 */
public class MenuController extends ViewObservable implements SceneInterface {

    @FXML
    private AnchorPane scenePane;

    /**
     * Handles the event fired when the player clicks on the exit button.
     *
     * @param event the event fired
     */
    public void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout!");
        alert.setContentText("Are you sure?");
        Stage stage;
        if (alert.showAndWait().orElse(null) == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Handles the event fired when the player clicks on the play button.
     *
     * @param event the event fired
     */
    public void play(ActionEvent event) {
        SceneController sceneController = new SceneController();
        sceneController.changeRootPane(observers, event, "connectionScene.fxml");
    }

}
