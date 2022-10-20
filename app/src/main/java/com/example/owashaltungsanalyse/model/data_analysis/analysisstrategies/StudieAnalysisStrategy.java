package com.example.owashaltungsanalyse.model.data_analysis.analysisstrategies;


import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;
import com.example.owashaltungsanalyse.model.data_analysis.AnalysisStrategy;
import com.example.owashaltungsanalyse.model.data_analysis.ICountLogic;
import com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols.StudieAnalysisEntry;
import com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols.StudieAnalysisProtocol;
import com.example.owashaltungsanalyse.model.observation.ISession;
import com.example.owashaltungsanalyse.model.observation.ISessionEntry;

import java.util.ArrayList;
import java.util.List;

public class StudieAnalysisStrategy extends AnalysisStrategy {

    public StudieAnalysisStrategy(ICountLogic countLogic) {
        super(countLogic);
    }

    @Override
    public AnalysisProtocol calculateAnalysisProtocol(List<ISession> sessions) {
        List<StudieAnalysisEntry> studieAnalysisEntries = new ArrayList<>();
        for (ISession session : sessions) {
            for (ISessionEntry entry : session.getSessionProtocol().getSessionEntryList()) {
                studieAnalysisEntries.add(new StudieAnalysisEntry(session.getSessionInfo(), entry));
            }
        }
        return new StudieAnalysisProtocol(studieAnalysisEntries);
    }
}
