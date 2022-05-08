package it.polimi.ingsw.model.characterCard;

import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MagicalMailManTest {

    CharacterCard magicalMailMan;
    Player currentPlayer;
    int maxMoves = 1;

    @BeforeEach
    void setUp() {
        magicalMailMan = new MagicalMailMan();
        currentPlayer = new Player(2);
        currentPlayer.setMaxMoves(maxMoves);
    }

    @Test
    void doEffectTest() {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("currentPlayer", currentPlayer);

        try{
            magicalMailMan.doEffect(parameters);
        }
        catch (Exception e){
            fail();
        }

        assertEquals(maxMoves+2, ((Player)parameters.get("currentPlayer")).getMaxMoves());
        assertEquals(maxMoves+2, currentPlayer.getMaxMoves());
    }

    @Test
    void getDescription() {
        String description = "You may move Mother Nature up to 2 additional Islands than is indicated by " +
                "the Assistant card you've played";

        assertEquals(description, magicalMailMan.getDescription());
    }

    @AfterEach
    void tearDown() {
        magicalMailMan = null;
    }


}