package com.example.owashaltungsanalyse.model.repository;

import com.example.owashaltungsanalyse.model.observation.ISession;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;

import java.util.List;

public interface IDatabase {

    List<ISessionInfo> loadAllSessionInfos();

    void storeSession(ISession session);

    List<ISession> loadSessions(ISessionInfo query);

}