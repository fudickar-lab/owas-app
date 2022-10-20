package com.example.owashaltungsanalyse.presenter.observation;

import com.example.owashaltungsanalyse.model.observation.*;
import com.example.owashaltungsanalyse.model.observation.shortcuts.IShortcutStrategy;
import com.example.owashaltungsanalyse.model.observation.shortcuts.LastSessionEntriesShortcutStrategy;

import javax.inject.Inject;
import java.util.List;

public class ObservationPresenter implements IObservationPresenter {

    private final ISessionManager sessionManager;
    private final IShortcutStrategy iShortcutStrategy = new LastSessionEntriesShortcutStrategy();

    @Inject
    public ObservationPresenter(ISessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Methode fügt die SessionEntries hinzu
     */
    public void submitObservation(int leg, int arm, int back, int useOfForce) {
        ISessionEntry sessionEntry = new SessionEntry(leg, arm, back, useOfForce);
        sessionManager.getCurrentSession().getSessionProtocol().addSessionEntry(sessionEntry);
    }

    public ISessionInfo getCurrentSessionInfo() {
        return this.sessionManager.getCurrentSession().getSessionInfo();
    }

    public List<ISessionEntry> getPossibleShortcuts() {
        return iShortcutStrategy.makeShortcuts(sessionManager.getCurrentSession().getSessionProtocol());

    }

    public ISessionEntry getLastSessionEntry() {
        List<ISessionEntry> entries = sessionManager.getCurrentSession().getSessionProtocol().getSessionEntryList();
        if (entries.isEmpty()) {
            return null;
        } else return entries.get(entries.size() - 1);
    }

    public IChat getChat() {
        return this.sessionManager.getCurrentSession().getChat();

    }

    public void addChatMessage(String message, String author) {
        ChatMessage chatMessage = new ChatMessage(message, author);
        this.sessionManager.getCurrentSession().getChat().getChatMessages().add(chatMessage);
    }

}