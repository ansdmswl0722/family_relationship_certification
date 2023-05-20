package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household,Integer> {
}
