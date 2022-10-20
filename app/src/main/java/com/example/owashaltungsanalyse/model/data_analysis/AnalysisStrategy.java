package com.example.owashaltungsanalyse.model.data_analysis;

import com.example.owashaltungsanalyse.model.observation.ISession;

import java.util.List;

public abstract class AnalysisStrategy {

    private final ICountLogic countLogic;

    public AnalysisStrategy(ICountLogic countLogic) {
        this.countLogic = countLogic;
    }

    public ICountLogic getCountLogic() {
        return countLogic;
    }

    public abstract AnalysisProtocol calculateAnalysisProtocol(List<ISession> sessions);

}