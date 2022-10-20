package com.example.owashaltungsanalyse.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity
public class SessionEntryEntity {
    @PrimaryKey
    public Long timestamp;

    @ForeignKey(entity = SessionEntity.class,
            parentColumns = "parentClassColumn",
            childColumns = "childClassColumn",
            onDelete = ForeignKey.CASCADE)
    public long sessionID;

    @ColumnInfo(name = "leg")
    public int leg;

    @ColumnInfo(name = "arm")
    public int arm;

    @ColumnInfo(name = "back")
    public int back;

    @ColumnInfo(name = "use of Force")
    public int useOfForce;

    @ColumnInfo(name = "Owas Code")
    public String owasCode;
}
