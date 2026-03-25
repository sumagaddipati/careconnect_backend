package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.VolunteerProfile;

public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, Long> {

    Optional<VolunteerProfile> findByUserId(Long userId);
    List<VolunteerProfile> findByStatus(String status);
    List<VolunteerProfile> findByStatusIgnoreCase(String status);
    

}