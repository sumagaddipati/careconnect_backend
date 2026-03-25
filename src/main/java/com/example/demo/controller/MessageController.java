package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/{userId}")
    public List<Message> getMessages(@PathVariable Long userId){
        return messageRepo.findByReceiverId(userId);
    }
}