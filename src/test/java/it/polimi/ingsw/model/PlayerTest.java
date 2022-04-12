package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.CardNotInDeckException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.polimi.ingsw.model.Card.*;
import static it.polimi.ingsw.model.Color.*;
import static it.polimi.ingsw.model.Wizard.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PlayerTest class tests Player class.
 *
 * @author Alessio Buda
 * @see Player
 */
class PlayerTest {
    Player player;
    private static final String nickname = "Foo";
    private static final Wizard wizard = FIRST;
    private static final int numPlayers = 2;
    private static final boolean teamLeader = false;
    private static final Color color = WHITE;
    /**
     *  Setups test.
     */
    @BeforeEach
    public void setup() {
        player = new Player(numPlayers);
    }

    /**
     * Tears down test.
     */
    @AfterEach
    void tearDown() {
        player = null;
    }

    @Test
    void PlayerTest_InvalidNumberOfPlayersGiven_throwsIllegalArgumentException () {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> player = new Player(5));

        Assertions.assertEquals("Number of players must between 2 and 4", thrown.getMessage());
    }

    /**
     * Verifies that nickname is correctly set and cannot be modified more than once.
     */
    @Test
    void setNicknameTest () {
        assertNull(player.getNickname());
        player.setNickname(nickname);
        assertEquals(nickname, player.getNickname());
        player.setNickname("Boo");
        assertEquals(nickname, player.getNickname());
    }
    /**
     * Verifies that a NullPointerException is thrown when nickname parameter of method setPlayerNickname is null.
     */
    @Test
    void setPlayerNickname_NullNickname_throwsNullPointerException (){
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> player.setNickname(null));

        Assertions.assertEquals("Player nickname cannot be null", thrown.getMessage());
    }

    /**
     * Verifies that wizard is correctly set and cannot be modified more than once.
     */
    @Test
    void setWizardTest () {
        assertNull(player.getWizard());
        player.setWizard(wizard);
        assertEquals(wizard, player.getWizard());
        player.setWizard(SECOND);
        assertEquals(wizard, player.getWizard());
    }

    /**
     * Verifies that a NullPointerException is thrown when wizard parameter of method setPlayerWizard is null.
     */
    @Test
    void setPlayerWizard_NullWizard_throwsNullPointerException (){
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> player.setWizard(null));

        Assertions.assertEquals("Player wizard cannot be null", thrown.getMessage());
    }

    /**
     * Verifies that teamLeader attribute is correctly set and cannot be modified more than once.
     */
    @Test
    void setTeamLeaderTest () {
        player.setTeamLeader(teamLeader);
        assertEquals(teamLeader, player.isTeamLeader());
        player.setTeamLeader(!teamLeader);
        assertEquals(teamLeader, player.isTeamLeader());
    }

    /**
     * Verifies that getNumCards method returns the number of cards in deck.
     * When no card has been removed from deck, this number should be 10.
     */
    @Test
    void getNumCardsTest() {
        assertEquals(10, player.getNumCards());
    }

    /**
     * Verifies that when a card is used:
     * - the number of cards in deck is decremented by 1;
     * - the removed card is no longer present in deck;
     * - maxMoves attribute is correctly set to card moves
     * - all the other cards, previously present is deck, are still present;
     * - the removed card is set as card currently in use.
     */
    @Test
    void useCardTest() {
        try {
            player.useCard(CARD1);
        } catch (CardNotInDeckException e) {
            fail();
        }
        assertEquals(9, player.getNumCards());
        List<Card> temp = new ArrayList<>(Arrays.asList(Card.values()));
        temp.remove(CARD1);
        if (!player.getDeck().containsAll(temp))
            fail();
        assertEquals(CARD1, player.getCardInUse());
        assertEquals(CARD1.getMoves(), player.getMaxMoves());

    }

    /**
     * Verifies that a CardNotInDeckException is thrown when the card parameter represents a card that has already been used and is no longer present in deck.
     *
     */
    @Test
    void useCardTest_CardGivenNotInDeck_throwsCardNotInDeckException() {
        try {
            player.useCard(CARD1);
        } catch (CardNotInDeckException e) {
            fail();
        }
        CardNotInDeckException thrown = Assertions.assertThrows(CardNotInDeckException.class, () -> player.useCard(CARD1));

        Assertions.assertEquals("Card not in deck", thrown.getMessage());

    }

    /**
     * Verifies that a NullPointerException is thrown when card parameter of method useCard is null.
     */
    @Test
    void useCardTest_NullCardGiven_throwsNullPointerException() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> player.useCard(null));

        Assertions.assertEquals("Card cannot be null", thrown.getMessage());

    }

    /**
     * Verifies that when a coin is added the number of total coins is incremented by 1.
     */
    @Test
    void addCoinsTest(){
        assertEquals(1, player.getCoins());
        player.addCoins(1);
        assertEquals(2, player.getCoins());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when numCoins parameter of method addCoins is negative.
     */
    @Test
    void addCoinsTest_NegativeNumberOfCoins_throwsIllegalArgumentException (){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> player.addCoins(-1));

        Assertions.assertEquals("Number of coins cannot be negative", thrown.getMessage());
    }

    /**
     * Verifies that when a coin is removed the number of total coins is decremented by 1.
     */
    @Test
    void removeCoinsTest(){
        assertEquals(1, player.getCoins());
        player.removeCoins(1);
        assertEquals(0, player.getCoins());
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when numCoins parameter of method removeCoins is negative.
     */
    @Test
    void removeCoinsTest_NegativeNumberOfCoins_throwsIllegalArgumentException (){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> player.removeCoins(-1));

        Assertions.assertEquals("Number of coins cannot be negative", thrown.getMessage());
    }

    @Test
    void setDashboardTest () {
        player = new Player(2);
        player.setTeamLeader(false);
        player.setDashboard(color);
        assertEquals(color, player.getDashboard().getTowerColor());
        assertEquals(7, player.getDashboard().getNumMaxStudents());
        assertEquals(8, player.getDashboard().getNumTowers());

        player = new Player(3);
        player.setTeamLeader(false);
        player.setDashboard(color);
        assertEquals(color, player.getDashboard().getTowerColor());
        assertEquals(9, player.getDashboard().getNumMaxStudents());
        assertEquals(6, player.getDashboard().getNumTowers());

        player = new Player(4);
        player.setTeamLeader(true);
        player.setDashboard(color);
        assertEquals(color, player.getDashboard().getTowerColor());
        assertEquals(7, player.getDashboard().getNumMaxStudents());
        assertEquals(8, player.getDashboard().getNumTowers());



        player = new Player(4);
        player.setTeamLeader(false);
        player.setDashboard(color);
        assertEquals(color, player.getDashboard().getTowerColor());
        assertEquals(7, player.getDashboard().getNumMaxStudents());
        assertEquals(0, player.getDashboard().getNumTowers());
    }
}