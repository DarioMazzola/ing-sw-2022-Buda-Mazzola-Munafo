package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Scene that asks the player to choose the team for this game.
 *
 * @author Alessio Buda
 */
public class SelectTeamController extends ViewObservable implements SceneInterface{
    @FXML
    private Label LeaderTeam1;

    @FXML
    private Label LeaderTeam2;

    @FXML
    private Label MemberTeam1;

    @FXML
    private Label MemberTeam2;

    @FXML
    private RadioButton Team1Btn;

    @FXML
    private RadioButton Team2Btn;

    @FXML
    private CheckBox TeamLeader;

    @FXML
    private Button SelectBtn;

    @FXML
    private ToggleGroup TeamChoice;

    private final String[] teamArray;

    private final String[] leaderArray;

    public SelectTeamController (String[] teamArray, String[] leaderArray) {
        this.teamArray = teamArray;
        this.leaderArray = leaderArray;
    }

    @FXML
    public void initialize() {
        if (teamArray[0] != null)
            MemberTeam1.setText(teamArray[0]);
        if (teamArray[1] != null)
            MemberTeam2.setText(teamArray[1]);
        if (leaderArray[0] != null)
            LeaderTeam1.setText(leaderArray[0]);
        if (leaderArray[1] != null)
            LeaderTeam2.setText(leaderArray[1]);

        int numPlayersTeam1 = (teamArray[0] == null ? 0 : 1) + (leaderArray[0] == null ? 0 : 1);
        int numPlayersTeam2 = (teamArray[1] == null ? 0 : 1) + (leaderArray[1] == null ? 0 : 1);

        if (numPlayersTeam1 == 2)
            Team1Btn.setDisable(true);
        if (numPlayersTeam2 == 2)
            Team2Btn.setDisable(true);

        SelectBtn.setDisable(true);
        Team1Btn.setOnAction(this::onButtonClick);
        Team2Btn.setOnAction(this::onButtonClick);
        SelectBtn.setOnAction(this::onSelect);
    }

    /**
     * Handles the event fired when the player select the image of one of the available towers.
     *
     * @param e the event fired
     */
    public void onSelect(ActionEvent e) {
        RadioButton selectedRadioButton = (RadioButton) TeamChoice.getSelectedToggle();
        if(selectedRadioButton == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid team selection");
            alert.setHeaderText("You must select a team \nfor this game before going on");
            alert.showAndWait();
            return;
        }
        SelectBtn.setDisable(true);

        int selectedTeam;

        switch (selectedRadioButton.getText()) {
            case "Team1":
                selectedTeam = 1;
                break;
            case "Team2":
                selectedTeam = 2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedRadioButton.getText());
        }

        boolean teamLeader = TeamLeader.isSelected();

        notifyObserver(observers -> observers.onUpdateTeam(selectedTeam, teamLeader));
    }

    /**
     * Handles the event fired when the player clicks on the button for Team1 or Team2.
     *
     * @param e the event fired
     */
    public void onButtonClick (ActionEvent e) {
        RadioButton btn = (RadioButton) e.getTarget();

        switch (btn.getText()) {
            case "Team1":
                TeamLeader.setDisable(leaderArray[0] != null);
                break;
            case "Team2":
                TeamLeader.setDisable(leaderArray[1] != null);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btn.getText());
        }

        SelectBtn.setDisable(false);
    }

}
