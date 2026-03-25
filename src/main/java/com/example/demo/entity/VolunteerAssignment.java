package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class VolunteerAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long volunteerId; // User.id
    private Long requestId;   // HelpRequest.id

    private String status; // ASSIGNED / COMPLETED
    private LocalDateTime assignedAt;
    private LocalDateTime completedAt;

    // getters
    public Long getId() { return id; }
    public Long getVolunteerId() { return volunteerId; }
    public Long getRequestId() { return requestId; }
    public String getStatus() { return status; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }

    // setters
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }
    public void setStatus(String status) { this.status = status; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}