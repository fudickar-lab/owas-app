package com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols;


import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;

import java.util.List;

// Aus Zeitgründen wurde die dazugehörige Strategy noch nicht implementiert.
public class SafetyAnalysisProtocol extends AnalysisProtocol {

    private List<Integer> safetyOfTheCodeList;

    public List<String> getExportData() {
        return null;
    }

}