package com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols;

import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;

import java.util.List;

// Aus Zeitgründen wurde die dazugehörige Strategy noch nicht implementiert.
public class WorkplaceAnalysisProtocol extends AnalysisProtocol {

    private List<Integer> armList;
    private List<Integer> legList;
    private List<Integer> backList;

    public List<String> getExportData() {
        return null;
    }

}