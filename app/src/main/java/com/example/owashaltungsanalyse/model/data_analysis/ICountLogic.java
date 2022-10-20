package com.example.owashaltungsanalyse.model.data_analysis;

import com.example.owashaltungsanalyse.model.observation.ISessionEntry;

import java.util.List;
import java.util.Map;

public interface ICountLogic {

    List<Integer> countSubCode(List<ISessionEntry> sessionEntries);

    Map<String, Integer> countCodes(List<ISessionEntry> sessionEntries);

}