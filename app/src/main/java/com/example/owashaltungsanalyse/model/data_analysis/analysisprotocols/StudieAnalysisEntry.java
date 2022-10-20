package com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols;

import com.example.owashaltungsanalyse.model.observation.ISessionEntry;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;


public class StudieAnalysisEntry {

    private final ISessionInfo sessionInfo;
    private final ISessionEntry sessionEntry;

    public StudieAnalysisEntry(ISessionInfo sessionInfo, ISessionEntry sessionEntry) {
        this.sessionInfo = sessionInfo;
        this.sessionEntry = sessionEntry;
    }

    public ISessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public ISessionEntry getSessionEntry() {
        return sessionEntry;
    }
}
