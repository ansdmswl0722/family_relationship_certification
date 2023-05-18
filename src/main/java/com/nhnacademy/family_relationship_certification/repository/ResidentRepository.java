package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident,Integer> {
}
