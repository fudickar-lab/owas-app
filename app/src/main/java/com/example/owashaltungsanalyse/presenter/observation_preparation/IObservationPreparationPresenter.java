package com.example.owashaltungsanalyse.presenter.observation_preparation;

import com.example.owashaltungsanalyse.model.observation.ISessionInfo;

import java.util.List;

public interface IObservationPreparationPresenter {

    void addSessionInfo(String rater, String worker, String workplace);

    void removeSessionInfo(int listIndex);

    List<ISessionInfo> getSessionInfoList();

    void startSession(int listIndex);

}