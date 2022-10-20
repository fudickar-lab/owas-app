package com.example.owashaltungsanalyse.presenter.data_analysis;

import com.example.owashaltungsanalyse.model.data_analysis.AnalysisStrategy;
import com.example.owashaltungsanalyse.model.data_analysis.IAnalysisManager;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;
import com.example.owashaltungsanalyse.model.observation.SessionInfo;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class DataRequestPresenter implements IDataRequestPresenter {

    private final IAnalysisManager analysisManager;

    @Inject
    public DataRequestPresenter(IAnalysisManager analysisManager) {
        this.analysisManager = analysisManager;
    }

    public List<ISessionInfo> loadAllSessionInfos() {
        return analysisManager.loadAllSessionInfos();
    }

    public List<String> getAnalysisStrategyNames() {
        List<String> strategyNames = new ArrayList<>();
        List<AnalysisStrategy> strategies = analysisManager.getAnalysisStrategies();
        for (AnalysisStrategy strategy : strategies) {
            strategyNames.add(strategy.getClass().getSimpleName());
        }
        return strategyNames;
    }

    public boolean calculateAnalysisProtocol(String rater, String worker, String workplace, String date, int analysisStrategy) {
        LocalDateTime dateTime = null;
        if (!date.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
            dateTime = LocalDateTime.parse(date, formatter);
        }
        if (rater.equals("")) {
            rater = null;
        }
        if (worker.equals("")) {
            worker = null;
        }
        if (workplace.equals("")) {
            workplace = null;
        }

        ISessionInfo query = new SessionInfo(rater, worker, workplace, dateTime);
        return analysisManager.calculateAnalysisProtocol(query, analysisStrategy);
    }

}