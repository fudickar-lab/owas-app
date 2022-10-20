package com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols;

import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;

import java.util.ArrayList;
import java.util.List;


public class StudieAnalysisProtocol extends AnalysisProtocol {
    private static final String DELIMITER = ";";
    private final List<StudieAnalysisEntry> studieAnalysisEntries;

    public StudieAnalysisProtocol(List<StudieAnalysisEntry> studieAnalysisEntries) {
        this.studieAnalysisEntries = studieAnalysisEntries;
    }

    public List<String> getExportData() {
        List<String> exportStringList = new ArrayList<>();
        exportStringList.add("rater" + DELIMITER
                + "worker" + DELIMITER
                + "workplace" + DELIMITER
                + "timestamp" + DELIMITER
                + "owas-code");
        for (StudieAnalysisEntry entry : this.studieAnalysisEntries) {
            exportStringList.add(entry.getSessionInfo().getRater() + DELIMITER
                    + entry.getSessionInfo().getWorker() + DELIMITER
                    + entry.getSessionInfo().getWorkplace() + DELIMITER
                    + entry.getSessionEntry().getTimestamp() + DELIMITER
                    + entry.getSessionEntry().toOWASCode());
        }
        return exportStringList;
    }

}

