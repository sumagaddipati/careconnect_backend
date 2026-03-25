package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HelpRequest;

@Repository
public interface HelpRequestRepository extends JpaRepository<HelpRequest, Long> {

    List<HelpRequest> findByStatus(String status);
    List<HelpRequest> findBySeniorId(Long seniorId);
    List<HelpRequest> findByVolunteerIdAndStatus(Long volunteerId, String status);

}