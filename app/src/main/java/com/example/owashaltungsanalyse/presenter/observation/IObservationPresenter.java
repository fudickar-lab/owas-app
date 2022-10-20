package com.example.owashaltungsanalyse.presenter.observation;

import com.example.owashaltungsanalyse.model.observation.IChat;
import com.example.owashaltungsanalyse.model.observation.ISessionEntry;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;

import java.util.List;

public interface IObservationPresenter {

    ISessionInfo getCurrentSessionInfo();

    void addChatMessage(String message, String author);

    List<ISessionEntry> getPossibleShortcuts();

    void submitObservation(int leg, int arm, int back, int useOfForce);

    ISessionEntry getLastSessionEntry();

    IChat getChat();
}