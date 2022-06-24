
package it.polimi.ingsw.view.gui.scenes;

import com.google.gson.Gson;
import it.polimi.ingsw.client.*;
import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.model.CharacterCardEnum;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.observer.ViewObservable;
import it.polimi.ingsw.view.gui.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;

import java.util.*;

import static it.polimi.ingsw.model.Color.*;
import static it.polimi.ingsw.model.House.*;

public class ActionSceneController extends ViewObservable implements SceneInterface {
    @FXML
    private ImageView GraveyardMain;
    @FXML
    private ImageView GraveyardDashboard1;
    @FXML
    private ImageView GraveyardDashboard2;
    @FXML
    private ImageView GraveyardDashboard3;
    @FXML
    private ImageView CardInUseMain;
    @FXML
    private ImageView CardInUseDashboard1;
    @FXML
    private ImageView CardInUseDashboard2;
    @FXML
    private ImageView CardInUseDashboard3;
    @FXML
    private ImageView CharacterCardAlternative;
    @FXML
    private ImageView ChatAlternative;
    @FXML
    private ImageView TwoDashboardAlternative;
    @FXML
    private ImageView OneDashboardAlternative;


    @FXML
    private Pane CharacterCardsPlacement;
    @FXML
    private Text NumOfCoins;
    @FXML
    private Pane Dashboard1;
    @FXML
    private Pane Dashboard2;
    @FXML
    private Pane Dashboard3;
    @FXML
    private ImageView CharacterCard1;

    @FXML
    private ImageView BlueStdCard1;

    @FXML
    private ImageView GreenStdCard1;

    @FXML
    private ImageView PinkStdCard1;

    @FXML
    private ImageView RedStdCard1;

    @FXML
    private ImageView YellowStdCard1;

    @FXML
    private ImageView CharacterCard2;
    @FXML
    private ImageView BlueStdCard2;

    @FXML
    private ImageView GreenStdCard2;

    @FXML
    private ImageView PinkStdCard2;

    @FXML
    private ImageView RedStdCard2;

    @FXML
    private ImageView YellowStdCard2;
    @FXML
    private ImageView CharacterCard3;
    @FXML
    private ImageView BlueStdCard3;

    @FXML
    private ImageView GreenStdCard3;

    @FXML
    private ImageView PinkStdCard3;

    @FXML
    private ImageView RedStdCard3;

    @FXML
    private ImageView YellowStdCard3;
    @FXML
    private Text CostCharacterCard1;
    @FXML
    private Text CostCharacterCard2;
    @FXML
    private Text CostCharacterCard3;
    @FXML
    private Pane Chat;

    @FXML
    private Text ChatReceivedText;

    @FXML
    private Button ChatSendBtn;

    @FXML
    private TextArea ChatText;

    @FXML
    private Pane ChatPane;

    @FXML
    private Text NoMessageText;

    @FXML
    private Pane Cloud4Num1;
    @FXML
    private Pane Cloud4Num2;
    @FXML
    private Pane Cloud4Num3;
    @FXML
    private Pane Cloud3Num1;
    @FXML
    private Pane Cloud3Num2;
    @FXML
    private Pane Cloud3Num3;
    @FXML
    private Pane Cloud3Num4;
    @FXML
    private ImageView Stud1Cloud4Num1;
    @FXML
    private ImageView Stud2Cloud4Num1;
    @FXML
    private ImageView Stud3Cloud4Num1;
    @FXML
    private ImageView Stud4Cloud4Num1;
    @FXML
    private ImageView Stud1Cloud4Num2;
    @FXML
    private ImageView Stud2Cloud4Num2;
    @FXML
    private ImageView Stud3Cloud4Num2;
    @FXML
    private ImageView Stud4Cloud4Num2;
    @FXML
    private ImageView Stud1Cloud4Num3;
    @FXML
    private ImageView Stud2Cloud4Num3;
    @FXML
    private ImageView Stud3Cloud4Num3;
    @FXML
    private ImageView Stud4Cloud4Num3;
    @FXML
    private ImageView Stud1Cloud3Num1;
    @FXML
    private ImageView Stud2Cloud3Num1;
    @FXML
    private ImageView Stud3Cloud3Num1;
    @FXML
    private ImageView Stud1Cloud3Num2;
    @FXML
    private ImageView Stud2Cloud3Num2;
    @FXML
    private ImageView Stud3Cloud3Num2;
    @FXML
    private ImageView Stud1Cloud3Num3;
    @FXML
    private ImageView Stud2Cloud3Num3;
    @FXML
    private ImageView Stud3Cloud3Num3;
    @FXML
    private ImageView Stud1Cloud3Num4;
    @FXML
    private ImageView Stud2Cloud3Num4;
    @FXML
    private ImageView Stud3Cloud3Num4;

    @FXML
    private Pane Island0;
    @FXML
    private ImageView StudYellowIsland0;
    @FXML
    private ImageView StudRedIsland0;
    @FXML
    private ImageView StudBlueIsland0;
    @FXML
    private ImageView StudPinkIsland0;
    @FXML
    private ImageView StudGreenIsland0;
    @FXML
    private ImageView TowerIsland0;
    @FXML
    private Text TowerNumberIsland0;
    @FXML
    private Pane Island1;
    @FXML
    private ImageView StudYellowIsland1;
    @FXML
    private ImageView StudRedIsland1;
    @FXML
    private ImageView StudBlueIsland1;
    @FXML
    private ImageView StudPinkIsland1;
    @FXML
    private ImageView StudGreenIsland1;
    @FXML
    private ImageView TowerIsland1;
    @FXML
    private Text TowerNumberIsland1;
    @FXML
    private Pane Island2;
    @FXML
    private ImageView StudYellowIsland2;
    @FXML
    private ImageView StudRedIsland2;
    @FXML
    private ImageView StudBlueIsland2;
    @FXML
    private ImageView StudPinkIsland2;
    @FXML
    private ImageView StudGreenIsland2;
    @FXML
    private ImageView TowerIsland2;
    @FXML
    private Text TowerNumberIsland2;
    @FXML
    private Pane Island3;
    @FXML
    private ImageView StudYellowIsland3;
    @FXML
    private ImageView StudRedIsland3;
    @FXML
    private ImageView StudBlueIsland3;
    @FXML
    private ImageView StudPinkIsland3;
    @FXML
    private ImageView StudGreenIsland3;
    @FXML
    private ImageView TowerIsland3;
    @FXML
    private Text TowerNumberIsland3;
    @FXML
    private Pane Island4;
    @FXML
    private ImageView StudYellowIsland4;
    @FXML
    private ImageView StudRedIsland4;
    @FXML
    private ImageView StudBlueIsland4;
    @FXML
    private ImageView StudPinkIsland4;
    @FXML
    private ImageView StudGreenIsland4;
    @FXML
    private ImageView TowerIsland4;
    @FXML
    private Text TowerNumberIsland4;
    @FXML
    private Pane Island5;
    @FXML
    private ImageView StudYellowIsland5;
    @FXML
    private ImageView StudRedIsland5;
    @FXML
    private ImageView StudBlueIsland5;
    @FXML
    private ImageView StudPinkIsland5;
    @FXML
    private ImageView StudGreenIsland5;
    @FXML
    private ImageView TowerIsland5;
    @FXML
    private Text TowerNumberIsland5;
    @FXML
    private Pane Island6;
    @FXML
    private ImageView StudYellowIsland6;
    @FXML
    private ImageView StudRedIsland6;
    @FXML
    private ImageView StudBlueIsland6;
    @FXML
    private ImageView StudPinkIsland6;
    @FXML
    private ImageView StudGreenIsland6;
    @FXML
    private ImageView TowerIsland6;
    @FXML
    private Text TowerNumberIsland6;
    @FXML
    private Pane Island7;
    @FXML
    private ImageView StudYellowIsland7;
    @FXML
    private ImageView StudRedIsland7;
    @FXML
    private ImageView StudBlueIsland7;
    @FXML
    private ImageView StudPinkIsland7;
    @FXML
    private ImageView StudGreenIsland7;
    @FXML
    private ImageView TowerIsland7;
    @FXML
    private Text TowerNumberIsland7;
    @FXML
    private Pane Island8;
    @FXML
    private ImageView StudYellowIsland8;
    @FXML
    private ImageView StudRedIsland8;
    @FXML
    private ImageView StudBlueIsland8;
    @FXML
    private ImageView StudPinkIsland8;
    @FXML
    private ImageView StudGreenIsland8;
    @FXML
    private ImageView TowerIsland8;
    @FXML
    private Text TowerNumberIsland8;
    @FXML
    private Pane Island9;
    @FXML
    private ImageView StudYellowIsland9;
    @FXML
    private ImageView StudRedIsland9;
    @FXML
    private ImageView StudBlueIsland9;
    @FXML
    private ImageView StudPinkIsland9;
    @FXML
    private ImageView StudGreenIsland9;
    @FXML
    private ImageView TowerIsland9;
    @FXML
    private Text TowerNumberIsland9;
    @FXML
    private Pane Island10;
    @FXML
    private ImageView StudYellowIsland10;
    @FXML
    private ImageView StudRedIsland10;
    @FXML
    private ImageView StudBlueIsland10;
    @FXML
    private ImageView StudPinkIsland10;
    @FXML
    private ImageView StudGreenIsland10;
    @FXML
    private ImageView TowerIsland10;
    @FXML
    private Text TowerNumberIsland10;
    @FXML
    private Pane Island11;
    @FXML
    private ImageView StudYellowIsland11;
    @FXML
    private ImageView StudRedIsland11;
    @FXML
    private ImageView StudBlueIsland11;
    @FXML
    private ImageView StudPinkIsland11;
    @FXML
    private ImageView StudGreenIsland11;
    @FXML
    private ImageView TowerIsland11;
    @FXML
    private Text TowerNumberIsland11;
    @FXML
    private ImageView NoEntryTileIsland0;
    @FXML
    private ImageView NoEntryTileIsland1;
    @FXML
    private ImageView NoEntryTileIsland2;
    @FXML
    private ImageView NoEntryTileIsland3;
    @FXML
    private ImageView NoEntryTileIsland4;
    @FXML
    private ImageView NoEntryTileIsland5;
    @FXML
    private ImageView NoEntryTileIsland6;
    @FXML
    private ImageView NoEntryTileIsland7;
    @FXML
    private ImageView NoEntryTileIsland8;
    @FXML
    private ImageView NoEntryTileIsland9;
    @FXML
    private ImageView NoEntryTileIsland10;
    @FXML
    private ImageView NoEntryTileIsland11;
    @FXML
    private ImageView MotherIsland0;
    @FXML
    private ImageView MotherIsland1;
    @FXML
    private ImageView MotherIsland2;
    @FXML
    private ImageView MotherIsland3;
    @FXML
    private ImageView MotherIsland4;
    @FXML
    private ImageView MotherIsland5;
    @FXML
    private ImageView MotherIsland6;
    @FXML
    private ImageView MotherIsland7;
    @FXML
    private ImageView MotherIsland8;
    @FXML
    private ImageView MotherIsland9;
    @FXML
    private ImageView MotherIsland10;
    @FXML
    private ImageView MotherIsland11;


    @FXML
    private ImageView EntranceStudMain1;
    @FXML
    private ImageView EntranceStudMain2;
    @FXML
    private ImageView EntranceStudMain3;
    @FXML
    private ImageView EntranceStudMain4;
    @FXML
    private ImageView EntranceStudMain5;
    @FXML
    private ImageView EntranceStudMain6;
    @FXML
    private ImageView EntranceStudMain7;
    @FXML
    private ImageView EntranceStudMain8;
    @FXML
    private ImageView EntranceStudMain9;
    @FXML
    private ImageView DiningGreenStd1Main;
    @FXML
    private ImageView DiningGreenStd2Main;
    @FXML
    private ImageView DiningGreenStd3Main;
    @FXML
    private ImageView DiningGreenStd4Main;
    @FXML
    private ImageView DiningGreenStd5Main;
    @FXML
    private ImageView DiningGreenStd6Main;
    @FXML
    private ImageView DiningGreenStd7Main;
    @FXML
    private ImageView DiningGreenStd8Main;
    @FXML
    private ImageView DiningGreenStd9Main;
    @FXML
    private ImageView DiningGreenStd10Main;
    @FXML
    private ImageView DiningRedStd1Main;
    @FXML
    private ImageView DiningRedStd2Main;
    @FXML
    private ImageView DiningRedStd3Main;
    @FXML
    private ImageView DiningRedStd4Main;
    @FXML
    private ImageView DiningRedStd5Main;
    @FXML
    private ImageView DiningRedStd6Main;
    @FXML
    private ImageView DiningRedStd7Main;
    @FXML
    private ImageView DiningRedStd8Main;
    @FXML
    private ImageView DiningRedStd9Main;
    @FXML
    private ImageView DiningRedStd10Main;
    @FXML
    private ImageView DiningYellowStd1Main;
    @FXML
    private ImageView DiningYellowStd2Main;
    @FXML
    private ImageView DiningYellowStd3Main;
    @FXML
    private ImageView DiningYellowStd4Main;
    @FXML
    private ImageView DiningYellowStd5Main;
    @FXML
    private ImageView DiningYellowStd6Main;
    @FXML
    private ImageView DiningYellowStd7Main;
    @FXML
    private ImageView DiningYellowStd8Main;
    @FXML
    private ImageView DiningYellowStd9Main;
    @FXML
    private ImageView DiningYellowStd10Main;
    @FXML
    private ImageView DiningPinkStd1Main;
    @FXML
    private ImageView DiningPinkStd2Main;
    @FXML
    private ImageView DiningPinkStd3Main;
    @FXML
    private ImageView DiningPinkStd4Main;
    @FXML
    private ImageView DiningPinkStd5Main;
    @FXML
    private ImageView DiningPinkStd6Main;
    @FXML
    private ImageView DiningPinkStd7Main;
    @FXML
    private ImageView DiningPinkStd8Main;
    @FXML
    private ImageView DiningPinkStd9Main;
    @FXML
    private ImageView DiningPinkStd10Main;
    @FXML
    private ImageView DiningBlueStd1Main;
    @FXML
    private ImageView DiningBlueStd2Main;
    @FXML
    private ImageView DiningBlueStd3Main;
    @FXML
    private ImageView DiningBlueStd4Main;
    @FXML
    private ImageView DiningBlueStd5Main;
    @FXML
    private ImageView DiningBlueStd6Main;
    @FXML
    private ImageView DiningBlueStd7Main;
    @FXML
    private ImageView DiningBlueStd8Main;
    @FXML
    private ImageView DiningBlueStd9Main;
    @FXML
    private ImageView DiningBlueStd10Main;
    @FXML
    private ImageView ProfGreenMain;
    @FXML
    private ImageView ProfBlueMain;
    @FXML
    private ImageView ProfRedMain;
    @FXML
    private ImageView ProfYellowMain;
    @FXML
    private ImageView ProfPinkMain;
    @FXML
    private ImageView Tower1Main;
    @FXML
    private ImageView Tower2Main;
    @FXML
    private ImageView Tower3Main;
    @FXML
    private ImageView Tower4Main;
    @FXML
    private ImageView Tower5Main;
    @FXML
    private ImageView Tower6Main;
    @FXML
    private ImageView Tower7Main;
    @FXML
    private ImageView Tower8Main;


    @FXML
    private ImageView EntranceStudDashboard1Stud1;
    @FXML
    private ImageView EntranceStudDashboard1Stud2;
    @FXML
    private ImageView EntranceStudDashboard1Stud3;
    @FXML
    private ImageView EntranceStudDashboard1Stud4;
    @FXML
    private ImageView EntranceStudDashboard1Stud5;
    @FXML
    private ImageView EntranceStudDashboard1Stud6;
    @FXML
    private ImageView EntranceStudDashboard1Stud7;
    @FXML
    private ImageView EntranceStudDashboard1Stud8;
    @FXML
    private ImageView EntranceStudDashboard1Stud9;
    @FXML
    private ImageView DiningRedStd1Dashboard1;
    @FXML
    private ImageView DiningRedStd2Dashboard1;
    @FXML
    private ImageView DiningRedStd3Dashboard1;
    @FXML
    private ImageView DiningRedStd4Dashboard1;
    @FXML
    private ImageView DiningRedStd5Dashboard1;
    @FXML
    private ImageView DiningRedStd6Dashboard1;
    @FXML
    private ImageView DiningRedStd7Dashboard1;
    @FXML
    private ImageView DiningRedStd8Dashboard1;
    @FXML
    private ImageView DiningRedStd9Dashboard1;
    @FXML
    private ImageView DiningRedStd10Dashboard1;
    @FXML
    private ImageView DiningBlueStd1Dashboard1;
    @FXML
    private ImageView DiningBlueStd2Dashboard1;
    @FXML
    private ImageView DiningBlueStd3Dashboard1;
    @FXML
    private ImageView DiningBlueStd4Dashboard1;
    @FXML
    private ImageView DiningBlueStd5Dashboard1;
    @FXML
    private ImageView DiningBlueStd6Dashboard1;
    @FXML
    private ImageView DiningBlueStd7Dashboard1;
    @FXML
    private ImageView DiningBlueStd8Dashboard1;
    @FXML
    private ImageView DiningBlueStd9Dashboard1;
    @FXML
    private ImageView DiningBlueStd10Dashboard1;
    @FXML
    private ImageView DiningGreenStd1Dashboard1;
    @FXML
    private ImageView DiningGreenStd2Dashboard1;
    @FXML
    private ImageView DiningGreenStd3Dashboard1;
    @FXML
    private ImageView DiningGreenStd4Dashboard1;
    @FXML
    private ImageView DiningGreenStd5Dashboard1;
    @FXML
    private ImageView DiningGreenStd6Dashboard1;
    @FXML
    private ImageView DiningGreenStd7Dashboard1;
    @FXML
    private ImageView DiningGreenStd8Dashboard1;
    @FXML
    private ImageView DiningGreenStd9Dashboard1;
    @FXML
    private ImageView DiningGreenStd10Dashboard1;
    @FXML
    private ImageView DiningYellowStd1Dashboard1;
    @FXML
    private ImageView DiningYellowStd2Dashboard1;
    @FXML
    private ImageView DiningYellowStd3Dashboard1;
    @FXML
    private ImageView DiningYellowStd4Dashboard1;
    @FXML
    private ImageView DiningYellowStd5Dashboard1;
    @FXML
    private ImageView DiningYellowStd6Dashboard1;
    @FXML
    private ImageView DiningYellowStd7Dashboard1;
    @FXML
    private ImageView DiningYellowStd8Dashboard1;
    @FXML
    private ImageView DiningYellowStd9Dashboard1;
    @FXML
    private ImageView DiningYellowStd10Dashboard1;
    @FXML
    private ImageView DiningPinkStd1Dashboard1;
    @FXML
    private ImageView DiningPinkStd2Dashboard1;
    @FXML
    private ImageView DiningPinkStd3Dashboard1;
    @FXML
    private ImageView DiningPinkStd4Dashboard1;
    @FXML
    private ImageView DiningPinkStd5Dashboard1;
    @FXML
    private ImageView DiningPinkStd6Dashboard1;
    @FXML
    private ImageView DiningPinkStd7Dashboard1;
    @FXML
    private ImageView DiningPinkStd8Dashboard1;
    @FXML
    private ImageView DiningPinkStd9Dashboard1;
    @FXML
    private ImageView DiningPinkStd10Dashboard1;
    @FXML
    private ImageView ProfGreenDashboard1;
    @FXML
    private ImageView ProfBlueDashboard1;
    @FXML
    private ImageView ProfRedDashboard1;
    @FXML
    private ImageView ProfYellowDashboard1;
    @FXML
    private ImageView ProfPinkDashboard1;
    @FXML
    private ImageView Tower1Dashboard1;
    @FXML
    private ImageView Tower2Dashboard1;
    @FXML
    private ImageView Tower3Dashboard1;
    @FXML
    private ImageView Tower4Dashboard1;
    @FXML
    private ImageView Tower5Dashboard1;
    @FXML
    private ImageView Tower6Dashboard1;
    @FXML
    private ImageView Tower7Dashboard1;
    @FXML
    private ImageView Tower8Dashboard1;


    @FXML
    private ImageView EntranceStudDashboard2Stud1;
    @FXML
    private ImageView EntranceStudDashboard2Stud2;
    @FXML
    private ImageView EntranceStudDashboard2Stud3;
    @FXML
    private ImageView EntranceStudDashboard2Stud4;
    @FXML
    private ImageView EntranceStudDashboard2Stud5;
    @FXML
    private ImageView EntranceStudDashboard2Stud6;
    @FXML
    private ImageView EntranceStudDashboard2Stud7;
    @FXML
    private ImageView EntranceStudDashboard2Stud8;
    @FXML
    private ImageView EntranceStudDashboard2Stud9;
    @FXML
    private ImageView DiningRedStd1Dashboard2;
    @FXML
    private ImageView DiningRedStd2Dashboard2;
    @FXML
    private ImageView DiningRedStd3Dashboard2;
    @FXML
    private ImageView DiningRedStd4Dashboard2;
    @FXML
    private ImageView DiningRedStd5Dashboard2;
    @FXML
    private ImageView DiningRedStd6Dashboard2;
    @FXML
    private ImageView DiningRedStd7Dashboard2;
    @FXML
    private ImageView DiningRedStd8Dashboard2;
    @FXML
    private ImageView DiningRedStd9Dashboard2;
    @FXML
    private ImageView DiningRedStd10Dashboard2;
    @FXML
    private ImageView DiningBlueStd1Dashboard2;
    @FXML
    private ImageView DiningBlueStd2Dashboard2;
    @FXML
    private ImageView DiningBlueStd3Dashboard2;
    @FXML
    private ImageView DiningBlueStd4Dashboard2;
    @FXML
    private ImageView DiningBlueStd5Dashboard2;
    @FXML
    private ImageView DiningBlueStd6Dashboard2;
    @FXML
    private ImageView DiningBlueStd7Dashboard2;
    @FXML
    private ImageView DiningBlueStd8Dashboard2;
    @FXML
    private ImageView DiningBlueStd9Dashboard2;
    @FXML
    private ImageView DiningBlueStd10Dashboard2;
    @FXML
    private ImageView DiningGreenStd1Dashboard2;
    @FXML
    private ImageView DiningGreenStd2Dashboard2;
    @FXML
    private ImageView DiningGreenStd3Dashboard2;
    @FXML
    private ImageView DiningGreenStd4Dashboard2;
    @FXML
    private ImageView DiningGreenStd5Dashboard2;
    @FXML
    private ImageView DiningGreenStd6Dashboard2;
    @FXML
    private ImageView DiningGreenStd7Dashboard2;
    @FXML
    private ImageView DiningGreenStd8Dashboard2;
    @FXML
    private ImageView DiningGreenStd9Dashboard2;
    @FXML
    private ImageView DiningGreenStd10Dashboard2;
    @FXML
    private ImageView DiningYellowStd1Dashboard2;
    @FXML
    private ImageView DiningYellowStd2Dashboard2;
    @FXML
    private ImageView DiningYellowStd3Dashboard2;
    @FXML
    private ImageView DiningYellowStd4Dashboard2;
    @FXML
    private ImageView DiningYellowStd5Dashboard2;
    @FXML
    private ImageView DiningYellowStd6Dashboard2;
    @FXML
    private ImageView DiningYellowStd7Dashboard2;
    @FXML
    private ImageView DiningYellowStd8Dashboard2;
    @FXML
    private ImageView DiningYellowStd9Dashboard2;
    @FXML
    private ImageView DiningYellowStd10Dashboard2;
    @FXML
    private ImageView DiningPinkStd1Dashboard2;
    @FXML
    private ImageView DiningPinkStd2Dashboard2;
    @FXML
    private ImageView DiningPinkStd3Dashboard2;
    @FXML
    private ImageView DiningPinkStd4Dashboard2;
    @FXML
    private ImageView DiningPinkStd5Dashboard2;
    @FXML
    private ImageView DiningPinkStd6Dashboard2;
    @FXML
    private ImageView DiningPinkStd7Dashboard2;
    @FXML
    private ImageView DiningPinkStd8Dashboard2;
    @FXML
    private ImageView DiningPinkStd9Dashboard2;
    @FXML
    private ImageView DiningPinkStd10Dashboard2;
    @FXML
    private ImageView ProfGreenDashboard2;
    @FXML
    private ImageView ProfBlueDashboard2;
    @FXML
    private ImageView ProfRedDashboard2;
    @FXML
    private ImageView ProfYellowDashboard2;
    @FXML
    private ImageView ProfPinkDashboard2;
    @FXML
    private ImageView Tower1Dashboard2;
    @FXML
    private ImageView Tower2Dashboard2;
    @FXML
    private ImageView Tower3Dashboard2;
    @FXML
    private ImageView Tower4Dashboard2;
    @FXML
    private ImageView Tower5Dashboard2;
    @FXML
    private ImageView Tower6Dashboard2;
    @FXML
    private ImageView Tower7Dashboard2;
    @FXML
    private ImageView Tower8Dashboard2;


    @FXML
    private ImageView EntranceStudDashboard3Stud1;
    @FXML
    private ImageView EntranceStudDashboard3Stud2;
    @FXML
    private ImageView EntranceStudDashboard3Stud3;
    @FXML
    private ImageView EntranceStudDashboard3Stud4;
    @FXML
    private ImageView EntranceStudDashboard3Stud5;
    @FXML
    private ImageView EntranceStudDashboard3Stud6;
    @FXML
    private ImageView EntranceStudDashboard3Stud7;
    @FXML
    private ImageView EntranceStudDashboard3Stud8;
    @FXML
    private ImageView EntranceStudDashboard3Stud9;
    @FXML
    private ImageView DiningRedStd1Dashboard3;
    @FXML
    private ImageView DiningRedStd2Dashboard3;
    @FXML
    private ImageView DiningRedStd3Dashboard3;
    @FXML
    private ImageView DiningRedStd4Dashboard3;
    @FXML
    private ImageView DiningRedStd5Dashboard3;
    @FXML
    private ImageView DiningRedStd6Dashboard3;
    @FXML
    private ImageView DiningRedStd7Dashboard3;
    @FXML
    private ImageView DiningRedStd8Dashboard3;
    @FXML
    private ImageView DiningRedStd9Dashboard3;
    @FXML
    private ImageView DiningRedStd10Dashboard3;
    @FXML
    private ImageView DiningBlueStd1Dashboard3;
    @FXML
    private ImageView DiningBlueStd2Dashboard3;
    @FXML
    private ImageView DiningBlueStd3Dashboard3;
    @FXML
    private ImageView DiningBlueStd4Dashboard3;
    @FXML
    private ImageView DiningBlueStd5Dashboard3;
    @FXML
    private ImageView DiningBlueStd6Dashboard3;
    @FXML
    private ImageView DiningBlueStd7Dashboard3;
    @FXML
    private ImageView DiningBlueStd8Dashboard3;
    @FXML
    private ImageView DiningBlueStd9Dashboard3;
    @FXML
    private ImageView DiningBlueStd10Dashboard3;
    @FXML
    private ImageView DiningGreenStd1Dashboard3;
    @FXML
    private ImageView DiningGreenStd2Dashboard3;
    @FXML
    private ImageView DiningGreenStd3Dashboard3;
    @FXML
    private ImageView DiningGreenStd4Dashboard3;
    @FXML
    private ImageView DiningGreenStd5Dashboard3;
    @FXML
    private ImageView DiningGreenStd6Dashboard3;
    @FXML
    private ImageView DiningGreenStd7Dashboard3;
    @FXML
    private ImageView DiningGreenStd8Dashboard3;
    @FXML
    private ImageView DiningGreenStd9Dashboard3;
    @FXML
    private ImageView DiningGreenStd10Dashboard3;
    @FXML
    private ImageView DiningYellowStd1Dashboard3;
    @FXML
    private ImageView DiningYellowStd2Dashboard3;
    @FXML
    private ImageView DiningYellowStd3Dashboard3;
    @FXML
    private ImageView DiningYellowStd4Dashboard3;
    @FXML
    private ImageView DiningYellowStd5Dashboard3;
    @FXML
    private ImageView DiningYellowStd6Dashboard3;
    @FXML
    private ImageView DiningYellowStd7Dashboard3;
    @FXML
    private ImageView DiningYellowStd8Dashboard3;
    @FXML
    private ImageView DiningYellowStd9Dashboard3;
    @FXML
    private ImageView DiningYellowStd10Dashboard3;
    @FXML
    private ImageView DiningPinkStd1Dashboard3;
    @FXML
    private ImageView DiningPinkStd2Dashboard3;
    @FXML
    private ImageView DiningPinkStd3Dashboard3;
    @FXML
    private ImageView DiningPinkStd4Dashboard3;
    @FXML
    private ImageView DiningPinkStd5Dashboard3;
    @FXML
    private ImageView DiningPinkStd6Dashboard3;
    @FXML
    private ImageView DiningPinkStd7Dashboard3;
    @FXML
    private ImageView DiningPinkStd8Dashboard3;
    @FXML
    private ImageView DiningPinkStd9Dashboard3;
    @FXML
    private ImageView DiningPinkStd10Dashboard3;
    @FXML
    private ImageView ProfGreenDashboard3;
    @FXML
    private ImageView ProfBlueDashboard3;
    @FXML
    private ImageView ProfRedDashboard3;
    @FXML
    private ImageView ProfYellowDashboard3;
    @FXML
    private ImageView ProfPinkDashboard3;
    @FXML
    private ImageView Tower1Dashboard3;
    @FXML
    private ImageView Tower2Dashboard3;
    @FXML
    private ImageView Tower3Dashboard3;
    @FXML
    private ImageView Tower4Dashboard3;
    @FXML
    private ImageView Tower5Dashboard3;
    @FXML
    private ImageView Tower6Dashboard3;
    @FXML
    private ImageView Tower7Dashboard3;
    @FXML
    private ImageView Tower8Dashboard3;

    @FXML
    private Button diningHallMainGreen;
    @FXML
    private Button diningHallMainRed;
    @FXML
    private Button diningHallMainYellow;
    @FXML
    private Button diningHallMainBlue;
    @FXML
    private Button diningHallMainPink;

    @FXML
    private Button Island00Btn;
    @FXML
    private Button Island01Btn;
    @FXML
    private Button Island02Btn;
    @FXML
    private Button Island03Btn;
    @FXML
    private Button Island04Btn;
    @FXML
    private Button Island05Btn;
    @FXML
    private Button Island06Btn;
    @FXML
    private Button Island07Btn;
    @FXML
    private Button Island08Btn;
    @FXML
    private Button Island09Btn;
    @FXML
    private Button Island10Btn;
    @FXML
    private Button Island11Btn;
    @FXML
    private Button Island12Btn;
    @FXML
    private Button Cloud3Btn1;
    @FXML
    private Button Cloud3Btn2;
    @FXML
    private Button Cloud3Btn3;
    @FXML
    private Button Cloud3Btn4;
    @FXML
    private Button Cloud4Btn1;
    @FXML
    private Button Cloud4Btn2;
    @FXML
    private Button Cloud4Btn3;


    @FXML
    private ImageView Card1Elem1;
    @FXML
    private ImageView Card1Elem2;
    @FXML
    private ImageView Card1Elem3;
    @FXML
    private ImageView Card1Elem4;
    @FXML
    private ImageView Card1Elem5;
    @FXML
    private ImageView Card1Elem6;
    @FXML
    private ImageView Card2Elem1;
    @FXML
    private ImageView Card2Elem2;
    @FXML
    private ImageView Card2Elem3;
    @FXML
    private ImageView Card2Elem4;
    @FXML
    private ImageView Card2Elem5;
    @FXML
    private ImageView Card2Elem6;
    @FXML
    private ImageView Card3Elem1;
    @FXML
    private ImageView Card3Elem2;
    @FXML
    private ImageView Card3Elem3;
    @FXML
    private ImageView Card3Elem4;
    @FXML
    private ImageView Card3Elem5;
    @FXML
    private ImageView Card3Elem6;

    @FXML
    private Text NameDashboard1;
    @FXML
    private Text NameDashboard2;
    @FXML
    private Text NameDashboard3;

    @FXML
    private TextArea Suggestions;

    @FXML
    private ImageView ImgIsland0;
    @FXML
    private ImageView ImgIsland1;
    @FXML
    private ImageView ImgIsland2;
    @FXML
    private ImageView ImgIsland3;
    @FXML
    private ImageView ImgIsland4;
    @FXML
    private ImageView ImgIsland5;
    @FXML
    private ImageView ImgIsland6;
    @FXML
    private ImageView ImgIsland7;
    @FXML
    private ImageView ImgIsland8;
    @FXML
    private ImageView ImgIsland9;
    @FXML
    private ImageView ImgIsland10;
    @FXML
    private ImageView ImgIsland11;


    @FXML
    private ImageView NoEntryTileDetailIsland0;
    @FXML
    private GridPane DescriptionIsland00;
    @FXML
    private Text NumGreenIsland0;
    @FXML
    private Text NumRedIsland0;
    @FXML
    private Text NumYellowIsland0;
    @FXML
    private Text NumBlueIsland0;
    @FXML
    private Text NumPinkIsland0;
    @FXML
    private Text NumNoEntryTileIsland0;

    @FXML
    private ImageView NoEntryTileDetailIsland1;
    @FXML
    private GridPane DescriptionIsland01;
    @FXML
    private Text NumGreenIsland1;
    @FXML
    private Text NumRedIsland1;
    @FXML
    private Text NumYellowIsland1;
    @FXML
    private Text NumBlueIsland1;
    @FXML
    private Text NumPinkIsland1;
    @FXML
    private Text NumNoEntryTileIsland1;

    @FXML
    private ImageView NoEntryTileDetailIsland2;
    @FXML
    private GridPane DescriptionIsland02;
    @FXML
    private Text NumGreenIsland2;
    @FXML
    private Text NumRedIsland2;
    @FXML
    private Text NumYellowIsland2;
    @FXML
    private Text NumBlueIsland2;
    @FXML
    private Text NumPinkIsland2;
    @FXML
    private Text NumNoEntryTileIsland2;

    @FXML
    private ImageView NoEntryTileDetailIsland3;
    @FXML
    private GridPane DescriptionIsland03;
    @FXML
    private Text NumGreenIsland3;
    @FXML
    private Text NumRedIsland3;
    @FXML
    private Text NumYellowIsland3;
    @FXML
    private Text NumBlueIsland3;
    @FXML
    private Text NumPinkIsland3;
    @FXML
    private Text NumNoEntryTileIsland3;

    @FXML
    private ImageView NoEntryTileDetailIsland4;
    @FXML
    private GridPane DescriptionIsland04;
    @FXML
    private Text NumGreenIsland4;
    @FXML
    private Text NumRedIsland4;
    @FXML
    private Text NumYellowIsland4;
    @FXML
    private Text NumBlueIsland4;
    @FXML
    private Text NumPinkIsland4;
    @FXML
    private Text NumNoEntryTileIsland4;

    @FXML
    private ImageView NoEntryTileDetailIsland5;
    @FXML
    private GridPane DescriptionIsland05;
    @FXML
    private Text NumGreenIsland5;
    @FXML
    private Text NumRedIsland5;
    @FXML
    private Text NumYellowIsland5;
    @FXML
    private Text NumBlueIsland5;
    @FXML
    private Text NumPinkIsland5;
    @FXML
    private Text NumNoEntryTileIsland5;

    @FXML
    private ImageView NoEntryTileDetailIsland6;
    @FXML
    private GridPane DescriptionIsland06;
    @FXML
    private Text NumGreenIsland6;
    @FXML
    private Text NumRedIsland6;
    @FXML
    private Text NumYellowIsland6;
    @FXML
    private Text NumBlueIsland6;
    @FXML
    private Text NumPinkIsland6;
    @FXML
    private Text NumNoEntryTileIsland6;

    @FXML
    private ImageView NoEntryTileDetailIsland7;
    @FXML
    private GridPane DescriptionIsland07;
    @FXML
    private Text NumGreenIsland7;
    @FXML
    private Text NumRedIsland7;
    @FXML
    private Text NumYellowIsland7;
    @FXML
    private Text NumBlueIsland7;
    @FXML
    private Text NumPinkIsland7;
    @FXML
    private Text NumNoEntryTileIsland7;

    @FXML
    private ImageView NoEntryTileDetailIsland8;
    @FXML
    private GridPane DescriptionIsland08;
    @FXML
    private Text NumGreenIsland8;
    @FXML
    private Text NumRedIsland8;
    @FXML
    private Text NumYellowIsland8;
    @FXML
    private Text NumBlueIsland8;
    @FXML
    private Text NumPinkIsland8;
    @FXML
    private Text NumNoEntryTileIsland8;

    @FXML
    private ImageView NoEntryTileDetailIsland9;
    @FXML
    private GridPane DescriptionIsland09;
    @FXML
    private Text NumGreenIsland9;
    @FXML
    private Text NumRedIsland9;
    @FXML
    private Text NumYellowIsland9;
    @FXML
    private Text NumBlueIsland9;
    @FXML
    private Text NumPinkIsland9;
    @FXML
    private Text NumNoEntryTileIsland9;

    @FXML
    private ImageView NoEntryTileDetailIsland10;
    @FXML
    private GridPane DescriptionIsland10;
    @FXML
    private Text NumGreenIsland10;
    @FXML
    private Text NumRedIsland10;
    @FXML
    private Text NumYellowIsland10;
    @FXML
    private Text NumBlueIsland10;
    @FXML
    private Text NumPinkIsland10;
    @FXML
    private Text NumNoEntryTileIsland10;

    @FXML
    private ImageView NoEntryTileDetailIsland11;
    @FXML
    private GridPane DescriptionIsland11;
    @FXML
    private Text NumGreenIsland11;
    @FXML
    private Text NumRedIsland11;
    @FXML
    private Text NumYellowIsland11;
    @FXML
    private Text NumBlueIsland11;
    @FXML
    private Text NumPinkIsland11;
    @FXML
    private Text NumNoEntryTileIsland11;

    @FXML
    private VBox boxCard1;
    @FXML
    private VBox boxCard2;
    @FXML
    private VBox boxCard3;

    private String[] card1Array;
    private String[] card2Array;
    private String[] card3Array;

    private GridPane[] descriptionArray;

    private ReducedGameModel gm;
    private final String nickname;

    private final int numMain;
    private ImageView[] EntranceMain;
    private ImageView[] islandsImageView;
    private Button[] diningHallMain;
    private List<Button> cloudsButtons;

    private House houseSelected;
    private House studentSelectedFromCard;
    private List<House> wantedStudents;
    private List<House> returnedStudents;


    private List<Map<String, Node>> islandList;
    private House[] entranceArray;
    private boolean moveMother;
    private ImageView[] characterCards;
    private Integer cardSelected;

    private final EventHandler<MouseEvent> selectStudent;
    private final EventHandler<MouseEvent> moveMotherFrom;
    private final EventHandler<MouseEvent> moveStudentToIsland;
    private final EventHandler<MouseEvent> selectStudentCancel;
    private final EventHandler<MouseEvent> moveMotherTo;
    private final EventHandler<MouseEvent> moveStudentToDiningHall;
    private final EventHandler<MouseEvent> selectCloud;
    private final EventHandler<MouseEvent> doNothing;
    private final EventHandler<MouseEvent> selectCharacterCard;
    private final EventHandler<MouseEvent> addNoEntryTile;
    private final EventHandler<MouseEvent> selectStudentForJolly;
    private final EventHandler<MouseEvent> pickStudentsFromEntrance;
    private final EventHandler<MouseEvent> selectStudentForSpoiledPrincess;
    private final EventHandler<MouseEvent> pickStudentsFromDiningHall;
    private final EventHandler<MouseEvent> selectStudentForMinstrel;
    private final EventHandler<MouseEvent> selectStudentForMonk;
    private final EventHandler<MouseEvent> selectIslandForMonk;
    private final EventHandler<MouseEvent> selectIslandForHerald;
    private final EventHandler<MouseEvent> selectStudentForMushroomHunter_Thief;
    private final EventHandler<MouseEvent> sendStudents;

    private Button[] islandButtons;

    public ActionSceneController(ReducedGameModel gm, String nickname) {
        this.gm = gm;
        this.nickname = nickname;
        numMain = Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getPlayerByNickname(nickname));

        selectStudent = this::selectStudent;
        selectStudentCancel = this::selectStudentCancel;
        moveMotherFrom = this::moveMotherFrom;
        moveStudentToIsland = this::moveStudentToIsland;
        moveMotherTo = this::moveMotherTo;
        moveStudentToDiningHall = this::moveStudentToDiningHall;
        selectCloud = this::onCloudSelected;
        doNothing = this::doNothing;
        selectCharacterCard = this::selectCharacterCard;
        addNoEntryTile = this::addNoEntryTile;
        selectStudentForJolly = this::selectStudentForJolly;
        pickStudentsFromEntrance = this::pickStudentsFromEntrance;
        selectStudentForSpoiledPrincess = this::selectStudentForSpoiledPrincess;
        pickStudentsFromDiningHall = this::pickStudentsFromDiningHall;
        selectStudentForMinstrel = this::selectStudentForMinstrel;
        selectStudentForMonk = this::selectStudentForMonk;
        selectIslandForMonk = this::selectIslandForMonk;
        selectIslandForHerald = this::selectIslandForHerald;
        selectStudentForMushroomHunter_Thief = this::selectStudentForMushroomHunter_Thief;
        sendStudents = this::sendStudents;
    }

    // <--------- Initialize methods --------->
    private void initializeMainPlayer() {
        initializeDashboardMain();

        fillGraveyard(GraveyardMain, gm.getArrayPlayers()[numMain]);
        fillCardInUse(CardInUseMain, gm.getArrayPlayers()[numMain]);

        if (gm.isExpertMode()) {
            setCoins(NumOfCoins, gm.getArrayPlayers()[numMain]);
        }
    }

    private void initializeDashboardMain() {
        Image image;
        int numStudents = 6;
        if (gm.getNumPlayers() == 3) {
            numStudents = 8;
        }
        int numTowers = 7;
        if (gm.getNumPlayers() == 3) {
            numTowers = 5;
        }

        EntranceMain = new ImageView[]{EntranceStudMain1, EntranceStudMain2, EntranceStudMain3, EntranceStudMain4, EntranceStudMain5, EntranceStudMain6, EntranceStudMain7, EntranceStudMain8, EntranceStudMain9};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[numMain].getDashboard().getStudents());
        int i = numStudents;

        entranceArray = new House[numStudents + 1];

        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            entranceArray[i] = BLUE;
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i--;
        }
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            entranceArray[i] = PINK;
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i--;
        }
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            entranceArray[i] = YELLOW;
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i--;
        }
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            entranceArray[i] = RED;
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i--;
        }
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            entranceArray[i] = GREEN;
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i--;
        }
        while (i >= 0) {
            EntranceMain[i].setVisible(false);
            i--;
        }

        initializeDiningHallMain();

        ImageView[] TowersMain = new ImageView[]{Tower1Main, Tower2Main, Tower3Main, Tower4Main, Tower5Main, Tower6Main, Tower7Main, Tower8Main};

        int numTow = gm.getArrayPlayers()[numMain].getDashboard().getNumTowersIn();
        Color colorTower = gm.getArrayPlayers()[numMain].getDashboard().getTowerColor();
        i = numTowers;
        String path;
        if (colorTower == BLACK) {
            path = "images/towers/torre_nera.png";
        } else if (colorTower == Color.GRAY) {
            path = "images/towers/torre_grigia.png";
        } else {
            path = "images/towers/torre_bianca.png";
        }

        while (numTow > 0) {
            image = new Image(path);
            TowersMain[i].setImage(image);
            TowersMain[i].setVisible(true);
            i--;
            numTow--;
        }
        while (i >= 0) {
            TowersMain[i].setVisible(false);
            i--;
        }
    }

    private void initializeDiningHallMain() {
        // parameters
        Map<House, Boolean> profMap;
        Image image;

        // Main player dining hall initialization
        ImageView[] DiningMainRed = new ImageView[]{DiningRedStd1Main, DiningRedStd2Main, DiningRedStd3Main, DiningRedStd4Main, DiningRedStd5Main, DiningRedStd6Main, DiningRedStd7Main, DiningRedStd8Main, DiningRedStd9Main, DiningRedStd10Main};

        ImageView[] DiningMainGreen = new ImageView[]{DiningGreenStd1Main, DiningGreenStd2Main, DiningGreenStd3Main, DiningGreenStd4Main, DiningGreenStd5Main, DiningGreenStd6Main, DiningGreenStd7Main, DiningGreenStd8Main, DiningGreenStd9Main, DiningGreenStd10Main};

        ImageView[] DiningMainBlue = new ImageView[]{DiningBlueStd1Main, DiningBlueStd2Main, DiningBlueStd3Main, DiningBlueStd4Main, DiningBlueStd5Main, DiningBlueStd6Main, DiningBlueStd7Main, DiningBlueStd8Main, DiningBlueStd9Main, DiningBlueStd10Main};

        ImageView[] DiningMainPink = new ImageView[]{DiningPinkStd1Main, DiningPinkStd2Main, DiningPinkStd3Main, DiningPinkStd4Main, DiningPinkStd5Main, DiningPinkStd6Main, DiningPinkStd7Main, DiningPinkStd8Main, DiningPinkStd9Main, DiningPinkStd10Main};

        ImageView[] DiningMainYellow = new ImageView[]{DiningYellowStd1Main, DiningYellowStd2Main, DiningYellowStd3Main, DiningYellowStd4Main, DiningYellowStd5Main, DiningYellowStd6Main, DiningYellowStd7Main, DiningYellowStd8Main, DiningYellowStd9Main, DiningYellowStd10Main};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[numMain].getDashboard().getDiningHall().getStudents());
        int i = 0;
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            DiningMainGreen[i].setImage(image);
            DiningMainGreen[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i++;
        }
        while (i < 10) {
            DiningMainGreen[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            DiningMainBlue[i].setImage(image);
            DiningMainBlue[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i++;
        }
        while (i < 10) {
            DiningMainBlue[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            DiningMainRed[i].setImage(image);
            DiningMainRed[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i++;
        }
        while (i < 10) {
            DiningMainRed[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            DiningMainYellow[i].setImage(image);
            DiningMainYellow[i].setVisible(true);
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i++;
        }
        while (i < 10) {
            DiningMainYellow[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            DiningMainPink[i].setImage(image);
            DiningMainPink[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i++;
        }
        while (i < 10) {
            DiningMainPink[i].setVisible(false);
            i++;
        }

        profMap = gm.getArrayPlayers()[numMain].getDashboard().getProfMap();

        if (profMap.get(House.PINK)) {
            image = new Image("images/professors/teacher_pink.png");
            ProfPinkMain.setImage(image);
            ProfPinkMain.setVisible(true);
        } else {
            ProfPinkMain.setVisible(false);
        }
        if (profMap.get(House.GREEN)) {
            image = new Image("images/professors/teacher_green.png");
            ProfGreenMain.setImage(image);
            ProfGreenMain.setVisible(true);
        } else {
            ProfGreenMain.setVisible(false);
        }
        if (profMap.get(YELLOW)) {
            image = new Image("images/professors/teacher_yellow.png");
            ProfYellowMain.setImage(image);
            ProfYellowMain.setVisible(true);
        } else {
            ProfYellowMain.setVisible(false);
        }
        if (profMap.get(House.RED)) {
            image = new Image("images/professors/teacher_red.png");
            ProfRedMain.setImage(image);
            ProfRedMain.setVisible(true);
        } else {
            ProfRedMain.setVisible(false);
        }
        if (profMap.get(House.BLUE)) {
            image = new Image("images/professors/teacher_blue.png");
            ProfBlueMain.setImage(image);
            ProfBlueMain.setVisible(true);
        } else {
            ProfBlueMain.setVisible(false);
        }
    }

    private void initializeSecondPlayer() {
        initializeDashboardSecondPlayer();

        NameDashboard1.setText(gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getNickname());

        fillGraveyard(GraveyardDashboard1, gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()]);
        fillCardInUse(CardInUseDashboard1, gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()]);
    }

    private void initializeDashboardSecondPlayer() {
        int numStudents = 6;
        if (gm.getNumPlayers() == 3) {
            numStudents = 8;
        }
        int numTowers = 7;
        if (gm.getNumPlayers() == 3) {
            numTowers = 5;
        }
        Image image;
        ImageView[] EntranceDashboard1 = new ImageView[]{EntranceStudDashboard1Stud1, EntranceStudDashboard1Stud2, EntranceStudDashboard1Stud3, EntranceStudDashboard1Stud4, EntranceStudDashboard1Stud5, EntranceStudDashboard1Stud6, EntranceStudDashboard1Stud7, EntranceStudDashboard1Stud8, EntranceStudDashboard1Stud9};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getStudents());
        int i = numStudents;
        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i--;
        }
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i--;
        }
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i--;
        }
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i--;
        }
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i--;
        }
        while (i >= 0) {
            EntranceDashboard1[i].setVisible(false);
            i--;
        }

        initializeDiningHallSecondPlayer();

        ImageView[] TowersDashboard1 = new ImageView[]{Tower1Dashboard1, Tower2Dashboard1, Tower3Dashboard1, Tower4Dashboard1, Tower5Dashboard1, Tower6Dashboard1, Tower7Dashboard1, Tower8Dashboard1};

        int numTow = gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getNumTowersIn();
        Color colorTower = gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getTowerColor();
        String path;
        i = numTowers;

        if (colorTower == BLACK) {
            path = "images/towers/torre_nera.png";
        } else if (colorTower == Color.GRAY) {
            path = "images/towers/torre_grigia.png";
        } else {
            path = "images/towers/torre_bianca.png";
        }

        while (numTow > 0) {
            image = new Image(path);
            TowersDashboard1[i].setImage(image);
            TowersDashboard1[i].setVisible(true);
            i--;
            numTow--;
        }
        while (i >= 0) {
            TowersDashboard1[i].setVisible(false);
            i--;
        }
    }

    private void initializeDiningHallSecondPlayer() {
        Map<House, Boolean> profMap;

        Image image;

        ImageView[] DiningDashboard1Red = new ImageView[]{DiningRedStd1Dashboard1, DiningRedStd2Dashboard1, DiningRedStd3Dashboard1, DiningRedStd4Dashboard1, DiningRedStd5Dashboard1, DiningRedStd6Dashboard1, DiningRedStd7Dashboard1, DiningRedStd8Dashboard1, DiningRedStd9Dashboard1, DiningRedStd10Dashboard1};

        ImageView[] DiningDashboard1Green = new ImageView[]{DiningGreenStd1Dashboard1, DiningGreenStd2Dashboard1, DiningGreenStd3Dashboard1, DiningGreenStd4Dashboard1, DiningGreenStd5Dashboard1, DiningGreenStd6Dashboard1, DiningGreenStd7Dashboard1, DiningGreenStd8Dashboard1, DiningGreenStd9Dashboard1, DiningGreenStd10Dashboard1};

        ImageView[] DiningDashboard1Blue = new ImageView[]{DiningBlueStd1Dashboard1, DiningBlueStd2Dashboard1, DiningBlueStd3Dashboard1, DiningBlueStd4Dashboard1, DiningBlueStd5Dashboard1, DiningBlueStd6Dashboard1, DiningBlueStd7Dashboard1, DiningBlueStd8Dashboard1, DiningBlueStd9Dashboard1, DiningBlueStd10Dashboard1};

        ImageView[] DiningDashboard1Pink = new ImageView[]{DiningPinkStd1Dashboard1, DiningPinkStd2Dashboard1, DiningPinkStd3Dashboard1, DiningPinkStd4Dashboard1, DiningPinkStd5Dashboard1, DiningPinkStd6Dashboard1, DiningPinkStd7Dashboard1, DiningPinkStd8Dashboard1, DiningPinkStd9Dashboard1, DiningPinkStd10Dashboard1};

        ImageView[] DiningDashboard1Yellow = new ImageView[]{DiningYellowStd1Dashboard1, DiningYellowStd2Dashboard1, DiningYellowStd3Dashboard1, DiningYellowStd4Dashboard1, DiningYellowStd5Dashboard1, DiningYellowStd6Dashboard1, DiningYellowStd7Dashboard1, DiningYellowStd8Dashboard1, DiningYellowStd9Dashboard1, DiningYellowStd10Dashboard1};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getDiningHall().getStudents());
        int i = 0;
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            DiningDashboard1Green[i].setImage(image);
            DiningDashboard1Green[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard1Green[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            DiningDashboard1Blue[i].setImage(image);
            DiningDashboard1Blue[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard1Blue[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            DiningDashboard1Red[i].setImage(image);
            DiningDashboard1Red[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard1Red[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            DiningDashboard1Yellow[i].setImage(image);
            DiningDashboard1Yellow[i].setVisible(true);
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard1Yellow[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            DiningDashboard1Pink[i].setImage(image);
            DiningDashboard1Pink[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard1Pink[i].setVisible(false);
            i++;
        }

        profMap = gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getProfMap();

        if (profMap.get(House.PINK)) {
            image = new Image("images/professors/teacher_pink.png");
            ProfPinkDashboard1.setImage(image);
            ProfPinkDashboard1.setVisible(true);
        } else {
            ProfPinkDashboard1.setVisible(false);
        }
        if (profMap.get(House.GREEN)) {
            image = new Image("images/professors/teacher_green.png");
            ProfGreenDashboard1.setImage(image);
            ProfGreenDashboard1.setVisible(true);
        } else {
            ProfGreenDashboard1.setVisible(false);
        }
        if (profMap.get(YELLOW)) {
            image = new Image("images/professors/teacher_yellow.png");
            ProfYellowDashboard1.setImage(image);
            ProfYellowDashboard1.setVisible(true);
        } else {
            ProfYellowDashboard1.setVisible(false);
        }
        if (profMap.get(House.RED)) {
            image = new Image("images/professors/teacher_red.png");
            ProfRedDashboard1.setImage(image);
            ProfRedDashboard1.setVisible(true);
        } else {
            ProfRedDashboard1.setVisible(false);
        }
        if (profMap.get(House.BLUE)) {
            image = new Image("images/professors/teacher_blue.png");
            ProfBlueDashboard1.setImage(image);
            ProfBlueDashboard1.setVisible(true);
        } else {
            ProfBlueDashboard1.setVisible(false);
        }
    }

    private void initializeThirdPlayer() {

        NameDashboard2.setText(gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getNickname());

        fillGraveyard(GraveyardDashboard2, gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()]);
        fillCardInUse(CardInUseDashboard2, gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()]);

        initializeDashboardThirdPlayer();
    }

    private void initializeDashboardThirdPlayer() {
        int numStudents = 6;
        if (gm.getNumPlayers() == 3) {
            numStudents = 8;
        }
        int numTowers = 7;
        if (gm.getNumPlayers() == 3) {
            numTowers = 5;
        }
        Image image;
        ImageView[] EntranceDashboard2 = new ImageView[]{EntranceStudDashboard2Stud1, EntranceStudDashboard2Stud2, EntranceStudDashboard2Stud3, EntranceStudDashboard2Stud4, EntranceStudDashboard2Stud5, EntranceStudDashboard2Stud6, EntranceStudDashboard2Stud7, EntranceStudDashboard2Stud8, EntranceStudDashboard2Stud9};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getStudents());
        int i = numStudents;
        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            EntranceDashboard2[i].setImage(image);
            EntranceDashboard2[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i--;
        }
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            EntranceDashboard2[i].setImage(image);
            EntranceDashboard2[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i--;
        }
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            EntranceDashboard2[i].setImage(image);
            EntranceDashboard2[i].setVisible(true);
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i--;
        }
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            EntranceDashboard2[i].setImage(image);
            EntranceDashboard2[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i--;
        }
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            EntranceDashboard2[i].setImage(image);
            EntranceDashboard2[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i--;
        }
        while (i >= 0) {
            EntranceDashboard2[i].setVisible(false);
            i--;
        }

        initializeDiningHallThirdPlayer();

        ImageView[] TowersDashboard2 = new ImageView[]{Tower1Dashboard2, Tower2Dashboard2, Tower3Dashboard2, Tower4Dashboard2, Tower5Dashboard2, Tower6Dashboard2, Tower7Dashboard2, Tower8Dashboard2};

        int numTow = gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getNumTowersIn();
        Color colorTower = gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getTowerColor();
        i = numTowers;
        String path;

        if (colorTower == BLACK) {
            path = "images/towers/torre_nera.png";
        } else if (colorTower == Color.GRAY) {
            path = "images/towers/torre_grigia.png";
        } else {
            path = "images/towers/torre_bianca.png";
        }

        while (numTow > 0) {
            image = new Image(path);
            TowersDashboard2[i].setImage(image);
            TowersDashboard2[i].setVisible(true);
            i--;
            numTow--;
        }
        while (i >= 0) {
            TowersDashboard2[i].setVisible(false);
            i--;
        }
    }

    private void initializeDiningHallThirdPlayer() {
        Map<House, Boolean> profMap;
        Image image;

        ImageView[] DiningDashboard2Red = new ImageView[]{DiningRedStd1Dashboard2, DiningRedStd2Dashboard2, DiningRedStd3Dashboard2, DiningRedStd4Dashboard2, DiningRedStd5Dashboard2, DiningRedStd6Dashboard2, DiningRedStd7Dashboard2, DiningRedStd8Dashboard2, DiningRedStd9Dashboard2, DiningRedStd10Dashboard2};

        ImageView[] DiningDashboard2Green = new ImageView[]{DiningGreenStd1Dashboard2, DiningGreenStd2Dashboard2, DiningGreenStd3Dashboard2, DiningGreenStd4Dashboard2, DiningGreenStd5Dashboard2, DiningGreenStd6Dashboard2, DiningGreenStd7Dashboard2, DiningGreenStd8Dashboard2, DiningGreenStd9Dashboard2, DiningGreenStd10Dashboard2};

        ImageView[] DiningDashboard2Blue = new ImageView[]{DiningBlueStd1Dashboard2, DiningBlueStd2Dashboard2, DiningBlueStd3Dashboard2, DiningBlueStd4Dashboard2, DiningBlueStd5Dashboard2, DiningBlueStd6Dashboard2, DiningBlueStd7Dashboard2, DiningBlueStd8Dashboard2, DiningBlueStd9Dashboard2, DiningBlueStd10Dashboard2};

        ImageView[] DiningDashboard2Pink = new ImageView[]{DiningPinkStd1Dashboard2, DiningPinkStd2Dashboard2, DiningPinkStd3Dashboard2, DiningPinkStd4Dashboard2, DiningPinkStd5Dashboard2, DiningPinkStd6Dashboard2, DiningPinkStd7Dashboard2, DiningPinkStd8Dashboard2, DiningPinkStd9Dashboard2, DiningPinkStd10Dashboard2};

        ImageView[] DiningDashboard2Yellow = new ImageView[]{DiningYellowStd1Dashboard2, DiningYellowStd2Dashboard2, DiningYellowStd3Dashboard2, DiningYellowStd4Dashboard2, DiningYellowStd5Dashboard2, DiningYellowStd6Dashboard2, DiningYellowStd7Dashboard2, DiningYellowStd8Dashboard2, DiningYellowStd9Dashboard2, DiningYellowStd10Dashboard2};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getDiningHall().getStudents());
        int i = 0;
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            DiningDashboard2Green[i].setImage(image);
            DiningDashboard2Green[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard2Green[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            DiningDashboard2Blue[i].setImage(image);
            DiningDashboard2Blue[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard2Blue[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            DiningDashboard2Red[i].setImage(image);
            DiningDashboard2Red[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard2Red[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            DiningDashboard2Yellow[i].setImage(image);
            DiningDashboard2Yellow[i].setVisible(true);
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard2Yellow[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            DiningDashboard2Pink[i].setImage(image);
            DiningDashboard2Pink[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard2Pink[i].setVisible(false);
            i++;
        }

        profMap = gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getProfMap();

        if (profMap.get(House.PINK)) {
            image = new Image("images/professors/teacher_pink.png");
            ProfPinkDashboard2.setImage(image);
            ProfPinkDashboard2.setVisible(true);
        } else {
            ProfPinkDashboard2.setVisible(false);
        }
        if (profMap.get(House.GREEN)) {
            image = new Image("images/professors/teacher_pink.png");
            ProfGreenDashboard2.setImage(image);
            ProfGreenDashboard2.setVisible(true);
        } else {
            ProfGreenDashboard2.setVisible(false);
        }
        if (profMap.get(YELLOW)) {
            image = new Image("images/professors/teacher_yellow.png");
            ProfYellowDashboard2.setImage(image);
            ProfYellowDashboard2.setVisible(true);
        } else {
            ProfYellowDashboard2.setVisible(false);
        }
        if (profMap.get(House.RED)) {
            image = new Image("images/professors/teacher_red.png");
            ProfRedDashboard2.setImage(image);
            ProfRedDashboard2.setVisible(true);
        } else {
            ProfRedDashboard2.setVisible(false);
        }
        if (profMap.get(House.BLUE)) {
            image = new Image("images/professors/teacher_blue.png");
            ProfBlueDashboard2.setImage(image);
            ProfBlueDashboard2.setVisible(true);
        } else {
            ProfBlueDashboard2.setVisible(false);
        }
    }

    private void initializeFourthPlayer() {

        NameDashboard3.setText(gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getNickname());

        fillGraveyard(GraveyardDashboard3, gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()]);
        fillCardInUse(CardInUseDashboard3, gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()]);

        initializeDashboardFourthPlayer();
    }

    private void initializeDashboardFourthPlayer() {
        int numStudents = 6;
        if (gm.getNumPlayers() == 3) {
            numStudents = 8;
        }
        int numTowers = 7;
        if (gm.getNumPlayers() == 3) {
            numTowers = 5;
        }
        Image image;
        ImageView[] EntranceDashboard3 = new ImageView[]{EntranceStudDashboard3Stud1, EntranceStudDashboard3Stud2, EntranceStudDashboard3Stud3, EntranceStudDashboard3Stud4, EntranceStudDashboard3Stud5, EntranceStudDashboard3Stud6, EntranceStudDashboard3Stud7, EntranceStudDashboard3Stud8, EntranceStudDashboard3Stud9};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getStudents());
        int i = numStudents;
        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            EntranceDashboard3[i].setImage(image);
            EntranceDashboard3[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i--;
        }
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            EntranceDashboard3[i].setImage(image);
            EntranceDashboard3[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i--;
        }
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            EntranceDashboard3[i].setImage(image);
            EntranceDashboard3[i].setVisible(true);
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i--;
        }
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            EntranceDashboard3[i].setImage(image);
            EntranceDashboard3[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i--;
        }
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            EntranceDashboard3[i].setImage(image);
            EntranceDashboard3[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i--;
        }
        while (i >= 0) {
            EntranceDashboard3[i].setVisible(false);
            i--;
        }

        initializeDiningHallFourthPlayer();

        ImageView[] TowersDashboard3 = new ImageView[]{Tower1Dashboard3, Tower2Dashboard3, Tower3Dashboard3, Tower4Dashboard3, Tower5Dashboard3, Tower6Dashboard3, Tower7Dashboard3, Tower8Dashboard3};

        int numTow = gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getNumTowersIn();
        Color colorTower = gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getTowerColor();
        i = numTowers;

        String path;
        if (colorTower == BLACK) {
            path = "images/towers/torre_nera.png";
        } else if (colorTower == Color.GRAY) {
            path = "images/towers/torre_grigia.png";
        } else {
            path = "images/towers/torre_bianca.png";
        }

        while (numTow > 0) {
            image = new Image(path);
            TowersDashboard3[i].setImage(image);
            TowersDashboard3[i].setVisible(true);
            i--;
            numTow--;
        }
        while (i >= 0) {
            TowersDashboard3[i].setVisible(false);
            i--;
        }
    }

    private void initializeDiningHallFourthPlayer() {
        Map<House, Boolean> profMap;
        Image image;

        ImageView[] DiningDashboard3Red = new ImageView[]{DiningRedStd1Dashboard3, DiningRedStd2Dashboard3, DiningRedStd3Dashboard3, DiningRedStd4Dashboard3, DiningRedStd5Dashboard3, DiningRedStd6Dashboard3, DiningRedStd7Dashboard3, DiningRedStd8Dashboard3, DiningRedStd9Dashboard3, DiningRedStd10Dashboard3};

        ImageView[] DiningDashboard3Green = new ImageView[]{DiningGreenStd1Dashboard3, DiningGreenStd2Dashboard3, DiningGreenStd3Dashboard3, DiningGreenStd4Dashboard3, DiningGreenStd5Dashboard3, DiningGreenStd6Dashboard3, DiningGreenStd7Dashboard3, DiningGreenStd8Dashboard3, DiningGreenStd9Dashboard3, DiningGreenStd10Dashboard3};

        ImageView[] DiningDashboard3Blue = new ImageView[]{DiningBlueStd1Dashboard3, DiningBlueStd2Dashboard3, DiningBlueStd3Dashboard3, DiningBlueStd4Dashboard3, DiningBlueStd5Dashboard3, DiningBlueStd6Dashboard3, DiningBlueStd7Dashboard3, DiningBlueStd8Dashboard3, DiningBlueStd9Dashboard3, DiningBlueStd10Dashboard3};

        ImageView[] DiningDashboard3Pink = new ImageView[]{DiningPinkStd1Dashboard3, DiningPinkStd2Dashboard3, DiningPinkStd3Dashboard3, DiningPinkStd4Dashboard3, DiningPinkStd5Dashboard3, DiningPinkStd6Dashboard3, DiningPinkStd7Dashboard3, DiningPinkStd8Dashboard3, DiningPinkStd9Dashboard3, DiningPinkStd10Dashboard3};

        ImageView[] DiningDashboard3Yellow = new ImageView[]{DiningYellowStd1Dashboard3, DiningYellowStd2Dashboard3, DiningYellowStd3Dashboard3, DiningYellowStd4Dashboard3, DiningYellowStd5Dashboard3, DiningYellowStd6Dashboard3, DiningYellowStd7Dashboard3, DiningYellowStd8Dashboard3, DiningYellowStd9Dashboard3, DiningYellowStd10Dashboard3};

        Map<House, Integer> houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getDiningHall().getStudents());
        int i = 0;
        while (houseMap.get(House.GREEN) != 0) {
            image = new Image("images/students/student_green.png");
            DiningDashboard3Green[i].setImage(image);
            DiningDashboard3Green[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard3Green[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.BLUE) != 0) {
            image = new Image("images/students/student_blue.png");
            DiningDashboard3Blue[i].setImage(image);
            DiningDashboard3Blue[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard3Blue[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.RED) != 0) {
            image = new Image("images/students/student_red.png");
            DiningDashboard3Red[i].setImage(image);
            DiningDashboard3Red[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard3Red[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(YELLOW) != 0) {
            image = new Image("images/students/student_yellow.png");
            DiningDashboard3Yellow[i].setImage(image);
            DiningDashboard3Yellow[i].setVisible(true);
            houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard3Yellow[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.PINK) != 0) {
            image = new Image("images/students/student_pink.png");
            DiningDashboard3Pink[i].setImage(image);
            DiningDashboard3Pink[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i++;
        }
        while (i < 10) {
            DiningDashboard3Pink[i].setVisible(false);
            i++;
        }

        profMap = gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getProfMap();

        if (profMap.get(House.PINK)) {
            image = new Image("images/professors/teacher_pink.png");
            ProfPinkDashboard3.setImage(image);
            ProfPinkDashboard3.setVisible(true);
        } else {
            ProfPinkDashboard3.setVisible(false);
        }
        if (profMap.get(House.GREEN)) {
            image = new Image("images/professors/teacher_green.png");
            ProfGreenDashboard3.setImage(image);
            ProfGreenDashboard3.setVisible(true);
        } else {
            ProfGreenDashboard3.setVisible(false);
        }
        if (profMap.get(YELLOW)) {
            image = new Image("images/professors/teacher_yellow.png");
            ProfYellowDashboard3.setImage(image);
            ProfYellowDashboard3.setVisible(true);
        } else {
            ProfYellowDashboard3.setVisible(false);
        }
        if (profMap.get(House.RED)) {
            image = new Image("images/professors/teacher_red.png");
            ProfRedDashboard3.setImage(image);
            ProfRedDashboard3.setVisible(true);
        } else {
            ProfRedDashboard3.setVisible(false);
        }
        if (profMap.get(House.BLUE)) {
            image = new Image("images/professors/teacher_blue.png");
            ProfBlueDashboard3.setImage(image);
            ProfBlueDashboard3.setVisible(true);
        } else {
            ProfBlueDashboard3.setVisible(false);
        }
    }

    private void initializeIslands() {

        islandsImageView = new ImageView[]{ImgIsland0, ImgIsland1, ImgIsland2, ImgIsland3, ImgIsland4, ImgIsland5,
                ImgIsland6, ImgIsland7, ImgIsland8, ImgIsland9, ImgIsland10, ImgIsland11};

        int i;

        i = gm.getIslandList().size();

        while (i < 12) {
            islandList.get(i).get("Pane").setVisible(false);
            islandList.get(i).get("Pane").setDisable(true);
            i++;
        }

        i = 0;
        Map<House, Integer> houseMap;
        Image image;

        islandButtons = new Button[gm.getIslandList().size()];

        while (i < gm.getIslandList().size()) {
            houseMap = new HashMap<>(gm.getIslandList().get(i).getStudents());

            List<Node> list = descriptionArray[i].getChildren().sorted();

            if (!gm.isExpertMode()) {
                list.get(0).setVisible(false);
            } else {
                ((Text) list.get(8)).setText("x" + gm.getIslandList().get(i).getNoEntryTile());
            }

            islandList.get(i).get("Green").setVisible(houseMap.get(GREEN) != 0);
            ((Text) list.get(7)).setText("x" + houseMap.get(GREEN));

            islandList.get(i).get("Red").setVisible(houseMap.get(RED) != 0);
            ((Text) list.get(10)).setText("x" + houseMap.get(RED));

            islandList.get(i).get("Yellow").setVisible(houseMap.get(YELLOW) != 0);
            ((Text) list.get(11)).setText("x" + houseMap.get(YELLOW));

            islandList.get(i).get("Pink").setVisible(houseMap.get(PINK) != 0);
            ((Text) list.get(9)).setText("x" + houseMap.get(PINK));

            islandList.get(i).get("Blue").setVisible(houseMap.get(BLUE) != 0);
            ((Text) list.get(6)).setText("x" + houseMap.get(BLUE));

            try {
                if (gm.getIslandList().get(i).getColorTower() != null) {
                    Text towerNumber = (Text) islandList.get(i).get("TowerNumber");
                    ImageView tower = (ImageView) islandList.get(i).get("Tower");
                    towerNumber.setText(Integer.toString(gm.getIslandList().get(i).getNumTowers()));

                    if (gm.getIslandList().get(i).getColorTower() == BLACK) {
                        image = new Image("images/towers/black_tower.png");
                        tower.setImage(image);
                    }
                    if (gm.getIslandList().get(i).getColorTower() == GRAY) {
                        image = new Image("images/towers/gray_tower.png");
                        tower.setImage(image);
                    }
                    if (gm.getIslandList().get(i).getColorTower() == WHITE) {
                        image = new Image("images/towers/white_tower.png");
                        tower.setImage(image);
                        towerNumber.setStyle("-fx-text-fill: #463333;");
                    }
                }
            } catch (IslandException ignored) {
            }

            ImageView noEntryTile = (ImageView) islandList.get(i).get("NoEntryTile");
            if (gm.isExpertMode() && gm.getIslandList().get(i).getNoEntryTile() != 0) {
                    noEntryTile.setVisible(true);
            } else {
                noEntryTile.setVisible(false);
            }


            ImageView mother = (ImageView) islandList.get(i).get("Mother");
            mother.setVisible(true);
            if (gm.getMotherIsland() != i) {
                mother.setVisible(false);
            }
            islandButtons[i] = getButtonByIsland(i);
            i++;
        }
    }

    public void initializeClouds() {

        cloudsButtons = new ArrayList<>();

        int numClouds = 0;

        switch (gm.getNumPlayers()) {
            case 2:
                numClouds = 2;
                break;
            case 3:
                numClouds = 3;
                break;
            case 4:
                numClouds = 4;
                break;
        }

        for (int c = 0; c < numClouds; c++) {
            if (gm.getArrayClouds()[c].isFull())
                cloudsButtons.add(getButtonByCloud(c));
        }

        Image image;
        Map<House, Integer> houseMap;
        if (gm.getNumPlayers() == 3) {
            Cloud3Num1.setVisible(false);
            Cloud3Num2.setVisible(false);
            Cloud3Num3.setVisible(false);
            Cloud3Num4.setVisible(false);

            ImageView[] Cloud1 = {Stud1Cloud4Num1, Stud2Cloud4Num1, Stud3Cloud4Num1, Stud4Cloud4Num1};
            for (ImageView i : Cloud1) {
                i.setVisible(false);
            }

            ImageView[] Cloud2 = {Stud1Cloud4Num2, Stud2Cloud4Num2, Stud3Cloud4Num2, Stud4Cloud4Num2};
            for (ImageView i : Cloud2) {
                i.setVisible(false);
            }

            ImageView[] Cloud3 = new ImageView[]{Stud1Cloud4Num3, Stud2Cloud4Num3, Stud3Cloud4Num3, Stud4Cloud4Num3};
            for (ImageView i : Cloud3) {
                i.setVisible(false);
            }

            houseMap = new HashMap<>(gm.getArrayClouds()[0].getStudents());

            int i = 0;
            while (houseMap.get(House.PINK) != 0) {
                image = new Image("images/students/student_pink.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                i++;
            }
            while (houseMap.get(House.GREEN) != 0) {
                image = new Image("images/students/student_green.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                i++;
            }
            while (houseMap.get(YELLOW) != 0) {
                image = new Image("images/students/student_yellow.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
                i++;
            }
            while (houseMap.get(House.RED) != 0) {
                image = new Image("images/students/student_red.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                i++;
            }
            while (houseMap.get(House.BLUE) != 0) {
                image = new Image("images/students/student_blue.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                i++;
            }

            houseMap = new HashMap<>(gm.getArrayClouds()[1].getStudents());
            i = 0;
            while (houseMap.get(House.PINK) != 0) {
                image = new Image("images/students/student_pink.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                i++;
            }
            while (houseMap.get(House.GREEN) != 0) {
                image = new Image("images/students/student_green.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                i++;
            }
            while (houseMap.get(YELLOW) != 0) {
                image = new Image("images/students/student_yellow.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
                i++;
            }
            while (houseMap.get(House.RED) != 0) {
                image = new Image("images/students/student_red.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                i++;
            }
            while (houseMap.get(House.BLUE) != 0) {
                image = new Image("images/students/student_blue.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                i++;
            }

            houseMap = new HashMap<>(gm.getArrayClouds()[2].getStudents());

            i = 0;
            while (houseMap.get(House.PINK) != 0) {
                image = new Image("images/students/student_pink.png");
                Cloud3[i].setImage(image);
                Cloud3[i].setVisible(true);
                houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                i++;
            }
            while (houseMap.get(House.GREEN) != 0) {
                image = new Image("images/students/student_green.png");
                Cloud3[i].setImage(image);
                Cloud3[i].setVisible(true);
                houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                i++;
            }
            while (houseMap.get(YELLOW) != 0) {
                image = new Image("images/students/student_yellow.png");
                Cloud3[i].setImage(image);
                Cloud3[i].setVisible(true);
                houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
                i++;
            }
            while (houseMap.get(House.RED) != 0) {
                image = new Image("images/students/student_red.png");
                Cloud3[i].setImage(image);
                Cloud3[i].setVisible(true);
                houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                i++;
            }
            while (houseMap.get(House.BLUE) != 0) {
                image = new Image("images/students/student_blue.png");
                Cloud3[i].setImage(image);
                Cloud3[i].setVisible(true);
                houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                i++;
            }
        } else {
            Cloud4Num1.setVisible(false);
            Cloud4Num2.setVisible(false);
            Cloud4Num3.setVisible(false);

            ImageView[] Cloud1 = new ImageView[]{Stud1Cloud3Num1, Stud2Cloud3Num1, Stud3Cloud3Num1};
            for (ImageView i : Cloud1) {
                i.setVisible(false);
            }

            ImageView[] Cloud2 = new ImageView[]{Stud1Cloud3Num2, Stud2Cloud3Num2, Stud3Cloud3Num2};
            for (ImageView i : Cloud2) {
                i.setVisible(false);
            }

            houseMap = new HashMap<>(gm.getArrayClouds()[0].getStudents());
            int i = 0;
            while (houseMap.get(House.PINK) != 0) {
                image = new Image("images/students/student_pink.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                i++;
            }
            while (houseMap.get(House.GREEN) != 0) {
                image = new Image("images/students/student_green.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                i++;
            }
            while (houseMap.get(YELLOW) != 0) {
                image = new Image("images/students/student_yellow.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
                i++;
            }
            while (houseMap.get(House.RED) != 0) {
                image = new Image("images/students/student_red.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                i++;
            }
            while (houseMap.get(House.BLUE) != 0) {
                image = new Image("images/students/student_blue.png");
                Cloud1[i].setImage(image);
                Cloud1[i].setVisible(true);
                houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                i++;
            }

            houseMap = new HashMap<>(gm.getArrayClouds()[1].getStudents());
            i = 0;
            while (houseMap.get(House.PINK) != 0) {
                image = new Image("images/students/student_pink.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                i++;
            }
            while (houseMap.get(House.GREEN) != 0) {
                image = new Image("images/students/student_green.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                i++;
            }
            while (houseMap.get(YELLOW) != 0) {
                image = new Image("images/students/student_yellow.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
                i++;
            }
            while (houseMap.get(House.RED) != 0) {
                image = new Image("images/students/student_red.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                i++;
            }
            while (houseMap.get(House.BLUE) != 0) {
                image = new Image("images/students/student_blue.png");
                Cloud2[i].setImage(image);
                Cloud2[i].setVisible(true);
                houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                i++;
            }

            if (gm.getNumPlayers() == 4) {
                ImageView[] Cloud3 = new ImageView[]{Stud1Cloud3Num3, Stud2Cloud3Num3, Stud3Cloud3Num3};
                for (ImageView img : Cloud3) {
                    img.setVisible(false);
                }

                ImageView[] Cloud4 = new ImageView[]{Stud1Cloud3Num4, Stud2Cloud3Num4, Stud3Cloud3Num4};
                for (ImageView img : Cloud1) {
                    img.setVisible(false);
                }

                houseMap = new HashMap<>(gm.getArrayClouds()[2].getStudents());
                i = 0;
                while (houseMap.get(House.PINK) != 0) {
                    image = new Image("images/students/student_pink.png");
                    Cloud3[i].setImage(image);
                    Cloud3[i].setVisible(true);
                    houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                    i++;
                }
                while (houseMap.get(House.GREEN) != 0) {
                    image = new Image("images/students/student_green.png");
                    Cloud3[i].setImage(image);
                    Cloud3[i].setVisible(true);
                    houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                    i++;
                }
                while (houseMap.get(YELLOW) != 0) {
                    image = new Image("images/students/student_yellow.png");
                    Cloud3[i].setImage(image);
                    Cloud3[i].setVisible(true);
                    houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
                    i++;
                }
                while (houseMap.get(House.RED) != 0) {
                    image = new Image("images/students/student_red.png");
                    Cloud3[i].setImage(image);
                    Cloud3[i].setVisible(true);
                    houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                    i++;
                }
                while (houseMap.get(House.BLUE) != 0) {
                    image = new Image("images/students/student_blue.png");
                    Cloud3[i].setImage(image);
                    Cloud3[i].setVisible(true);
                    houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                    i++;
                }

                houseMap = new HashMap<>(gm.getArrayClouds()[3].getStudents());
                i = 0;
                while (houseMap.get(House.PINK) != 0) {
                    image = new Image("images/students/student_pink.png");
                    Cloud4[i].setImage(image);
                    Cloud4[i].setVisible(true);
                    houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                    i++;
                }
                while (houseMap.get(House.GREEN) != 0) {
                    image = new Image("images/students/student_green.png");
                    Cloud4[i].setImage(image);
                    Cloud4[i].setVisible(true);
                    houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                    i++;
                }
                while (houseMap.get(YELLOW) != 0) {
                    image = new Image("images/students/student_yellow.png");
                    Cloud4[i].setImage(image);
                    Cloud4[i].setVisible(true);
                    houseMap.replace(YELLOW, houseMap.get(YELLOW) - 1);
                    i++;
                }
                while (houseMap.get(House.RED) != 0) {
                    image = new Image("images/students/student_red.png");
                    Cloud4[i].setImage(image);
                    Cloud4[i].setVisible(true);
                    houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                    i++;
                }
                while (houseMap.get(House.BLUE) != 0) {
                    image = new Image("images/students/student_blue.png");
                    Cloud4[i].setImage(image);
                    Cloud4[i].setVisible(true);
                    houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                    i++;
                }
            } else {
                Cloud3Num3.setVisible(false);
                Cloud3Num4.setVisible(false);
                Cloud3Num3.setDisable(true);
                Cloud3Num4.setDisable(true);
            }

        }
    }

    private void initializeChat(String msg) {
        ReducedPlayer teamMate = null;
        for (ReducedPlayer p : gm.getArrayPlayers()) {
            if (p.getDashboard().getTowerColor().equals(gm.getPlayerByNickname(nickname).getDashboard().getTowerColor()) && !p.getNickname().equals(nickname)) {
                teamMate = p;
                break;
            }
        }
        NoMessageText.setText("Message from " + (teamMate == null ? "your team mate" : teamMate.getNickname()));
        ChatPane.setVisible(true);
        ChatReceivedText.setText(msg);
    }

    public void setGameModel(ReducedGameModel gm) {
        this.gm = gm;
    }

    // <--------- Update methods --------->
    public void updateIslands() {
        initializeIslands();
    }

    public void updateDiningHall(ReducedDiningHall diningHall) {
        int numPlayer = determinePlayer(gm.getPlayerByNickname(diningHall.getNickname()));

        switch (numPlayer) {
            case 1:
                initializeDiningHallMain();
                break;
            case 2:
                initializeDiningHallSecondPlayer();
                break;
            case 3:
                initializeDiningHallThirdPlayer();
                break;
            case 4:
                initializeDiningHallFourthPlayer();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + numPlayer);
        }
    }

    public void updateDashboard(ReducedDashboard dashboard) {
        int numPlayer = determinePlayer(gm.getPlayerByNickname(dashboard.getNickname()));

        switch (numPlayer) {
            case 1:
                initializeDashboardMain();
                break;
            case 2:
                initializeDashboardSecondPlayer();
                break;
            case 3:
                initializeDashboardThirdPlayer();
                break;
            case 4:
                initializeDashboardFourthPlayer();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + numPlayer);
        }
    }

    public void updatePlayer(ReducedPlayer player) {
        int numPlayer = determinePlayer(gm.getPlayerByNickname(player.getNickname()));

        switch (numPlayer) {
            case 1:
                initializeMainPlayer();
                break;
            case 2:
                initializeSecondPlayer();
                break;
            case 3:
                initializeThirdPlayer();
                break;
            case 4:
                initializeFourthPlayer();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + numPlayer);
        }
    }

    public void updateClouds() {
        initializeClouds();
    }

    public void updateMotherNature() {
        initializeIslands();
    }

    public void updateCurrentPlayer(ReducedPlayer currentPlayer) {
        updatePlayer(currentPlayer);
    }

    public void updateChat(String msg) {
        initializeChat(msg);
    }

    private int determinePlayer(ReducedPlayer player) {
        int numPlayer = Arrays.asList(gm.getArrayPlayers()).indexOf(player);

        int numSecond = (numMain + 1) % gm.getNumPlayers();
        int numThird = (numMain + 2) % gm.getNumPlayers();
        int numFourth = (numMain + 3) % gm.getNumPlayers();

        if (numPlayer == numMain) {
            return 1;
        } else if (numPlayer == numSecond) {
            return 2;
        } else if (numPlayer == numThird) {
            return 3;
        } else if (numPlayer == numFourth) {
            return 4;
        }
        return 0;
    }

    public void initialize() {

        this.diningHallMain = new Button[]{diningHallMainGreen, diningHallMainRed, diningHallMainYellow, diningHallMainPink, diningHallMainBlue};

        this.characterCards = new ImageView[]{CharacterCard1, CharacterCard2, CharacterCard3};

        Map<House, Integer> houseMap;

        descriptionArray = new GridPane[]{DescriptionIsland00, DescriptionIsland01, DescriptionIsland02, DescriptionIsland03, DescriptionIsland04, DescriptionIsland05, DescriptionIsland06, DescriptionIsland07, DescriptionIsland08, DescriptionIsland09, DescriptionIsland10, DescriptionIsland11};

        int i;

        Image image;

        //first player
        initializeMainPlayer();

        //second player
        initializeSecondPlayer();

        if (gm.getNumPlayers() == 2) { //show of hid dashboards
            Dashboard2.setVisible(false);
            Dashboard3.setVisible(false);
            Chat.setVisible(false);
            Chat.setDisable(true);
            ChatAlternative.setVisible(true);
            TwoDashboardAlternative.setVisible(true);
        } else if (gm.getNumPlayers() > 2) {
            //third player
            initializeDashboardThirdPlayer();


            if (gm.getNumPlayers() == 4) {
                if (!gm.isChat()) {
                    Chat.setVisible(false);
                    Chat.setDisable(true);
                    ChatAlternative.setVisible(true);
                } else {
                    ChatSendBtn.setDisable(true);
                    ChatPane.setVisible(false);
                    ChatText.setOnKeyTyped(this::onChatTextTyped);
                    ChatSendBtn.setOnAction(this::onChatSendBtnClick);
                }
                //fourth player
                initializeDashboardFourthPlayer();
            } else {
                Dashboard3.setVisible(false);
                Chat.setDisable(true);
                Chat.setVisible(false);
                ChatAlternative.setVisible(true);
                OneDashboardAlternative.setVisible(true);
            }
        }
        if (!gm.isExpertMode()) {
            CharacterCardsPlacement.setVisible(false);
            CharacterCardAlternative.setVisible(true);
        } else {
            CharacterCardsPlacement.setVisible(true);
            CostCharacterCard1.setText(Integer.toString(gm.getCharacterCardDeck()[0].getCost()));
            CostCharacterCard2.setText(Integer.toString(gm.getCharacterCardDeck()[1].getCost()));
            CostCharacterCard3.setText(Integer.toString(gm.getCharacterCardDeck()[2].getCost()));

            ImageView[] Card1Arr = new ImageView[]{Card1Elem1, Card1Elem2, Card1Elem3, Card1Elem4, Card1Elem5, Card1Elem6};
            ImageView[] Card2Arr = new ImageView[]{Card2Elem1, Card2Elem2, Card2Elem3, Card2Elem4, Card2Elem5, Card2Elem6};
            ImageView[] Card3Arr = new ImageView[]{Card3Elem1, Card3Elem2, Card3Elem3, Card3Elem4, Card3Elem5, Card3Elem6};

            card1Array = new String[6];
            card2Array = new String[6];
            card3Array = new String[6];

            String path = null;
            switch (gm.getCharacterCardDeck()[0].getType()) {
                case FARMER:
                    path = "images/CharacterCards/Farmer.jpg";
                    break;
                case MAGICAL_MAILMAN:
                    path = "images/CharacterCards/MagicalMailman.jpg";
                    break;
                case HERB_GRANMA:
                    path = "images/CharacterCards/HerbGranma.jpg";

                    int numNoEntry = gm.getCharacterCardDeck()[0].getNoEntryTile();
                    image = new Image("images/Clouds/NoEntryTileBackup.png");
                    i = 0;
                    while (numNoEntry > 0) {
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "NoEntryTile";
                        numNoEntry--;
                        i++;
                    }
                    while (i < 6) {
                        Card1Arr[i].setVisible(false);
                        Card1Arr[i].setDisable(true);
                        card1Array[i] = "null";
                        i++;
                    }
                    break;
                case MINSTREL:
                    path = "images/CharacterCards/Minstrel.jpg";
                    break;
                case MONK:
                    path = "images/CharacterCards/Monk.jpg";
                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[0].getHouseMap());
                    i = 0;
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }
                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }

                    while (i < 6) {
                        Card1Arr[i].setVisible(false);
                        Card1Arr[i].setDisable(true);
                        card1Array[i] = "null";
                        i++;
                    }
                    break;
                case HERALD:
                    path = "images/CharacterCards/Herald.jpg";
                    break;
                case CENTAUR:
                    path = "images/CharacterCards/Centaur.jpg";
                    break;
                case JOLLY:
                    path = "images/CharacterCards/Jolly.jpg";

                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[0].getHouseMap());
                    i = 0;
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }

                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }

                    while (i < 6) {
                        Card1Arr[i].setVisible(false);
                        Card1Arr[i].setDisable(true);
                        card1Array[i] = "null";
                        i++;
                    }
                    break;
                case KNIGHT:
                    path = "images/CharacterCards/Knight.jpg";
                    break;
                case MUSHROOM_HUNTER:
                    path = "images/CharacterCards/MushroomHunter.jpg";
                    image = new Image("images/students/student_blue.png");
                    Card1Arr[4].setImage(image);
                    Card1Arr[4].setVisible(true);
                    Card1Arr[4].setDisable(false);
                    card1Array[4] = "Blue";

                    image = new Image("images/students/student_pink.png");
                    Card1Arr[3].setImage(image);
                    Card1Arr[3].setVisible(true);
                    Card1Arr[3].setDisable(false);
                    card1Array[3] = "Pink";

                    image = new Image("images/students/student_yellow.png");
                    Card1Arr[2].setImage(image);
                    Card1Arr[2].setVisible(true);
                    Card1Arr[2].setDisable(false);
                    card1Array[2] = "Yellow";

                    image = new Image("images/students/student_red.png");
                    Card1Arr[1].setImage(image);
                    Card1Arr[1].setVisible(true);
                    Card1Arr[1].setDisable(false);
                    card1Array[1] = "Red";

                    image = new Image("images/students/student_green.png");
                    Card1Arr[0].setImage(image);
                    Card1Arr[0].setVisible(true);
                    Card1Arr[0].setDisable(false);
                    card1Array[0] = "Green";
                    i = 5;
                    while (i < 6) {
                        Card1Arr[i].setVisible(false);
                        Card1Arr[i].setDisable(true);
                        card1Array[i] = "null";
                        i++;
                    }
                    break;
                case SPOILED_PRINCESS:
                    path = "images/CharacterCards/SpoiledPrincess.jpg";

                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[0].getHouseMap());
                    i = 0;
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }
                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card1Arr[i].setImage(image);
                        Card1Arr[i].setVisible(true);
                        Card1Arr[i].setDisable(false);
                        card1Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }
                    while (i < 6) {
                        Card1Arr[i].setVisible(false);
                        Card1Arr[i].setDisable(true);
                        card1Array[i] = "null";
                        i++;
                    }
                    break;
                case THIEF:
                    path = "images/CharacterCards/Thief.jpg";

                    image = new Image("images/students/student_green.png");
                    Card1Arr[0].setImage(image);
                    Card1Arr[0].setVisible(true);
                    Card1Arr[0].setDisable(false);
                    card1Array[0] = "Green";

                    image = new Image("images/students/student_red.png");
                    Card1Arr[1].setImage(image);
                    Card1Arr[1].setVisible(true);
                    Card1Arr[1].setDisable(false);
                    card1Array[1] = "Red";

                    image = new Image("images/students/student_yellow.png");
                    Card1Arr[2].setImage(image);
                    Card1Arr[2].setVisible(true);
                    Card1Arr[2].setDisable(false);
                    card1Array[2] = "Yellow";

                    image = new Image("images/students/student_pink.png");
                    Card1Arr[3].setImage(image);
                    Card1Arr[3].setVisible(true);
                    Card1Arr[3].setDisable(false);
                    card1Array[3] = "Pink";

                    image = new Image("images/students/student_blue.png");
                    Card1Arr[4].setImage(image);
                    Card1Arr[4].setVisible(true);
                    Card1Arr[4].setDisable(false);
                    card1Array[4] = "Blue";

                    i = 5;
                    while (i < 6) {
                        Card1Arr[i].setVisible(false);
                        Card1Arr[i].setDisable(true);
                        card1Array[i] = "null";
                        i++;
                    }
                    break;
            }
            image = new Image(path);

            CharacterCard1.setImage(image);

            switch (gm.getCharacterCardDeck()[1].getType()) {
                case FARMER:
                    path = "images/CharacterCards/Farmer.jpg";
                    break;
                case MAGICAL_MAILMAN:
                    path = "images/CharacterCards/MagicalMailman.jpg";
                    break;
                case HERB_GRANMA:
                    path = "images/CharacterCards/HerbGranma.jpg";

                    int numNoEntry = gm.getCharacterCardDeck()[1].getNoEntryTile();
                    image = new Image("images/Clouds/NoEntryTileBackup.png");
                    i = 0;
                    while (numNoEntry > 0) {
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "NoEntryTile";
                        numNoEntry--;
                        i++;
                    }
                    while (i < 6) {
                        Card2Arr[i].setVisible(false);
                        Card2Arr[i].setDisable(true);
                        card2Array[i] = "null";
                        i++;
                    }
                    break;
                case MINSTREL:
                    path = "images/CharacterCards/Minstrel.jpg";
                    break;
                case MONK:
                    path = "images/CharacterCards/Monk.jpg";
                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[1].getHouseMap());
                    i = 0;
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }
                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }
                    while (i < 6) {
                        Card2Arr[i].setVisible(false);
                        Card2Arr[i].setDisable(true);
                        card2Array[i] = "null";
                        i++;
                    }
                    break;
                case HERALD:
                    path = "images/CharacterCards/Herald.jpg";
                    break;
                case CENTAUR:
                    path = "images/CharacterCards/Centaur.jpg";
                    break;
                case JOLLY:
                    path = "images/CharacterCards/Jolly.jpg";

                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[1].getHouseMap());
                    i = 0;
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }

                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }
                    while (i < 6) {
                        Card2Arr[i].setVisible(false);
                        Card2Arr[i].setDisable(true);
                        card2Array[i] = "null";
                        i++;
                    }
                    break;
                case KNIGHT:
                    path = "images/CharacterCards/Knight.jpg";
                    break;
                case MUSHROOM_HUNTER:
                    path = "images/CharacterCards/MushroomHunter.jpg";

                    image = new Image("images/students/student_blue.png");
                    Card2Arr[4].setImage(image);
                    Card2Arr[4].setVisible(true);
                    Card2Arr[4].setDisable(false);
                    card2Array[4] = "Blue";

                    image = new Image("images/students/student_pink.png");
                    Card2Arr[3].setImage(image);
                    Card2Arr[3].setVisible(true);
                    Card2Arr[3].setDisable(false);
                    card2Array[3] = "Pink";

                    image = new Image("images/students/student_yellow.png");
                    Card2Arr[2].setImage(image);
                    Card2Arr[2].setVisible(true);
                    Card2Arr[2].setDisable(false);
                    card2Array[2] = "Yellow";

                    image = new Image("images/students/student_red.png");
                    Card2Arr[1].setImage(image);
                    Card2Arr[1].setVisible(true);
                    Card2Arr[1].setDisable(false);
                    card2Array[1] = "Red";

                    image = new Image("images/students/student_green.png");
                    Card2Arr[0].setImage(image);
                    Card2Arr[0].setVisible(true);
                    Card2Arr[0].setDisable(false);
                    card2Array[0] = "Green";
                    i = 5;
                    while (i < 6) {
                        Card2Arr[i].setVisible(false);
                        Card2Arr[i].setDisable(true);
                        card2Array[i] = "null";
                        i++;
                    }
                    break;
                case SPOILED_PRINCESS:
                    path = "images/CharacterCards/SpoiledPrincess.jpg";

                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[1].getHouseMap());
                    i = 0;
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card2Arr[i].setImage(image);
                        Card2Arr[i].setVisible(true);
                        Card2Arr[i].setDisable(false);
                        card2Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (i < 6) {
                        Card2Arr[i].setVisible(false);
                        Card2Arr[i].setDisable(true);
                        card2Array[i] = "null";
                        i++;
                    }
                    break;
                case THIEF:
                    path = "images/CharacterCards/Thief.jpg";

                    image = new Image("images/students/student_green.png");
                    Card2Arr[0].setImage(image);
                    Card2Arr[0].setVisible(true);
                    Card2Arr[0].setDisable(false);
                    card2Array[0] = "Green";

                    image = new Image("images/students/student_red.png");
                    Card2Arr[1].setImage(image);
                    Card2Arr[1].setVisible(true);
                    Card2Arr[1].setDisable(false);
                    card2Array[1] = "Red";

                    image = new Image("images/students/student_yellow.png");
                    Card2Arr[2].setImage(image);
                    Card2Arr[2].setVisible(true);
                    Card2Arr[2].setDisable(false);
                    card2Array[2] = "Yellow";

                    image = new Image("images/students/student_pink.png");
                    Card2Arr[3].setImage(image);
                    Card2Arr[3].setVisible(true);
                    Card2Arr[3].setDisable(false);
                    card2Array[3] = "Pink";

                    image = new Image("images/students/student_blue.png");
                    Card2Arr[4].setImage(image);
                    Card2Arr[4].setVisible(true);
                    Card2Arr[4].setDisable(false);
                    card2Array[4] = "Blue";
                    i = 5;
                    while (i < 6) {
                        Card2Arr[i].setVisible(false);
                        Card2Arr[i].setDisable(true);
                        card2Array[i] = "null";
                        i++;
                    }
                    break;
            }
            image = new Image(path);

            CharacterCard2.setImage(image);

            switch (gm.getCharacterCardDeck()[2].getType()) {
                case FARMER:
                    path = "images/CharacterCards/Farmer.jpg";
                    break;
                case MAGICAL_MAILMAN:
                    path = "images/CharacterCards/MagicalMailman.jpg";
                    break;
                case HERB_GRANMA:
                    path = "images/CharacterCards/HerbGranma.jpg";

                    int numNoEntry = gm.getCharacterCardDeck()[2].getNoEntryTile();
                    image = new Image("images/Clouds/NoEntryTileBackup.png");
                    i = 0;
                    while (numNoEntry > 0) {
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "NoEntryTile";
                        numNoEntry--;
                        i++;
                    }
                    while (i < 6) {
                        Card3Arr[i].setVisible(false);
                        Card3Arr[i].setDisable(true);
                        card3Array[i] = "null";
                        i++;
                    }
                    break;
                case MINSTREL:
                    path = "images/CharacterCards/Minstrel.jpg";
                    break;
                case MONK:
                    path = "images/CharacterCards/Monk.jpg";
                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[2].getHouseMap());
                    i = 0;
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (i < 6) {
                        Card3Arr[i].setVisible(false);
                        Card3Arr[i].setDisable(true);
                        card3Array[i] = "null";
                        i++;
                    }
                    break;
                case HERALD:
                    path = "images/CharacterCards/Herald.jpg";
                    break;
                case CENTAUR:
                    path = "images/CharacterCards/Centaur.jpg";
                    break;
                case JOLLY:
                    path = "images/CharacterCards/Jolly.jpg";

                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[2].getHouseMap());
                    i = 0;
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }

                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }
                    while (i < 6) {
                        Card3Arr[i].setVisible(false);
                        Card3Arr[i].setDisable(true);
                        card3Array[i] = "null";
                        i++;
                    }
                    break;
                case KNIGHT:
                    path = "images/CharacterCards/Knight.jpg";
                    break;
                case MUSHROOM_HUNTER:
                    path = "images/CharacterCards/MushroomHunter.jpg";

                    image = new Image("images/students/student_blue.png");
                    Card3Arr[4].setImage(image);
                    Card3Arr[4].setVisible(true);
                    Card3Arr[4].setDisable(false);
                    card3Array[4] = "Blue";

                    image = new Image("images/students/student_pink.png");
                    Card3Arr[3].setImage(image);
                    Card3Arr[3].setVisible(true);
                    Card3Arr[3].setDisable(false);
                    card3Array[3] = "Pink";

                    image = new Image("images/students/student_yellow.png");
                    Card3Arr[2].setImage(image);
                    Card3Arr[2].setVisible(true);
                    Card3Arr[2].setDisable(false);
                    card3Array[2] = "Yellow";

                    image = new Image("images/students/student_red.png");
                    Card3Arr[1].setImage(image);
                    Card3Arr[1].setVisible(true);
                    Card3Arr[1].setDisable(false);
                    card3Array[1] = "Red";

                    image = new Image("images/students/student_green.png");
                    Card3Arr[0].setImage(image);
                    Card3Arr[0].setVisible(true);
                    Card3Arr[0].setDisable(false);
                    card3Array[0] = "Green";
                    i = 5;
                    while (i < 6) {
                        Card3Arr[i].setVisible(false);
                        Card3Arr[i].setDisable(true);
                        card3Array[i] = "null";
                        i++;
                    }
                    break;
                case SPOILED_PRINCESS:
                    path = "images/CharacterCards/SpoiledPrincess.jpg";

                    houseMap = new HashMap<>(gm.getCharacterCardDeck()[2].getHouseMap());
                    i = 0;
                    while (houseMap.get(BLUE) > 0) {
                        image = new Image("images/students/student_blue.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Blue";
                        houseMap.put(BLUE, houseMap.get(BLUE) - 1);
                        i++;
                    }
                    while (houseMap.get(PINK) > 0) {
                        image = new Image("images/students/student_pink.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Pink";
                        houseMap.put(PINK, houseMap.get(PINK) - 1);
                        i++;
                    }
                    while (houseMap.get(YELLOW) > 0) {
                        image = new Image("images/students/student_yellow.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Yellow";
                        houseMap.put(YELLOW, houseMap.get(YELLOW) - 1);
                        i++;
                    }
                    while (houseMap.get(RED) > 0) {
                        image = new Image("images/students/student_red.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Red";
                        houseMap.put(RED, houseMap.get(RED) - 1);
                        i++;
                    }
                    while (houseMap.get(GREEN) > 0) {
                        image = new Image("images/students/student_green.png");
                        Card3Arr[i].setImage(image);
                        Card3Arr[i].setVisible(true);
                        Card3Arr[i].setDisable(false);
                        card3Array[i] = "Green";
                        houseMap.put(GREEN, houseMap.get(GREEN) - 1);
                        i++;
                    }
                    while (i < 6) {
                        Card3Arr[i].setVisible(false);
                        Card3Arr[i].setDisable(true);
                        card3Array[i] = "null";
                        i++;
                    }
                    break;
                case THIEF:
                    path = "images/CharacterCards/Thief.jpg";

                    image = new Image("images/students/student_green.png");
                    Card3Arr[0].setImage(image);
                    Card3Arr[0].setVisible(true);
                    Card3Arr[0].setDisable(false);
                    card3Array[0] = "Green";

                    image = new Image("images/students/student_red.png");
                    Card3Arr[1].setImage(image);
                    Card3Arr[1].setVisible(true);
                    Card3Arr[1].setDisable(false);
                    card3Array[1] = "Red";

                    image = new Image("images/students/student_yellow.png");
                    Card3Arr[2].setImage(image);
                    Card3Arr[2].setVisible(true);
                    Card3Arr[2].setDisable(false);
                    card3Array[2] = "Yellow";

                    image = new Image("images/students/student_pink.png");
                    Card3Arr[3].setImage(image);
                    Card3Arr[3].setVisible(true);
                    Card3Arr[3].setDisable(false);
                    card3Array[3] = "Pink";

                    image = new Image("images/students/student_blue.png");
                    Card3Arr[4].setImage(image);
                    Card3Arr[4].setVisible(true);
                    Card3Arr[4].setDisable(false);
                    card3Array[4] = "Blue";
                    i = 5;
                    while (i < 6) {
                        Card3Arr[i].setVisible(false);
                        Card3Arr[i].setDisable(true);
                        card3Array[i] = "null";
                        i++;
                    }
                    break;
            }
            image = new Image(path);

            CharacterCard3.setImage(image);
        }

        initializeClouds();

        islandList = new ArrayList<>();
        for (i = 0; i < 12; i++) {
            islandList.add(new HashMap<>());
        }
        islandList.get(0).put("Pane", Island0);
        islandList.get(0).put("Description", DescriptionIsland00);
        islandList.get(0).put("Yellow", StudYellowIsland0);
        islandList.get(0).put("Red", StudRedIsland0);
        islandList.get(0).put("Blue", StudBlueIsland0);
        islandList.get(0).put("Pink", StudPinkIsland0);
        islandList.get(0).put("Green", StudGreenIsland0);
        islandList.get(0).put("Tower", TowerIsland0);
        islandList.get(0).put("TowerNumber", TowerNumberIsland0);
        islandList.get(0).put("NoEntryTile", NoEntryTileIsland0);
        islandList.get(0).put("Mother", MotherIsland0);

        Island0.setOnMouseEntered(this::onMouseEnteredIsland);
        Island0.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(1).put("Pane", Island1);
        islandList.get(1).put("Description", DescriptionIsland01);
        islandList.get(1).put("Yellow", StudYellowIsland1);
        islandList.get(1).put("Red", StudRedIsland1);
        islandList.get(1).put("Blue", StudBlueIsland1);
        islandList.get(1).put("Pink", StudPinkIsland1);
        islandList.get(1).put("Green", StudGreenIsland1);
        islandList.get(1).put("Tower", TowerIsland1);
        islandList.get(1).put("TowerNumber", TowerNumberIsland1);
        islandList.get(1).put("NoEntryTile", NoEntryTileIsland1);
        islandList.get(1).put("Mother", MotherIsland1);

        Island1.setOnMouseEntered(this::onMouseEnteredIsland);
        Island1.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(2).put("Pane", Island2);
        islandList.get(2).put("Description", DescriptionIsland02);
        islandList.get(2).put("Yellow", StudYellowIsland2);
        islandList.get(2).put("Red", StudRedIsland2);
        islandList.get(2).put("Blue", StudBlueIsland2);
        islandList.get(2).put("Pink", StudPinkIsland2);
        islandList.get(2).put("Green", StudGreenIsland2);
        islandList.get(2).put("Tower", TowerIsland2);
        islandList.get(2).put("TowerNumber", TowerNumberIsland2);
        islandList.get(2).put("NoEntryTile", NoEntryTileIsland2);
        islandList.get(2).put("Mother", MotherIsland2);

        Island2.setOnMouseEntered(this::onMouseEnteredIsland);
        Island2.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(3).put("Pane", Island3);
        islandList.get(3).put("Description", DescriptionIsland03);
        islandList.get(3).put("Yellow", StudYellowIsland3);
        islandList.get(3).put("Red", StudRedIsland3);
        islandList.get(3).put("Blue", StudBlueIsland3);
        islandList.get(3).put("Pink", StudPinkIsland3);
        islandList.get(3).put("Green", StudGreenIsland3);
        islandList.get(3).put("Tower", TowerIsland3);
        islandList.get(3).put("TowerNumber", TowerNumberIsland3);
        islandList.get(3).put("NoEntryTile", NoEntryTileIsland3);
        islandList.get(3).put("Mother", MotherIsland3);

        Island3.setOnMouseEntered(this::onMouseEnteredIsland);
        Island3.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(4).put("Pane", Island4);
        islandList.get(4).put("Description", DescriptionIsland04);
        islandList.get(4).put("Yellow", StudYellowIsland4);
        islandList.get(4).put("Red", StudRedIsland4);
        islandList.get(4).put("Blue", StudBlueIsland4);
        islandList.get(4).put("Pink", StudPinkIsland4);
        islandList.get(4).put("Green", StudGreenIsland4);
        islandList.get(4).put("Tower", TowerIsland4);
        islandList.get(4).put("TowerNumber", TowerNumberIsland4);
        islandList.get(4).put("NoEntryTile", NoEntryTileIsland4);
        islandList.get(4).put("Mother", MotherIsland4);

        Island4.setOnMouseEntered(this::onMouseEnteredIsland);
        Island4.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(5).put("Pane", Island5);
        islandList.get(5).put("Description", DescriptionIsland05);
        islandList.get(5).put("Yellow", StudYellowIsland5);
        islandList.get(5).put("Red", StudRedIsland5);
        islandList.get(5).put("Blue", StudBlueIsland5);
        islandList.get(5).put("Pink", StudPinkIsland5);
        islandList.get(5).put("Green", StudGreenIsland5);
        islandList.get(5).put("Tower", TowerIsland5);
        islandList.get(5).put("TowerNumber", TowerNumberIsland5);
        islandList.get(5).put("NoEntryTile", NoEntryTileIsland5);
        islandList.get(5).put("Mother", MotherIsland5);

        Island5.setOnMouseEntered(this::onMouseEnteredIsland);
        Island5.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(6).put("Pane", Island6);
        islandList.get(6).put("Description", DescriptionIsland06);
        islandList.get(6).put("Yellow", StudYellowIsland6);
        islandList.get(6).put("Red", StudRedIsland6);
        islandList.get(6).put("Blue", StudBlueIsland6);
        islandList.get(6).put("Pink", StudPinkIsland6);
        islandList.get(6).put("Green", StudGreenIsland6);
        islandList.get(6).put("Tower", TowerIsland6);
        islandList.get(6).put("TowerNumber", TowerNumberIsland6);
        islandList.get(6).put("NoEntryTile", NoEntryTileIsland6);
        islandList.get(6).put("Mother", MotherIsland6);

        Island6.setOnMouseEntered(this::onMouseEnteredIsland);
        Island6.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(7).put("Pane", Island7);
        islandList.get(7).put("Description", DescriptionIsland07);
        islandList.get(7).put("Yellow", StudYellowIsland7);
        islandList.get(7).put("Red", StudRedIsland7);
        islandList.get(7).put("Blue", StudBlueIsland7);
        islandList.get(7).put("Pink", StudPinkIsland7);
        islandList.get(7).put("Green", StudGreenIsland7);
        islandList.get(7).put("Tower", TowerIsland7);
        islandList.get(7).put("TowerNumber", TowerNumberIsland7);
        islandList.get(7).put("NoEntryTile", NoEntryTileIsland7);
        islandList.get(7).put("Mother", MotherIsland7);

        Island7.setOnMouseEntered(this::onMouseEnteredIsland);
        Island7.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(8).put("Pane", Island8);
        islandList.get(8).put("Description", DescriptionIsland08);
        islandList.get(8).put("Yellow", StudYellowIsland8);
        islandList.get(8).put("Red", StudRedIsland8);
        islandList.get(8).put("Blue", StudBlueIsland8);
        islandList.get(8).put("Pink", StudPinkIsland8);
        islandList.get(8).put("Green", StudGreenIsland8);
        islandList.get(8).put("Tower", TowerIsland8);
        islandList.get(8).put("TowerNumber", TowerNumberIsland8);
        islandList.get(8).put("NoEntryTile", NoEntryTileIsland8);
        islandList.get(8).put("Mother", MotherIsland8);

        Island8.setOnMouseEntered(this::onMouseEnteredIsland);
        Island8.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(9).put("Pane", Island9);
        islandList.get(9).put("Description", DescriptionIsland09);
        islandList.get(9).put("Yellow", StudYellowIsland9);
        islandList.get(9).put("Red", StudRedIsland9);
        islandList.get(9).put("Blue", StudBlueIsland9);
        islandList.get(9).put("Pink", StudPinkIsland9);
        islandList.get(9).put("Green", StudGreenIsland9);
        islandList.get(9).put("Tower", TowerIsland9);
        islandList.get(9).put("TowerNumber", TowerNumberIsland9);
        islandList.get(9).put("NoEntryTile", NoEntryTileIsland9);
        islandList.get(9).put("Mother", MotherIsland9);

        Island9.setOnMouseEntered(this::onMouseEnteredIsland);
        Island9.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(10).put("Pane", Island10);
        islandList.get(10).put("Description", DescriptionIsland10);
        islandList.get(10).put("Yellow", StudYellowIsland10);
        islandList.get(10).put("Red", StudRedIsland10);
        islandList.get(10).put("Blue", StudBlueIsland10);
        islandList.get(10).put("Pink", StudPinkIsland10);
        islandList.get(10).put("Green", StudGreenIsland10);
        islandList.get(10).put("Tower", TowerIsland10);
        islandList.get(10).put("TowerNumber", TowerNumberIsland10);
        islandList.get(10).put("NoEntryTile", NoEntryTileIsland10);
        islandList.get(10).put("Mother", MotherIsland10);

        Island10.setOnMouseEntered(this::onMouseEnteredIsland);
        Island10.setOnMouseExited(this::onMouseExitedIsland);

        islandList.get(11).put("Pane", Island11);
        islandList.get(11).put("Description", DescriptionIsland11);
        islandList.get(11).put("Yellow", StudYellowIsland11);
        islandList.get(11).put("Red", StudRedIsland11);
        islandList.get(11).put("Blue", StudBlueIsland11);
        islandList.get(11).put("Pink", StudPinkIsland11);
        islandList.get(11).put("Green", StudGreenIsland11);
        islandList.get(11).put("Tower", TowerIsland11);
        islandList.get(11).put("TowerNumber", TowerNumberIsland11);
        islandList.get(11).put("NoEntryTile", NoEntryTileIsland11);
        islandList.get(11).put("Mother", MotherIsland11);

        Island11.setOnMouseEntered(this::onMouseEnteredIsland);
        Island11.setOnMouseExited(this::onMouseExitedIsland);

        initializeIslands();


        Dashboard1.setDisable(true);
        Dashboard2.setDisable(true);
        Dashboard3.setDisable(true);

        Suggestions.setOnMouseClicked(doNothing);
        Suggestions.setWrapText(true);
    }

    public void initializeEvents() {

        if (moveMother) {
            int currentIsland = gm.getMotherIsland();
            islandButtons[currentIsland].setOnMouseClicked(moveMotherFrom);
        } else {
            for (ImageView student : EntranceMain) {
                student.setOpacity(1);
                student.setOnMouseClicked(selectStudent);
                student.getStyleClass().clear();
            }
        }
        for (ImageView card : characterCards) {
            card.setOnMouseClicked(selectCharacterCard);
        }
    }

    // <--------- Event handlers --------->

    private void onMouseEnteredIsland(MouseEvent e) {
        Pane island = (Pane) e.getTarget();

        int islandIndex = getIslandIdByPane(island);

        islandList.get(islandIndex).get("Description").setVisible(true);
    }

    private void onMouseExitedIsland(MouseEvent e) {
        Pane island = (Pane) e.getTarget();

        int islandIndex = getIslandIdByPane(island);

        islandList.get(islandIndex).get("Description").setVisible(false);
    }

    private void selectStudent(MouseEvent event) {
        ImageView studentSelected = (ImageView) event.getSource();

        houseSelected = getHouseById(studentSelected.getId());

        for (ImageView student : EntranceMain) {
            if (!studentSelected.getId().equals(student.getId())) {
                student.setOnMouseClicked(doNothing);
                student.setOpacity(0.5);
            }
            student.getStyleClass().clear();
        }

        studentSelected.setOnMouseClicked(selectStudentCancel);
        studentSelected.getStyleClass().add("dropShadow");

        //enable all the islands
        for (Button b : islandButtons) {
            b.setOnMouseClicked(moveStudentToIsland);
        }

        for (ImageView island : islandsImageView) {
            island.getStyleClass().add("dropShadow");
        }

        for (ImageView card : characterCards) {
            card.setOnMouseClicked(doNothing);
        }

        getButtonByHouse(houseSelected).setOnMouseClicked(moveStudentToDiningHall);

    }

    private void selectStudentCancel(MouseEvent mouseEvent) {

        removeEvents();
        initializeEvents();

        for (ImageView student : EntranceMain) {
            student.setOpacity(1);
            student.getStyleClass().clear();
        }

        houseSelected = null;
    }

    public void moveStudentToDiningHall(MouseEvent event) {

        removeEvents();
        for (ImageView student : EntranceMain) {
            student.setOpacity(1);
            student.getStyleClass().clear();
        }
        notifyObserver(observer -> observer.onMoveStudentsToDiningHall(houseSelected));
        houseSelected = null;
    }

    private void moveStudentToIsland(MouseEvent event) {

        Button islandClicked = (Button) event.getSource();

        removeEvents();
        int islandPosition = getIslandById(islandClicked.getId());
        notifyObserver(observer -> observer.onMoveStudentsToIsland(houseSelected, islandPosition));

        for (ImageView student : EntranceMain) {
            student.setOpacity(1);
            student.getStyleClass().clear();
        }
        houseSelected = null;
    }

    private void onChatSendBtnClick(ActionEvent e) {
        String message = ChatText.getText();
        ChatText.clear();
        notifyObserver(observers -> observers.onSendMessage(message));
    }

    private void onChatTextTyped(KeyEvent e) {
        ChatSendBtn.setDisable(false);
    }

    private void moveMotherFrom(MouseEvent event) {

        int currentIsland = gm.getMotherIsland();

        getMotherByIsland(gm.getMotherIsland()).getStyleClass().add("dropShadow");

        for (int i = 0; i < gm.getPlayerByNickname(nickname).getMaxMoves(); i++) {
            currentIsland++;
            if (currentIsland == gm.getIslandList().size()) {
                currentIsland = 0;
            }
            islandButtons[currentIsland].setOnMouseClicked(moveMotherTo);
            islandsImageView[currentIsland].getStyleClass().add("dropShadow");
        }
    }

    private void moveMotherTo(MouseEvent event) {

        int islandTo = getIslandById(((Button) event.getSource()).getId());

        int position = islandTo - gm.getMotherIsland();

        for (ImageView island : islandsImageView) {
            island.getStyleClass().clear();
        }

        notifyObserver(observer -> observer.onMoveMotherNature(position));
    }

    private void doNothing(MouseEvent event) {
    }

    private void removeEvents() {

        for (ImageView student : EntranceMain) {
            student.setOnMouseClicked(doNothing);
            student.setOpacity(0.5);
            student.getStyleClass().clear();
        }

        for (Button b : islandButtons) {
            b.setOnMouseClicked(doNothing);
        }

        for (ImageView island : islandsImageView) {
            island.getStyleClass().clear();
        }

        if (houseSelected != null)
            getButtonByHouse(houseSelected).setOnMouseClicked(doNothing);

        for (ImageView card : characterCards) {
            card.setOnMouseClicked(doNothing);
        }

    }

    //<--------- Utility methods --------->

    private void fillGraveyard(ImageView imgview, ReducedPlayer p) {

        Image image;

        if (p.getGraveyard() != null) {
            switch (p.getGraveyard()) {
                case CARD1:
                    image = new Image("/images/Graveyards/Assistente_1.jpg");
                    imgview.setImage(image);
                    break;

                case CARD2:
                    image = new Image("/images/Graveyards/Assistente_2.jpg");
                    imgview.setImage(image);
                    break;

                case CARD3:
                    image = new Image("/images/Graveyards/Assistente_3.jpg");
                    imgview.setImage(image);
                    break;

                case CARD4:
                    image = new Image("/images/Graveyards/Assistente_4.jpg");
                    imgview.setImage(image);
                    break;

                case CARD5:
                    image = new Image("/images/Graveyards/Assistente_5.jpg");
                    imgview.setImage(image);
                    break;

                case CARD6:
                    image = new Image("/images/Graveyards/Assistente_6.jpg");
                    imgview.setImage(image);
                    break;

                case CARD7:
                    image = new Image("/images/Graveyards/Assistente_7.jpg");
                    imgview.setImage(image);
                    break;

                case CARD8:
                    image = new Image("/images/Graveyards/Assistente_8.jpg");
                    imgview.setImage(image);
                    break;

                case CARD9:
                    image = new Image("/images/Graveyards/Assistente_9.jpg");
                    imgview.setImage(image);
                    break;

                case CARD10:
                    image = new Image("/images/Graveyards/Assistente_10.jpg");
                    imgview.setImage(image);
                    break;
            }
        } else {
            String path = null;
            switch (p.getWizard()) {
                case FIRST:
                    path = "/images/wizards/first.png";
                    break;
                case SECOND:
                    path = "/images/wizards/second.png";
                    break;
                case THIRD:
                    path = "/images/wizards/third.png";
                    break;
                case FOURTH:
                    path = "/images/wizards/fourth.png";
                    break;
            }
            image = new Image(path);
            imgview.setImage(image);
            imgview.setVisible(true);
        }
    }

    private void fillCardInUse(ImageView imgview, ReducedPlayer p) {

        Image image;

        if (p.getCardInUse() != null) {
            switch (p.getCardInUse()) {
                case CARD1:
                    image = new Image("/images/assistant/Assistente_1.png");
                    imgview.setImage(image);
                    break;

                case CARD2:
                    image = new Image("/images/assistant/Assistente_2.png");
                    imgview.setImage(image);
                    break;

                case CARD3:
                    image = new Image("/images/assistant/Assistente_3.png");
                    imgview.setImage(image);
                    break;

                case CARD4:
                    image = new Image("/images/assistant/Assistente_4.png");
                    imgview.setImage(image);
                    break;

                case CARD5:
                    image = new Image("/images/assistant/Assistente_5.png");
                    imgview.setImage(image);
                    break;

                case CARD6:
                    image = new Image("/images/assistant/Assistente_6.png");
                    imgview.setImage(image);
                    break;

                case CARD7:
                    image = new Image("/images/assistant/Assistente_7.png");
                    imgview.setImage(image);
                    break;

                case CARD8:
                    image = new Image("/images/assistant/Assistente_8.png");
                    imgview.setImage(image);
                    break;

                case CARD9:
                    image = new Image("/images/assistant/Assistente_9.png");
                    imgview.setImage(image);
                    break;

                case CARD10:
                    image = new Image("/images/assistant/Assistente_10.png");
                    imgview.setImage(image);
                    break;
            }
        }
    }

    private void setCoins(Text t, ReducedPlayer p) {
        t.setText(Integer.toString(p.getCoins()));
    }

    private House getHouseById(String id) {
        int position = Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1;

        return entranceArray[position];
    }

    private House getHouseByButton(Button button) {

        String id = button.getId();

        if (id.endsWith("Green"))
            return GREEN;
        if (id.endsWith("Blue"))
            return BLUE;
        if (id.endsWith("Red"))
            return RED;
        if (id.endsWith("Yellow"))
            return YELLOW;
        if (id.endsWith("Pink"))
            return PINK;

        throw new IllegalArgumentException();
    }

    private int getIslandIdByPane(Pane island) {
        int islandIndex;

        switch (island.getId()) {
            case "Island0":
                islandIndex = 0;
                break;
            case "Island1":
                islandIndex = 1;
                break;
            case "Island2":
                islandIndex = 2;
                break;
            case "Island3":
                islandIndex = 3;
                break;
            case "Island4":
                islandIndex = 4;
                break;
            case "Island5":
                islandIndex = 5;
                break;
            case "Island6":
                islandIndex = 6;
                break;
            case "Island7":
                islandIndex = 7;
                break;
            case "Island8":
                islandIndex = 8;
                break;
            case "Island9":
                islandIndex = 9;
                break;
            case "Island10":
                islandIndex = 10;
                break;
            case "Island11":
                islandIndex = 11;
                break;
            case "Island12":
                islandIndex = 12;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + island.getId());
        }

        return islandIndex;
    }

    /**
     * Returns the element contained in a specific cell of the character card's element array
     *
     * @param id the id of the element clicked
     * @return Blue, Pink, Green, Red or Yellow if it is a student, returns NoEntryTile if it is a no entry tile and returns null if it is empty
     */
    private String getElemCard1(String id) { //if a number, then it's a noentrytile, if a house, then it's a student
        int position = Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1;

        return card1Array[position];
    }

    /**
     * Returns the element contained in a specific cell of the character card's element array
     *
     * @param id the id of the element clicked
     * @return Blue, Pink, Green, Red or Yellow if it is a student, returns NoEntryTile if it is a no entry tile and returns null if it is empty
     */
    private String getElemCard2(String id) {
        int position = Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1;

        return card2Array[position];
    }

    /**
     * Returns the element contained in a specific cell of the character card's element array
     *
     * @param id the id of the element clicked
     * @return Blue, Pink, Green, Red or Yellow if it is a student, returns NoEntryTile if it is a no entry tile and returns null if it is empty
     */
    private String getElemCard3(String id) {
        int position = Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1;

        return card3Array[position];
    }

    private Button getButtonByHouse(House house) throws IllegalArgumentException {

        switch (house) {
            case GREEN:
                return diningHallMain[0];
            case RED:
                return diningHallMain[1];
            case YELLOW:
                return diningHallMain[2];
            case PINK:
                return diningHallMain[3];
            case BLUE:
                return diningHallMain[4];
            default:
                throw new IllegalArgumentException("You did not select the correct button");
        }
    }

    private Button getButtonByIsland(int i) {

        Button b = null;
        switch (i) {
            case 0:
                b = Island00Btn;
                break;
            case 1:
                b = Island01Btn;
                break;
            case 2:
                b = Island02Btn;
                break;
            case 3:
                b = Island03Btn;
                break;
            case 4:
                b = Island04Btn;
                break;
            case 5:
                b = Island05Btn;
                break;
            case 6:
                b = Island06Btn;
                break;
            case 7:
                b = Island07Btn;
                break;
            case 8:
                b = Island08Btn;
                break;
            case 9:
                b = Island09Btn;
                break;
            case 10:
                b = Island10Btn;
                break;
            case 11:
                b = Island11Btn;
                break;
        }

        return b;
    }

    private Button getButtonByCloud(int c) {

        int numPlayers = gm.getNumPlayers();
        Button button = null;

        if (numPlayers == 3) {
            switch (c) {
                case 0:
                    button = Cloud4Btn1;
                    break;
                case 1:
                    button = Cloud4Btn2;
                    break;
                case 2:
                    button = Cloud4Btn3;
                    break;
            }
        } else {
            switch (c) {
                case 0:
                    button = Cloud3Btn1;
                    break;
                case 1:
                    button = Cloud3Btn2;
                    break;
                case 2:
                    button = Cloud3Btn3;
                    break;
                case 4:
                    button = Cloud3Btn4;
                    break;
            }
        }

        return button;
    }

    private int getIslandById(String id) {
        return Integer.parseInt(String.valueOf(id.charAt(6))) * 10
                + Integer.parseInt(String.valueOf(id.charAt(7)));
    }

    public void setGm(ReducedGameModel gm) {
        this.gm = gm;
    }

    public void setMoveMother() {
        this.moveMother = true;

        for (ImageView student : EntranceMain) {
            student.setOnMouseClicked(doNothing);
        }

        for (Button diningHall : diningHallMain) {
            diningHall.setOnMouseClicked(doNothing);
        }

        for (Button island : islandButtons) {
            island.setOnMouseClicked(doNothing);
        }

    }

    public void setCloudSelectable() {

        removeEvents();

        for (Button cloud : cloudsButtons) {
            if (cloud.getOnMouseClicked() == null)
                cloud.setOnMouseClicked(selectCloud);
        }
    }

    public int getCloudById(String id) {
        return Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1;
    }

    public void onCloudSelected(MouseEvent event) {

        Button cloud = (Button) event.getSource();

        int cloudSelected = getCloudById(cloud.getId());

        notifyObserver(observer -> observer.onUpdateCloud(cloudSelected));

        for (Button c : cloudsButtons) {
            c.setOnMouseClicked(doNothing);
        }

    }

    public void setSuggestions(List<String> message, String type) {
        if (type.equals("selectCloud")) {
            Suggestions.setText("You can now select a cloud from those available!");
            return;
        }

        if (type.equals("actionPhase")) {
            int i = 0;
            Suggestions.setText("You can now do one of the following:\n");
            while (i < message.size()) {
                switch (message.get(i)) {
                    case "Move students to dining hall or to island":
                        Suggestions.appendText("Move a student from the entrance to the dining hall or to an island\n");
                        break;
                    case "Select character card":
                        Suggestions.appendText("Use a character card\n");
                        break;
                    case "Send a message to your team mate":
                        Suggestions.appendText("Send a message to your team mate\n");
                        break;
                }
                i++;
            }
            if (!message.contains("Move students to dining hall or to island")){
                Suggestions.appendText("Move mother nature\n");
            }
            return;
        }

        switch (type) {
            case "Monk":
                Suggestions.setText("You now have to select a student from the card and an island where to move it");
                break;
            case "HerbGranma":
                Suggestions.setText("You now have to select an island where to put the no entry tile");
                break;
            case "Jolly":
                Suggestions.setText("You now have to select a student from the card and a student from the dining hall");
                break;
            case "Minstrel":
                Suggestions.setText("You now have to select a student from the dining hall and a student from the entrance");
                break;
            case "SpoiledPrincess":
                Suggestions.setText("You now have to select a student from the card to add to the dining hall");
                break;
            case "Thief":
                Suggestions.setText("You now have to select a type of student to take from every player");
                break;
            default:
                Suggestions.setText("");
        }
    }

    private ImageView getMotherByIsland(int island) {
        switch (island) {
            case 0:
                return MotherIsland0;
            case 1:
                return MotherIsland1;
            case 2:
                return MotherIsland2;
            case 3:
                return MotherIsland3;
            case 4:
                return MotherIsland4;
            case 5:
                return MotherIsland5;
            case 6:
                return MotherIsland6;
            case 7:
                return MotherIsland7;
            case 8:
                return MotherIsland8;
            case 9:
                return MotherIsland9;
            case 10:
                return MotherIsland10;
            case 11:
                return MotherIsland11;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void selectCharacterCard(MouseEvent event) {

        ImageView card = (ImageView) event.getSource();

        String id = card.getId();
        this.cardSelected = Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1;

        SceneController.getActiveScene().getRoot().setEffect(new GaussianBlur());

        Alert alert;

        //if the player does not have enough coins
        if (gm.getPlayerByNickname(nickname).getCoins() < gm.getCharacterCardDeck()[cardSelected].getCost()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(SceneController.getActiveScene().getWindow());
            alert.initModality(Modality.APPLICATION_MODAL);

            alert.setTitle("Select assistant card");
            alert.setHeaderText("You don't have enough coins to use this card!");

            alert.showAndWait();
            SceneController.getActiveScene().getRoot().setEffect(null);
            return;
        }

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(SceneController.getActiveScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.setTitle("Select assistant card");
        alert.setHeaderText("Card selected: " + gm.getCharacterCardDeck()[cardSelected].getCardName());
        alert.setContentText("Do you want to use this card?");

        //if the player wants to use the card
        if (alert.showAndWait().orElse(ButtonType.OK) == ButtonType.OK) {
            removeEvents();

            CharacterCardEnum type = gm.getCharacterCardDeck()[cardSelected].getType();
            setSuggestions(null, gm.getCharacterCardDeck()[cardSelected].getCardName());

            switch (type) {
                case MONK:
                    for (Node n : getVBoxByCard(cardSelected).getChildren()) {
                        n.setOnMouseClicked(selectStudentForMonk);
                        n.getStyleClass().add("dropShadow");
                    }
                    break;
                case HERB_GRANMA: //ok
                    for (Button island : islandButtons) {
                        island.setOnMouseClicked(addNoEntryTile);
                    }
                    break;
                case JOLLY:
                    for (Node n : getVBoxByCard(cardSelected).getChildren()) {
                        n.setOnMouseClicked(selectStudentForJolly);
                    }
                    card.setOnMouseClicked(sendStudents);
                    break;
                case MINSTREL:
                    for (ImageView student : EntranceMain) {
                        student.setOpacity(1);
                        student.setOnMouseClicked(selectStudentForMinstrel);
                    }
                    for (Button button : diningHallMain) {
                        button.setOnMouseClicked(pickStudentsFromDiningHall);
                    }
                    card.setOnMouseClicked(sendStudents);
                    break;
                case SPOILED_PRINCESS:
                    for (Node n : getVBoxByCard(cardSelected).getChildren()) {
                        n.setOnMouseClicked(selectStudentForSpoiledPrincess);
                    }
                    break;
                case HERALD:
                    for (Button island : islandButtons) {
                        island.setOnMouseClicked(selectIslandForHerald);
                    }
                    for (ImageView island : islandsImageView) {
                        island.getStyleClass().add("dropShadow");
                    }
                    break;

                case THIEF: //ok
                case MUSHROOM_HUNTER:
                    //capire come aggiungere gli studenti nella vbox

                    for (Node n : getVBoxByCard(cardSelected).getChildren()) {
                        n.setOnMouseClicked(selectStudentForMushroomHunter_Thief);
                    }
                    break;

                case FARMER:
                case CENTAUR:
                case KNIGHT:
                case MAGICAL_MAILMAN:
                    notifyObserver(observer -> observer.onUpdateCharacterCard(cardSelected, null));
                    break;
                default:
                    System.err.println("Error in chooseCharacterCard of ActionController switch");
            }
        }
        SceneController.getActiveScene().getRoot().setEffect(null);
    }

    // <------- HerbGranma ------->
    private void addNoEntryTile(MouseEvent event) {
        Button islandClicked = (Button) event.getSource();

        int islandPosition = getIslandById(islandClicked.getId());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("island", islandPosition);

        notifyObserver(observer -> observer.onUpdateCharacterCard(cardSelected, parameters));
        initializeEvents();

        //cardSelected = null;
    }

    // <------- Jolly ------->
    private void selectStudentForJolly(MouseEvent event) {
        ImageView studentSelected = (ImageView) event.getSource();

        VBox box = (VBox) studentSelected.getParent();
        String id = studentSelected.getId();

        studentSelectedFromCard = getHouseArrayFromMap(gm.getCharacterCardDeck()[cardSelected].getHouseMap()).
                get(Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1);

        if (wantedStudents == null)
            wantedStudents = new ArrayList<>();

        wantedStudents.add(studentSelectedFromCard);

        if (wantedStudents.size() == 3) {
            for (Node n : box.getChildren()) {
                n.setOnMouseClicked(doNothing);
                n.getStyleClass().clear();
            }

            for (ImageView student : EntranceMain) {
                student.setOnMouseClicked(pickStudentsFromEntrance);
                student.setOpacity(1);
            }
        }
    }

    private void pickStudentsFromEntrance(MouseEvent event) {
        ImageView studentSelected = (ImageView) event.getSource();

        House house = getHouseById(studentSelected.getId());

        if (cardSelected != null) {
            for (Node student : getVBoxByCard(cardSelected).getChildren()) {
                student.setOnMouseClicked(doNothing);
            }
        }

        if (returnedStudents == null) {
            returnedStudents = new ArrayList<>();
        }
        returnedStudents.add(house);
    }

    // <------- SpoiledPrincess ------->
    private void selectStudentForSpoiledPrincess(MouseEvent event) {
        ImageView studentSelected = (ImageView) event.getSource();

        VBox box = (VBox) studentSelected.getParent();
        String id = studentSelected.getId();

        studentSelectedFromCard = House.values()[Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1];

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("wantedHouse", studentSelectedFromCard);

        notifyObserver(observer -> observer.onUpdateCharacterCard(cardSelected, parameters));

        //cardSelected = null;
        for (Node n : box.getChildren()) {
            n.setOnMouseClicked(doNothing);
            n.getStyleClass().clear();
        }

        initializeEvents();
    }

    // <------- Minstrel ------->
    private void selectStudentForMinstrel(MouseEvent event) {
        ImageView selected = (ImageView) event.getSource();

        House house = getHouseById(selected.getId());

        if (wantedStudents == null)
            wantedStudents = new ArrayList<>();

        wantedStudents.add(house);

        if (wantedStudents.size() == 2) {
            for (ImageView student : EntranceMain) {
                student.setOnMouseClicked(doNothing);
                student.getStyleClass().clear();
            }
        }
    }

    private void pickStudentsFromDiningHall(MouseEvent event) {
        Button studentSelected = (Button) event.getSource();

        House diningHallSelected = getHouseByButton(studentSelected);

        if (returnedStudents == null) {
            returnedStudents = new ArrayList<>();
        }

        returnedStudents.add(diningHallSelected);

        if(returnedStudents.size() == 2) {
            for(Button student : diningHallMain) {
                student.setOnMouseClicked(doNothing);
            }
        }
    }

    private void sendStudents(MouseEvent event) {

        if (wantedStudents.size() == returnedStudents.size()) {
            Map<String, Object> parameters = new HashMap<>();
            Gson gson = new Gson();

            if(gm.getCharacterCardDeck()[cardSelected].getType() == CharacterCardEnum.MINSTREL) {
                parameters.put("fromDashboard", gson.toJson(wantedStudents.toArray()));
                parameters.put("fromDiningHall", gson.toJson(returnedStudents.toArray()));
            }
            else if (gm.getCharacterCardDeck()[cardSelected].getType() == CharacterCardEnum.JOLLY) {

                Map<House, Integer> wantedMap = new HashMap<>();
                Map<House, Integer> returnedMap = new HashMap<>();

                for(House h : House.values()) {
                    wantedMap.put(h, 0);
                    returnedMap.put(h, 0);
                }
                for(int i=0; i<wantedStudents.size(); i++) {
                    wantedMap.put(wantedStudents.get(i), wantedMap.get(wantedStudents.get(i)) + 1);
                    returnedMap.put(returnedStudents.get(i), returnedMap.get(returnedStudents.get(i)) + 1);
                }
                parameters.put("wantedStudents", gson.toJson(wantedMap));
                parameters.put("returnedStudents", gson.toJson(returnedMap));
            }
            notifyObserver(observer -> observer.onUpdateCharacterCard(cardSelected, parameters));

            initializeEvents();

            wantedStudents = null;
            returnedStudents = null;
        }
    }

    // <------- Monk ------->
    private void selectStudentForMonk(MouseEvent event) {
        ImageView studentSelected = (ImageView) event.getSource();

        VBox box = (VBox) studentSelected.getParent();
        String id = studentSelected.getId();

        studentSelectedFromCard = getHouseArrayFromMap(gm.getCharacterCardDeck()[cardSelected].getHouseMap()).
                get(Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1);

        if (wantedStudents == null)
            wantedStudents = new ArrayList<>();

        wantedStudents.add(studentSelectedFromCard);

        for (Node n : box.getChildren()) {
            n.setOnMouseClicked(doNothing);
        }

        for (Button island : islandButtons) {
            island.setOnMouseClicked(selectIslandForMonk);
        }

        for (ImageView island : islandsImageView) {
            island.getStyleClass().add("dropShadow");
        }
    }

    private void selectIslandForMonk(MouseEvent event) {
        Button island = (Button) event.getSource();

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("wantedHouse", wantedStudents.get(0));

        parameters.put("destinationIsland", Arrays.asList(islandButtons).indexOf(island));
        notifyObserver(observer -> observer.onUpdateCharacterCard(cardSelected, parameters));

        wantedStudents = null;
    }

    // <------- MushroomHunter ------->
    // <------- Thief ------->
    private void selectStudentForMushroomHunter_Thief(MouseEvent event) {
        ImageView studentSelected = (ImageView) event.getSource();

        VBox box = (VBox) studentSelected.getParent();
        String id = studentSelected.getId();

        studentSelectedFromCard = House.values()[Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1];

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("house", studentSelectedFromCard);
        parameters.put("wantedHouse", studentSelectedFromCard);

        notifyObserver(observer -> observer.onUpdateCharacterCard(cardSelected, parameters));

        for (Node n : box.getChildren()) {
            n.setOnMouseClicked(doNothing);
        }
    }

    // <------- Herald ------->
    private void selectIslandForHerald(MouseEvent event) {
        Button island = (Button) event.getSource();

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("island", Arrays.asList(islandButtons).indexOf(island));
        notifyObserver(observer -> observer.onUpdateCharacterCard(cardSelected, parameters));
        initializeEvents();

        wantedStudents = null;
    }

    private VBox getVBoxByCard(int card) {

        switch (card) {
            case 0:
                return boxCard1;
            case 1:
                return boxCard2;
            case 2:
                return boxCard3;
            default:
                throw new IllegalArgumentException("Not valid card select");
        }
    }

    private List<House> getHouseArrayFromMap(Map<House, Integer> map) {

        Map<House, Integer> copy = new HashMap<>(map);

        List<House> returned = new ArrayList<>();

        for (House h : House.values()) {
            for (int i = 0; i < copy.get(h); i++) {
                returned.add(h);
            }
        }

        return returned;
    }
}
