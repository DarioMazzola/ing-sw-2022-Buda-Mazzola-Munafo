package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.model.Color;

import static it.polimi.ingsw.messages.MessageType.COLOR_TOWER;

/**
 * ColorTower class represents ColorTower network message.
 * This message is sent from the client to the server to communicate the player's chosen tower color.
 *
 * @author Alessio Buda
 */
public class ColorTower extends CommandMessage {
    private final Color towerColor;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname
     * @param towerColor the tower color selceted by the player
     */
    public ColorTower(String nickname, Color towerColor) {
        super(COLOR_TOWER, nickname);
        this.towerColor = towerColor;
    }

    public Color getTowerColor() {
        return towerColor;
    }
}
