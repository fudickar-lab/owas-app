package com.example.owashaltungsanalyse.model.observation.shortcuts;

import com.example.owashaltungsanalyse.model.observation.ISessionEntry;
import com.example.owashaltungsanalyse.model.observation.SessionProtocol;

import java.util.List;

public interface IShortcutStrategy {

    List<ISessionEntry> makeShortcuts(SessionProtocol sessionProtocol);

}