package com.example.owashaltungsanalyse.model.data_analysis;

import com.example.owashaltungsanalyse.model.observation.ISessionEntry;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountLogic implements ICountLogic {

    @Inject
    public CountLogic() {
    }

    public List<Integer> countSubCode(List<ISessionEntry> sessionEntries) {
        // Für spätere Strategies würde sich anbieten nur die Teilcodes zu zählen. Aus Zeitgründen wurde dies noch nicht implementiert
        return null;
    }


    /**
     * gibt eine Map zurück mit den Owas-Code als String und deren Anzahl
     */
    public Map<String, Integer> countCodes(List<ISessionEntry> sessionEntries) {
        Map<String, Integer> map = new HashMap<>();
        for (ISessionEntry sessionEntry : sessionEntries) {
            String code = sessionEntry.toOWASCode();
            Integer value = map.get(code);
            if (value == null) {
                map.put(code, 1);
            } else {
                map.put(code, value + 1);
            }
        }
        return map;
    }

}