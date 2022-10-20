package com.example.owashaltungsanalyse.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity
public class ChatMessageEntity {
    @PrimaryKey
    public Long timestamp;

    @ForeignKey(entity = SessionEntity.class,
            parentColumns = "parentClassColumn",
            childColumns = "childClassColumn",
            onDelete = ForeignKey.CASCADE)
    public long sessionID;

    @ColumnInfo(name = "message")
    public String message;

    @ColumnInfo(name = "author")
    public String author;
}
