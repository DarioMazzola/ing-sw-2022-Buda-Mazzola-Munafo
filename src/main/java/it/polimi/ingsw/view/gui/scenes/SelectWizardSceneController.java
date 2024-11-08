package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.List;

import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Scene that asks the player to choose the wizard for this game.
 */
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

    private ImageView[] wizardImages;

    private final List<Wizard> availableWizards;

    public SelectWizardSceneController(List<Wizard> availableWizards) {
        this.availableWizards = availableWizards;
    }

    @FXML
    public void initialize() {
        RadioButton[] radioButtons = new RadioButton[]{firstWizard, secondWizard, thirdWizard, fourthWizard};
        wizardImages = new ImageView[]{firstWizardImage, secondWizardImage, thirdWizardImage, fourthWizardImage};
        for (ImageView wizardImage : wizardImages) {
            wizardImage.setOpacity(0.5);
            wizardImage.setOnMouseClicked(this::imageClicked);
        }
        selectButton.setOnAction(this::onSelect);
        selectButton.setDisable(true);


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

    /**
     * Manages the wizard selection event on click of the image associated with the wizard
     *
     * @param e the event fired
     */
    public void imageClicked(MouseEvent e) {
        System.out.println("imageClicked");
        ImageView img = (ImageView) e.getTarget();

        for (ImageView wizard : wizardImages) {
            wizard.getStyleClass().clear();
        }
        img.getStyleClass().add("dropShadow");

        RadioButton radioBtn = getButtonByImageView(img);
        radioBtn.setSelected(true);
        selectButton.setDisable(false);
    }

    /**
     * Handles the event fired when the player click on the select button.
     *
     * @param e the event fired
     */
    public void onSelect(ActionEvent e) {

        RadioButton selectedRadioButton = (RadioButton) wizards.getSelectedToggle();
        if (selectedRadioButton == null) {
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

    /**
     * Returns the RadioButton associated with the image clicked.
     *
     * @param imageView the ImageView selected
     * @return the RadioButton associated
     */
    public RadioButton getButtonByImageView(ImageView imageView) {
        String id = imageView.getId();
        System.out.println("Clicked: " + id);

        RadioButton button;

        switch (id) {
            case "firstWizardImage":
                button = firstWizard;
                break;
            case "secondWizardImage":
                button = secondWizard;
                break;
            case "thirdWizardImage":
                button = thirdWizard;
                break;
            case "fourthWizardImage":
                button = fourthWizard;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }

        return button;
    }
}
