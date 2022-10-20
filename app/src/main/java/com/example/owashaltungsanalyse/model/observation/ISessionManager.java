package com.example.owashaltungsanalyse.model.observation;

import java.util.List;

public interface ISessionManager {

    void addSessionInfo(ISessionInfo sessionInfo);

    void removeSessionInfo(int listIndex);

    List<ISessionInfo> getSessionInfoList();

    void startSession(int listIndex);

    ISession getCurrentSession();

}