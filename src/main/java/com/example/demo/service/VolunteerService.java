package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.HelpRequest;
import com.example.demo.entity.VolunteerProfile;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.repository.VolunteerProfileRepository;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerProfileRepository volunteerRepo;

    @Autowired
    private HelpRequestRepository helpRequestRepo;

    // ===============================
    // SAVE VOLUNTEER PROFILE
    // ===============================
    public VolunteerProfile saveProfile(VolunteerProfile profile) {

    profile.setStatus("PENDING"); // ✅ correct

    return volunteerRepo.save(profile);
}

    // ===============================
    // GET AVAILABLE REQUESTS
    // ===============================
    public List<HelpRequest> getAvailableRequests() {

        return helpRequestRepo.findByStatus("PENDING");
    }

    // ===============================
    // ACCEPT REQUEST
    // ===============================
    public HelpRequest acceptRequest(Long requestId, Long volunteerId) {

        HelpRequest request = helpRequestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setVolunteerId(volunteerId);
        request.setStatus("ACCEPTED");
        request.setAcceptedAt(LocalDateTime.now());

        return helpRequestRepo.save(request);
    }

    // ===============================
    // COMPLETE REQUEST
    // ===============================
    public HelpRequest completeRequest(Long requestId) {

        HelpRequest request = helpRequestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("COMPLETED");
        request.setCompletedAt(LocalDateTime.now());

        return helpRequestRepo.save(request);
    }
}