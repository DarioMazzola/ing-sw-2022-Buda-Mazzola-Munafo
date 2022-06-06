package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;

import static javafx.scene.control.Alert.AlertType.*;

public class LoginSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private TextField usernameBox;
    @FXML
    private Button playButton;


    public void onPlayButton(ActionEvent event) {
        String nickname = usernameBox.getText();
        if(nickname == null || nickname.isEmpty()){
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid nickname");
            alert.setHeaderText("Empty nickname! Please insert a nickname\nthat is at least 1 character long");
            alert.showAndWait();
            return;
        }
        notifyObserver(observers -> observers.onCreateNewGame(nickname));
    }
}