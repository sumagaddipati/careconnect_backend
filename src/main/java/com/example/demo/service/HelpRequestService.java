package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.HelpRequest;
import com.example.demo.repository.HelpRequestRepository;

@Service
public class HelpRequestService {

    @Autowired
    private HelpRequestRepository repo;

    public HelpRequest createRequest(HelpRequest req) {
        req.setStatus("PENDING");
        req.setCreatedAt(LocalDateTime.now());
        return repo.save(req);
    }

    public List<HelpRequest> getBySenior(Long seniorId) {
        return repo.findAll()
                   .stream()
                   .filter(r -> r.getSeniorId().equals(seniorId))
                   .toList();
    }
}
