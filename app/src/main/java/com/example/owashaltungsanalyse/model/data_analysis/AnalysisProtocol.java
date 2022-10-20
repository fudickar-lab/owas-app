package com.example.owashaltungsanalyse.model.data_analysis;

import com.example.owashaltungsanalyse.database.export.IFileExporter;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;

import javax.inject.Inject;
import java.util.List;

public abstract class AnalysisProtocol {

    @Inject
    IFileExporter fileExporter;
    // Diese query war für die aus Zeitgründen noch nicht implementierten Strategies gedacht
    private ISessionInfo query;

    public AnalysisProtocol() {
    }

    public abstract List<String> getExportData();
}