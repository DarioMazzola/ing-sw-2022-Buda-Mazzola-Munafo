package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.List;

import static javafx.scene.control.Alert.AlertType.ERROR;

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

    private final List<Color> colorTowersAvailable;

    public SelectTowerColorSceneController(List<Color> colorTowersAvailable) {
        this.colorTowersAvailable = colorTowersAvailable;
    }

    @FXML
    public void initialize(){

        for(Color c : colorTowersAvailable) {
            getImageViewByColor(c).setOpacity(1);
        }
    }

    public void grayClicked(MouseEvent e){
        grayButton.setSelected(true);
        selectButton.setDisable(false);
    }

    public void blackClicked(MouseEvent e){
        blackButton.setSelected(true);
        selectButton.setDisable(false);
    }

    public void whiteClicked(MouseEvent e){
        whiteButton.setSelected(true);
        selectButton.setDisable(false);
    }

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
        blackButton.setDisable(true);
        whiteButton.setDisable(true);
        grayButton.setDisable(true);

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

        System.out.println("colore selezionato: " + towerColorChosen);
    }

    public ImageView getImageViewByColor(Color c) {

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
}
