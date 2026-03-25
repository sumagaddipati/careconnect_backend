package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class FamilyLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long familyUserId;

    private Long seniorUserId;

    private String seniorCode;

    private String status; // ACTIVE / REMOVED

    public FamilyLink() {
    }

    public Long getId() {
        return id;
    }

    public Long getFamilyUserId() {
        return familyUserId;
    }

    public void setFamilyUserId(Long familyUserId) {
        this.familyUserId = familyUserId;
    }

    public Long getSeniorUserId() {
        return seniorUserId;
    }

    public void setSeniorUserId(Long seniorUserId) {
        this.seniorUserId = seniorUserId;
    }

    public String getSeniorCode() {
        return seniorCode;
    }

    public void setSeniorCode(String seniorCode) {
        this.seniorCode = seniorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}