package com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols;


import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionCodesAnalysisProtocol extends AnalysisProtocol {
    private static final String DELIMITER = ";";
    private final Map<String, Integer> owasCodeCounted;

    public SessionCodesAnalysisProtocol(Map<String, Integer> owasCodeCounted) {
        this.owasCodeCounted = owasCodeCounted;
    }

    public List<String> getExportData() {
        List<String> owasCodesCountedList = new ArrayList<>();
        for (String key : this.owasCodeCounted.keySet()) {
            int value = this.owasCodeCounted.get(key);
            owasCodesCountedList.add(key + DELIMITER + value);
        }
        return owasCodesCountedList;
    }

    public Map<String, Integer> getOwasCodeCounted() {
        return this.owasCodeCounted;
    }
}