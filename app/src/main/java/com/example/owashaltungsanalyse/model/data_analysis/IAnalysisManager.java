package com.example.owashaltungsanalyse.model.data_analysis;

import com.example.owashaltungsanalyse.model.observation.ISessionInfo;

import java.util.List;

public interface IAnalysisManager {

    List<ISessionInfo> loadAllSessionInfos();

    List<AnalysisStrategy> getAnalysisStrategies();

    boolean calculateAnalysisProtocol(ISessionInfo query, int analysisStrategy);

    AnalysisProtocol getCurrentAnalysisProtocol();

}