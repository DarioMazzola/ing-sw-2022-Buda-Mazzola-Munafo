package it.polimi.ingsw.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.CharacterCardEnum.*;
import static org.junit.jupiter.api.Assertions.*;

class CharacterCardFactoryTest {

    CharacterCard characterCard;
    CharacterCardFactory characterCardFactory;

    @BeforeEach
    void setUp() {
        characterCardFactory = new CharacterCardFactory();
    }

    @Test
    void getCharacterCard_FarmerCreation() {
        try{
            characterCard = characterCardFactory.getCharacterCard(FARMER, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("Farmer", characterCard.toString());
        assertEquals(2, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof Farmer);
    }

    @Test
    void getCharacterCard_MagicalMailManCreation() {
        try{
            characterCard = characterCardFactory.getCharacterCard(MAGICAL_MAILMAN, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("MagicalMailMan", characterCard.toString());
        assertEquals(1, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof MagicalMailMan);
    }

    @Test
    void getCharacterCard_HerbGranmaCreation() {
        try{
            characterCard = characterCardFactory.getCharacterCard(HERB_GRANMA, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("HerbGranma", characterCard.toString());
        assertEquals(2, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof HerbGranma);
    }

    @Test
    void getCharacterCard_MinstrelCreation() {

        try{
            characterCard = characterCardFactory.getCharacterCard(MINSTREL, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("Minstrel", characterCard.toString());
        assertEquals(1, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof Minstrel);
    }

    @Test
    void getCharacterCard_MonkCreation() {

        Map<String, Object> parameters = new HashMap<>();

        Bag bag = new Bag();

        parameters.put("Bag", bag);

        int BagSizeBefore = bag.getTotalStudentsNumber();

        Map<House, Integer> studentsInBagBefore = new HashMap<>();
        Map<House, Integer> studentsInBagAfter = new HashMap<>();
        Map<House, Integer> studentsOnCard;

        for(House h : House.values()){
            studentsInBagBefore.put(h, bag.getHouseStudents(h));
        }

        try{
            characterCard = characterCardFactory.getCharacterCard(MONK, bag);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        assertEquals("Monk", characterCard.toString());
        assertEquals(1, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertEquals(BagSizeBefore - 4, bag.getTotalStudentsNumber());

        for(House h : House.values()){
            studentsInBagAfter.put(h, bag.getHouseStudents(h));
        }

        assertEquals(1, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        parameters.put("method", "getStudents");
        try {
            characterCard.doEffect(parameters);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        studentsOnCard = (Map<House, Integer>) parameters.get("studentsOnCard");

        for(House h : House.values()){
            assertEquals(studentsInBagBefore.get(h), studentsOnCard.get(h) + studentsInBagAfter.get(h));
        }

        assertTrue(characterCard instanceof Monk);
    }


    @Test
    void getCharacterCard_HeraldCreation() {

        try{
            characterCard = characterCardFactory.getCharacterCard(HERALD, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("Herald", characterCard.toString());
        assertEquals(3, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof Herald);
    }

    @Test
    void getCharacterCard_CentaurCreation() {

        try{
            characterCard = characterCardFactory.getCharacterCard(CENTAUR, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("Centaur", characterCard.toString());
        assertEquals(3, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof Centaur);
    }

    @Test
    void getCharacterCard_JollyCreation() {

        Map<String, Object> parameters = new HashMap<>();
        Bag bag = new Bag();

        parameters.put("Bag", bag);

        int BagSizeBefore = bag.getTotalStudentsNumber();

        Map<House, Integer> studentsInBagBefore = new HashMap<>();
        Map<House, Integer> studentsInBagAfter = new HashMap<>();
        Map<House, Integer> studentsOnCard;

        for(House h : House.values()){
            studentsInBagBefore.put(h, bag.getHouseStudents(h));
        }

        try{
            characterCard = characterCardFactory.getCharacterCard(JOLLY, bag);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        assertEquals(BagSizeBefore - 6, bag.getTotalStudentsNumber());

        for(House h : House.values()){
            studentsInBagAfter.put(h, bag.getHouseStudents(h));
        }

        assertEquals(1, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        parameters.put("method", "getStudents");
        try {
            characterCard.doEffect(parameters);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        studentsOnCard = (Map<House, Integer>) parameters.get("studentsOnCard");

        for(House h : House.values()){
            assertEquals(studentsInBagBefore.get(h), studentsOnCard.get(h) + studentsInBagAfter.get(h));
        }
        assertEquals("Jolly", characterCard.toString());

        assertTrue(characterCard instanceof Jolly);
    }

    @Test
    void getCharacterCard_KnightCreation() {

        try{
            characterCard = characterCardFactory.getCharacterCard(KNIGHT, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("Knight", characterCard.toString());
        assertEquals(2, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof Knight);
    }

    @Test
    void getCharacterCard_MushroomHunterCreation() {

        try{
            characterCard = characterCardFactory.getCharacterCard(MUSHROOM_HUNTER, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("MushroomHunter", characterCard.toString());
        assertEquals(3, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof MushroomHunter);
    }

    @Test
    void getCharacterCard_SpoiledPrincessCreation() {

        Map<String, Object> parameters = new HashMap<>();

        Bag bag = new Bag();

        parameters.put("Bag", bag);

        int BagSizeBefore = bag.getTotalStudentsNumber();

        Map<House, Integer> studentsInBagBefore = new HashMap<>();
        Map<House, Integer> studentsInBagAfter = new HashMap<>();
        Map<House, Integer> studentsOnCard;

        for(House h : House.values()){
            studentsInBagBefore.put(h, bag.getHouseStudents(h));
        }

        try{
            characterCard = characterCardFactory.getCharacterCard(SPOILED_PRINCESS, bag);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        assertEquals(BagSizeBefore - 4, bag.getTotalStudentsNumber());

        for(House h : House.values()){
            studentsInBagAfter.put(h, bag.getHouseStudents(h));
        }

        assertEquals(2, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        parameters.put("method", "getStudents");
        try {
            characterCard.doEffect(parameters);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }

        studentsOnCard = (Map<House, Integer>) parameters.get("studentsOnCard");

        for(House h : House.values()){
            assertEquals(studentsInBagBefore.get(h), studentsOnCard.get(h) + studentsInBagAfter.get(h));
        }

        assertTrue(characterCard instanceof SpoiledPrincess);
    }

    @Test
    void getCharacterCard_ThiefCreation() {

        try{
            characterCard = characterCardFactory.getCharacterCard(THIEF, null);
        }
        catch (Exception e){
            fail();
        }

        assertEquals("Thief", characterCard.toString());
        assertEquals(3, characterCard.getCost());
        assertTrue(characterCard.isNeverUsed());
        assertFalse(characterCard.isInUse());

        assertTrue(characterCard instanceof Thief);
    }

    @AfterEach
    void tearDown() {
        characterCardFactory = null;
    }
}