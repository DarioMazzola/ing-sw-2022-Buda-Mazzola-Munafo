//package it.polimi.ingsw.messages.answer;
//
//import it.polimi.ingsw.model.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static it.polimi.ingsw.messages.MessageType.*;
//import static it.polimi.ingsw.model.Card.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class AnswerSerializerTest {
//
//    private AnswerMessage message;
//    private String nickname = "Nickname";
//
//    @AfterEach
//    void tearDown() {
//        message = null;
//        nickname = null;
//    }
//
////    @Test
////    void serializeTest_ACK() {
////
////        int typeOfACK = 1;
////
////        message = new Ack(nickname, typeOfACK);
////
////        String expectedSerialized = "[\"ACK\",\"{\\\"typeOfACK\\\":1,\\\"messageType\\\":\\\"ACK\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////        String serializedMessage = AnswerSerializer.serialize(message);
////
////        assertEquals(expectedSerialized, serializedMessage);
////    }
//
////    @Test
////    void deserializeTest_ACK() {
////
////        String serializedMessage = "[\"ACK\",\"{\\\"typeOfACK\\\":1,\\\"messageType\\\":\\\"ACK\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(Ack.class, answerMessage);
////        assertEquals(ACK, answerMessage.getType());
////        //todo pensare ad un modo per prendere il typeOfACk
////
////    }
//
////    @Test
////    void serializeTest_NACK() {
////
////        int typeOfNACK = 1;
////
////        message = new Nack(nickname, typeOfNACK);
////
////        String expectedSerialized = "[\"NACK\",\"{\\\"typeOfError\\\":1,\\\"messageType\\\":\\\"NACK\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////        String serializedMessage = AnswerSerializer.serialize(message);
////
////        assertEquals(expectedSerialized, serializedMessage);
////    }
//
//    @Test
//    void deserializeTest_NACK() {
//
//        String serializedMessage = "[\"NACK\",\"{\\\"typeOfError\\\":1,\\\"messageType\\\":\\\"NACK\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//
//        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
//
//        assertInstanceOf(Nack.class, answerMessage);
//        assertEquals(NACK, answerMessage.getType());
//        //todo pensare ad un modo per prendere il typeOfACk
//
//    }
//
//    @Test
//    void serializeTest_ALL_ASSISTANT_CARDS() {
//
//        List<Card> cards = new ArrayList<>();
//
//        cards.add(CARD1);
//        cards.add(CARD2);
//        cards.add(CARD3);
//
////        message = new AllAssistantCards(nickname, cards);
//
//        String expectedSerialized = "[\"ALL_ASSISTANT_CARDS\",\"{\\\"playersDeck\\\":[\\\"CARD1\\\",\\\"CARD2\\\",\\\"CARD3\\\"],\\\"messageType\\\":\\\"ALL_ASSISTANT_CARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
////    @Test
////    void deserializeTest_ALL_ASSISTANT_CARDS() {
////
////        String serializedMessage = "[\"ALL_ASSISTANT_CARDS\",\"{\\\"playersDeck\\\":[\\\"CARD1\\\",\\\"CARD2\\\",\\\"CARD3\\\"],\\\"messageType\\\":\\\"ALL_ASSISTANT_CARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(AllAssistantCards.class, answerMessage);
////        assertEquals(nickname, answerMessage.getNickname());
////        assertEquals(ALL_ASSISTANT_CARDS, answerMessage.getType());
////        //todo pensare ad un modo per prendere i parametri
////
////    }
//
////    @Test
////    void serializeTest_ALL_AVAILABLE_WIZARDS() {
////
////        List<Wizard> wizards = new ArrayList<>();
////
////        wizards.add(FIRST);
////        wizards.add(SECOND);
////        wizards.add(THIRD);
////
////        message = new AllAvailableWizards(nickname, wizards);
////
////        String expectedSerialized = "[\"ALL_AVAILABLE_WIZARDS\",\"{\\\"availableWizards\\\":[\\\"FIRST\\\",\\\"SECOND\\\",\\\"THIRD\\\"],\\\"messageType\\\":\\\"ALL_AVAILABLE_WIZARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////        String serializedMessage = AnswerSerializer.serialize(message);
////
////        assertEquals(expectedSerialized, serializedMessage);
////    }
//
//    @Test
//    void deserializeTest_ALL_AVAILABLE_WIZARDS() {
//
//        String serializedMessage = "[\"ALL_AVAILABLE_WIZARDS\",\"{\\\"availableWizards\\\":[\\\"FIRST\\\",\\\"SECOND\\\",\\\"THIRD\\\"],\\\"messageType\\\":\\\"ALL_AVAILABLE_WIZARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//
//        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
//
//        assertInstanceOf(SelectWizard.class, answerMessage);
////        assertEquals(nickname, answerMessage.getNickname());
//        assertEquals(SELECT_WIZARD, answerMessage.getType());
//        //todo pensare ad un modo per prendere i parametri
//
//    }
//
////    @Test
////    void serializeTest_ALL_CHARACTER_CARDS() {
////
////        List<CharacterCard> cards = new ArrayList<>();
////
////        cards.add(new HerbGranma());
////        cards.add(new Knight());
////        cards.add(new Herald());
////
////        message = new AllCharacterCards(nickname, cards);
////
////        String expectedSerialized = "[\"ALL_CHARACTER_CARDS\",\"{\\\"characterCards\\\":[{\\\"noEntryTileNumber\\\":4,\\\"cost\\\":2,\\\"neverUsed\\\":true,\\\"inUse\\\":false,\\\"cardName\\\":\\\"HerbGranma\\\"},{\\\"cost\\\":2,\\\"neverUsed\\\":true,\\\"inUse\\\":false,\\\"cardName\\\":\\\"Knight\\\"},{\\\"cost\\\":3,\\\"neverUsed\\\":true,\\\"inUse\\\":false,\\\"cardName\\\":\\\"Herald\\\"}],\\\"messageType\\\":\\\"ALL_CHARACTER_CARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////        String serializedMessage = AnswerSerializer.serialize(message);
////
////        assertEquals(expectedSerialized, serializedMessage);
////    }
//
////    @Test
////    void deserializeTest_ALL_CHARACTER_CARDS() {
////
////        String serializedMessage = "[\"ALL_CHARACTER_CARDS\",\"{\\\"characterCards\\\":[{\\\"noEntryTileNumber\\\":4,\\\"cost\\\":2,\\\"neverUsed\\\":true,\\\"inUse\\\":false,\\\"cardName\\\":\\\"HerbGranma\\\"},{\\\"cost\\\":2,\\\"neverUsed\\\":true,\\\"inUse\\\":false,\\\"cardName\\\":\\\"Knight\\\"},{\\\"cost\\\":3,\\\"neverUsed\\\":true,\\\"inUse\\\":false,\\\"cardName\\\":\\\"Herald\\\"}],\\\"messageType\\\":\\\"ALL_CHARACTER_CARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(SelectCharacterCard.class, answerMessage);
//////        assertEquals(nickname, answerMessage.getNickname());
////        assertEquals(ALL_CHARACTER_CARDS, answerMessage.getType());
////        //todo pensare ad un modo per prendere i parametri
////
////    }
//
//    @Test
//    void serializeTest_ALL_NICKNAMES() {
//
//        List<String> nicknames = new ArrayList<>();
//
//        nicknames.add("Nick1");
//        nicknames.add("Nick2");
////        message = new AllNicknames(nickname, nicknames);
//
//        String expectedSerialized = "[\"ALL_NICKNAMES\",\"{\\\"selectedNicknames\\\":[\\\"Nick1\\\",\\\"Nick2\\\"],\\\"messageType\\\":\\\"ALL_NICKNAMES\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
////    @Test
////    void deserializeTest_ALL_NICKNAMES() {
////
////        String serializedMessage = "[\"ALL_NICKNAMES\",\"{\\\"selectedNicknames\\\":[\\\"Nick1\\\",\\\"Nick2\\\"],\\\"messageType\\\":\\\"ALL_NICKNAMES\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(AllNicknames.class, answerMessage);
////        assertEquals(nickname, answerMessage.getNickname());
////        assertEquals(ALL_NICKNAMES, answerMessage.getType());
////        //todo pensare ad un modo per prendere i parametri
////    }
//
//    /*@Test
//    void serializeTest_AVAILABLE_CLOUDS() {
//
//        String expectedSerialized = "[\"AVAILABLE_CLOUDS\",\"{\\\"cloudList\\\":[{\\\"houseMap\\\":{\\\"PINK\\\":0,\\\"GREEN\\\":0,\\\"BLUE\\\":0,\\\"YELLOW\\\":0,\\\"RED\\\":0},\\\"numMaxStud\\\":3,\\\"full\\\":false}],\\\"messageType\\\":\\\"AVAILABLE_CLOUDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        List<Cloud> clouds = new ArrayList<>();
//
//        Cloud cloud = new Cloud(2);
//        Map<House, Integer> students = cloud.getStudents();
//
//        clouds.add(cloud);
//
//        message = new AvailableClouds(nickname, clouds);
//
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }*/
//
////    @Test
////    void deserializeTest_AVAILABLE_CLOUDS() {
////
////        String serializedMessage = "[\"AVAILABLE_CLOUDS\",\"{\\\"cloudList\\\":[{\\\"houseMap\\\":{\\\"RED\\\":0,\\\"BLUE\\\":0,\\\"YELLOW\\\":0,\\\"PINK\\\":0,\\\"GREEN\\\":0},\\\"numMaxStud\\\":3,\\\"full\\\":false}],\\\"messageType\\\":\\\"AVAILABLE_CLOUDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(AvailableClouds.class, answerMessage);
////        assertEquals(nickname, answerMessage.getNickname());
////        assertEquals(AVAILABLE_CLOUDS, answerMessage.getType());
////        //todo pensare ad un modo per prendere i parametri
////
////    }
//
//    @Test
//    void serializeTest_END_GAME_DISCONNECTION() {
//
//        message = new EndGameDisconnection();
//
//        String expectedSerialized = "[\"END_GAME_DISCONNECTION\",\"{\\\"messageType\\\":\\\"END_GAME_DISCONNECTION\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
////    @Test
////    void deserializeTest_END_GAME_DISCONNECTION() {
////
////        String serializedMessage = "[\"END_GAME_DISCONNECTION\",\"{\\\"messageType\\\":\\\"END_GAME_DISCONNECTION\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(EndGameDisconnection.class, answerMessage);
////        assertEquals(nickname, answerMessage.getNickname());
////        assertEquals(END_GAME_DISCONNECTION, answerMessage.getType());
////        //todo pensare ad un modo per prendere i parametri
////
////    }
//
//    @Test
//    void serializeTest_NEXT_PHASE() {
//
//        //message = new NextPhase(nickname);
//
//        String expectedSerialized = "[\"NEXT_PHASE\",\"{\\\"messageType\\\":\\\"NEXT_PHASE\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
////    @Test
////    void deserializeTest_NEXT_PHASE() {
////
////        String serializedMessage = "[\"NEXT_PHASE\",\"{\\\"messageType\\\":\\\"NEXT_PHASE\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(NextPhase.class, answerMessage);
////        assertEquals(nickname, answerMessage.getNickname());
////        assertEquals(NEXT_PHASE, answerMessage.getType());
////        //todo pensare ad un modo per prendere i parametri
////
////    }
//
//    @Test
//    void serializeTest_PING() {
//
//        int typeOfNACK = 1;
//
//        message = new Ping();
//
//        String expectedSerialized = "[\"PING\",\"{\\\"messageType\\\":\\\"PING\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
//    @Test
//    void deserializeTest_PING() {
//
//        String serializedMessage = "[\"PING\",\"{\\\"messageType\\\":\\\"PING\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//
//        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
//
//        assertInstanceOf(Ping.class, answerMessage);
//        assertEquals(PING, answerMessage.getType());
//        //todo pensare ad un modo per prendere i parametri
//
//    }
//
//    @Test
//    void serializeTest_PLAYER_MAX_MOVES() {
//
//        int maxMoves = 3;
//        message = new PlayerMaxMoves(maxMoves);
//
//        String expectedSerialized = "[\"PLAYER_MAX_MOVES\",\"{\\\"maxMoves\\\":3,\\\"messageType\\\":\\\"PLAYER_MAX_MOVES\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
//    @Test
//    void deserializeTest_PLAYER_MAX_MOVES() {
//
//        String serializedMessage = "[\"PLAYER_MAX_MOVES\",\"{\\\"maxMoves\\\":3,\\\"messageType\\\":\\\"PLAYER_MAX_MOVES\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//
//        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
//
//        assertInstanceOf(PlayerMaxMoves.class, answerMessage);
//        assertEquals(PLAYER_MAX_MOVES, answerMessage.getType());
//        //todo pensare ad un modo per prendere i parametri
//
//    }
//
//    @Test
//    void serializeTest_SEND_WINNER() {
//
//        message = new SendWinner(nickname);
//
//        String expectedSerialized = "[\"SEND_WINNER\",\"{\\\"winner\\\":\\\"Nickname\\\",\\\"messageType\\\":\\\"SEND_WINNER\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
//    @Test
//    void deserializeTest_SEND_WINNER() {
//
//        String serializedMessage = "[\"SEND_WINNER\",\"{\\\"winner\\\":\\\"Nickname\\\",\\\"messageType\\\":\\\"SEND_WINNER\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//
//        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
//
//        assertInstanceOf(SendWinner.class, answerMessage);
//        assertEquals(SEND_WINNER, answerMessage.getType());
//        //todo pensare ad un modo per prendere i parametri
//
//    }
//
//    /*@Test
//    void serializeTest_STUDENTS_IN_ENTRANCE() {
//
//        Map<House, Integer> studentsInEntrance = new HashMap<>();
//
//        studentsInEntrance.put(House.RED, 3);
//        studentsInEntrance.put(House.YELLOW, 2);
//
//        message = new StudentsInEntrance(nickname, studentsInEntrance);
//
//        String expectedSerialized = "[\"STUDENTS_IN_ENTRANCE\",\"{\\\"houseMap\\\":{\\\"RED\\\":3,\\\"YELLOW\\\":2},\\\"messageType\\\":\\\"STUDENTS_IN_ENTRANCE\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }*/
//
//
//    @Test
//    void deserializeTest_STUDENTS_IN_ENTRANCE() {
//
//        String serializedMessage = "[\"STUDENTS_IN_ENTRANCE\",\"{\\\"houseMap\\\":{\\\"RED\\\":3,\\\"YELLOW\\\":2},\\\"messageType\\\":\\\"STUDENTS_IN_ENTRANCE\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//
//        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
//
//        assertInstanceOf(StudentsInEntrance.class, answerMessage);
//        assertEquals(STUDENTS_IN_ENTRANCE, answerMessage.getType());
//        //todo pensare ad un modo per prendere i parametri
//
//    }
//
//    /*@Test
//    void serializeTest_TEAM_TABLE_UPDATE() {
//
//        message = new TeamTableUpdate(nickname, );
//
//        String expectedSerialized = "[\"NACK\",\"{\\\"typeOfError\\\":1,\\\"messageType\\\":\\\"NACK\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }*/
//
//    @Test
//    void serializeTest_TOWER_COLOR_UPDATE() {
//
//        List<Color> towers = new ArrayList<>();
//
//        towers.add(Color.BLACK);
//        towers.add(Color.WHITE);
//
//        message = new SelectColorTower(towers);
//
//        String expectedSerialized = "[\"TOWER_COLOR_UPDATE\",\"{\\\"availableColors\\\":[\\\"BLACK\\\",\\\"WHITE\\\"],\\\"messageType\\\":\\\"TOWER_COLOR_UPDATE\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
////    @Test
////    void deserializeTest_TOWER_COLOR_UPDATE() {
////
////        String serializedMessage = "[\"TOWER_COLOR_UPDATE\",\"{\\\"availableColors\\\":[\\\"BLACK\\\",\\\"WHITE\\\"],\\\"messageType\\\":\\\"TOWER_COLOR_UPDATE\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
////
////        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
////
////        assertInstanceOf(SelectColorTower.class, answerMessage);
////        assertEquals(TOWER_COLOR_UPDATE, answerMessage.getType());
////        //todo pensare ad un modo per prendere i parametri
////
////    }
//
//    @Test
//    void serializeTest_USED_ASSISTANT_CARDS() {
//
//        List<Card> assistantUsed = new ArrayList<>();
//
//        assistantUsed.add(CARD1);
//        assistantUsed.add(CARD2);
//
//        message = new UsedAssistantCards(assistantUsed);
//
//        String expectedSerialized = "[\"USED_ASSISTANT_CARDS\",\"{\\\"usedCards\\\":[\\\"CARD1\\\",\\\"CARD2\\\"],\\\"messageType\\\":\\\"USED_ASSISTANT_CARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//        String serializedMessage = AnswerSerializer.serialize(message);
//
//        assertEquals(expectedSerialized, serializedMessage);
//    }
//
//    @Test
//    void deserializeTest_USED_ASSISTANT_CARDS() {
//
//        String serializedMessage = "[\"USED_ASSISTANT_CARDS\",\"{\\\"usedCards\\\":[\\\"CARD1\\\",\\\"CARD2\\\"],\\\"messageType\\\":\\\"USED_ASSISTANT_CARDS\\\",\\\"nickname\\\":\\\"Nickname\\\"}\"]";
//
//
//        AnswerMessage answerMessage = AnswerSerializer.deserialize(serializedMessage);
//
//        assertInstanceOf(UsedAssistantCards.class, answerMessage);
//        assertEquals(USED_ASSISTANT_CARDS, answerMessage.getType());
//        //todo pensare ad un modo per prendere i parametri
//
//    }
//}