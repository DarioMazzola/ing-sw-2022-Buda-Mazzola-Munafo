package it.polimi.ingsw.messages.command;

import it.polimi.ingsw.messages.MessageType;

import java.util.List;

public class ActionPhase extends CommandMessage{
    private final List<String> availableActions;

    public ActionPhase(String nickname, List<String> availableActions) {
        super(MessageType.ACTION_PHASE, nickname);
        this.availableActions = availableActions;
    }

    public List<String> getAvailableActions() {
        return availableActions;
    }
}
