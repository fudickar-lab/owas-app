package com.example.owashaltungsanalyse.model.observation;

import java.time.LocalDateTime;

public interface ISessionInfo {

    String getRater();

    String getWorker();

    String getWorkplace();

    LocalDateTime getTimestamp();

    void setTimestamp(LocalDateTime timestamp);

}