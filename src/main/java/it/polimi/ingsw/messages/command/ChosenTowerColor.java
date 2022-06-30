package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.Color;

import static it.polimi.ingsw.messages.MessageType.CHOSEN_TOWER_COLOR;

/**
 * SelectColorTower class represents SelectColorTower network message.
 * This message is sent from the client to the server to communicate the player's chosen tower color.
 *
 * @author Alessio Buda & Dario Mazzola & Gabriele Munafo'
 */
public class ChosenTowerColor extends CommandMessage {
    private final Color towerColor;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param towerColor the tower color selected by the player
     */
    public ChosenTowerColor(String nickname, Color towerColor) {
        super(CHOSEN_TOWER_COLOR, nickname);
        this.towerColor = towerColor;
    }

    public Color getTowerColor() {
        return towerColor;
    }
}
