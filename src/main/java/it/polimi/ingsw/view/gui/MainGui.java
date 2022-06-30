package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.client.ClientController;
import it.polimi.ingsw.view.gui.scenes.MenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        Gui view = new Gui();
        ClientController clientController = new ClientController(view);
        view.addObserver(clientController);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/MenuScene.fxml"));

        Parent root ;
        try{
            root = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
            return;
        }
        MenuController controller = loader.getController();
        controller.addObserver(clientController);

        // Show the scene containing the root layout.
        Scene scene = new Scene(root, Color.LIGHTSKYBLUE);
        stage.setScene(scene);

        stage.setOnCloseRequest(e -> logout(stage));

        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setTitle("Eriantys");
        stage.setFullScreenExitHint("");
        stage.show();
    }

    private void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Logout");
        alert.setHeaderText("Do you want to log out?");
        alert.setContentText("This game will be saved on the server. If you want to restore it, " +
                "log in with the same credentials");

        if(alert.showAndWait().orElse(ButtonType.OK).equals(ButtonType.OK)) {
            stage.close();
            Platform.exit();
            System.exit(-1234);
        }
    }
}