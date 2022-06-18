package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.client.ReducedGameModel;
import it.polimi.ingsw.client.ReducedPlayer;
import it.polimi.ingsw.exceptions.IslandException;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
    private ImageView CharacterCard2;
    @FXML
    private ImageView CharacterCard3;
    @FXML
    private Text CostCharacterCard1;
    @FXML
    private Text CostCharacterCard2;
    @FXML
    private Text CostCharacterCard3;
    @FXML
    private Pane Chat;

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

    private ReducedGameModel gm;
    private final String nickname;
    private ImageView[] EntranceMain;
    private Button[] diningHallMain;
    private House houseSelected;
    private List<Map<String, Node>> islandList;
    private House[] entranceArray;
    private boolean moveMother;
    private EventHandler<MouseEvent> selectStudent;
    private EventHandler<MouseEvent> moveMotherFrom;
    private EventHandler<MouseEvent> moveStudentToIsland;
    private EventHandler<MouseEvent> selectStudentCancel;
    private EventHandler<MouseEvent> moveMotherTo;


    private Button[] islandButtons;

    public ActionSceneController(ReducedGameModel gm, String nickname) {
        this.gm = gm;
        this.nickname = nickname;
    }

    public void initializeIsland() {
        int i = 0;
        Map<House, Integer> houseMap;
        Image image;

        while (i < gm.getIslandList().size()) {
            houseMap = new HashMap<>(gm.getIslandList().get(i).getStudents());

            islandList.get(i).get("Yellow").setVisible(houseMap.get(YELLOW) != 0);
            islandList.get(i).get("Red").setVisible(houseMap.get(RED) != 0);
            islandList.get(i).get("Blue").setVisible(houseMap.get(BLUE) != 0);
            islandList.get(i).get("Green").setVisible(houseMap.get(GREEN) != 0);
            islandList.get(i).get("Pink").setVisible(houseMap.get(PINK) != 0);

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
            } catch (IslandException e) {}

            if (gm.isExpertMode()) {
                ImageView noEntryTile = (ImageView) islandList.get(i).get("NoEntryTile");
                if (gm.getIslandList().get(i).getNoEntryTile() == 0) {
                    noEntryTile.setVisible(false);
                }
            } else {
                ImageView noEntryTile = (ImageView) islandList.get(i).get("NoEntryTile");
                noEntryTile.setVisible(false);
            }

            if (gm.getMotherIsland() != i) {
                ImageView mother = (ImageView) islandList.get(i).get("Mother");
                mother.setVisible(false);
            }
            i++;
        }
    }

    public void initialize() {

        this.diningHallMain = new Button[]{diningHallMainGreen, diningHallMainRed, diningHallMainYellow,
                diningHallMainBlue, diningHallMainPink};

        this.islandButtons = new Button[]{Island00Btn, Island01Btn, Island02Btn, Island03Btn, Island04Btn, Island05Btn,
                Island06Btn, Island07Btn, Island08Btn, Island09Btn, Island10Btn, Island11Btn};

        int numMain = Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getPlayerByNickname(nickname));

        Map<House, Integer> houseMap;
        Map<House, Boolean> profMap;

        int numStudents = 6;
        if (gm.getNumPlayers() == 3) {
            numStudents = 8;
        }
        int numTowers = 7;
        if (gm.getNumPlayers() == 3) {
            numTowers = 5;
        }

        int i;

        Image image;

        //first player
        EntranceMain = new ImageView[]{EntranceStudMain1, EntranceStudMain2, EntranceStudMain3, EntranceStudMain4, EntranceStudMain5, EntranceStudMain6, EntranceStudMain7, EntranceStudMain8, EntranceStudMain9};

        fillGraveyard(GraveyardMain, gm.getArrayPlayers()[numMain]);
        fillCardInUse(CardInUseMain, gm.getArrayPlayers()[numMain]);

        houseMap = new HashMap<>(gm.getArrayPlayers()[numMain].getDashboard().getStudents());
        i = numStudents;

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

        ImageView[] DiningMainRed = new ImageView[10];
        FillDiningHall(DiningMainRed, DiningRedStd1Main, DiningRedStd2Main, DiningRedStd3Main, DiningRedStd4Main, DiningRedStd5Main, DiningRedStd6Main, DiningRedStd7Main, DiningRedStd8Main, DiningRedStd9Main, DiningRedStd10Main);

        ImageView[] DiningMainGreen = new ImageView[10];
        FillDiningHall(DiningMainGreen, DiningGreenStd1Main, DiningGreenStd2Main, DiningGreenStd3Main, DiningGreenStd4Main, DiningGreenStd5Main, DiningGreenStd6Main, DiningGreenStd7Main, DiningGreenStd8Main, DiningGreenStd9Main, DiningGreenStd10Main);

        ImageView[] DiningMainBlue = new ImageView[10];
        FillDiningHall(DiningMainBlue, DiningBlueStd1Main, DiningBlueStd2Main, DiningBlueStd3Main, DiningBlueStd4Main, DiningBlueStd5Main, DiningBlueStd6Main, DiningBlueStd7Main, DiningBlueStd8Main, DiningBlueStd9Main, DiningBlueStd10Main);

        ImageView[] DiningMainPink = new ImageView[10];
        FillDiningHall(DiningMainPink, DiningPinkStd1Main, DiningPinkStd2Main, DiningPinkStd3Main, DiningPinkStd4Main, DiningPinkStd5Main, DiningPinkStd6Main, DiningPinkStd7Main, DiningPinkStd8Main, DiningPinkStd9Main, DiningPinkStd10Main);

        ImageView[] DiningMainYellow = new ImageView[10];
        FillDiningHall(DiningMainYellow, DiningYellowStd1Main, DiningYellowStd2Main, DiningYellowStd3Main, DiningYellowStd4Main, DiningYellowStd5Main, DiningYellowStd6Main, DiningYellowStd7Main, DiningYellowStd8Main, DiningYellowStd9Main, DiningYellowStd10Main);

        houseMap = new HashMap<>(gm.getArrayPlayers()[numMain].getDashboard().getDiningHall().getStudents());
        i = 0;
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
            ProfRedMain.setVisible(false);
        } else {
            ProfRedMain.setVisible(true);
        }
        if (profMap.get(House.BLUE)) {
            image = new Image("images/professors/teacher_blue.png");
            ProfBlueMain.setImage(image);
            ProfBlueMain.setVisible(true);
        } else {
            ProfBlueMain.setVisible(false);
        }

        ImageView[] TowersMain = new ImageView[8];
        FillTowers(TowersMain, Tower1Main, Tower2Main, Tower3Main, Tower4Main, Tower5Main, Tower6Main, Tower7Main, Tower8Main);

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

        //second player
        ImageView[] EntranceDashboard1 = new ImageView[9];
        FillEntrance(EntranceDashboard1, EntranceStudDashboard1Stud1, EntranceStudDashboard1Stud2, EntranceStudDashboard1Stud3, EntranceStudDashboard1Stud4, EntranceStudDashboard1Stud5, EntranceStudDashboard1Stud6, EntranceStudDashboard1Stud7, EntranceStudDashboard1Stud8, EntranceStudDashboard1Stud9);

        fillGraveyard(GraveyardDashboard1, gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()]);
        fillCardInUse(CardInUseDashboard1, gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()]);

        houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getStudents());
        i = numStudents;
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

        ImageView[] DiningDashboard1Red = new ImageView[10];
        FillDiningHall(DiningDashboard1Red, DiningRedStd1Dashboard1, DiningRedStd2Dashboard1, DiningRedStd3Dashboard1, DiningRedStd4Dashboard1, DiningRedStd5Dashboard1, DiningRedStd6Dashboard1, DiningRedStd7Dashboard1, DiningRedStd8Dashboard1, DiningRedStd9Dashboard1, DiningRedStd10Dashboard1);

        ImageView[] DiningDashboard1Green = new ImageView[10];
        FillDiningHall(DiningDashboard1Green, DiningGreenStd1Dashboard1, DiningGreenStd2Dashboard1, DiningGreenStd3Dashboard1, DiningGreenStd4Dashboard1, DiningGreenStd5Dashboard1, DiningGreenStd6Dashboard1, DiningGreenStd7Dashboard1, DiningGreenStd8Dashboard1, DiningGreenStd9Dashboard1, DiningGreenStd10Dashboard1);

        ImageView[] DiningDashboard1Blue = new ImageView[10];
        FillDiningHall(DiningDashboard1Blue, DiningBlueStd1Dashboard1, DiningBlueStd2Dashboard1, DiningBlueStd3Dashboard1, DiningBlueStd4Dashboard1, DiningBlueStd5Dashboard1, DiningBlueStd6Dashboard1, DiningBlueStd7Dashboard1, DiningBlueStd8Dashboard1, DiningBlueStd9Dashboard1, DiningBlueStd10Dashboard1);

        ImageView[] DiningDashboard1Pink = new ImageView[10];
        FillDiningHall(DiningDashboard1Pink, DiningPinkStd1Dashboard1, DiningPinkStd2Dashboard1, DiningPinkStd3Dashboard1, DiningPinkStd4Dashboard1, DiningPinkStd5Dashboard1, DiningPinkStd6Dashboard1, DiningPinkStd7Dashboard1, DiningPinkStd8Dashboard1, DiningPinkStd9Dashboard1, DiningPinkStd10Dashboard1);

        ImageView[] DiningDashboard1Yellow = new ImageView[10];
        FillDiningHall(DiningDashboard1Yellow, DiningYellowStd1Dashboard1, DiningYellowStd2Dashboard1, DiningYellowStd3Dashboard1, DiningYellowStd4Dashboard1, DiningYellowStd5Dashboard1, DiningYellowStd6Dashboard1, DiningYellowStd7Dashboard1, DiningYellowStd8Dashboard1, DiningYellowStd9Dashboard1, DiningYellowStd10Dashboard1);

        houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getDiningHall().getStudents());
        i = 0;
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

        ImageView[] TowersDashboard1 = new ImageView[8];
        FillTowers(TowersDashboard1, Tower1Dashboard1, Tower2Dashboard1, Tower3Dashboard1, Tower4Dashboard1, Tower5Dashboard1, Tower6Dashboard1, Tower7Dashboard1, Tower8Dashboard1);

        numTow = gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getNumTowersIn();
        colorTower = gm.getArrayPlayers()[(numMain + 1) % gm.getNumPlayers()].getDashboard().getTowerColor();
        i = numTowers;

        if (colorTower == BLACK) {
            path = "images/towers/torre_nera.png";
        } else if (colorTower == Color.GRAY) {
            path = "images/towers/torre_grigia.png";
        } else {
            path ="images/towers/torre_bianca.png";
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

        if (gm.getNumPlayers() == 2) { //show of hid dashboards
            Dashboard2.setVisible(false);
            Dashboard3.setVisible(false);
            Chat.setVisible(false);
            Chat.setDisable(true);
            ChatAlternative.setVisible(true);
            TwoDashboardAlternative.setVisible(true);
        } else if (gm.getNumPlayers() > 2) {
            //third player
            ImageView[] EntranceDashboard2 = new ImageView[9];
            FillEntrance(EntranceDashboard2, EntranceStudDashboard2Stud1, EntranceStudDashboard2Stud2, EntranceStudDashboard2Stud3, EntranceStudDashboard2Stud4, EntranceStudDashboard2Stud5, EntranceStudDashboard2Stud6, EntranceStudDashboard2Stud7, EntranceStudDashboard2Stud8, EntranceStudDashboard2Stud9);

            fillGraveyard(GraveyardDashboard2, gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()]);
            fillCardInUse(CardInUseDashboard2, gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()]);

            houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getStudents());
            i = numStudents;
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

            ImageView[] DiningDashboard2Red = new ImageView[10];
            FillDiningHall(DiningDashboard2Red, DiningRedStd1Dashboard2, DiningRedStd2Dashboard2, DiningRedStd3Dashboard2, DiningRedStd4Dashboard2, DiningRedStd5Dashboard2, DiningRedStd6Dashboard2, DiningRedStd7Dashboard2, DiningRedStd8Dashboard2, DiningRedStd9Dashboard2, DiningRedStd10Dashboard2);

            ImageView[] DiningDashboard2Green = new ImageView[10];
            FillDiningHall(DiningDashboard2Green, DiningGreenStd1Dashboard2, DiningGreenStd2Dashboard2, DiningGreenStd3Dashboard2, DiningGreenStd4Dashboard2, DiningGreenStd5Dashboard2, DiningGreenStd6Dashboard2, DiningGreenStd7Dashboard2, DiningGreenStd8Dashboard2, DiningGreenStd9Dashboard2, DiningGreenStd10Dashboard2);

            ImageView[] DiningDashboard2Blue = new ImageView[10];
            FillDiningHall(DiningDashboard2Blue, DiningBlueStd1Dashboard2, DiningBlueStd2Dashboard2, DiningBlueStd3Dashboard2, DiningBlueStd4Dashboard2, DiningBlueStd5Dashboard2, DiningBlueStd6Dashboard2, DiningBlueStd7Dashboard2, DiningBlueStd8Dashboard2, DiningBlueStd9Dashboard2, DiningBlueStd10Dashboard2);

            ImageView[] DiningDashboard2Pink = new ImageView[10];
            FillDiningHall(DiningDashboard2Pink, DiningPinkStd1Dashboard2, DiningPinkStd2Dashboard2, DiningPinkStd3Dashboard2, DiningPinkStd4Dashboard2, DiningPinkStd5Dashboard2, DiningPinkStd6Dashboard2, DiningPinkStd7Dashboard2, DiningPinkStd8Dashboard2, DiningPinkStd9Dashboard2, DiningPinkStd10Dashboard2);

            ImageView[] DiningDashboard2Yellow = new ImageView[10];
            FillDiningHall(DiningDashboard2Yellow, DiningYellowStd1Dashboard2, DiningYellowStd2Dashboard2, DiningYellowStd3Dashboard2, DiningYellowStd4Dashboard2, DiningYellowStd5Dashboard2, DiningYellowStd6Dashboard2, DiningYellowStd7Dashboard2, DiningYellowStd8Dashboard2, DiningYellowStd9Dashboard2, DiningYellowStd10Dashboard2);

            houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getDiningHall().getStudents());
            i = 0;
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

            ImageView[] TowersDashboard2 = new ImageView[8];
            FillTowers(TowersDashboard2, Tower1Dashboard2, Tower2Dashboard2, Tower3Dashboard2, Tower4Dashboard2, Tower5Dashboard2, Tower6Dashboard2, Tower7Dashboard2, Tower8Dashboard2);

            numTow = gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getNumTowersIn();
            colorTower = gm.getArrayPlayers()[(numMain + 2) % gm.getNumPlayers()].getDashboard().getTowerColor();
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
                TowersDashboard2[i].setImage(image);
                TowersDashboard2[i].setVisible(true);
                i--;
                numTow--;
            }
            while (i >= 0) {
                TowersDashboard2[i].setVisible(false);
                i--;
            }


            if (gm.getNumPlayers() == 4) {
                if (!gm.isChat()) {
                    Chat.setVisible(false);
                    Chat.setDisable(true);
                    ChatAlternative.setVisible(true);
                }
                //fourth player
                ImageView[] EntranceDashboard3 = new ImageView[9];
                FillEntrance(EntranceDashboard3, EntranceStudDashboard3Stud1, EntranceStudDashboard3Stud2, EntranceStudDashboard3Stud3, EntranceStudDashboard3Stud4, EntranceStudDashboard3Stud5, EntranceStudDashboard3Stud6, EntranceStudDashboard3Stud7, EntranceStudDashboard3Stud8, EntranceStudDashboard3Stud9);

                fillGraveyard(GraveyardDashboard3, gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()]);
                fillCardInUse(CardInUseDashboard3, gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()]);

                houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getStudents());
                i = numStudents;
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
                    image = new Image("students/student_yellow.png");
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

                ImageView[] DiningDashboard3Red = new ImageView[10];
                FillDiningHall(DiningDashboard3Red, DiningRedStd1Dashboard3, DiningRedStd2Dashboard3, DiningRedStd3Dashboard3, DiningRedStd4Dashboard3, DiningRedStd5Dashboard3, DiningRedStd6Dashboard3, DiningRedStd7Dashboard3, DiningRedStd8Dashboard3, DiningRedStd9Dashboard3, DiningRedStd10Dashboard3);

                ImageView[] DiningDashboard3Green = new ImageView[10];
                FillDiningHall(DiningDashboard3Green, DiningGreenStd1Dashboard3, DiningGreenStd2Dashboard3, DiningGreenStd3Dashboard3, DiningGreenStd4Dashboard3, DiningGreenStd5Dashboard3, DiningGreenStd6Dashboard3, DiningGreenStd7Dashboard3, DiningGreenStd8Dashboard3, DiningGreenStd9Dashboard3, DiningGreenStd10Dashboard3);

                ImageView[] DiningDashboard3Blue = new ImageView[10];
                FillDiningHall(DiningDashboard3Blue, DiningBlueStd1Dashboard3, DiningBlueStd2Dashboard3, DiningBlueStd3Dashboard3, DiningBlueStd4Dashboard3, DiningBlueStd5Dashboard3, DiningBlueStd6Dashboard3, DiningBlueStd7Dashboard3, DiningBlueStd8Dashboard3, DiningBlueStd9Dashboard3, DiningBlueStd10Dashboard3);

                ImageView[] DiningDashboard3Pink = new ImageView[10];
                FillDiningHall(DiningDashboard3Pink, DiningPinkStd1Dashboard3, DiningPinkStd2Dashboard3, DiningPinkStd3Dashboard3, DiningPinkStd4Dashboard3, DiningPinkStd5Dashboard3, DiningPinkStd6Dashboard3, DiningPinkStd7Dashboard3, DiningPinkStd8Dashboard3, DiningPinkStd9Dashboard3, DiningPinkStd10Dashboard3);

                ImageView[] DiningDashboard3Yellow = new ImageView[10];
                FillDiningHall(DiningDashboard3Yellow, DiningYellowStd1Dashboard3, DiningYellowStd2Dashboard3, DiningYellowStd3Dashboard3, DiningYellowStd4Dashboard3, DiningYellowStd5Dashboard3, DiningYellowStd6Dashboard3, DiningYellowStd7Dashboard3, DiningYellowStd8Dashboard3, DiningYellowStd9Dashboard3, DiningYellowStd10Dashboard3);

                houseMap = new HashMap<>(gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getDiningHall().getStudents());
                i = 0;
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

                ImageView[] TowersDashboard3 = new ImageView[8];
                FillTowers(TowersDashboard3, Tower1Dashboard3, Tower2Dashboard3, Tower3Dashboard3, Tower4Dashboard3, Tower5Dashboard3, Tower6Dashboard3, Tower7Dashboard3, Tower8Dashboard3);

                numTow = gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getNumTowersIn();
                colorTower = gm.getArrayPlayers()[(numMain + 3) % gm.getNumPlayers()].getDashboard().getTowerColor();
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
                    TowersDashboard3[i].setImage(image);
                    TowersDashboard3[i].setVisible(true);
                    i--;
                    numTow--;
                }
                while (i >= 0) {
                    TowersDashboard3[i].setVisible(false);
                    i--;
                }
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
            setCoins(NumOfCoins, gm.getArrayPlayers()[numMain]);
            CostCharacterCard1.setText(Integer.toString(gm.getCharacterCardDeck()[0].getCost()));
            CostCharacterCard2.setText(Integer.toString(gm.getCharacterCardDeck()[1].getCost()));
            CostCharacterCard3.setText(Integer.toString(gm.getCharacterCardDeck()[2].getCost()));

            switch (gm.getCharacterCardDeck()[0].getType()) {
                case FARMER:
                    path = "images/CharacterCards/Farmer.jpg";
                    break;
                case MAGICAL_MAILMAN:
                    path = "images/CharacterCards/MagicalMailman.jpg";
                    break;
                case HERB_GRANMA:
                    path = "images/CharacterCards/HerbGranma.jpg";
                    break;
                case MINSTREL:
                    path = "images/CharacterCards/Minstrel.jpg";
                    break;
                case MONK:
                    path = "images/CharacterCards/Monk.jpg";
                    break;
                case HERALD:
                    path = "images/CharacterCards/Herald.jpg";
                    break;
                case CENTAUR:
                    path = "images/CharacterCards/Centaur.jpg";
                    break;
                case JOLLY:
                    path = "images/CharacterCards/Jolly.jpg";
                    break;
                case KNIGHT:
                    path = "images/CharacterCards/Knight.jpg";
                    break;
                case MUSHROOM_HUNTER:
                    path = "images/CharacterCards/MushroomHunter.jpg";
                    break;
                case SPOILED_PRINCESS:
                    path = "images/CharacterCards/SpoiledPrincess.jpg";
                    break;
                case THIEF:
                    path = "images/CharacterCards/Thief.jpg";
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
                    break;
                case MINSTREL:
                    path = "images/CharacterCards/Minstrel.jpg";
                    break;
                case MONK:
                    path = "images/CharacterCards/Monk.jpg";
                    break;
                case HERALD:
                    path = "images/CharacterCards/Herald.jpg";
                    break;
                case CENTAUR:
                    path = "images/CharacterCards/Centaur.jpg";
                    break;
                case JOLLY:
                    path = "images/CharacterCards/Jolly.jpg";
                    break;
                case KNIGHT:
                    path = "images/CharacterCards/Knight.jpg";
                    break;
                case MUSHROOM_HUNTER:
                    path = "images/CharacterCards/MushroomHunter.jpg";
                    break;
                case SPOILED_PRINCESS:
                    path = "images/CharacterCards/SpoiledPrincess.jpg";
                    break;
                case THIEF:
                    path = "images/CharacterCards/Thief.jpg";
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
                    break;
                case MINSTREL:
                    path = "images/CharacterCards/Minstrel.jpg";
                    break;
                case MONK:
                    path = "images/CharacterCards/Monk.jpg";
                    break;
                case HERALD:
                    path = "images/CharacterCards/Herald.jpg";
                    break;
                case CENTAUR:
                    path = "images/CharacterCards/Centaur.jpg";
                    break;
                case JOLLY:
                    path = "images/CharacterCards/Jolly.jpg";
                    break;
                case KNIGHT:
                    path = "images/CharacterCards/Knight.jpg";
                    break;
                case MUSHROOM_HUNTER:
                    path = "images/CharacterCards/MushroomHunter.jpg";
                    break;
                case SPOILED_PRINCESS:
                    path = "images/CharacterCards/SpoiledPrincess.jpg";
                    break;
                case THIEF:
                    path = "images/CharacterCards/Thief.jpg";
                    break;
            }
            image = new Image(path);
            CharacterCard3.setImage(image);
        }
        if (gm.getNumPlayers() == 3) {
            Cloud3Num1.setVisible(false);
            Cloud3Num2.setVisible(false);
            Cloud3Num3.setVisible(false);
            Cloud3Num4.setVisible(false);

            ImageView[] Cloud1 = new ImageView[4];
            FillCloud4(Cloud1, Stud1Cloud4Num1, Stud2Cloud4Num1, Stud3Cloud4Num1, Stud4Cloud4Num1);

            ImageView[] Cloud2 = new ImageView[4];
            FillCloud4(Cloud2, Stud1Cloud4Num2, Stud2Cloud4Num2, Stud3Cloud4Num2, Stud4Cloud4Num2);

            ImageView[] Cloud3 = new ImageView[4];
            FillCloud4(Cloud3, Stud1Cloud4Num3, Stud2Cloud4Num3, Stud3Cloud4Num3, Stud4Cloud4Num3);

            houseMap = new HashMap<>(gm.getArrayClouds()[0].getStudents());

            i = 0;
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

            ImageView[] Cloud1 = new ImageView[3];
            FillCloud3(Cloud1, Stud1Cloud3Num1, Stud2Cloud3Num1, Stud3Cloud3Num1);

            ImageView[] Cloud2 = new ImageView[3];
            FillCloud3(Cloud2, Stud1Cloud3Num2, Stud2Cloud3Num2, Stud3Cloud3Num2);

            houseMap = new HashMap<>(gm.getArrayClouds()[0].getStudents());
            i = 0;
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
                ImageView[] Cloud3 = new ImageView[3];
                FillCloud3(Cloud3, Stud1Cloud3Num3, Stud2Cloud3Num3, Stud3Cloud3Num3);

                ImageView[] Cloud4 = new ImageView[3];
                FillCloud3(Cloud4, Stud1Cloud3Num4, Stud2Cloud3Num4, Stud3Cloud3Num4);

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

        islandList = new ArrayList<>();
        for (i = 0; i < 12; i++) {
            islandList.add(new HashMap<>());
        }
        islandList.get(0).put("Pane", Island0);
        islandList.get(0).put("Yellow", StudYellowIsland0);
        islandList.get(0).put("Red", StudRedIsland0);
        islandList.get(0).put("Blue", StudBlueIsland0);
        islandList.get(0).put("Pink", StudPinkIsland0);
        islandList.get(0).put("Green", StudGreenIsland0);
        islandList.get(0).put("Tower", TowerIsland0);
        islandList.get(0).put("TowerNumber", TowerNumberIsland0);
        islandList.get(0).put("NoEntryTile", NoEntryTileIsland0);
        islandList.get(0).put("Mother", MotherIsland0);

        islandList.get(1).put("Pane", Island1);
        islandList.get(1).put("Yellow", StudYellowIsland1);
        islandList.get(1).put("Red", StudRedIsland1);
        islandList.get(1).put("Blue", StudBlueIsland1);
        islandList.get(1).put("Pink", StudPinkIsland1);
        islandList.get(1).put("Green", StudGreenIsland1);
        islandList.get(1).put("Tower", TowerIsland1);
        islandList.get(1).put("TowerNumber", TowerNumberIsland1);
        islandList.get(1).put("NoEntryTile", NoEntryTileIsland1);
        islandList.get(1).put("Mother", MotherIsland1);

        islandList.get(2).put("Pane", Island2);
        islandList.get(2).put("Yellow", StudYellowIsland2);
        islandList.get(2).put("Red", StudRedIsland2);
        islandList.get(2).put("Blue", StudBlueIsland2);
        islandList.get(2).put("Pink", StudPinkIsland2);
        islandList.get(2).put("Green", StudGreenIsland2);
        islandList.get(2).put("Tower", TowerIsland2);
        islandList.get(2).put("TowerNumber", TowerNumberIsland2);
        islandList.get(2).put("NoEntryTile", NoEntryTileIsland2);
        islandList.get(2).put("Mother", MotherIsland2);

        islandList.get(3).put("Pane", Island3);
        islandList.get(3).put("Yellow", StudYellowIsland3);
        islandList.get(3).put("Red", StudRedIsland3);
        islandList.get(3).put("Blue", StudBlueIsland3);
        islandList.get(3).put("Pink", StudPinkIsland3);
        islandList.get(3).put("Green", StudGreenIsland3);
        islandList.get(3).put("Tower", TowerIsland3);
        islandList.get(3).put("TowerNumber", TowerNumberIsland3);
        islandList.get(3).put("NoEntryTile", NoEntryTileIsland3);
        islandList.get(3).put("Mother", MotherIsland3);

        islandList.get(4).put("Pane", Island4);
        islandList.get(4).put("Yellow", StudYellowIsland4);
        islandList.get(4).put("Red", StudRedIsland4);
        islandList.get(4).put("Blue", StudBlueIsland4);
        islandList.get(4).put("Pink", StudPinkIsland4);
        islandList.get(4).put("Green", StudGreenIsland4);
        islandList.get(4).put("Tower", TowerIsland4);
        islandList.get(4).put("TowerNumber", TowerNumberIsland4);
        islandList.get(4).put("NoEntryTile", NoEntryTileIsland4);
        islandList.get(4).put("Mother", MotherIsland4);

        islandList.get(5).put("Pane", Island5);
        islandList.get(5).put("Yellow", StudYellowIsland5);
        islandList.get(5).put("Red", StudRedIsland5);
        islandList.get(5).put("Blue", StudBlueIsland5);
        islandList.get(5).put("Pink", StudPinkIsland5);
        islandList.get(5).put("Green", StudGreenIsland5);
        islandList.get(5).put("Tower", TowerIsland5);
        islandList.get(5).put("TowerNumber", TowerNumberIsland5);
        islandList.get(5).put("NoEntryTile", NoEntryTileIsland5);
        islandList.get(5).put("Mother", MotherIsland5);

        islandList.get(6).put("Pane", Island6);
        islandList.get(6).put("Yellow", StudYellowIsland6);
        islandList.get(6).put("Red", StudRedIsland6);
        islandList.get(6).put("Blue", StudBlueIsland6);
        islandList.get(6).put("Pink", StudPinkIsland6);
        islandList.get(6).put("Green", StudGreenIsland6);
        islandList.get(6).put("Tower", TowerIsland6);
        islandList.get(6).put("TowerNumber", TowerNumberIsland6);
        islandList.get(6).put("NoEntryTile", NoEntryTileIsland6);
        islandList.get(6).put("Mother", MotherIsland6);

        islandList.get(7).put("Pane", Island7);
        islandList.get(7).put("Yellow", StudYellowIsland7);
        islandList.get(7).put("Red", StudRedIsland7);
        islandList.get(7).put("Blue", StudBlueIsland7);
        islandList.get(7).put("Pink", StudPinkIsland7);
        islandList.get(7).put("Green", StudGreenIsland7);
        islandList.get(7).put("Tower", TowerIsland7);
        islandList.get(7).put("TowerNumber", TowerNumberIsland7);
        islandList.get(7).put("NoEntryTile", NoEntryTileIsland7);
        islandList.get(7).put("Mother", MotherIsland7);

        islandList.get(8).put("Pane", Island8);
        islandList.get(8).put("Yellow", StudYellowIsland8);
        islandList.get(8).put("Red", StudRedIsland8);
        islandList.get(8).put("Blue", StudBlueIsland8);
        islandList.get(8).put("Pink", StudPinkIsland8);
        islandList.get(8).put("Green", StudGreenIsland8);
        islandList.get(8).put("Tower", TowerIsland8);
        islandList.get(8).put("TowerNumber", TowerNumberIsland8);
        islandList.get(8).put("NoEntryTile", NoEntryTileIsland8);
        islandList.get(8).put("Mother", MotherIsland8);

        islandList.get(9).put("Pane", Island9);
        islandList.get(9).put("Yellow", StudYellowIsland9);
        islandList.get(9).put("Red", StudRedIsland9);
        islandList.get(9).put("Blue", StudBlueIsland9);
        islandList.get(9).put("Pink", StudPinkIsland9);
        islandList.get(9).put("Green", StudGreenIsland9);
        islandList.get(9).put("Tower", TowerIsland9);
        islandList.get(9).put("TowerNumber", TowerNumberIsland9);
        islandList.get(9).put("NoEntryTile", NoEntryTileIsland9);
        islandList.get(9).put("Mother", MotherIsland9);

        islandList.get(10).put("Pane", Island10);
        islandList.get(10).put("Yellow", StudYellowIsland10);
        islandList.get(10).put("Red", StudRedIsland10);
        islandList.get(10).put("Blue", StudBlueIsland10);
        islandList.get(10).put("Pink", StudPinkIsland10);
        islandList.get(10).put("Green", StudGreenIsland10);
        islandList.get(10).put("Tower", TowerIsland10);
        islandList.get(10).put("TowerNumber", TowerNumberIsland10);
        islandList.get(10).put("NoEntryTile", NoEntryTileIsland10);
        islandList.get(10).put("Mother", MotherIsland10);

        islandList.get(11).put("Pane", Island11);
        islandList.get(11).put("Yellow", StudYellowIsland11);
        islandList.get(11).put("Red", StudRedIsland11);
        islandList.get(11).put("Blue", StudBlueIsland11);
        islandList.get(11).put("Pink", StudPinkIsland11);
        islandList.get(11).put("Green", StudGreenIsland11);
        islandList.get(11).put("Tower", TowerIsland11);
        islandList.get(11).put("TowerNumber", TowerNumberIsland11);
        islandList.get(11).put("NoEntryTile", NoEntryTileIsland11);
        islandList.get(11).put("Mother", MotherIsland11);

        i = gm.getIslandList().size();
        while (i < 12) {
            islandList.get(i).get("Pane").setVisible(false);
            islandList.get(i).get("Pane").setDisable(true);
            i++;
        }

        initializeIsland();

        Dashboard1.setDisable(true);
        Dashboard2.setDisable(true);
        Dashboard3.setDisable(true);

        selectStudent = this::selectStudent;
        selectStudentCancel = this::selectStudentCancel;
        moveMotherFrom = this::moveMotherFrom;
        moveStudentToIsland = this::moveStudentToIsland;
        moveMotherTo = this::moveMotherTo;

        for(ImageView student : EntranceMain){
            student.setOnMouseClicked(selectStudent);
        }

        if(moveMother) {
            islandButtons[gm.getMotherIsland()].setOnMouseClicked(moveMotherFrom);
        }
        else {
            for (Button island : islandButtons) {
                island.setOnMouseClicked(moveStudentToIsland);
            }
        }

        System.out.println(gm.getPlayerByNickname(nickname).getDashboard());
        System.out.println(gm.getIslandList());
    }

    private void FillCloud4(ImageView[] cloud1, ImageView stud1Cloud4Num1, ImageView stud2Cloud4Num1, ImageView stud3Cloud4Num1, ImageView stud4Cloud4Num1) {
        cloud1[0] = stud1Cloud4Num1;
        cloud1[1] = stud2Cloud4Num1;
        cloud1[2] = stud3Cloud4Num1;
        cloud1[3] = stud4Cloud4Num1;
    }

    private void FillCloud3(ImageView[] cloud1, ImageView stud1Cloud3Num1, ImageView stud2Cloud3Num1, ImageView stud3Cloud3Num1) {
        cloud1[0] = stud1Cloud3Num1;
        cloud1[1] = stud2Cloud3Num1;
        cloud1[2] = stud3Cloud3Num1;
    }

    private void FillTowers(ImageView[] TowersMain, ImageView Tower1Main, ImageView Tower2Main, ImageView Tower3Main, ImageView Tower4Main, ImageView Tower5Main, ImageView Tower6Main, ImageView Tower7Main, ImageView Tower8Main) {
        TowersMain[0] = Tower1Main;
        TowersMain[1] = Tower2Main;
        TowersMain[2] = Tower3Main;
        TowersMain[3] = Tower4Main;
        TowersMain[4] = Tower5Main;
        TowersMain[5] = Tower6Main;
        TowersMain[6] = Tower7Main;
        TowersMain[7] = Tower8Main;
    }

    private void FillDiningHall(ImageView[] diningMainRed, ImageView diningRedStd1Main, ImageView diningRedStd2Main, ImageView diningRedStd3Main, ImageView diningRedStd4Main, ImageView diningRedStd5Main, ImageView diningRedStd6Main, ImageView diningRedStd7Main, ImageView diningRedStd8Main, ImageView diningRedStd9Main, ImageView diningRedStd10Main) {
        diningMainRed[0] = diningRedStd1Main;
        diningMainRed[1] = diningRedStd2Main;
        diningMainRed[2] = diningRedStd3Main;
        diningMainRed[3] = diningRedStd4Main;
        diningMainRed[4] = diningRedStd5Main;
        diningMainRed[5] = diningRedStd6Main;
        diningMainRed[6] = diningRedStd7Main;
        diningMainRed[7] = diningRedStd8Main;
        diningMainRed[8] = diningRedStd9Main;
        diningMainRed[9] = diningRedStd10Main;
    }

    private void FillEntrance(ImageView[] Entrance, ImageView diningRedStd1Main, ImageView diningRedStd2Main, ImageView diningRedStd3Main, ImageView diningRedStd4Main, ImageView diningRedStd5Main, ImageView diningRedStd6Main, ImageView diningRedStd7Main, ImageView diningRedStd8Main, ImageView diningRedStd9Main) {
        Entrance[0] = diningRedStd1Main;
        Entrance[1] = diningRedStd2Main;
        Entrance[2] = diningRedStd3Main;
        Entrance[3] = diningRedStd4Main;
        Entrance[4] = diningRedStd5Main;
        Entrance[5] = diningRedStd6Main;
        Entrance[6] = diningRedStd7Main;
        Entrance[7] = diningRedStd8Main;
        Entrance[8] = diningRedStd9Main;
    }

    private void fillGraveyard(ImageView imgview, ReducedPlayer p) {

        Image image;

        if(p.getGraveyard() != null) {
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
            switch (p.getWizard()){
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

        if(p.getCardInUse() != null) {
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

    private void selectStudent(MouseEvent event) {
        ImageView studentSelected = (ImageView) event.getSource();
        System.out.println("Student selected: " + studentSelected.getId());

        houseSelected = getHouseById(studentSelected.getId());

        for(ImageView student : EntranceMain){
            if(! studentSelected.getId().equals(student.getId())) {
                student.removeEventHandler(MouseEvent.MOUSE_CLICKED, selectStudent);
                student.setOpacity(0.8);
            }
        }

        studentSelected.setOnMouseClicked(selectStudentCancel);

        //enables the diningHall of the house selected buttons
        getButtonByHouse(houseSelected).setDisable(false);

        //enable all the islands
        for(Button b : islandButtons) {
            b.setDisable(false);
        }
    }

    private void selectStudentCancel(MouseEvent mouseEvent) {
        for(ImageView student : EntranceMain){
            student.setOpacity(1);
            student.setOnMouseClicked(selectStudent);
        }

        for(Button b : islandButtons) {
            b.removeEventHandler(MouseEvent.MOUSE_CLICKED, moveStudentToIsland);
        }

        getButtonByHouse(houseSelected).setDisable(true);

        houseSelected = null;
    }

    public void moveStudentToDiningHall(MouseEvent event) {
        notifyObserver(observer -> observer.onMoveStudentsToDiningHall(houseSelected));
    }

    private void moveStudentToIsland(MouseEvent event) {

        Button islandClicked = (Button) event.getSource();

        for(Button b : islandButtons) {
            b.removeEventHandler(MouseEvent.MOUSE_CLICKED, moveStudentToIsland);
        }

        for(ImageView student : EntranceMain){
            student.removeEventHandler(MouseEvent.MOUSE_CLICKED, selectStudent);
            student.setOpacity(1);
        }

        int islandPosition = getIslandById(islandClicked.getId());
        System.out.println("islandPosition: "+ islandPosition);
        notifyObserver(observer -> observer.onMoveStudentsToIsland(houseSelected, islandPosition));
    }

    private House getHouseById(String id) {
        int position = Integer.parseInt(String.valueOf(id.charAt(id.length() - 1))) - 1;

        System.out.println(entranceArray[position]);
        return entranceArray[position];
    }

    private Button getButtonByHouse(House house) throws IllegalArgumentException {

        switch (house){
            case GREEN:
                return diningHallMain[0];
            case RED:
                return diningHallMain[1];
            case YELLOW:
                return diningHallMain[2];
            case BLUE:
                return diningHallMain[3];
            case PINK:
                return diningHallMain[4];
            default:
                throw new IllegalArgumentException("You did not select the correct button");
        }
    }

    private int getIslandById (String id) {
        return Integer.parseInt(String.valueOf(id.charAt(6))) * 10
                + Integer.parseInt(String.valueOf(id.charAt(7)));
    }

    public void setGm(ReducedGameModel gm) {
        this.gm = gm;
    }

    public void setMoveMother(){
        this.moveMother = true;
    }

    private void moveMotherFrom(MouseEvent event) {

        int currentIsland = gm.getMotherIsland();

        for(int i=0; i<gm.getPlayerByNickname(nickname).getMaxMoves(); i++) {
            currentIsland++;
            if(currentIsland == gm.getIslandList().size()) {
                currentIsland = 0;
            }
            islandButtons[currentIsland + i].setOnMouseClicked(moveMotherTo);
        }

    }

    private void moveMotherTo(MouseEvent event) {

        int islandTo = getIslandById(((Button)event.getSource()).getId());

        int position = islandTo - gm.getMotherIsland();

        notifyObserver(observer -> observer.onMoveMotherNature(position));
    }
}
