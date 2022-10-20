package com.example.owashaltungsanalyse.presenter.data_analysis;

import com.example.owashaltungsanalyse.model.data_analysis.AnalysisProtocol;

import java.io.FileDescriptor;

public interface IDataAnalysisPresenter {

    void export(FileDescriptor filePath);

    AnalysisProtocol getCurrentAnalysisProtocol();

}