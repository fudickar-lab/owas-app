package com.example.owashaltungsanalyse.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SessionEntity {

    @PrimaryKey(autoGenerate = true)
    public long ID;

    @ColumnInfo(name = "timestamp")
    public Long timestamp;

    @ColumnInfo(name = "rater")
    public String rater;

    @ColumnInfo(name = "worker")
    public String worker;

    @ColumnInfo(name = "workplace")
    public String workplace;
}
