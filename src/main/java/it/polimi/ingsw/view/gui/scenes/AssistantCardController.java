package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.client.ReducedGameModel;
import it.polimi.ingsw.client.ReducedPlayer;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.House;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AssistantCardController extends ViewObservable implements SceneInterface {
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

    private ReducedGameModel gm;
    private String nickname;

    public AssistantCardController(ReducedGameModel gm, String nickname){
        this.gm = gm;
        this.nickname = nickname;
        initialize();
    }

    public void initialize(){

        int numMain = Arrays.asList(gm.getArrayPlayers()).indexOf(gm.getPlayerByNickname(nickname));

        Map<House, Integer> houseMap = new HashMap<>();
        Map<House, Boolean> profMap = new HashMap<>();

        int numStudents = 6;
        if (gm.getNumPlayers() == 3){
            numStudents = 8;
        }
        int numTowers = 5;
        if (gm.getNumPlayers() == 3){
            numTowers = 7;
        }

        int i;

        File file;
        Image image;

        //first player
        ImageView[] EntranceMain = new ImageView[9];
        FillEntrance(EntranceMain, EntranceStudMain1, EntranceStudMain2, EntranceStudMain3, EntranceStudMain4, EntranceStudMain5, EntranceStudMain6, EntranceStudMain7, EntranceStudMain8, EntranceStudMain9);

        fillGraveyard(GraveyardMain, gm.getArrayPlayers()[numMain]);
        fillCardInUse(CardInUseMain, gm.getArrayPlayers()[numMain]);

        houseMap = gm.getArrayPlayers()[numMain].getDashboard().getStudents();
        i = numStudents;
        while (houseMap.get(House.BLUE)!= 0){
            file = new File("images/students/student_blue.png");
            image = new Image(file.toURI().toString());
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i--;
        }
        while (houseMap.get(House.PINK)!= 0){
            file = new File("images/students/student_pink.png");
            image = new Image(file.toURI().toString());
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i--;
        }
        while (houseMap.get(House.YELLOW)!= 0){
            file = new File("images/students_yellow.png");
            image = new Image(file.toURI().toString());
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
            i--;
        }
        while (houseMap.get(House.RED)!= 0){
            file = new File("images/students/student_red.png");
            image = new Image(file.toURI().toString());
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i--;
        }
        while (houseMap.get(House.GREEN)!= 0){
            file = new File("images/students/student_green.png");
            image = new Image(file.toURI().toString());
            EntranceMain[i].setImage(image);
            EntranceMain[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i--;
        }
        while (i>=0){
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

        houseMap = gm.getArrayPlayers()[numMain].getDashboard().getDiningHall().getStudents();
        i = 0;
        while (houseMap.get(House.GREEN)!= 0){
            file = new File("images/students/student_green.png");
            image = new Image(file.toURI().toString());
            DiningMainGreen[i].setImage(image);
            DiningMainGreen[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i++;
        }
        while(i<10){
            DiningMainGreen[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.BLUE)!= 0){
            file = new File("images/students/student_blue.png");
            image = new Image(file.toURI().toString());
            DiningMainBlue[i].setImage(image);
            DiningMainBlue[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i++;
        }
        while(i<10){
            DiningMainBlue[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.RED)!= 0){
            file = new File("images/students/student_red.png");
            image = new Image(file.toURI().toString());
            DiningMainRed[i].setImage(image);
            DiningMainRed[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i++;
        }
        while(i<10){
            DiningMainRed[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.YELLOW)!= 0){
            file = new File("images/students/student_yellow.png");
            image = new Image(file.toURI().toString());
            DiningMainYellow[i].setImage(image);
            DiningMainYellow[i].setVisible(true);
            houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
            i++;
        }
        while(i<10){
            DiningMainYellow[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.PINK)!= 0){
            file = new File("images/students/student_pink.png");
            image = new Image(file.toURI().toString());
            DiningMainPink[i].setImage(image);
            DiningMainPink[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i++;
        }
        while(i<10){
            DiningMainPink[i].setVisible(false);
            i++;
        }

        profMap = gm.getArrayPlayers()[numMain].getDashboard().getProfMap();

        if (profMap.get(House.PINK)){
            file = new File("images/professors/teacher_pink.png");
            image = new Image(file.toURI().toString());
            ProfPinkMain.setImage(image);
            ProfPinkMain.setVisible(true);
        }
        else {
            ProfPinkMain.setVisible(false);
        }
        if (profMap.get(House.GREEN)){
            file = new File("images/professors/teacher_green.png");
            image = new Image(file.toURI().toString());
            ProfGreenMain.setImage(image);
            ProfGreenMain.setVisible(true);
        }
        else {
            ProfGreenMain.setVisible(false);
        }
        if (profMap.get(House.YELLOW)){
            file = new File("images/professors/teacher_yellow.png");
            image = new Image(file.toURI().toString());
            ProfYellowMain.setImage(image);
            ProfYellowMain.setVisible(true);
        }
        else {
            ProfYellowMain.setVisible(false);
        }
        if (profMap.get(House.RED)){
            file = new File("images/professors/teacher_red.png");
            image = new Image(file.toURI().toString());
            ProfRedMain.setImage(image);
            ProfRedMain.setVisible(false);
        }
        else {
            ProfRedMain.setVisible(true);
        }
        if (profMap.get(House.BLUE)){
            file = new File("images/professors/teacher_blue.png");
            image = new Image(file.toURI().toString());
            ProfBlueMain.setImage(image);
            ProfBlueMain.setVisible(true);
        }
        else {
            ProfBlueMain.setVisible(false);
        }

        ImageView[] TowersMain = new ImageView[8];
        FillTowers(TowersMain, Tower1Main, Tower2Main, Tower3Main, Tower4Main, Tower5Main, Tower6Main, Tower7Main, Tower8Main);

        int numTow = gm.getArrayPlayers()[numMain].getDashboard().getNumTowersIn();
        Color colorTower = gm.getArrayPlayers()[numMain].getDashboard().getTowerColor();
        i = numTowers;

        if (colorTower == Color.BLACK){
            file = new File("images/students/torre_nera.png");
        } else if (colorTower == Color.GRAY){
            file = new File("images/students/torre_grigia.png");
        } else {
            file = new File("images/students/torre_bianca.png");
        }

        while (numTow>0){
            image = new Image(file.toURI().toString());
            TowersMain[i].setImage(image);
            TowersMain[i].setVisible(true);
            i--;
            numTow--;
        }
        while (i>=0){
            TowersMain[i].setVisible(false);
            i--;
        }


        //second player
        ImageView[] EntranceDashboard1 = new ImageView[9];
        FillEntrance(EntranceDashboard1, EntranceStudDashboard1Stud1, EntranceStudDashboard1Stud2, EntranceStudDashboard1Stud3, EntranceStudDashboard1Stud4, EntranceStudDashboard1Stud5, EntranceStudDashboard1Stud6, EntranceStudDashboard1Stud7, EntranceStudDashboard1Stud8, EntranceStudDashboard1Stud9);

        fillGraveyard(GraveyardDashboard1, gm.getArrayPlayers()[(numMain+1)%gm.getNumPlayers()]);
        fillCardInUse(CardInUseDashboard1, gm.getArrayPlayers()[(numMain+1)%gm.getNumPlayers()]);

        houseMap = gm.getArrayPlayers()[(numMain+1)%gm.getNumPlayers()].getDashboard().getStudents();
        i = numStudents;
        while (houseMap.get(House.BLUE)!= 0){
            file = new File("images/students/student_blue.png");
            image = new Image(file.toURI().toString());
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i--;
        }
        while (houseMap.get(House.PINK)!= 0){
            file = new File("images/students/student_pink.png");
            image = new Image(file.toURI().toString());
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i--;
        }
        while (houseMap.get(House.YELLOW)!= 0){
            file = new File("images/students_yellow.png");
            image = new Image(file.toURI().toString());
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
            i--;
        }
        while (houseMap.get(House.RED)!= 0){
            file = new File("images/students/student_red.png");
            image = new Image(file.toURI().toString());
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i--;
        }
        while (houseMap.get(House.GREEN)!= 0){
            file = new File("images/students/student_green.png");
            image = new Image(file.toURI().toString());
            EntranceDashboard1[i].setImage(image);
            EntranceDashboard1[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i--;
        }
        while (i>=0){
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

        houseMap = gm.getArrayPlayers()[(numMain+1)%gm.getNumPlayers()].getDashboard().getDiningHall().getStudents();
        i = 0;
        while (houseMap.get(House.GREEN)!= 0){
            file = new File("images/students/student_green.png");
            image = new Image(file.toURI().toString());
            DiningDashboard1Green[i].setImage(image);
            DiningDashboard1Green[i].setVisible(true);
            houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
            i++;
        }
        while(i<10){
            DiningDashboard1Green[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.BLUE)!= 0){
            file = new File("images/students/student_blue.png");
            image = new Image(file.toURI().toString());
            DiningDashboard1Blue[i].setImage(image);
            DiningDashboard1Blue[i].setVisible(true);
            houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
            i++;
        }
        while(i<10){
            DiningDashboard1Blue[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.RED)!= 0){
            file = new File("images/students/student_red.png");
            image = new Image(file.toURI().toString());
            DiningDashboard1Red[i].setImage(image);
            DiningDashboard1Red[i].setVisible(true);
            houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
            i++;
        }
        while(i<10){
            DiningDashboard1Red[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.YELLOW)!= 0){
            file = new File("images/students/student_yellow.png");
            image = new Image(file.toURI().toString());
            DiningDashboard1Yellow[i].setImage(image);
            DiningDashboard1Yellow[i].setVisible(true);
            houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
            i++;
        }
        while(i<10){
            DiningDashboard1Yellow[i].setVisible(false);
            i++;
        }
        i = 0;
        while (houseMap.get(House.PINK)!= 0){
            file = new File("images/students/student_pink.png");
            image = new Image(file.toURI().toString());
            DiningDashboard1Pink[i].setImage(image);
            DiningDashboard1Pink[i].setVisible(true);
            houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
            i++;
        }
        while(i<10){
            DiningDashboard1Pink[i].setVisible(false);
            i++;
        }

        profMap = gm.getArrayPlayers()[(numMain+1)%gm.getNumPlayers()].getDashboard().getProfMap();

        if (profMap.get(House.PINK)){
            file = new File("images/professors/teacher_pink.png");
            image = new Image(file.toURI().toString());
            ProfPinkDashboard1.setImage(image);
            ProfPinkDashboard1.setVisible(true);
        }
        else {
            ProfPinkDashboard1.setVisible(false);
        }
        if (profMap.get(House.GREEN)){
            file = new File("images/professors/teacher_green.png");
            image = new Image(file.toURI().toString());
            ProfGreenDashboard1.setImage(image);
            ProfGreenDashboard1.setVisible(true);
        }
        else {
            ProfGreenDashboard1.setVisible(false);
        }
        if (profMap.get(House.YELLOW)){
            file = new File("images/professors/teacher_yellow.png");
            image = new Image(file.toURI().toString());
            ProfYellowDashboard1.setImage(image);
            ProfYellowDashboard1.setVisible(true);
        }
        else {
            ProfYellowDashboard1.setVisible(false);
        }
        if (profMap.get(House.RED)){
            file = new File("images/professors/teacher_red.png");
            image = new Image(file.toURI().toString());
            ProfRedDashboard1.setImage(image);
            ProfRedDashboard1.setVisible(true);
        }
        else {
            ProfRedDashboard1.setVisible(false);
        }
        if (profMap.get(House.BLUE)){
            file = new File("images/professors/teacher_blue.png");
            image = new Image(file.toURI().toString());
            ProfBlueDashboard1.setImage(image);
            ProfBlueDashboard1.setVisible(true);
        }
        else {
            ProfBlueDashboard1.setVisible(false);
        }

        ImageView[] TowersDashboard1 = new ImageView[8];
        FillTowers(TowersDashboard1, Tower1Dashboard1, Tower2Dashboard1, Tower3Dashboard1, Tower4Dashboard1, Tower5Dashboard1, Tower6Dashboard1, Tower7Dashboard1, Tower8Dashboard1);

        numTow = gm.getArrayPlayers()[(numMain+1)%gm.getNumPlayers()].getDashboard().getNumTowersIn();
        colorTower = gm.getArrayPlayers()[(numMain+1)%gm.getNumPlayers()].getDashboard().getTowerColor();
        i = numTowers;

        if (colorTower == Color.BLACK){
            file = new File("images/students/torre_nera.png");
        } else if (colorTower == Color.GRAY){
            file = new File("images/students/torre_grigia.png");
        } else {
            file = new File("images/students/torre_bianca.png");
        }

        while (numTow>0){
            image = new Image(file.toURI().toString());
            TowersDashboard1[i].setImage(image);
            TowersDashboard1[i].setVisible(true);
            i--;
            numTow--;
        }
        while (i>=0){
            TowersDashboard1[i].setVisible(false);
            i--;
        }

        if (gm.getNumPlayers() == 2){ //show of hid dashboards
            Dashboard2.setVisible(false);
            Dashboard3.setVisible(false);
        } else if (gm.getNumPlayers() > 2){
            //third player
            ImageView[] EntranceDashboard2 = new ImageView[9];
            FillEntrance(EntranceDashboard2, EntranceStudDashboard2Stud1, EntranceStudDashboard2Stud2, EntranceStudDashboard2Stud3, EntranceStudDashboard2Stud4, EntranceStudDashboard2Stud5, EntranceStudDashboard2Stud6, EntranceStudDashboard2Stud7, EntranceStudDashboard2Stud8, EntranceStudDashboard2Stud9);

            fillGraveyard(GraveyardDashboard2, gm.getArrayPlayers()[(numMain+2)%gm.getNumPlayers()]);
            fillCardInUse(CardInUseDashboard2, gm.getArrayPlayers()[(numMain+2)%gm.getNumPlayers()]);

            houseMap = gm.getArrayPlayers()[(numMain+2)%gm.getNumPlayers()].getDashboard().getStudents();
            i = numStudents;
            while (houseMap.get(House.BLUE)!= 0){
                file = new File("images/students/student_blue.png");
                image = new Image(file.toURI().toString());
                EntranceDashboard2[i].setImage(image);
                EntranceDashboard2[i].setVisible(true);
                houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                i--;
            }
            while (houseMap.get(House.PINK)!= 0){
                file = new File("images/students/student_pink.png");
                image = new Image(file.toURI().toString());
                EntranceDashboard2[i].setImage(image);
                EntranceDashboard2[i].setVisible(true);
                houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                i--;
            }
            while (houseMap.get(House.YELLOW)!= 0){
                file = new File("images/students_yellow.png");
                image = new Image(file.toURI().toString());
                EntranceDashboard2[i].setImage(image);
                EntranceDashboard2[i].setVisible(true);
                houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
                i--;
            }
            while (houseMap.get(House.RED)!= 0){
                file = new File("images/students/student_red.png");
                image = new Image(file.toURI().toString());
                EntranceDashboard2[i].setImage(image);
                EntranceDashboard2[i].setVisible(true);
                houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                i--;
            }
            while (houseMap.get(House.GREEN)!= 0){
                file = new File("images/students/student_green.png");
                image = new Image(file.toURI().toString());
                EntranceDashboard2[i].setImage(image);
                EntranceDashboard2[i].setVisible(true);
                houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                i--;
            }
            while (i>=0){
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

            houseMap = gm.getArrayPlayers()[(numMain+2)%gm.getNumPlayers()].getDashboard().getDiningHall().getStudents();
            i = 0;
            while (houseMap.get(House.GREEN)!= 0){
                file = new File("images/students/student_green.png");
                image = new Image(file.toURI().toString());
                DiningDashboard2Green[i].setImage(image);
                DiningDashboard2Green[i].setVisible(true);
                houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                i++;
            }
            while(i<10){
                DiningDashboard2Green[i].setVisible(false);
                i++;
            }
            i = 0;
            while (houseMap.get(House.BLUE)!= 0){
                file = new File("images/students/student_blue.png");
                image = new Image(file.toURI().toString());
                DiningDashboard2Blue[i].setImage(image);
                DiningDashboard2Blue[i].setVisible(true);
                houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                i++;
            }
            while(i<10){
                DiningDashboard2Blue[i].setVisible(false);
                i++;
            }
            i = 0;
            while (houseMap.get(House.RED)!= 0){
                file = new File("images/students/student_red.png");
                image = new Image(file.toURI().toString());
                DiningDashboard2Red[i].setImage(image);
                DiningDashboard2Red[i].setVisible(true);
                houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                i++;
            }
            while(i<10){
                DiningDashboard2Red[i].setVisible(false);
                i++;
            }
            i = 0;
            while (houseMap.get(House.YELLOW)!= 0){
                file = new File("images/students/student_yellow.png");
                image = new Image(file.toURI().toString());
                DiningDashboard2Yellow[i].setImage(image);
                DiningDashboard2Yellow[i].setVisible(true);
                houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
                i++;
            }
            while(i<10){
                DiningDashboard2Yellow[i].setVisible(false);
                i++;
            }
            i = 0;
            while (houseMap.get(House.PINK)!= 0){
                file = new File("images/students/student_pink.png");
                image = new Image(file.toURI().toString());
                DiningDashboard2Pink[i].setImage(image);
                DiningDashboard2Pink[i].setVisible(true);
                houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                i++;
            }
            while(i<10){
                DiningDashboard2Pink[i].setVisible(false);
                i++;
            }

            profMap = gm.getArrayPlayers()[(numMain+2)%gm.getNumPlayers()].getDashboard().getProfMap();

            if (profMap.get(House.PINK)){
                file = new File("images/professors/teacher_pink.png");
                image = new Image(file.toURI().toString());
                ProfPinkDashboard2.setImage(image);
                ProfPinkDashboard2.setVisible(true);
            }
            else {
                ProfPinkDashboard2.setVisible(false);
            }
            if (profMap.get(House.GREEN)){
                file = new File("images/professors/teacher_green.png");
                image = new Image(file.toURI().toString());
                ProfGreenDashboard2.setImage(image);
                ProfGreenDashboard2.setVisible(true);
            }
            else {
                ProfGreenDashboard2.setVisible(false);
            }
            if (profMap.get(House.YELLOW)){
                file = new File("images/professors/teacher_yellow.png");
                image = new Image(file.toURI().toString());
                ProfYellowDashboard2.setImage(image);
                ProfYellowDashboard2.setVisible(true);
            }
            else {
                ProfYellowDashboard2.setVisible(false);
            }
            if (profMap.get(House.RED)){
                file = new File("images/professors/teacher_red.png");
                image = new Image(file.toURI().toString());
                ProfRedDashboard2.setImage(image);
                ProfRedDashboard2.setVisible(true);
            }
            else {
                ProfRedDashboard2.setVisible(false);
            }
            if (profMap.get(House.BLUE)){
                file = new File("images/professors/teacher_blue.png");
                image = new Image(file.toURI().toString());
                ProfBlueDashboard2.setImage(image);
                ProfBlueDashboard2.setVisible(true);
            }
            else {
                ProfBlueDashboard2.setVisible(false);
            }

            ImageView[] TowersDashboard2 = new ImageView[8];
            FillTowers(TowersDashboard2, Tower1Dashboard2, Tower2Dashboard2, Tower3Dashboard2, Tower4Dashboard2, Tower5Dashboard2, Tower6Dashboard2, Tower7Dashboard2, Tower8Dashboard2);

            numTow = gm.getArrayPlayers()[(numMain+2)%gm.getNumPlayers()].getDashboard().getNumTowersIn();
            colorTower = gm.getArrayPlayers()[(numMain+2)%gm.getNumPlayers()].getDashboard().getTowerColor();
            i = numTowers;

            if (colorTower == Color.BLACK){
                file = new File("images/students/torre_nera.png");
            } else if (colorTower == Color.GRAY){
                file = new File("images/students/torre_grigia.png");
            } else {
                file = new File("images/students/torre_bianca.png");
            }

            while (numTow>0){
                image = new Image(file.toURI().toString());
                TowersDashboard2[i].setImage(image);
                TowersDashboard2[i].setVisible(true);
                i--;
                numTow--;
            }
            while (i>=0){
                TowersDashboard2[i].setVisible(false);
                i--;
            }


            if (gm.getNumPlayers() == 4) {
                //fourth player
                ImageView[] EntranceDashboard3 = new ImageView[9];
                FillEntrance(EntranceDashboard3, EntranceStudDashboard3Stud1, EntranceStudDashboard3Stud2, EntranceStudDashboard3Stud3, EntranceStudDashboard3Stud4, EntranceStudDashboard3Stud5, EntranceStudDashboard3Stud6, EntranceStudDashboard3Stud7, EntranceStudDashboard3Stud8, EntranceStudDashboard3Stud9);

                fillGraveyard(GraveyardDashboard3, gm.getArrayPlayers()[(numMain+3)%gm.getNumPlayers()]);
                fillCardInUse(CardInUseDashboard3, gm.getArrayPlayers()[(numMain+3)%gm.getNumPlayers()]);

                houseMap = gm.getArrayPlayers()[(numMain+3)%gm.getNumPlayers()].getDashboard().getStudents();
                i = numStudents;
                while (houseMap.get(House.BLUE)!= 0){
                    file = new File("images/students/student_blue.png");
                    image = new Image(file.toURI().toString());
                    EntranceDashboard3[i].setImage(image);
                    EntranceDashboard3[i].setVisible(true);
                    houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                    i--;
                }
                while (houseMap.get(House.PINK)!= 0){
                    file = new File("images/students/student_pink.png");
                    image = new Image(file.toURI().toString());
                    EntranceDashboard3[i].setImage(image);
                    EntranceDashboard3[i].setVisible(true);
                    houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                    i--;
                }
                while (houseMap.get(House.YELLOW)!= 0){
                    file = new File("images/students_yellow.png");
                    image = new Image(file.toURI().toString());
                    EntranceDashboard3[i].setImage(image);
                    EntranceDashboard3[i].setVisible(true);
                    houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
                    i--;
                }
                while (houseMap.get(House.RED)!= 0){
                    file = new File("images/students/student_red.png");
                    image = new Image(file.toURI().toString());
                    EntranceDashboard3[i].setImage(image);
                    EntranceDashboard3[i].setVisible(true);
                    houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                    i--;
                }
                while (houseMap.get(House.GREEN)!= 0){
                    file = new File("images/students/student_green.png");
                    image = new Image(file.toURI().toString());
                    EntranceDashboard3[i].setImage(image);
                    EntranceDashboard3[i].setVisible(true);
                    houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                    i--;
                }
                while (i>=0){
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

                houseMap = gm.getArrayPlayers()[(numMain+3)%gm.getNumPlayers()].getDashboard().getDiningHall().getStudents();
                i = 0;
                while (houseMap.get(House.GREEN)!= 0){
                    file = new File("images/students/student_green.png");
                    image = new Image(file.toURI().toString());
                    DiningDashboard3Green[i].setImage(image);
                    DiningDashboard3Green[i].setVisible(true);
                    houseMap.replace(House.GREEN, houseMap.get(House.GREEN) - 1);
                    i++;
                }
                while(i<10){
                    DiningDashboard3Green[i].setVisible(false);
                    i++;
                }
                i = 0;
                while (houseMap.get(House.BLUE)!= 0){
                    file = new File("images/students/student_blue.png");
                    image = new Image(file.toURI().toString());
                    DiningDashboard3Blue[i].setImage(image);
                    DiningDashboard3Blue[i].setVisible(true);
                    houseMap.replace(House.BLUE, houseMap.get(House.BLUE) - 1);
                    i++;
                }
                while(i<10){
                    DiningDashboard3Blue[i].setVisible(false);
                    i++;
                }
                i = 0;
                while (houseMap.get(House.RED)!= 0){
                    file = new File("images/students/student_red.png");
                    image = new Image(file.toURI().toString());
                    DiningDashboard3Red[i].setImage(image);
                    DiningDashboard3Red[i].setVisible(true);
                    houseMap.replace(House.RED, houseMap.get(House.RED) - 1);
                    i++;
                }
                while(i<10){
                    DiningDashboard3Red[i].setVisible(false);
                    i++;
                }
                i = 0;
                while (houseMap.get(House.YELLOW)!= 0){
                    file = new File("images/students/student_yellow.png");
                    image = new Image(file.toURI().toString());
                    DiningDashboard3Yellow[i].setImage(image);
                    DiningDashboard3Yellow[i].setVisible(true);
                    houseMap.replace(House.YELLOW, houseMap.get(House.YELLOW) - 1);
                    i++;
                }
                while(i<10){
                    DiningDashboard3Yellow[i].setVisible(false);
                    i++;
                }
                i = 0;
                while (houseMap.get(House.PINK)!= 0){
                    file = new File("images/students/student_pink.png");
                    image = new Image(file.toURI().toString());
                    DiningDashboard3Pink[i].setImage(image);
                    DiningDashboard3Pink[i].setVisible(true);
                    houseMap.replace(House.PINK, houseMap.get(House.PINK) - 1);
                    i++;
                }
                while(i<10){
                    DiningDashboard3Pink[i].setVisible(false);
                    i++;
                }

                profMap = gm.getArrayPlayers()[(numMain+3)%gm.getNumPlayers()].getDashboard().getProfMap();

                if (profMap.get(House.PINK)){
                    file = new File("images/professors/teacher_pink.png");
                    image = new Image(file.toURI().toString());
                    ProfPinkDashboard3.setImage(image);
                    ProfPinkDashboard3.setVisible(true);
                }
                else {
                    ProfPinkDashboard3.setVisible(false);
                }
                if (profMap.get(House.GREEN)){
                    file = new File("images/professors/teacher_green.png");
                    image = new Image(file.toURI().toString());
                    ProfGreenDashboard3.setImage(image);
                    ProfGreenDashboard3.setVisible(true);
                }
                else {
                    ProfGreenDashboard3.setVisible(false);
                }
                if (profMap.get(House.YELLOW)){
                    file = new File("images/professors/teacher_yellow.png");
                    image = new Image(file.toURI().toString());
                    ProfYellowDashboard3.setImage(image);
                    ProfYellowDashboard3.setVisible(true);
                }
                else {
                    ProfYellowDashboard3.setVisible(false);
                }
                if (profMap.get(House.RED)){
                    file = new File("images/professors/teacher_red.png");
                    image = new Image(file.toURI().toString());
                    ProfRedDashboard3.setImage(image);
                    ProfRedDashboard3.setVisible(true);
                }
                else {
                    ProfRedDashboard3.setVisible(false);
                }
                if (profMap.get(House.BLUE)){
                    file = new File("images/professors/teacher_blue.png");
                    image = new Image(file.toURI().toString());
                    ProfBlueDashboard3.setImage(image);
                    ProfBlueDashboard3.setVisible(true);
                }
                else {
                    ProfBlueDashboard3.setVisible(false);
                }

                ImageView[] TowersDashboard3 = new ImageView[8];
                FillTowers(TowersDashboard3, Tower1Dashboard3, Tower2Dashboard3, Tower3Dashboard3, Tower4Dashboard3, Tower5Dashboard3, Tower6Dashboard3, Tower7Dashboard3, Tower8Dashboard3);

                numTow = gm.getArrayPlayers()[(numMain+3)%gm.getNumPlayers()].getDashboard().getNumTowersIn();
                colorTower = gm.getArrayPlayers()[(numMain+3)%gm.getNumPlayers()].getDashboard().getTowerColor();
                i = numTowers;

                if (colorTower == Color.BLACK){
                    file = new File("images/students/torre_nera.png");
                } else if (colorTower == Color.GRAY){
                    file = new File("images/students/torre_grigia.png");
                } else {
                    file = new File("images/students/torre_bianca.png");
                }

                while (numTow>0){
                    image = new Image(file.toURI().toString());
                    TowersDashboard3[i].setImage(image);
                    TowersDashboard3[i].setVisible(true);
                    i--;
                    numTow--;
                }
                while (i>=0){
                    TowersDashboard3[i].setVisible(false);
                    i--;
                }
            }
            else {
                Dashboard3.setVisible(false);
            }
        }

        if (!gm.isExpertMode()){
            CharacterCardsPlacement.setVisible(false);
        } else {
            CharacterCardsPlacement.setVisible(true);
            setCoins(NumOfCoins, gm.getArrayPlayers()[numMain]);
            CostCharacterCard1.setText(Integer.toString(gm.getCharacterCardDeck()[0].getCost()));
            CostCharacterCard2.setText(Integer.toString(gm.getCharacterCardDeck()[1].getCost()));
            CostCharacterCard3.setText(Integer.toString(gm.getCharacterCardDeck()[2].getCost()));

            switch(gm.getCharacterCardDeck()[0].getType()){
                case FARMER:
                    file = new File("images/CharacterCards/Farmer.jpg");
                    break;
                case MAGICAL_MAILMAN:
                    file = new File("images/CharacterCards/MagicalMailman.jpg");
                    break;
                case HERB_GRANMA:
                    file = new File("images/CharacterCards/HerbGranma.jpg");
                    break;
                case MINSTREL:
                    file = new File("images/CharacterCards/Minstrel.jpg");
                    break;
                case MONK:
                    file = new File("images/CharacterCards/Monk.jpg");
                    break;
                case HERALD:
                    file = new File("images/CharacterCards/Herald.jpg");
                    break;
                case CENTAUR:
                    file = new File("images/CharacterCards/Centuar.jpg");
                    break;
                case JOLLY:
                    file = new File("images/CharacterCards/Jolly.jpg");
                    break;
                case KNIGHT:
                    file = new File("images/CharacterCards/Knight.jpg");
                    break;
                case MUSHROOM_HUNTER:
                    file = new File("images/CharacterCards/MushroomHunter.jpg");
                    break;
                case SPOILED_PRINCESS:
                    file = new File("images/CharacterCards/SpoiledPrincess.jpg");
                    break;
                case THIEF:
                    file = new File("images/CharacterCards/Thief.jpg");
                    break;
            }
            image = new Image(file.toURI().toString());
            CharacterCard1.setImage(image);

            switch(gm.getCharacterCardDeck()[1].getType()){
                case FARMER:
                    file = new File("images/CharacterCards/Farmer.jpg");
                    break;
                case MAGICAL_MAILMAN:
                    file = new File("images/CharacterCards/MagicalMailman.jpg");
                    break;
                case HERB_GRANMA:
                    file = new File("images/CharacterCards/HerbGranma.jpg");
                    break;
                case MINSTREL:
                    file = new File("images/CharacterCards/Minstrel.jpg");
                    break;
                case MONK:
                    file = new File("images/CharacterCards/Monk.jpg");
                    break;
                case HERALD:
                    file = new File("images/CharacterCards/Herald.jpg");
                    break;
                case CENTAUR:
                    file = new File("images/CharacterCards/Centuar.jpg");
                    break;
                case JOLLY:
                    file = new File("images/CharacterCards/Jolly.jpg");
                    break;
                case KNIGHT:
                    file = new File("images/CharacterCards/Knight.jpg");
                    break;
                case MUSHROOM_HUNTER:
                    file = new File("images/CharacterCards/MushroomHunter.jpg");
                    break;
                case SPOILED_PRINCESS:
                    file = new File("images/CharacterCards/SpoiledPrincess.jpg");
                    break;
                case THIEF:
                    file = new File("images/CharacterCards/Thief.jpg");
                    break;
            }
            image = new Image(file.toURI().toString());
            CharacterCard2.setImage(image);

            switch(gm.getCharacterCardDeck()[2].getType()){
                case FARMER:
                    file = new File("images/CharacterCards/Farmer.jpg");
                    break;
                case MAGICAL_MAILMAN:
                    file = new File("images/CharacterCards/MagicalMailman.jpg");
                    break;
                case HERB_GRANMA:
                    file = new File("images/CharacterCards/HerbGranma.jpg");
                    break;
                case MINSTREL:
                    file = new File("images/CharacterCards/Minstrel.jpg");
                    break;
                case MONK:
                    file = new File("images/CharacterCards/Monk.jpg");
                    break;
                case HERALD:
                    file = new File("images/CharacterCards/Herald.jpg");
                    break;
                case CENTAUR:
                    file = new File("images/CharacterCards/Centuar.jpg");
                    break;
                case JOLLY:
                    file = new File("images/CharacterCards/Jolly.jpg");
                    break;
                case KNIGHT:
                    file = new File("images/CharacterCards/Knight.jpg");
                    break;
                case MUSHROOM_HUNTER:
                    file = new File("images/CharacterCards/MushroomHunter.jpg");
                    break;
                case SPOILED_PRINCESS:
                    file = new File("images/CharacterCards/SpoiledPrincess.jpg");
                    break;
                case THIEF:
                    file = new File("images/CharacterCards/Thief.jpg");
                    break;
            }
            image = new Image(file.toURI().toString());
            CharacterCard3.setImage(image);
        }
    }

    private void FillTowers(ImageView[] TowersMain, ImageView Tower1Main, ImageView Tower2Main, ImageView Tower3Main, ImageView Tower4Main, ImageView Tower5Main, ImageView Tower6Main, ImageView Tower7Main, ImageView Tower8Main){
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

    private void fillGraveyard(ImageView imgview, ReducedPlayer p){

        File file;
        Image image;

        switch(p.getGraveyard()){
            case CARD1:
                file = new File("/images/assistant/Assistente_1.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD2:
                file = new File("/images/assistant/Assistente_2.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD3:
                file = new File("/images/assistant/Assistente_3.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD4:
                file = new File("/images/assistant/Assistente_4.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD5:
                file = new File("/images/assistant/Assistente_5.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD6:
                file = new File("/images/assistant/Assistente_6.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD7:
                file = new File("/images/assistant/Assistente_7.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD8:
                file = new File("/images/assistant/Assistente_8.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD9:
                file = new File("/images/assistant/Assistente_9.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD10:
                file = new File("/images/assistant/Assistente_10.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;
        }

    }

    private void fillCardInUse(ImageView imgview, ReducedPlayer p){

        File file;
        Image image;

        switch(p.getCardInUse()){
            case CARD1:
                file = new File("/images/assistant/Assistente_1.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD2:
                file = new File("/images/assistant/Assistente_2.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD3:
                file = new File("/images/assistant/Assistente_3.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD4:
                file = new File("/images/assistant/Assistente_4.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD5:
                file = new File("/images/assistant/Assistente_5.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD6:
                file = new File("/images/assistant/Assistente_6.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD7:
                file = new File("/images/assistant/Assistente_7.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD8:
                file = new File("/images/assistant/Assistente_8.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD9:
                file = new File("/images/assistant/Assistente_9.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;

            case CARD10:
                file = new File("/images/assistant/Assistente_10.jpg");
                image = new Image(file.toURI().toString());
                imgview.setImage(image);
                break;
        }

    }

    private void setCoins(Text t, ReducedPlayer p){
        t.setText(Integer.toString(p.getCoins()));
    }
}
