package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private Long receiverId;

    private String content;
    private String role;
    private String timestamp;

    // =====================
    // GETTERS
    // =====================

    public Long getId() { return id; }

    public Long getSenderId() { return senderId; }
    public Long getReceiverId() { return receiverId; }

    public String getContent() { return content; }
    public String getRole() { return role; }
    public String getTimestamp() { return timestamp; }

    // =====================
    // SETTERS (IMPORTANT 🔥)
    // =====================

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}