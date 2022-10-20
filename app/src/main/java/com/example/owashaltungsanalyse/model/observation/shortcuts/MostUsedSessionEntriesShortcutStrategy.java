package com.example.owashaltungsanalyse.model.observation.shortcuts;

import com.example.owashaltungsanalyse.model.data_analysis.CountLogic;
import com.example.owashaltungsanalyse.model.data_analysis.ICountLogic;
import com.example.owashaltungsanalyse.model.observation.ISessionEntry;
import com.example.owashaltungsanalyse.model.observation.SessionEntry;
import com.example.owashaltungsanalyse.model.observation.SessionProtocol;

import java.util.*;


/**
 * Ein Beispiel wie eine zweite Shortcut-Strategy aussehen kann.
 * Sie könnte analog wie im Beispiel der AnalysisStrategy eingefügt werden.
*/
public class MostUsedSessionEntriesShortcutStrategy implements IShortcutStrategy {
    private final ICountLogic countlogik;

    public MostUsedSessionEntriesShortcutStrategy() {
        this.countlogik = new CountLogic();
    }

    /**
     * SessionEntrys werden nach häufigkeit absteigend sortiert und doppelte ignoriert
     */
    public List<ISessionEntry> makeShortcuts(SessionProtocol sessionProtocol) {
        Map<String, Integer> map = countlogik.countCodes(sessionProtocol.getSessionEntryList());
        //die Strings und Integer der Map werden in einer Liste abgespeichert um diese dann sortieren zu können
        ArrayList<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        //nutzung von new Comparator, um zu sagen wie sortiert werden soll
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //hier wird der zweite Wert mit dem ersten verglichen um die Liste absteigend zu sortieren
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        // liste nun zu Liste ISessionEntry umwandeln
        List<ISessionEntry> sessionEntryList = new ArrayList<>(entryList.size());
        for (Map.Entry<String, Integer> entry : entryList) {
            String owasCode = entry.getKey();
            ISessionEntry sessionEntry = new SessionEntry(owasCode);
            sessionEntryList.add(sessionEntry);
        }
        return sessionEntryList;
    }

}