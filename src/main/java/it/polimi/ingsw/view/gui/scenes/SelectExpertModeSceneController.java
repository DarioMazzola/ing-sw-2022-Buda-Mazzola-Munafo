package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class SelectExpertModeSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private RadioButton yesButton;
    @FXML
    private RadioButton noButton;
    @FXML
    private ToggleGroup expertButton ;
    @FXML
    private Button continueButton;

    public void onSelectExpertMode(ActionEvent event){
        RadioButton selectedRadioButton = (RadioButton) expertButton.getSelectedToggle();
        if(selectedRadioButton == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid expert mode selection");
            alert.setHeaderText("You must select whether to play in expert mode \nfor this game before going on");
            alert.showAndWait();
            return;
        }
        continueButton.setDisable(true);
        yesButton.setDisable(true);
        noButton.setDisable(true);

        String selected = selectedRadioButton.getText();
        boolean expertMode = false;

        switch (selected) {
            case "Yes":
                expertMode = true;
                break;
            case "No":
                break;
        }

        boolean finalExpertMode = expertMode;
        Platform.runLater(() -> notifyObserver(obs -> obs.onUpdateExpertMode(finalExpertMode)));
    }

}
