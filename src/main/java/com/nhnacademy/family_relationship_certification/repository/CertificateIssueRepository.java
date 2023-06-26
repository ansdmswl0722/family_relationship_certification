package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.domain.CertificateIssueDto;
import com.nhnacademy.family_relationship_certification.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue,Integer> {

    CertificateIssueDto findByCertificateId(Long certificateId);
}
