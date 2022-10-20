package com.example.owashaltungsanalyse.model.data_analysis.analysisstrategies;

import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;
import com.example.owashaltungsanalyse.model.data_analysis.AnalysisStrategy;
import com.example.owashaltungsanalyse.model.data_analysis.ICountLogic;
import com.example.owashaltungsanalyse.model.observation.ISession;

import java.util.List;

// Aus Zeitgründen wurde diese Strategy noch nicht implementiert.
public class TimeAnalysisStrategy extends AnalysisStrategy {

    public TimeAnalysisStrategy(ICountLogic countLogic) {
        super(countLogic);
    }

    @Override
    public AnalysisProtocol calculateAnalysisProtocol(List<ISession> sessions) {
        return null;
    }
}