package com.example.owashaltungsanalyse.model.data_analysis.analysisstrategies;

import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;
import com.example.owashaltungsanalyse.model.data_analysis.AnalysisStrategy;
import com.example.owashaltungsanalyse.model.data_analysis.ICountLogic;
import com.example.owashaltungsanalyse.model.observation.ISession;

import java.util.List;

// Aus Zeitgründen wurde diese Strategy noch nicht implementiert.
public class SafetyAnalysisStrategy extends AnalysisStrategy {

    public SafetyAnalysisStrategy(ICountLogic countLogic) {
        super(countLogic);
    }

    @Override
    public AnalysisProtocol calculateAnalysisProtocol(List<ISession> sessions) {
        return null;
    }

}