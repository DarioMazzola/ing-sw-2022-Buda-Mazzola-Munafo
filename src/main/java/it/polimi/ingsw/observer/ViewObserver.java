package it.polimi.ingsw.observer;

import it.polimi.ingsw.messages.command.*;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Wizard;

public interface ViewObserver {
    void onUpdateNewGame(String nickname);

    void onUpdateServerInfo();

    void onUpdateExpertMode(boolean expertMode);

    void onUpdateNumPlayers(int numPlayers);

    void onUpdateNickname(String nickname);

    void onUpdateWizard(Wizard wizard);

    void onUpdateTeam(int selectedTeam, boolean isTeamLeader);

    void onSelectTower(Color towerColor);
}