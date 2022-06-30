package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Scene that asks the player to choose the number of player for this game
 *
 * @author Dario Mazzola.
 */
public class SelectNumPlayersController extends ViewObservable implements SceneInterface{

    @FXML
    private Button selectNumPlayers;

    @FXML
    private RadioButton players2;
    @FXML
    private RadioButton players3;
    @FXML
    private RadioButton players4;
    @FXML
    private ToggleGroup playerButton;

    /**
     * Handles the event fired when the player click on the select button.
     *
     * @param event the event fired
     */
    public void selectNumPlayers(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) playerButton.getSelectedToggle();
        if(selectedRadioButton == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid number of player");
            alert.setHeaderText("You must select the number of players \nfor this game before going on");
            alert.showAndWait();
            return;
        }
        selectNumPlayers.setDisable(true);
        players2.setDisable(true);
        players3.setDisable(true);
        players4.setDisable(true);

        String selected = selectedRadioButton.getText();
        int playersNumber = 0;

        switch (selected) {
            case "2 players":
                playersNumber = 2;
                break;
            case "3 players":
                playersNumber = 3;
                break;
            case "4 players":
                playersNumber = 4;
                break;
        }

        int finalPlayersNumber = playersNumber;
        Platform.runLater(() -> notifyObserver(obs -> obs.onUpdateNumPlayers(finalPlayersNumber)));
    }

}
