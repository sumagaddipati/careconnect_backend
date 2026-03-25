package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SeniorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String seniorCode;

    @Column(columnDefinition = "LONGTEXT")
    private String profileImage;

    private String fullName;
    private String age;
    private String gender;
    private String phone;
    private String address;
    private String city;

    @Column(columnDefinition = "LONGTEXT")
    private String healthConditions;

    private String otherCondition;
    private String medications;
    private String allergies;
    private String mobility;

    private String livesAlone;
    private String familySupport;
    private String familyName;
    private String familyPhone;
    private String familyEmail;

    private String language;
    private String preferredVolunteerGender;
    private String preferredTime;

    @Column(columnDefinition = "LONGTEXT")
    private String specialNotes;

    @Column(columnDefinition = "LONGTEXT")
    private String emergencyNotes;

    private String riskLevel;

    public SeniorProfile() {}

    public Long getId() { return id; }
    public void setId(Long id){ this.id=id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getSeniorCode() { return seniorCode; }
    public void setSeniorCode(String seniorCode) { this.seniorCode = seniorCode; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getHealthConditions() { return healthConditions; }
    public void setHealthConditions(String healthConditions) { this.healthConditions = healthConditions; }

    public String getOtherCondition() { return otherCondition; }
    public void setOtherCondition(String otherCondition) { this.otherCondition = otherCondition; }

    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getMobility() { return mobility; }
    public void setMobility(String mobility) { this.mobility = mobility; }

    public String getLivesAlone() { return livesAlone; }
    public void setLivesAlone(String livesAlone) { this.livesAlone = livesAlone; }

    public String getFamilySupport() { return familySupport; }
    public void setFamilySupport(String familySupport) { this.familySupport = familySupport; }

    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public String getFamilyPhone() { return familyPhone; }
    public void setFamilyPhone(String familyPhone) { this.familyPhone = familyPhone; }

    public String getFamilyEmail() { return familyEmail; }
    public void setFamilyEmail(String familyEmail) { this.familyEmail = familyEmail; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getPreferredVolunteerGender() { return preferredVolunteerGender; }
    public void setPreferredVolunteerGender(String preferredVolunteerGender) { this.preferredVolunteerGender = preferredVolunteerGender; }

    public String getPreferredTime() { return preferredTime; }
    public void setPreferredTime(String preferredTime) { this.preferredTime = preferredTime; }

    public String getSpecialNotes() { return specialNotes; }
    public void setSpecialNotes(String specialNotes) { this.specialNotes = specialNotes; }

    public String getEmergencyNotes() { return emergencyNotes; }
    public void setEmergencyNotes(String emergencyNotes) { this.emergencyNotes = emergencyNotes; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
}