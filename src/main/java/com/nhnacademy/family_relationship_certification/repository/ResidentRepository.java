package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.domain.RelationshipDto;
import com.nhnacademy.family_relationship_certification.domain.ResidentNameDto;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident,Integer> {
    Page<ResidentNameDto> getAllBy(Pageable pageable);

    @Query("select r from Resident r join fetch r.relationships where r.residentId = ?1")
    RelationshipDto getFamilyRelationship(int residentId);
}
