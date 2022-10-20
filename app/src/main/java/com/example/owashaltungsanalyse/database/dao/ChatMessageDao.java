package com.example.owashaltungsanalyse.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.owashaltungsanalyse.database.entities.ChatMessageEntity;

import java.util.List;


@Dao
public interface ChatMessageDao {

    @Insert
    void storeChat(ChatMessageEntity chatMessageEntity);

    @Query("SELECT * From chatmessageentity")
    List<ChatMessageEntity> loadAllChatMessages();

    @Query("SELECT * FROM chatmessageentity WHERE sessionID =:id")
    List<ChatMessageEntity> loadChatMessages(long id);
}
