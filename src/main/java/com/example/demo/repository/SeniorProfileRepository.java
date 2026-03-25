package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SeniorProfile;

public interface SeniorProfileRepository extends JpaRepository<SeniorProfile, Long> {

    Optional<SeniorProfile> findByUserId(Long userId);

    Optional<SeniorProfile> findBySeniorCode(String seniorCode);

}