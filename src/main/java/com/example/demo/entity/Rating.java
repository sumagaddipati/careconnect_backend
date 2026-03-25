package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestId;

    private int rating; // 1 to 5

    private String comment;

    private LocalDateTime createdAt;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public Long getRequestId() { return requestId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ===== SETTERS =====
    public void setRequestId(Long requestId) { this.requestId = requestId; }
    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
