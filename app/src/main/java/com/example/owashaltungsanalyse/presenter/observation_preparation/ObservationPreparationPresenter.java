package com.example.owashaltungsanalyse.presenter.observation_preparation;

import com.example.owashaltungsanalyse.model.observation.ISessionInfo;
import com.example.owashaltungsanalyse.model.observation.ISessionManager;
import com.example.owashaltungsanalyse.model.observation.SessionInfo;

import javax.inject.Inject;
import java.util.List;

public class ObservationPreparationPresenter implements IObservationPreparationPresenter {

    private final ISessionManager sessionManager;

    @Inject
    public ObservationPreparationPresenter(ISessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void addSessionInfo(String rater, String worker, String workplace) {
        ISessionInfo sessionInfo = new SessionInfo(rater, worker, workplace);
        this.sessionManager.addSessionInfo(sessionInfo);
    }

    public void removeSessionInfo(int listIndex) {
        this.sessionManager.removeSessionInfo(listIndex);
    }

    public List<ISessionInfo> getSessionInfoList() {
        return this.sessionManager.getSessionInfoList();
    }

    public void startSession(int listIndex) {
        this.sessionManager.startSession(listIndex);
    }

}