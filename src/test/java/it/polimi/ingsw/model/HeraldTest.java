package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.BagException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HeraldTest {

    CharacterCard characterCard;
    @BeforeEach
    void setUp() throws BagException {
        characterCard = new CharacterCardFactory().getCharacterCard(CharacterCardEnum.HERALD, null);
    }

    @Test
    void doEffect() {
        Map<String, Object> parameters = new HashMap<>();
        Player currentPlayer = new Player(2);
        Player otherPlayer = new Player(2);

        currentPlayer.setDashboard(Color.BLACK, "currentPlayer");
        otherPlayer.setDashboard(Color.GRAY, "otherPlayer");

        Player[] players = new Player[2];

        players[0] = currentPlayer;
        players[1] = otherPlayer;

        CharacterCard[] characterCardDeck = new CharacterCard[3];

        characterCardDeck[0] = characterCard;
        characterCardDeck[1] = new Knight();
        characterCardDeck[2] = new HerbGranma();

        boolean expertMode = true;
        Island island = new Island(expertMode);
        parameters.put("Island", island);
        parameters.put("ArrayPlayers", players);
        parameters.put("ExpertMode", true);
        parameters.put("NumPlayers", 2);

        parameters.put("CharacterCardDeck", characterCardDeck);

        try {
            characterCard.doEffect(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertNull(parameters.get("Output"));
    }

    @Test
    void getDescription() {
        String description = "Choose an Island and resolve the Island as if Mother Nature had ended her movement there. " +
                "Mother Nature will still move and the Island where she ends her movement  will also be resolved.";
        assertEquals(description, characterCard.getDescription());
    }

    @Test
    void getType() {
        assertEquals(CharacterCardEnum.HERALD, characterCard.getType());
    }
}