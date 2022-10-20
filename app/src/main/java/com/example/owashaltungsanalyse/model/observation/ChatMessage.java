package com.example.owashaltungsanalyse.model.observation;

import java.time.LocalDateTime;

public class ChatMessage {

    private LocalDateTime timestamp;
    private String message;
    private String author;

    public ChatMessage(String message, String author) {
        this.message = message;
        this.author = author;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}