package com.example.owashaltungsanalyse.presenter.data_analysis;

import com.example.owashaltungsanalyse.model.observation.ISessionInfo;

import java.util.List;

public interface IDataRequestPresenter {

    List<ISessionInfo> loadAllSessionInfos();

    List<String> getAnalysisStrategyNames();

    boolean calculateAnalysisProtocol(String rater, String worker, String workplace, String date, int analysisStrategy);

}