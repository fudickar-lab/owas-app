package com.example.owashaltungsanalyse.model.observation.shortcuts;

import com.example.owashaltungsanalyse.model.observation.ISessionEntry;
import com.example.owashaltungsanalyse.model.observation.SessionProtocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Shortcuts-Methode: berechnet die zuletzt genutzten Codes
 */
public class LastSessionEntriesShortcutStrategy implements IShortcutStrategy {

    private boolean inlist = false;
    private final List<ISessionEntry> reversedSessionEntryList = new ArrayList<>();

    public List<ISessionEntry> makeShortcuts(SessionProtocol sessionProtocol) {
        reversedSessionEntryList.addAll(sessionProtocol.getSessionEntryList());
        Collections.reverse(reversedSessionEntryList);

        List<ISessionEntry> list = new ArrayList<>();
        for (ISessionEntry entry : reversedSessionEntryList) {
            for (ISessionEntry entriesList : list) {
                if (entry.toOWASCode().equals(entriesList.toOWASCode())) {
                    inlist = true;
                }
            }
            if (!inlist) {
                list.add(entry);
            }
            inlist = false;
        }
        return list;
    }

}