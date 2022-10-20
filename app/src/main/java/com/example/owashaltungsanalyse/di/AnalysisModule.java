package com.example.owashaltungsanalyse.di;

import com.example.owashaltungsanalyse.database.export.CSVFileExporter;
import com.example.owashaltungsanalyse.database.export.IFileExporter;
import com.example.owashaltungsanalyse.model.data_analysis.*;
import com.example.owashaltungsanalyse.model.data_analysis.analysisstrategies.SessionCodesAnalysisStrategy;
import com.example.owashaltungsanalyse.model.data_analysis.analysisstrategies.StudieAnalysisStrategy;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;


@Module
@InstallIn(ApplicationComponent.class)
public abstract class AnalysisModule {

    @Provides
    public static List<AnalysisStrategy> provideIAnalysisStrategy(ICountLogic countLogic) {
        List<AnalysisStrategy> strategies = new ArrayList<>();

        strategies.add(new SessionCodesAnalysisStrategy(countLogic));
        strategies.add(new StudieAnalysisStrategy(countLogic));
        /*
        Wenn die neuen Strategien geschrieben sind, können sie hier ganz einfach hinzugefügt werden

        strategies.add(new TimeAnalysisStrategy(countLogic));
        strategies.add(new WorkplaceAnalysisStrategy(countLogic));
        strategies.add(new SafetyAnalysisStrategy(countLogic));
        */

        return strategies;
    }

    @Singleton
    @Binds
    public abstract IAnalysisManager bindIAnalysisManager(AnalysisManager analysisManager);

    @Binds
    public abstract IFileExporter bindIFileExport(CSVFileExporter csvFileExporter);

    @Binds
    public abstract ICountLogic bindICountLogic(CountLogic countLogic);


}
