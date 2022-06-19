package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class SelectRestoreGameSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private ToggleGroup restoreButtons;
    @FXML
    private Button selectButton;

    @FXML
    public void initialize() {
        selectButton.setOnAction(this::onSelectButton);
    }

    public void onSelectButton(ActionEvent event) {
        RadioButton radioButton = (RadioButton) restoreButtons.getSelectedToggle();

        if(radioButton == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid expert mode selection");
            alert.setHeaderText("You must select whether to restore the game \nor not before going on");
            alert.showAndWait();
            return;
        }
        boolean restore;

        restore = !radioButton.getText().equalsIgnoreCase("no");
        notifyObserver(observer -> observer.onRestoreGame(restore));

        selectButton.setDisable(true);
    }
}
