package com.example.owashaltungsanalyse.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.owashaltungsanalyse.database.dao.ChatMessageDao;
import com.example.owashaltungsanalyse.database.dao.SessionDao;
import com.example.owashaltungsanalyse.database.dao.SessionEntryDao;
import com.example.owashaltungsanalyse.database.dao.SessionInfoDao;
import com.example.owashaltungsanalyse.database.entities.ChatMessageEntity;
import com.example.owashaltungsanalyse.database.entities.SessionEntity;
import com.example.owashaltungsanalyse.database.entities.SessionEntryEntity;

@Database(entities = {SessionEntryEntity.class, SessionEntity.class, ChatMessageEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SessionInfoDao sessionInfoDao();

    public abstract SessionEntryDao sessionEntryDao();

    public abstract SessionDao sessionDao();

    public abstract ChatMessageDao chatMessageDao();

}