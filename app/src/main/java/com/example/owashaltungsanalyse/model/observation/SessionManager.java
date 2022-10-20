package com.example.owashaltungsanalyse.model.observation;


import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

public class SessionManager implements ISessionManager {

    private final Provider<ISession> iSessionProvider;
    private final List<ISessionInfo> sessionInfoList = new ArrayList<>();
    private ISession currentSession;

    @Inject
    public SessionManager(Provider<ISession> provider) {
        this.iSessionProvider = provider;
    }

    public void addSessionInfo(ISessionInfo sessionInfo) {
        this.sessionInfoList.add(sessionInfo);
    }

    public void removeSessionInfo(int listIndex) {
        this.sessionInfoList.remove(listIndex);
    }

    public List<ISessionInfo> getSessionInfoList() {
        return this.sessionInfoList;
    }

    public void startSession(int listIndex) {
        ISessionInfo sessionInfo = this.sessionInfoList.remove(listIndex);
        this.currentSession = iSessionProvider.get();
        this.currentSession.setSessionInfo(sessionInfo);
    }

    public ISession getCurrentSession() {
        return this.currentSession;
    }

}