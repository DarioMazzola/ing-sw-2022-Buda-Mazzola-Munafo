package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.model.House;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



public class ActionSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private ImageView island_1;
    @FXML
    private ImageView island_2;

    private final ImageView[] islandArray = new ImageView[2];

    private House StudentToMoveChosen;

    public void selectStudent(MouseEvent mouseEvent) {

        System.out.println("Clicco studente");

        islandArray[0] = island_1;
        islandArray[1] = island_2;
        ImageView studentSelected = (ImageView)mouseEvent.getSource();
        studentSelected.setOnMouseClicked(this::selectStudentCancel);

        for(ImageView island : islandArray){
            island.setDisable(false);
            island.addEventHandler(MouseEvent.MOUSE_CLICKED, this::stampa);
        }
    }

    public void selectStudentCancel(MouseEvent mouseEvent) {
        ImageView studentSelected = (ImageView)mouseEvent.getSource();

        studentSelected.setOnMouseClicked(this::selectStudent);

        for(ImageView island : islandArray){
            island.setDisable(true);
        }
    }

    public void stampa(MouseEvent event){
        System.out.print("Clicco isola: ");
        System.out.println(event.getSource());
    }

    private int getPositionById(String id){

        if (id.substring(0, 26).equals("EntranceStudDashboard1Stud")){
            return id.charAt(26);
        }

        return -1;
    }
}
