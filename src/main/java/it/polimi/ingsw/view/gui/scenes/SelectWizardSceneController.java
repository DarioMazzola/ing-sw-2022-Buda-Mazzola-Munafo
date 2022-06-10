package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class SelectWizardSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private RadioButton firstWizard;

    @FXML
    private RadioButton secondWizard;

    @FXML
    private RadioButton thirdWizard;

    @FXML
    private RadioButton fourthWizard;

    @FXML
    private ToggleGroup wizards;

    @FXML
    private Button selectButton;

    public void firstClicked(MouseEvent e){
        System.out.println("First clicked");
        firstWizard.setSelected(true);
        firstWizard.setDisable(true);
    }

    public void secondClicked(MouseEvent e){
        System.out.println("Second clicked");
        secondWizard.setSelected(true);
        secondWizard.setDisable(true);
    }

    public void thirdClicked(MouseEvent e){
        System.out.println("Third clicked");
        thirdWizard.setSelected(true);
        thirdWizard.setDisable(true);
    }

    public void fourthClicked(MouseEvent e){
        System.out.println("Fourth clicked");
        fourthWizard.setSelected(true);
        fourthWizard.setDisable(true);
    }

    public void onSelect(ActionEvent e) {

        RadioButton selectedRadioButton = (RadioButton) wizards.getSelectedToggle();
        if(selectedRadioButton == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid wizard selection");
            alert.setHeaderText("You must select a wizard \nfor this game before going on");
            alert.showAndWait();
            return;
        }
        selectButton.setDisable(true);
        firstWizard.setDisable(true);
        secondWizard.setDisable(true);
        thirdWizard.setDisable(true);
        fourthWizard.setDisable(true);

        Wizard chosenWizard;

        switch (selectedRadioButton.getText()) {
            case "FIRST":
                chosenWizard = Wizard.FIRST;
                break;
            case "SECOND":
                chosenWizard = Wizard.SECOND;
                break;
            case "THIRD":
                chosenWizard = Wizard.THIRD;
                break;
            case "FOURTH":
                chosenWizard = Wizard.FOURTH;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedRadioButton.getText());
        }

        System.out.println("Selected wizard: " + chosenWizard);

        Wizard finalChosenWizard = chosenWizard;
        notifyObserver(observers -> observers.onUpdateWizard(finalChosenWizard));
    }
}
