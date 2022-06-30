package it.polimi.ingsw.view.gui.scenes;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class WinnerSceneController extends ViewObservable implements SceneInterface {
    private final String winner;
    private final String nickname;

    @FXML
    private Text WinnerText;
    @FXML
    private RadioButton YesBtn;
    @FXML
    private RadioButton NoBtn;
    @FXML
    private ToggleGroup YNBtns;
    @FXML
    private Button SelectBtn;

    public WinnerSceneController (String winner, String nickname) {
        this.winner = winner;
        this.nickname = nickname;

    }

    public void initialize() {
        WinnerText.setText(winner + " won the game! Thank you for playing!");
        Font font = new Font("Baskerville Old Face", 22);
        WinnerText.setFont(font);
        SelectBtn.setDisable(true);
        YesBtn.setOnAction(this::onButtonClick);
        NoBtn.setOnAction(this::onButtonClick);
        SelectBtn.setOnAction(this::onSelect);
    }

    private void onButtonClick (ActionEvent e) {
        SelectBtn.setDisable(false);
    }

    private void onSelect(ActionEvent e) {
        RadioButton btn = (RadioButton) YNBtns.getSelectedToggle();
        if(btn == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid new game selection");
            alert.setHeaderText("You must select whether you want to play a new game!");
            alert.showAndWait();
            return;
        }
        YesBtn.setDisable(true);
        NoBtn.setDisable(true);
        boolean newGame;
        switch (btn.getText()) {
            case "Yes":
                newGame = true;
                break;
            case "No":
                newGame = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btn.getText());
        }
        System.out.println("newGame: " + newGame);
        if (newGame)
            notifyObserver(observers -> observers.onCreateNewGame(nickname));
        else
            System.exit(0);
    }
}
