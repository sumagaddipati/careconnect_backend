package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.HelpRequest;
import com.example.demo.entity.SeniorProfile;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.repository.SeniorProfileRepository;

@RestController
@RequestMapping("/api/senior")
@CrossOrigin("*")
public class SeniorController {

    @Autowired
    private SeniorProfileRepository profileRepo;

    @Autowired
    private HelpRequestRepository requestRepo;

    // GET PROFILE

    @GetMapping("/profile/{userId}")
    public SeniorProfile getProfile(@PathVariable Long userId) {

        return profileRepo.findByUserId(userId)
                .orElseGet(() -> {
                    SeniorProfile p = new SeniorProfile();
                    p.setUserId(userId);
                    return profileRepo.save(p);
                });
    }

    // SAVE / UPDATE PROFILE

    @PostMapping("/profile")
public SeniorProfile saveProfile(@RequestBody SeniorProfile profile) {

    SeniorProfile existing = profileRepo.findByUserId(profile.getUserId()).orElse(null);

    if(existing != null){
        profile.setId(existing.getId());
    }

    return profileRepo.save(profile);
}

    // IMAGE UPLOAD

    @PostMapping("/upload-image")
public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {

    String folder = "uploads/";
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

    Path path = Paths.get(folder + fileName);

    Files.createDirectories(path.getParent());
    Files.write(path, file.getBytes());

    return fileName;
}

    // CREATE HELP REQUEST

    @PostMapping("/request")
    public HelpRequest createRequest(@RequestBody HelpRequest req) {

        req.setStatus("PENDING");

        return requestRepo.save(req);
    }

    // VIEW REQUESTS

    @GetMapping("/requests/{seniorId}")
    public List<HelpRequest> myRequests(@PathVariable Long seniorId) {

        return requestRepo.findBySeniorId(seniorId);
    }
}