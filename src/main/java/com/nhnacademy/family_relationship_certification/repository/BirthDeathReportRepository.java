package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportRepository extends JpaRepository<BirthDeathReportResident,BirthDeathReportResident.Pk> {
}
