package it.polimi.ingsw.view.gui.scenes;

import it.polimi.ingsw.client.ReducedGameModel;
import it.polimi.ingsw.model.Card;
import it.polimi.ingsw.observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SelectAssistantCardSceneController extends ViewObservable implements SceneInterface {

    @FXML
    private GridPane characterCards;
    @FXML
    private ToggleGroup assistantButtons;
    @FXML
    private Button selectButton;

    private final ReducedGameModel gm;
    private final String nickname;
    private final List<Card> availableCards;

    public SelectAssistantCardSceneController(ReducedGameModel gm, String nickname, List<Card> availableCards){

        this.gm = gm;
        this.nickname = nickname;
        this.availableCards = availableCards;
    }

    @FXML
    public void initialize() {
        List<Card> playersDeck = gm.getPlayerByNickname(nickname).getDeck();

        Set<Card> result = availableCards.stream().distinct().filter(gm.getPlayerByNickname(nickname).
                getDeck()::contains).collect(Collectors.toSet());

        List<Node> cards = characterCards.getChildren().sorted();

        int i = 0;
        for(Card c : playersDeck) {
            Node node = cards.get(i);
            if(! result.contains(c)) {
                node.setDisable(true);
                node.setOpacity(0.5);
            }
            else{
                node.setDisable(false);
                node.setOnMouseClicked(this::selectCharacterCard);
            }

            Image cardImage = new Image(getUrlByValue(c.getValue()));

            ((ImageView)node).setImage(cardImage);
            i++;
        }

        selectButton.setOnAction(this::onSelectClicked);
    }

    public void selectCharacterCard(MouseEvent mouseEvent) {
        ImageView imageClicked = (ImageView) mouseEvent.getSource();

        int i = getValueByImage(imageClicked.getImage());
        List<Toggle> toggles = assistantButtons.getToggles();
        toggles.get(i).setSelected(true);

        for(Toggle t : toggles){
            if(! t.equals(toggles.get(i))){
                t.setSelected(false);
            }
        }
        selectButton.setDisable(false);
    }

    public void onSelectClicked(ActionEvent event) {

        RadioButton radio = (RadioButton)assistantButtons.getSelectedToggle();
        int i = Integer.parseInt(radio.getText());

        List<Card> playersDeck = gm.getPlayerByNickname(nickname).getDeck();
        notifyObserver(observer -> observer.onUpdateAssistantCard(playersDeck.get(i)));
    }

    public String getUrlByValue(int value) {

        String url = "/images/assistant/";

        switch (value) {
            case 1:
                url = url + "Assistente_1.png";
                break;
            case 2:
                url = url + "Assistente_2.png";
                break;
            case 3:
                url = url + "Assistente_3.png";
                break;
            case 4:
                url = url + "Assistente_4.png";
                break;
            case 5:
                url = url + "Assistente_5.png";
                break;
            case 6:
                url = url + "Assistente_6.png";
                break;
            case 7:
                url = url + "Assistente_7.png";
                break;
            case 8:
                url = url + "Assistente_8.png";
                break;
            case 9:
                url = url + "Assistente_9.png";
                break;
            case 10:
                url = url + "Assistente_10.png";
                break;
        }

        return url;
    }

    public int getValueByImage(Image image) {

        String url;

        url = image.getUrl().substring(image.getUrl().length() - 6);
        int value1 = Integer.parseInt(String.valueOf(url.charAt(1)));

        if (url.charAt(0) == '_') {
            return value1;
        }
        else {
            return Integer.parseInt(String.valueOf(url.charAt(0))) * 10 +  value1;
        }
    }
}
