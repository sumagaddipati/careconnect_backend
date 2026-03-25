package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VolunteerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    // BASIC DETAILS
    private String fullName;
    private String age;
    private String phone;
    private String city;
    private String address;
    private String gender;

    // EXPERIENCE
    private String experienceNotes;

    // SKILLS
    private Boolean firstAidCertified;
    private Boolean medicalTraining;
    private Boolean hasVehicle;
    private String vehicleType;

    // 🔥 PROFILE IMAGE (MAIN THING)
    private String documentPath; // used as volunteer photo

    // STATUS (SIMPLE)
    private String status; // ACTIVE

    // OPTIONAL (can use later)
    private Double rating = 0.0;
    private Integer totalReviews = 0;

    // ======================
    // GETTERS & SETTERS
    // ======================

    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getExperienceNotes() { return experienceNotes; }
    public void setExperienceNotes(String experienceNotes) { this.experienceNotes = experienceNotes; }

    public Boolean getFirstAidCertified() { return firstAidCertified; }
    public void setFirstAidCertified(Boolean firstAidCertified) { this.firstAidCertified = firstAidCertified; }

    public Boolean getMedicalTraining() { return medicalTraining; }
    public void setMedicalTraining(Boolean medicalTraining) { this.medicalTraining = medicalTraining; }

    public Boolean getHasVehicle() { return hasVehicle; }
    public void setHasVehicle(Boolean hasVehicle) { this.hasVehicle = hasVehicle; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getDocumentPath() { return documentPath; }
    public void setDocumentPath(String documentPath) { this.documentPath = documentPath; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public void setId(Long id) {
    this.id = id;
}

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
}