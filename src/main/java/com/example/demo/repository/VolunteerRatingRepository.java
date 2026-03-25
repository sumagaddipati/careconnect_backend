package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VolunteerRating;
@Repository
public interface VolunteerRatingRepository
        extends JpaRepository<VolunteerRating, Long> {

    List<VolunteerRating> findByVolunteerId(Long volunteerId);

}