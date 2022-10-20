package com.example.owashaltungsanalyse.di;

import com.example.owashaltungsanalyse.presenter.data_analysis.DataAnalysisPresenter;
import com.example.owashaltungsanalyse.presenter.data_analysis.DataRequestPresenter;
import com.example.owashaltungsanalyse.presenter.data_analysis.IDataAnalysisPresenter;
import com.example.owashaltungsanalyse.presenter.data_analysis.IDataRequestPresenter;
import com.example.owashaltungsanalyse.presenter.observation.IObservationPresenter;
import com.example.owashaltungsanalyse.presenter.observation.IWaitingRoomPresenter;
import com.example.owashaltungsanalyse.presenter.observation.ObservationPresenter;
import com.example.owashaltungsanalyse.presenter.observation.WaitingRoomPresenter;
import com.example.owashaltungsanalyse.presenter.observation_preparation.IObservationPreparationPresenter;
import com.example.owashaltungsanalyse.presenter.observation_preparation.ObservationPreparationPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;



@Module
@InstallIn(ActivityComponent.class)
abstract class PresenterModule {

    @Binds
    public abstract IWaitingRoomPresenter bindIWaitingRoomPresenter(WaitingRoomPresenter waitingRoomPresenter);

    @Binds
    public abstract IObservationPreparationPresenter bindIObservationPreparationPresenter(ObservationPreparationPresenter observationPreparationPresenter);

    @Binds
    public abstract IObservationPresenter bindIObservationPresenter(ObservationPresenter observationPresenter);

    @Binds
    public abstract IDataAnalysisPresenter bindIDataAnalysisPresenter(DataAnalysisPresenter dataAnalysisPresenter);

    @Binds
    public abstract IDataRequestPresenter bindIDataRequestPresenter(DataRequestPresenter dataRequestPresenter);

}