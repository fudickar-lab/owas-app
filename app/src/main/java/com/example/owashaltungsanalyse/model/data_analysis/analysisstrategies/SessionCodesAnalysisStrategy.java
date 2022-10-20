package com.example.owashaltungsanalyse.model.data_analysis.analysisstrategies;

import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;
import com.example.owashaltungsanalyse.model.data_analysis.AnalysisStrategy;
import com.example.owashaltungsanalyse.model.data_analysis.ICountLogic;
import com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols.SessionCodesAnalysisProtocol;
import com.example.owashaltungsanalyse.model.observation.ISession;
import com.example.owashaltungsanalyse.model.observation.ISessionEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionCodesAnalysisStrategy extends AnalysisStrategy {

    public SessionCodesAnalysisStrategy(ICountLogic countLogic) {
        super(countLogic);
    }

    @Override
    public AnalysisProtocol calculateAnalysisProtocol(List<ISession> sessions) {
        List<ISessionEntry> list = new ArrayList<>();
        for (ISession session : sessions) {
            for (ISessionEntry entry : session.getSessionProtocol().getSessionEntryList()) {
                list.add(entry);
            }
        }
        Map<String, Integer> map = getCountLogic().countCodes(list);

        return new SessionCodesAnalysisProtocol(map);
    }

}