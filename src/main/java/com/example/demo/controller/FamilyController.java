package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.FamilyLinkRequest;
import com.example.demo.entity.HelpRequest;
import com.example.demo.entity.SeniorProfile;
import com.example.demo.service.FamilyService;

@RestController
@RequestMapping("/api/family")
@CrossOrigin("*")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    // =========================
    // LINK FAMILY TO SENIOR
    // =========================

    @PostMapping("/link")
    public String linkSenior(@RequestBody FamilyLinkRequest req){

        return familyService.linkSenior(
                req.getName(),
                req.getCode(),
                req.getFamilyId()
        );
    }

    // =========================
    // GET SENIOR PROFILE
    // =========================

    @GetMapping("/profile/{familyId}")
    public SeniorProfile getProfile(@PathVariable Long familyId){

        return familyService.getSeniorProfile(familyId);
    }

    // =========================
    // CREATE REQUEST
    // =========================

    @PostMapping("/request")
    public HelpRequest createFamilyRequest(@RequestBody HelpRequest req){

        return familyService.createRequest(req);
    }

    // =========================
    // VIEW REQUESTS
    // =========================

    @GetMapping("/requests/{familyId}")
    public List<HelpRequest> getRequests(@PathVariable Long familyId){

        return familyService.getRequests(familyId);
    }

}