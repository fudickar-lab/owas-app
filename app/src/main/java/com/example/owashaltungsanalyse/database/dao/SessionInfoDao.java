package com.example.owashaltungsanalyse.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.owashaltungsanalyse.database.entities.SessionEntity;

import java.util.List;


@Dao
public interface SessionInfoDao {

    @Query("SELECT * FROM SessionEntity")
    List<SessionEntity> loadAllSessionInfos();


}
