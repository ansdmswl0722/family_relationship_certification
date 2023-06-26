package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.config.DatabaseConfig;
import com.nhnacademy.family_relationship_certification.config.JpaConfig;
import com.nhnacademy.family_relationship_certification.config.RootConfig;
import com.nhnacademy.family_relationship_certification.config.WebConfig;
import com.nhnacademy.family_relationship_certification.domain.CertificateIssueDto;
import com.nhnacademy.family_relationship_certification.entity.CertificateIssue;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy(
        @ContextConfiguration(
                classes = {RootConfig.class, WebConfig.class, DatabaseConfig.class, JpaConfig.class}
        )
)
@TestPropertySource("classpath:db.properties")
class CertificateIssueRepositoryTest {
    @Autowired
    ResidentRepository residentRepository;
    @Autowired
    CertificateIssueRepository certificateIssueRepository;
    @Test
    void test1() {
        Resident resident = residentRepository.findById(4).get();
        Long num = Long.parseLong("1234");
        CertificateIssue certificateIssue = new CertificateIssue(
                num,resident,"가족관계증명서", LocalDate.now()
        );
        certificateIssueRepository.saveAndFlush(certificateIssue);
        CertificateIssueDto dto = certificateIssueRepository.findByCertificateId(num);
        assertThat(dto.getResident().getRegistrationBaseAddress()).isEqualTo("경기도 성남시 분당구 대왕판교로645번길");


    }
}