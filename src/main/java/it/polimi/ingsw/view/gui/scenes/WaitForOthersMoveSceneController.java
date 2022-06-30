package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Scene representing the waiting room for the player when another player is choosing the color of the towers,
 * the assistant card or the wizard. This scene will be shown as a PopUp alerting the player to wait.
 *
 * @author Dario Mazzola
 */
public class WaitForOthersMoveSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private Text message;
    @FXML
    private StackPane stackPane;

    private Stage waitStage;
    private final String move;

    /**
     * Class constructor.
     *
     * @param move the choice that the player is waiting for.
     */
    public WaitForOthersMoveSceneController(String move) {
        this.move = move;
    }

    @FXML
    public void initialize() {

        message.setText("Another player is choosing the " + move + ". Wait your turn");
        Font font = new Font("Baskerville Old Face", 22);
        message.setFont(font);
    }
}
