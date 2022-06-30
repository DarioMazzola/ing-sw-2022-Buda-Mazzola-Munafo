package it.polimi.ingsw.messages.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Command serializer test class. Its tests verify that every command message is serialized and deserialized
 * correctly by the methods serialize and deserialize.
 *
 * @author Dario Mazzola
 */
class CommandSerializerTest {

    @Test
    void serializeAndDeserializeChatMessageServerClientTest() {
        CommandMessage message = new ChatMessageClientServer(null,null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializePongTest() {
        CommandMessage message = new Pong(null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeNewGameTest() {
        CommandMessage message = new NewGame(null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeNicknameTest() {
        CommandMessage message = new Nickname(null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenCharacterCardTest() {
        CommandMessage message = new ChosenCharacterCard(null, 0, null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenNumPlayersTest() {
        CommandMessage message = new ChosenNumPlayers(null,0);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenExpertModeTest() {
        CommandMessage message = new ChosenExpertMode(null,true);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenRestoreGameTest() {
        CommandMessage message = new ChosenRestoreGame(null,true);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenWizardTest() {
        CommandMessage message = new ChosenWizard(null,null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenTeamTest() {
        CommandMessage message = new ChosenTeam(null,0, false);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenTowerColorTest() {
        CommandMessage message = new ChosenTowerColor(null,null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenAssistantCardTest() {
        CommandMessage message = new ChosenAssistantCard(null,null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeMoveStudentToDiningHallTest() {
        CommandMessage message = new MoveStudentToDiningHall(null,null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeMoveStudentToIslandTest() {
        CommandMessage message = new MoveStudentToIsland(null,null, 0);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeMoveMotherTest() {
        CommandMessage message = new MoveMother(null,0);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenCloudTest() {
        CommandMessage message = new ChosenCloud(null,0);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeChosenChatTest() {
        CommandMessage message = new ChosenChat(null,true);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

    @Test
    void serializeAndDeserializeReloadMessagesTest() {
        CommandMessage message = new ReloadMessages(null);

        String serialized = CommandSerializer.serialize(message);

        CommandMessage messageDeserialized = CommandSerializer.deserialize(serialized);

        assertEquals(message.getType(), messageDeserialized.getType());
    }

}