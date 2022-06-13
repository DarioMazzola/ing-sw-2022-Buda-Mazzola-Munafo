package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.List;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class SelectWizardSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private RadioButton firstWizard;

    @FXML
    private ImageView firstWizardImage;

    @FXML
    private RadioButton secondWizard;

    @FXML
    private ImageView secondWizardImage;

    @FXML
    private RadioButton thirdWizard;

    @FXML
    private ImageView thirdWizardImage;

    @FXML
    private RadioButton fourthWizard;

    @FXML
    private ImageView fourthWizardImage;

    @FXML
    private ToggleGroup wizards;

    @FXML
    private Button selectButton;

    private final List<Wizard> availableWizards;
    public SelectWizardSceneController (List<Wizard> availableWizards) {
        this.availableWizards = availableWizards;
    }

    public void initialize() {
        selectButton.setDisable(true);
        RadioButton[] radioButtons = new RadioButton[]{firstWizard, secondWizard, thirdWizard, fourthWizard};
        ImageView[] wizardImages = new ImageView[]{firstWizardImage, secondWizardImage, thirdWizardImage, fourthWizardImage};

        for (ImageView wizardImage : wizardImages) {
            wizardImage.setOpacity(0.5);
        }

        for (RadioButton r : radioButtons) {
            r.setDisable(true);
        }

        for (Wizard w : availableWizards) {
            switch (w) {
                case FIRST:
                    firstWizard.setDisable(false);
                    firstWizardImage.setOpacity(1);
                    break;
                case SECOND:
                    secondWizard.setDisable(false);
                    secondWizardImage.setOpacity(1);
                    break;
                case THIRD:
                    thirdWizard.setDisable(false);
                    thirdWizardImage.setOpacity(1);
                    break;
                case FOURTH:
                    fourthWizard.setDisable(false);
                    fourthWizardImage.setOpacity(1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + w);
            }
        }
    }
    public void firstClicked(MouseEvent e){
        System.out.println("First clicked");
        firstWizard.setSelected(true);
        firstWizard.setDisable(true);
        selectButton.setDisable(false);
    }

    public void secondClicked(MouseEvent e){
        System.out.println("Second clicked");
        secondWizard.setSelected(true);
        secondWizard.setDisable(true);
        selectButton.setDisable(false);
    }

    public void thirdClicked(MouseEvent e){
        System.out.println("Third clicked");
        thirdWizard.setSelected(true);
        thirdWizard.setDisable(true);
        selectButton.setDisable(false);
    }

    public void fourthClicked(MouseEvent e){
        System.out.println("Fourth clicked");
        fourthWizard.setSelected(true);
        fourthWizard.setDisable(true);
        selectButton.setDisable(false);
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
