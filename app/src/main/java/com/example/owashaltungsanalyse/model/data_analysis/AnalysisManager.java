package com.example.owashaltungsanalyse.model.data_analysis;

import com.example.owashaltungsanalyse.model.observation.ISession;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;
import com.example.owashaltungsanalyse.model.repository.IDatabase;

import javax.inject.Inject;
import java.util.List;


public class AnalysisManager implements IAnalysisManager {

    private final IDatabase database;
    private final List<AnalysisStrategy> analysisStrategies;
    private AnalysisProtocol currentAnalysisProtocol;

    @Inject
    public AnalysisManager(IDatabase database, List<AnalysisStrategy> analysisStrategies) {
        this.database = database;
        this.analysisStrategies = analysisStrategies;
    }

    public List<ISessionInfo> loadAllSessionInfos() {
        return database.loadAllSessionInfos();
    }

    public boolean calculateAnalysisProtocol(ISessionInfo query, int analysisStrategy) {
        List<ISession> sessions = database.loadSessions(query);
        this.currentAnalysisProtocol = analysisStrategies.get(analysisStrategy).calculateAnalysisProtocol(sessions);
        return this.currentAnalysisProtocol != null;
    }

    @Override
    public List<AnalysisStrategy> getAnalysisStrategies() {
        return analysisStrategies;
    }

    @Override
    public AnalysisProtocol getCurrentAnalysisProtocol() {
        return currentAnalysisProtocol;
    }
}