package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FamilyLink;
import com.example.demo.entity.HelpRequest;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.entity.VolunteerAssignment;
import com.example.demo.entity.VolunteerProfile;

import com.example.demo.repository.FamilyLinkRepository;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VolunteerAssignmentRepository;
import com.example.demo.repository.VolunteerProfileRepository;

@Service
public class AdminService {

    @Autowired
    private HelpRequestRepository requestRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VolunteerProfileRepository profileRepo;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private VolunteerAssignmentRepository assignRepo;

    @Autowired
    private FamilyLinkRepository familyRepo;

    // =========================
    // DASHBOARD STATS
    // =========================
    public Map<String, Object> getStats() {

        Map<String, Object> map = new HashMap<>();

        map.put("totalUsers", userRepo.count());
        map.put("totalRequests", requestRepo.count());

        long active = requestRepo.findAll()
                .stream()
                .filter(r -> "PENDING".equalsIgnoreCase(r.getStatus()))
                .count();

        map.put("activeRequests", active);

        return map;
    }

    // =========================
    // REQUESTS
    // =========================
    public List<HelpRequest> getAllRequests() {
        return requestRepo.findAll();
    }

    public HelpRequest updateRequestStatus(Long id, String status) {

        HelpRequest req = requestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus(status.trim().toUpperCase());

        return requestRepo.save(req);
    }

    // =========================
    // VOLUNTEERS
    // =========================
    public List<User> getVolunteers() {
        return userRepo.findByRole("VOLUNTEER");
    }

    public void removeVolunteer(Long id) {
        userRepo.deleteById(id);
    }

    // =========================
    // SENIORS
    // =========================
    public List<User> getSeniors() {
        return userRepo.findByRole("SENIOR");
    }

    // =========================
    // 🔥 PENDING VOLUNTEERS
    // =========================
    public List<VolunteerProfile> getPendingVolunteers() {
        return profileRepo.findByStatusIgnoreCase("PENDING");
    }
    

    // =========================
    // 🔥 APPROVE / REJECT
    // =========================
    public VolunteerProfile verifyVolunteer(Long id, String status) {

        VolunteerProfile v = profileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        String clean = status.trim().toUpperCase();

        if (!clean.equals("APPROVED") && !clean.equals("REJECTED")) {
            throw new RuntimeException("Invalid status");
        }

        v.setStatus(clean);

        return profileRepo.save(v);
    }

    // =========================
    // 🔥 ALL VOLUNTEERS
    // =========================
    public List<VolunteerProfile> getAllVolunteerProfiles() {
        return profileRepo.findAll();
    }

    // =========================
    // MESSAGE SYSTEM
    // =========================
    public String sendMessage(Long requestId, String msg) {

        HelpRequest req = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Long seniorId = req.getSeniorId();

        VolunteerAssignment assign = assignRepo.findByRequestId(requestId);

        if (assign != null) {
            Message m = new Message();
            m.setSenderId(0L);
            m.setReceiverId(assign.getVolunteerId());
            m.setContent(msg);
            m.setRole("ADMIN");
            m.setTimestamp(new Date().toString());

            messageRepo.save(m);
        }

        List<FamilyLink> families = familyRepo.findBySeniorUserId(seniorId);

        for (FamilyLink f : families) {
            Message m = new Message();
            m.setSenderId(0L);
            m.setReceiverId(f.getFamilyUserId());
            m.setContent(msg);
            m.setRole("ADMIN");
            m.setTimestamp(new Date().toString());

            messageRepo.save(m);
        }

        return "Message sent";
    }
}