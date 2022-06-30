package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.gui.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.List;

import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Scene that asks the player to choose the color of the towers.
 *
 * @author Dario Mazzola.
 */
public class SelectTowerColorSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private RadioButton grayButton;
    @FXML
    private RadioButton whiteButton;
    @FXML
    private RadioButton blackButton;
    @FXML
    private ToggleGroup towersButton ;
    @FXML
    private Button selectButton;
    @FXML
    private ImageView grayImg;
    @FXML
    private ImageView whiteImg;
    @FXML
    private ImageView blackImg;

    private ImageView[] towers;

    private final List<Color> colorTowersAvailable;

    /**
     * Class constructor.
     *
     * @param colorTowersAvailable the tower color available
     */
    public SelectTowerColorSceneController(List<Color> colorTowersAvailable) {
        this.colorTowersAvailable = colorTowersAvailable;
    }

    @FXML
    public void initialize(){

        for(Color c : colorTowersAvailable) {
            ImageView t = getImageViewByColor(c);
            t.setOnMouseClicked(this::towerClicked);
            t.setOpacity(1);
            t.setDisable(false);
        }

        towers = new ImageView[]{grayImg, whiteImg, blackImg};

    }

    /**
     * Handles the event fired when the player select the image of one of the available towers.
     *
     * @param e the event fired
     */
    public void towerClicked(MouseEvent e){
        ImageView towerClicked = (ImageView) e.getSource();

        for(ImageView tower : towers) {
            tower.getStyleClass().clear();
        }
        towerClicked.getStyleClass().add("dropShadow");

        RadioButton button = getButtonByImageView(towerClicked);

        button.setSelected(true);

        selectButton.setDisable(false);
        selectButton.setOnAction(this::onSelect);
    }

    /**
     * Handles the event fired when the player click on the select button.
     *
     * @param e the event fired
     */
    public void onSelect(ActionEvent e) {

        RadioButton selectedRadioButton = (RadioButton) towersButton.getSelectedToggle();
        if(selectedRadioButton == null) {
            Alert alert = new Alert(ERROR, "", ButtonType.OK);
            alert.setTitle("Invalid tower color selection");
            alert.setHeaderText("You must select a tower color \nfor this game before going on");
            alert.showAndWait();
            return;
        }
        selectButton.setDisable(true);
        blackImg.setDisable(true);
        whiteImg.setDisable(true);
        grayImg.setDisable(true);

        Color towerColorChosen = null;

        switch (selectedRadioButton.getText()) {
            case "black":
                towerColorChosen = Color.BLACK;
                break;
            case "white":
                towerColorChosen = Color.WHITE;
                break;
            case "gray":
                towerColorChosen = Color.GRAY;
                break;
        }
        Color finalTowerColorChosen = towerColorChosen;

        notifyObserver(observer -> observer.onUpdateTowerColor(finalTowerColorChosen));
    }

    /**
     * Returns the ImageView associated to the color of the tower given as a parameter.
     *
     * @param c the color of the tower
     * @return the ImageView associated with the Color passed as a parameter
     */
    private ImageView getImageViewByColor(Color c) {

        ImageView imageView = null;

        switch (c){
            case BLACK:
                imageView = blackImg;
                break;
            case WHITE:
                imageView = whiteImg;
                break;
            case GRAY:
                imageView = grayImg;
                break;
        }

        return imageView;
    }

    /**
     * Returns the RadioButton associated with the image given as a parameter.
     *
     * @param imageView the image clicked
     * @return the RadioButton associated with the image passed as a parameter
     */
    private RadioButton getButtonByImageView(ImageView imageView){
        String id = imageView.getId();

        RadioButton btn = null;

        if(id.startsWith("gray")){
            btn = grayButton;
        }
        else if(id.startsWith("black")){
            btn = blackButton;
        }
        else if(id.startsWith("white")){
            btn = whiteButton;
        }

        return btn;
    }
}
