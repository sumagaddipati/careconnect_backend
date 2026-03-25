package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.MessageRequest;
import com.example.demo.entity.HelpRequest;
import com.example.demo.entity.User;
import com.example.demo.entity.VolunteerProfile;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // =========================
    // DASHBOARD STATS
    // =========================
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return adminService.getStats();
    }

    // =========================
    // HELP REQUESTS
    // =========================
    @GetMapping("/requests")
    public List<HelpRequest> getAllRequests() {
        return adminService.getAllRequests();
    }

    @PutMapping("/requests/{id}")
    public HelpRequest updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return adminService.updateRequestStatus(id, body.get("status"));
    }

    // =========================
    // VOLUNTEERS (USER TABLE)
    // =========================
    @GetMapping("/volunteers")
    public List<User> getVolunteers() {
        return adminService.getVolunteers();
    }

    @DeleteMapping("/volunteers/{id}")
    public String removeVolunteer(@PathVariable Long id) {
        adminService.removeVolunteer(id);
        return "Volunteer Removed";
    }

    // =========================
    // SENIORS
    // =========================
    @GetMapping("/seniors")
    public List<User> getSeniors() {
        return adminService.getSeniors();
    }

    // =========================
    // 🔥 PENDING VOLUNTEERS (IMPORTANT)
    // ONLY NOT APPROVED
    // =========================
    @GetMapping("/volunteer-verifications")
    public List<VolunteerProfile> getPendingVolunteers() {
        return adminService.getPendingVolunteers();
    }

    // =========================
    // 🔥 APPROVE / REJECT
    // =========================
    @PutMapping("/volunteer-verifications/{id}")
    public VolunteerProfile verifyVolunteer(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return adminService.verifyVolunteer(id, body.get("status"));
    }

    // =========================
    // 🔥 ALL VOLUNTEERS (AFTER ACTION)
    // =========================
    @GetMapping("/all-volunteers")
    public List<VolunteerProfile> getAllVolunteers() {
        return adminService.getAllVolunteerProfiles();
    }

    // =========================
    // MESSAGE
    // =========================
    @PostMapping("/send-message")
    public String sendMessage(@RequestBody MessageRequest req) {
        return adminService.sendMessage(
                req.getRequestId(),
                req.getMsg()
        );
    }
}