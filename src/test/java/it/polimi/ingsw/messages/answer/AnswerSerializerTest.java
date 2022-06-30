package it.polimi.ingsw.messages.answer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Answer serializer test class. Its tests verify that every answer message is serialized and deserialized
 * correctly by the methods serialize and deserialize.
 *
 * @author Dario Mazzola
 */
class AnswerSerializerTest {

    @Test
    void serializeAndDeserializeGoToLobbyTest() {
        AnswerMessage message = new GoToLobby();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeActionPhaseTest() {
        AnswerMessage message = new ActionPhase(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeEndGameDisconnectionTest() {
        AnswerMessage message = new EndGameDisconnection(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeGameFullTest() {
        AnswerMessage message = new GameFull();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeGoToWaitingRoomTest() {
        AnswerMessage message = new GoToWaitingRoom();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeNackTest() {
        AnswerMessage message = new Nack(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializePingTest() {
        AnswerMessage message = new Ping();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectAssistantCardTest() {
        AnswerMessage message = new SelectAssistantCard(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectCharacterCardTest() {
        AnswerMessage message = new SelectCharacterCard(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectCloudTest() {
        AnswerMessage message = new SelectCloud();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectColorTowerTest() {
        AnswerMessage message = new SelectColorTower(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectExpertModeTest() {
        AnswerMessage message = new SelectExpertMode();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectNicknameTest() {
        AnswerMessage message = new SelectNickname();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectNumPlayersTest() {
        AnswerMessage message = new SelectNumPlayers();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectRestoreGameTest() {
        AnswerMessage message = new SelectRestoreGame();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectTeamTest() {
        AnswerMessage message = new SelectTeam(null, null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectWizardTest() {
        AnswerMessage message = new SelectWizard(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSendWinnerTest() {
        AnswerMessage message = new SendWinner(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateCharacterCardTest() {
        AnswerMessage message = new UpdateCharacterCard(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateCloudTest() {
        AnswerMessage message = new UpdateCloud(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateDashboardTest() {
        AnswerMessage message = new UpdateDashboard(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateDiningHallTest() {
        AnswerMessage message = new UpdateDiningHall(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateGameModelTest() {
        AnswerMessage message = new UpdateGameModel(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateIslandTest() {
        AnswerMessage message = new UpdateIsland(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdatePlayerTest() {
        AnswerMessage message = new UpdatePlayer(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateCurrentPlayerTest() {
        AnswerMessage message = new UpdateCurrentPlayer(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeUpdateMotherIslandTest() {
        AnswerMessage message = new UpdateMotherIsland(0);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeRememberNicknameTest() {
        AnswerMessage message = new RememberNickname(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeSelectChatTest() {
        AnswerMessage message = new SelectChat();

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChatMessageServerClientTest() {
        AnswerMessage message = new ChatMessageServerClient(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeWaitForOthersMovesTest() {
        AnswerMessage message = new WaitForOthersMoves(null);

        String serialized = AnswerSerializer.serialize(message);

        AnswerMessage messageDeserialized = AnswerSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

}