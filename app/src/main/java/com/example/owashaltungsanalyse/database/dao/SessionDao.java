package com.example.owashaltungsanalyse.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.owashaltungsanalyse.database.entities.ChatMessageEntity;
import com.example.owashaltungsanalyse.database.entities.SessionEntity;
import com.example.owashaltungsanalyse.database.entities.SessionEntryEntity;

import java.util.List;


@Dao
public interface SessionDao {

    @Query("SELECT * From sessionentity WHERE (rater =:rater OR :rater is null) AND (:worker is null OR worker =:worker) AND" +
            " (:workplace is Null OR workplace =:workplace) AND " +
            "(:timestamp is Null OR timestamp =:timestamp OR timestamp Between :timestamp and (:timestamp+ 60000))")
    List<SessionEntity> loadSessions(String rater, String worker, String workplace, Long timestamp);

    @Insert
    long storeSession(SessionEntity sessionEntity);

    @Insert
    void storeChatMessages(List<ChatMessageEntity> chatMessageEntities);

    @Insert
    void storeSessionEntry(List<SessionEntryEntity> sessionEntryEntities);
}
