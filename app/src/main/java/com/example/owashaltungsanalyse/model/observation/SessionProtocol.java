package com.example.owashaltungsanalyse.model.observation;

import java.util.ArrayList;
import java.util.List;

public class SessionProtocol {

    private final List<ISessionEntry> sessionEntryList;

    public SessionProtocol() {
        this.sessionEntryList = new ArrayList<>();
    }

    public void addSessionEntry(ISessionEntry sessionEntry) {
        this.sessionEntryList.add(sessionEntry);
    }

    public List<ISessionEntry> getSessionEntryList() {
        return sessionEntryList;
    }

}