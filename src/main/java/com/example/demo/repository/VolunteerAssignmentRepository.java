package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.VolunteerAssignment;

public interface VolunteerAssignmentRepository 
        extends JpaRepository<VolunteerAssignment, Long> {

    Optional<VolunteerAssignment> findByVolunteerIdAndStatus(
            Long volunteerId, String status);

    List<VolunteerAssignment> findByVolunteerIdAndStatusIn(
            Long volunteerId, List<String> statuses);
            VolunteerAssignment findByRequestId(Long requestId);

}