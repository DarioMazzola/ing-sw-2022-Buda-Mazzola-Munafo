package it.polimi.ingsw.messages.answer;

import it.polimi.ingsw.client.ReducedGameModel;

import static it.polimi.ingsw.messages.MessageType.UPDATE_GAME_MODEL;

/**
 * Class representing messages from the server to the client to notify the change of the Game Model.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class UpdateGameModel extends AnswerMessage{

    private final ReducedGameModel gameModel;

    /**
     * Class constructor
     *
     * @param gameModel the gameModel to send
     */
    public UpdateGameModel(ReducedGameModel gameModel){
        super(UPDATE_GAME_MODEL);
        this.gameModel = gameModel;
    }

    public ReducedGameModel getGameModel() {
        return gameModel;
    }
}
