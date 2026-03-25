package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FamilyLink;

public interface FamilyLinkRepository extends JpaRepository<FamilyLink, Long>{

    Optional<FamilyLink> findByFamilyUserId(Long familyUserId);

    List<FamilyLink> findAllByFamilyUserId(Long familyUserId);

    Optional<FamilyLink> findByFamilyUserIdAndSeniorUserId(Long familyUserId, Long seniorUserId);
        List<FamilyLink> findBySeniorUserId(Long seniorUserId);

}