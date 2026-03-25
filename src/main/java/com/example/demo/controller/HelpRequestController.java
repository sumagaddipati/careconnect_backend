package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.HelpRequest;
import com.example.demo.service.HelpRequestService;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin
public class HelpRequestController {

    @Autowired
    private HelpRequestService service;

    @PostMapping
    public HelpRequest create(@RequestBody HelpRequest req){
        return service.createRequest(req);
    }

    @GetMapping("/senior/{seniorId}")
    public List<HelpRequest> myRequests(@PathVariable Long seniorId){
        return service.getBySenior(seniorId);
    }
}