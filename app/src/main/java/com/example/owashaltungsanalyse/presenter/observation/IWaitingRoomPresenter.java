package com.example.owashaltungsanalyse.presenter.observation;

import com.example.owashaltungsanalyse.model.observation.ISessionInfo;

import java.util.List;

public interface IWaitingRoomPresenter {

    List<ISessionInfo> getSessionInfoList();

    void endCurrentSession();

    ISessionInfo getCurrentSessionInfo();


}