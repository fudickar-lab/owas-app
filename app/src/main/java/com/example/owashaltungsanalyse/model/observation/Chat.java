package com.example.owashaltungsanalyse.model.observation;

import java.util.ArrayList;
import java.util.List;

public class Chat implements IChat {

    private final List<ChatMessage> chatMessages;

    public Chat() {
        this.chatMessages = new ArrayList<>();
    }

    public List<ChatMessage> getChatMessages() {
        return this.chatMessages;
    }

}