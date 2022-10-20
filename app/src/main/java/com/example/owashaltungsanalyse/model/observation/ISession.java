package com.example.owashaltungsanalyse.model.observation;

public interface ISession {

    void endSession();

    ISessionInfo getSessionInfo();

    void setSessionInfo(ISessionInfo sessionInfo);

    SessionProtocol getSessionProtocol();

    void setSessionProtocol(SessionProtocol sessionProtocol);

    IChat getChat();


}