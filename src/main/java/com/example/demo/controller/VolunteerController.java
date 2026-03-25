package com.example.demo.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.HelpRequest;
import com.example.demo.entity.VolunteerProfile;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.repository.VolunteerProfileRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/volunteer")
@CrossOrigin("*")
public class VolunteerController {

    @Autowired
    private VolunteerProfileRepository profileRepo;

    @Autowired
    private HelpRequestRepository requestRepo;

    // ======================
    // SAVE / UPDATE PROFILE
    // ======================
    @PostMapping("/profile")
    public String saveProfile(
            @RequestParam("profile") String json,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            VolunteerProfile profile = mapper.readValue(json, VolunteerProfile.class);

            if (profile.getUserId() == null) {
                return "ERROR: userId missing";
            }

            // 🔥 CHECK EXISTING PROFILE
            VolunteerProfile existing = profileRepo.findByUserId(profile.getUserId()).orElse(null);

            if (existing != null) {
                profile.setId(existing.getId());

                // keep old image if new not uploaded
                if (file == null || file.isEmpty()) {
                    profile.setDocumentPath(existing.getDocumentPath());
                }
            }

            profile.setStatus("ACTIVE"); // simplified

            // 🔥 FILE SAVE
            if (file != null && !file.isEmpty()) {

                String uploadDir = System.getProperty("user.dir") + "/uploads";

                File folder = new File(uploadDir);
                if (!folder.exists()) folder.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                File dest = new File(uploadDir + "/" + fileName);
                file.transferTo(dest);

                profile.setDocumentPath("uploads/" + fileName);
            }

            profileRepo.save(profile);

            return "Saved";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    // ======================
    // GET PROFILE
    // ======================
    @GetMapping("/profile/{userId}")
    public VolunteerProfile getProfile(@PathVariable Long userId) {
        return profileRepo.findByUserId(userId).orElse(null);
    }

    // ======================
    // GET AVAILABLE REQUESTS
    // ======================
    @GetMapping("/requests/{userId}")
    public List<HelpRequest> getRequests(@PathVariable Long userId) {
        return requestRepo.findByStatus("PENDING");
    }

    // ======================
    // ACCEPT REQUEST
    // ======================
    @PostMapping("/accept/{requestId}/{userId}")
    public String accept(@PathVariable Long requestId, @PathVariable Long userId) {

        HelpRequest r = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // 🔥 prevent double accept
        if (r.getStatus().equals("ACCEPTED")) {
            return "ALREADY_ACCEPTED";
        }

        r.setStatus("ACCEPTED");
        r.setVolunteerId(userId);
        r.setAcceptedAt(LocalDateTime.now());

        requestRepo.save(r);

        return "ACCEPTED";
    }

    // ======================
    // MY TASKS (ACCEPTED)
    // ======================
    @GetMapping("/mytasks/{userId}")
    public List<HelpRequest> myTasks(@PathVariable Long userId) {
        return requestRepo.findByVolunteerIdAndStatus(userId, "ACCEPTED");
    }

    // ======================
    // COMPLETE TASK (DONE)
    // ======================
    @PostMapping("/complete/{requestId}")
    public String complete(@PathVariable Long requestId) {

        HelpRequest r = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        r.setStatus("COMPLETED");
        r.setCompletedAt(LocalDateTime.now());

        requestRepo.save(r);

        return "COMPLETED";
    }

    // ======================
    // HISTORY (COMPLETED)
    // ======================
    @GetMapping("/history/{userId}")
    public List<HelpRequest> history(@PathVariable Long userId) {
        return requestRepo.findByVolunteerIdAndStatus(userId, "COMPLETED");
    }
}