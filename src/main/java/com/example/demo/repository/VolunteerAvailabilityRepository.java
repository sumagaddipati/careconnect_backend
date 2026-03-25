package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VolunteerAvailability;
@Repository
public interface VolunteerAvailabilityRepository
        extends JpaRepository<VolunteerAvailability, Long> {

    Optional<VolunteerAvailability> findByVolunteerId(Long volunteerId);

}