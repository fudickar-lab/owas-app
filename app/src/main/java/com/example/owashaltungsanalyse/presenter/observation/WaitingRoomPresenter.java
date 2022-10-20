package com.example.owashaltungsanalyse.presenter.observation;

import com.example.owashaltungsanalyse.model.observation.ISessionInfo;
import com.example.owashaltungsanalyse.model.observation.ISessionManager;

import javax.inject.Inject;
import java.util.List;

public class WaitingRoomPresenter implements IWaitingRoomPresenter {

    private final ISessionManager sessionManager;

    @Inject
    public WaitingRoomPresenter(ISessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public List<ISessionInfo> getSessionInfoList() {
        return this.sessionManager.getSessionInfoList();
    }

    public void endCurrentSession() {
        if (this.sessionManager.getCurrentSession() != null) {
            this.sessionManager.getCurrentSession().endSession();
        }
    }

    public ISessionInfo getCurrentSessionInfo() {
        return this.sessionManager.getCurrentSession().getSessionInfo();
    }


}