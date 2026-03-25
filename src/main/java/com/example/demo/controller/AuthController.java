package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.FamilyLink;
import com.example.demo.entity.SeniorCodeGenerator;
import com.example.demo.entity.SeniorProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.FamilyLinkRepository;
import com.example.demo.repository.SeniorProfileRepository;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SeniorProfileRepository profileRepo;

    @Autowired
    private FamilyLinkRepository linkRepo;

    @Autowired
    private PasswordEncoder encoder;

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {

        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(req.getRole());

        User savedUser = userRepo.save(user);

        // =========================
        // SENIOR REGISTRATION
        // =========================

        if ("SENIOR".equalsIgnoreCase(savedUser.getRole())) {

            SeniorProfile profile = new SeniorProfile();

            profile.setUserId(savedUser.getId());

            String code;

            do {
                code = SeniorCodeGenerator.generate();
            } while (profileRepo.findBySeniorCode(code).isPresent());

            profile.setSeniorCode(code);
            profile.setFullName(savedUser.getName());

            profileRepo.save(profile);
        }

        // =========================
        // FAMILY REGISTRATION
        // =========================

        if ("FAMILY".equalsIgnoreCase(savedUser.getRole())) {

            if(req.getSeniorCode() == null || req.getSeniorCode().isEmpty()){
                return "Senior ID required";
            }

            SeniorProfile senior = profileRepo
                    .findBySeniorCode(req.getSeniorCode())
                    .orElse(null);

            if (senior == null) {
                return "Invalid Senior ID";
            }

            if (!senior.getFullName().equalsIgnoreCase(req.getSeniorName())) {
                return "Senior name does not match";
            }

            FamilyLink link = new FamilyLink();

            link.setFamilyUserId(savedUser.getId());
            link.setSeniorUserId(senior.getUserId());
            link.setSeniorCode(req.getSeniorCode());

            linkRepo.save(link);
        }

        return "Registration successful";
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String password = req.get("password");

        User user = userRepo.findByEmail(email).orElse(null);

        if (user == null)
            return Map.of("error", "User not found");

        if (!encoder.matches(password, user.getPassword()))
            return Map.of("error", "Invalid password");

        return Map.of(
                "token", "dummy-token",
                "role", user.getRole(),
                "userId", user.getId(),
                "name", user.getName());
    }
}