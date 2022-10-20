package com.example.owashaltungsanalyse.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.owashaltungsanalyse.database.entities.SessionEntryEntity;

import java.util.List;


@Dao
public interface SessionEntryDao {

    @Insert
    void storeSessionEntries(SessionEntryEntity sessionEntryEntity);

    @Query("SELECT * FROM SessionEntryEntity")
    List<SessionEntryEntity> loadSessionEntries();

    @Query("SELECT * FROM SessionEntryEntity WHERE sessionID =:id")
    List<SessionEntryEntity> loadSessionEntriesBySession(long id);
}
