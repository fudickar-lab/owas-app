package com.example.owashaltungsanalyse.presenter.data_analysis;

import com.example.owashaltungsanalyse.database.export.IFileExporter;
import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;
import com.example.owashaltungsanalyse.model.data_analysis.IAnalysisManager;

import javax.inject.Inject;
import java.io.FileDescriptor;
import java.util.List;

public class DataAnalysisPresenter implements IDataAnalysisPresenter {

    private final IAnalysisManager analysisManager;
    private final IFileExporter csvFileExporter;

    @Inject
    public DataAnalysisPresenter(IAnalysisManager analysisManager, IFileExporter fileExporter) {
        this.analysisManager = analysisManager;
        this.csvFileExporter = fileExporter;
    }

    public void export(FileDescriptor fileDescriptor) {
        List<String> owasCodeCounted = analysisManager.getCurrentAnalysisProtocol().getExportData();
        this.csvFileExporter.writeFileUTF8(fileDescriptor, owasCodeCounted);
    }

    public AnalysisProtocol getCurrentAnalysisProtocol() {
        return analysisManager.getCurrentAnalysisProtocol();
    }

}