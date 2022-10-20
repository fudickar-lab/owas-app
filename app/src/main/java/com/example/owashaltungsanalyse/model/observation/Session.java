package com.example.owashaltungsanalyse.model.observation;

import com.example.owashaltungsanalyse.model.repository.IDatabase;

import javax.inject.Inject;

public class Session implements ISession {

    private ISessionInfo sessionInfo;
    private SessionProtocol sessionProtocol;
    private final IChat chat;
    private final IDatabase database;

    @Inject
    public Session(IDatabase database) {
        this.database = database;
        this.chat = new Chat();
        this.sessionProtocol = new SessionProtocol();
    }

    public void endSession() {
        this.database.storeSession(this);
    }

    public ISessionInfo getSessionInfo() {
        return this.sessionInfo;
    }

    @Override
    public void setSessionInfo(ISessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public SessionProtocol getSessionProtocol() {
        return this.sessionProtocol;
    }

    public void setSessionProtocol(SessionProtocol sessionProtocol) {
        this.sessionProtocol = sessionProtocol;
    }

    public IChat getChat() {
        return this.chat;
    }

}