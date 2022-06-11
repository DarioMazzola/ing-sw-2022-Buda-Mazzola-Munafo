package it.polimi.ingsw.observer;

import it.polimi.ingsw.client.ReducedIsland;
import it.polimi.ingsw.model.*;

import java.util.Map;

/**
 * Class representing the observer for view elements.
 *
 * @author Dario Mazzola & Alessio Buda
 */

public interface ViewObserver {

    /**
     * Called when a client expresses an intention to connect to the server to play.
     *
     * @param ip   the IP address to which the client wants to connect
     * @param port the port number the client wants to connect to.
     */
    void onInit(String ip, int port);

    /**
     * Method that represents the start of a game.
     *
     * @param nickname the nickname chosen by the client in this game.
     */
    void onCreateNewGame(String nickname);

    /**
     * Method representing the client sending the nickname. This method is invoked if
     * the nickname, previously sent, is wrong (empty, already in use, ...).
     *
     * @param nickname the new nickname chosen by the client in this game.
     */
    void onUpdateNickname(String nickname);

    /**
     * Method that represents the sending, by the client who created the game first, of the number
     * of players who will be able to take part in the game.
     *
     * @param numPlayers the number of players in this game.
     */
    void onUpdateNumPlayers(int numPlayers);

    /**
     * Method that represents the sending of the player preference for communication between team members.
     *
     * @param chat true if the player wants to allow communication between team members
     */
    void onUpdateChat (boolean chat);

    /**
     * Method that represents the sending of the wizard that the player wants to use in this game.
     *
     * @param wizard the wizard chosen by the player.
     */
    void onUpdateWizard(Wizard wizard);

    /**
     * Method invoked by the player who first created the game. Used for choosing
     * whether to play the game in expertMode or not.
     *
     * @param expertMode true if the game will be played in expert mode, false otherwise.
     */
    void onUpdateExpertMode(boolean expertMode);

    /**
     * Method used to specify which team the player wants to belong to and whether
     * the player wants to be the team leader or not,
     *
     * @param selectedTeam the team chosen.
     * @param isTeamLeader true if the player wants to be the team leader, false otherwise.
     */
    void onUpdateTeam(int selectedTeam, boolean isTeamLeader);

    /**
     * Method used to specify the tower color that the player wants to own in this game.
     *
     * @param towerColor the tower color chosen.
     */
    void onUpdateTowerColor(Color towerColor);

    /**
     * Method used to specify the tower color that the player wants to use the assistant
     * card specified as a parameters.
     *
     * @param chosenCard the assistant card chosen.
     */
    void onUpdateAssistantCard(Card chosenCard);

    /**
     * Method used to send the house of student the player wants to move from his/hers dashboard to his/hers dining hall.
     *
     * @param chosenHouse the house of the student to move
     */
    void onMoveStudentsToDiningHall(House chosenHouse);

    /**
     * Method used send the house of the student the player wants to move from his/hers dashboard to an island and the desired island.
     *
     * @param chosenHouse the house of the student to move
     * @param chosenIsland an index identifying the island where the student should be placed
     */
    void onMoveStudentsToIsland(House chosenHouse, int chosenIsland);

    /**
     * Method used to send the number of which mother nature should be moved.
     *
     * @param moves the number of moves of which mother nature should be moved
     */
    void onMoveMotherNature(int moves);

    /**
     * Method used to send the cloud from which the player wants to refill his/hers entrance.
     *
     * @param chosenCloud an index identifying the chosen cloud
     */
    void onUpdateCloud(int chosenCloud);

    /**
     * Method used to disconnect a client.
     */
    void onDisconnection();

    /**
     * Method used to send the server the character card the player has chosen to use and the required parameters.
     *
     * @param cardIndex index of the chosen character card in the deck
     * @param parameters the parameters needed to use the card
     */
    void onUpdateCharacterCard(int cardIndex, Map<String, Object> parameters);

    /**
     * Method used to signal the server whether the player wants to restore a saved game or start a new one.
     *
     * @param toRestore true if the player wants to restore the game, false otherwise
     */
    void onRestoreGame(boolean toRestore);

    /**
     * Method used to send a chat message to the server.
     *
     * @param message the message to be sent
     */
    void onSendMessage(String message);

    void waitForMessage();
}