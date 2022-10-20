package com.example.owashaltungsanalyse.model.observation;

import java.time.LocalDateTime;

public class SessionInfo implements ISessionInfo {

    private final String rater;
    private final String worker;
    private final String workplace;
    private LocalDateTime timestamp;

    public SessionInfo(String rater, String worker, String workplace) {
        this.rater = rater;
        this.worker = worker;
        this.workplace = workplace;
        this.timestamp = LocalDateTime.now();
    }

    public SessionInfo(String rater, String worker, String workplace, LocalDateTime timestamp) {
        this.rater = rater;
        this.worker = worker;
        this.workplace = workplace;
        this.timestamp = timestamp;
    }

    @Override
    public String getRater() {
        return rater;
    }


    @Override
    public String getWorker() {
        return worker;
    }


    @Override
    public String getWorkplace() {
        return workplace;
    }


    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "rater='" + rater + '\'' +
                ", worker='" + worker + '\'' +
                ", workplace='" + workplace + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}