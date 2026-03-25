package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FamilyLink;
import com.example.demo.entity.HelpRequest;
import com.example.demo.entity.SeniorProfile;
import com.example.demo.repository.FamilyLinkRepository;
import com.example.demo.repository.HelpRequestRepository;
import com.example.demo.repository.SeniorProfileRepository;

@Service
public class FamilyService {

    @Autowired
    private FamilyLinkRepository linkRepo;

    @Autowired
    private SeniorProfileRepository seniorRepo;

    @Autowired
    private HelpRequestRepository requestRepo;

    // ======================
    // LINK SENIOR
    // ======================

    public String linkSenior(String name, String code, Long familyId){

        SeniorProfile senior = seniorRepo
                .findBySeniorCode(code)
                .orElse(null);

        if(senior == null)
            return "Invalid Senior Code";

        if(!senior.getFullName().equalsIgnoreCase(name))
            return "Senior name does not match";

        if(linkRepo.findByFamilyUserIdAndSeniorUserId(familyId, senior.getUserId()).isPresent())
            return "Already connected to this senior";

        FamilyLink link = new FamilyLink();

        link.setFamilyUserId(familyId);
        link.setSeniorUserId(senior.getUserId());
        link.setSeniorCode(code);
        link.setStatus("ACTIVE");

        linkRepo.save(link);

        return "Connected successfully";
    }

    // ======================
    // GET PROFILE
    // ======================

    public SeniorProfile getSeniorProfile(Long familyId){

        FamilyLink link = linkRepo
                .findByFamilyUserId(familyId)
                .orElse(null);

        if(link == null)
            return null;

        return seniorRepo
                .findByUserId(link.getSeniorUserId())
                .orElse(null);
    }

    // ======================
    // CREATE REQUEST
    // ======================

    public HelpRequest createRequest(HelpRequest req){

        req.setStatus("PENDING");
        req.setCreatedAt(LocalDateTime.now());

        return requestRepo.save(req);
    }

    // ======================
    // GET REQUESTS
    // ======================

    public List<HelpRequest> getRequests(Long familyId){

        FamilyLink link = linkRepo
                .findByFamilyUserId(familyId)
                .orElse(null);

        if(link == null)
            return List.of();

        return requestRepo.findBySeniorId(link.getSeniorUserId());
    }

}