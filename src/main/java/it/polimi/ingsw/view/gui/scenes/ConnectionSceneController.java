package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.gui.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ConnectionSceneController extends ViewObservable implements SceneInterface {

    public Button login;
    @FXML
    private TextField serverIpBox;
    @FXML
    private TextField serverPortBox;

    public void initialize() {
        serverIpBox.setText("127.0.0.1");
        serverPortBox.setText("1234");
    }

    public void login(ActionEvent event) {

        SceneController sceneController = new SceneController();

        String ip = serverIpBox.getText();
        int port = Integer.parseInt(serverPortBox.getText());
        notifyObserver(observers -> observers.onInit(ip, port));
        sceneController.changeRootPane(observers, event, "loginScene.fxml");
    }
}
