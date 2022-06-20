package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class SelectChatController extends ViewObservable implements SceneInterface {
    @FXML
    private RadioButton YesBtn;

    @FXML
    private RadioButton NoBtn;

    @FXML
    ToggleGroup YNBtns;

    @FXML
    Button SelectBtn;

    public void initialize() {
        SelectBtn.setDisable(true);
    }

    public void onBtnClick (ActionEvent e) {
        SelectBtn.setDisable(false);
    }

    public void onSelect (ActionEvent e) {
        RadioButton btn = (RadioButton) YNBtns.getSelectedToggle();
        if(btn == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid chat selection");
            alert.setHeaderText("You must select whether you want a chat \nfor this game before going on");
            alert.showAndWait();
            return;
        }
        YesBtn.setDisable(true);
        NoBtn.setDisable(true);
        boolean chat;
        switch (btn.getText()) {
            case "YES":
                chat = true;
                break;
            case "NO":
                chat = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btn.getText());
        }
        System.out.println("chat: " + chat);
        notifyObserver(observers -> observers.onUpdateChat(chat));
    }
}
