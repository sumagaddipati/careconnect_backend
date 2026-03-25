package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class HelpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Senior who created request
    private Long seniorId;

    private String title;
    private String description;
    private String location;

    private LocalDateTime scheduledTime;

    private Boolean emergency;

    // PENDING / ACCEPTED / COMPLETED
    private String status;

    private LocalDateTime createdAt;

    // Assigned volunteer
    private Long volunteerId;

    private LocalDateTime acceptedAt;
    private LocalDateTime completedAt;

    // Payment
    private boolean paymentRequired;
    private double paymentAmount;
    private String paymentMethod; // UPI / CASH / FREE
    private String paymentStatus; // PENDING / PAID

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSeniorId() { return seniorId; }
    public void setSeniorId(Long seniorId) { this.seniorId = seniorId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }

    public Boolean getEmergency() { return emergency; }
    public void setEmergency(Boolean emergency) { this.emergency = emergency; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public LocalDateTime getAcceptedAt() { return acceptedAt; }
    public void setAcceptedAt(LocalDateTime acceptedAt) { this.acceptedAt = acceptedAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public boolean isPaymentRequired() { return paymentRequired; }
    public void setPaymentRequired(boolean paymentRequired) { this.paymentRequired = paymentRequired; }

    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}