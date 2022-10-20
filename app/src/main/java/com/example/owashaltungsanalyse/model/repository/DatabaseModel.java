package com.example.owashaltungsanalyse.model.repository;

import com.example.owashaltungsanalyse.database.AppDatabase;
import com.example.owashaltungsanalyse.database.entities.ChatMessageEntity;
import com.example.owashaltungsanalyse.database.entities.SessionEntity;
import com.example.owashaltungsanalyse.database.entities.SessionEntryEntity;
import com.example.owashaltungsanalyse.model.observation.*;

import javax.inject.Inject;
import javax.inject.Provider;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse dient dazu die Daten so umzuwandeln, dass Datenbank und Model jeweils damit arbeiten können
 */
public class DatabaseModel implements IDatabase {
    private final AppDatabase database;
    @Inject
    Provider<ISession> provider;

    @Inject
    public DatabaseModel(AppDatabase database) {
        this.database = database;
    }

    @Override
    public List<ISessionInfo> loadAllSessionInfos() {
        List<ISessionInfo> sessionInfoList = new ArrayList<>();
        List<SessionEntity> sessionEntities = database.sessionInfoDao().loadAllSessionInfos();
        for (SessionEntity entity : sessionEntities) {
            ISessionInfo sessionInfo = new SessionInfo(entity.rater, entity.worker, entity.workplace);
            Long timestamp = entity.timestamp;
            LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            sessionInfo.setTimestamp(time);
            sessionInfoList.add(sessionInfo);
        }

        return sessionInfoList;
    }

    @Override
    public void storeSession(ISession session) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.rater = session.getSessionInfo().getRater();
        sessionEntity.worker = session.getSessionInfo().getWorker();
        sessionEntity.workplace = session.getSessionInfo().getWorkplace();
        sessionEntity.timestamp = session.getSessionInfo().getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        long sessionID = database.sessionDao().storeSession(sessionEntity);

        List<ChatMessageEntity> chatMessageEntities = new ArrayList<>();

        for (ChatMessage chatMessage : session.getChat().getChatMessages()) {
            ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
            chatMessageEntity.message = chatMessage.getMessage();
            chatMessageEntity.author = chatMessage.getAuthor();
            chatMessageEntity.timestamp = chatMessage.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            chatMessageEntity.sessionID = sessionID;
            chatMessageEntities.add(chatMessageEntity);
        }
        database.sessionDao().storeChatMessages(chatMessageEntities);

        List<SessionEntryEntity> sessionEntryEntities = new ArrayList<>();
        for (ISessionEntry entry : session.getSessionProtocol().getSessionEntryList()) {
            SessionEntryEntity sessionEntryEntity = new SessionEntryEntity();
            sessionEntryEntity.leg = entry.getLeg();
            sessionEntryEntity.arm = entry.getArm();
            sessionEntryEntity.back = entry.getBack();
            sessionEntryEntity.useOfForce = entry.getUseOfForce();
            sessionEntryEntity.timestamp = entry.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            sessionEntryEntity.sessionID = sessionID;
            sessionEntryEntities.add(sessionEntryEntity);
        }
        database.sessionDao().storeSessionEntry(sessionEntryEntities);
    }

    @Override
    public List<ISession> loadSessions(ISessionInfo query) {
        //Liste für Rückgabe festlegen
        List<ISession> sessionList = new ArrayList<>();
        //Liste der Entity erstellen
        List<SessionEntity> sessionEntryEntities = new ArrayList<>();
        //prüfen ob es ein Datum gibt, wenn ja einfügen und lise erstellen sonst mit null liste erstellen
        if (query.getTimestamp() != null) {
            long time = query.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            sessionEntryEntities.addAll(database.sessionDao().loadSessions(query.getRater(), query.getWorker(),
                    query.getWorkplace(), time));
        } else {
            query.getTimestamp();
            sessionEntryEntities.addAll(database.sessionDao().loadSessions(query.getRater(), query.getWorker(),
                    query.getWorkplace(), null));
        }

        for (SessionEntity sessionEntity : sessionEntryEntities) {
            //Anlegen der SessionInfo
            ISessionInfo sessionInfo = new SessionInfo(sessionEntity.rater, sessionEntity.worker, sessionEntity.workplace);

            //Timestamp von Long in LocalDateTime umwandeln
            Long timestamp = sessionEntity.timestamp;
            LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            sessionInfo.setTimestamp(time);

            //neue Session mit der oben erstellten SessionInfo anlegen
            ISession session1 = provider.get();
            session1.setSessionInfo(sessionInfo);

            //Die Chatmessages aus der Datenbank laden
            for (ChatMessageEntity chatMessageEntity : database.chatMessageDao().loadChatMessages(sessionEntity.ID)) {

                //Die chatmessages der Session laden und mit den Werten der Datenbank setzen
                for (ChatMessage chatMessage : session1.getChat().getChatMessages()) {
                    chatMessage.setAuthor(chatMessageEntity.author);
                    chatMessage.setMessage(chatMessageEntity.message);
                    Long timeChat = chatMessageEntity.timestamp;
                    chatMessage.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeChat), ZoneId.systemDefault()));
                }
            }
            //Die Entities aus der Datenbank laden
            for (SessionEntryEntity sessionEntryEntity : database.sessionEntryDao().
                    loadSessionEntriesBySession(sessionEntity.ID)) {
                long sessionEntryTimestamp = sessionEntryEntity.timestamp;
                //neue SessionEntry mit den daten anlegen.
                ISessionEntry sessionEntry = new SessionEntry(sessionEntryEntity.leg, sessionEntryEntity.arm,
                        sessionEntryEntity.back, sessionEntryEntity.useOfForce);
                sessionEntry.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(sessionEntryTimestamp),
                        ZoneId.systemDefault()));

                //hinzufügen der Entries zum Protocol der Session
                session1.getSessionProtocol().addSessionEntry(sessionEntry);
            }


            //Session in die Liste laden
            sessionList.add(session1);

        }
        //Liste zurückgeben.
        return sessionList;
    }
}
