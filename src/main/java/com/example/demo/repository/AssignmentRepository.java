package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.VolunteerAssignment;

public interface AssignmentRepository extends JpaRepository<VolunteerAssignment, Long> {
    Optional<VolunteerAssignment> findByRequestId(Long requestId);
}
