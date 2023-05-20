package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.config.DatabaseConfig;
import com.nhnacademy.family_relationship_certification.config.JpaConfig;
import com.nhnacademy.family_relationship_certification.config.RootConfig;
import com.nhnacademy.family_relationship_certification.config.WebConfig;
import com.nhnacademy.family_relationship_certification.entity.FamilyRelationship;
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

import java.time.LocalDateTime;

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
class FamilyRelationshipRepositoryTest {
    @Autowired
    ResidentRepository residentRepository;
    @Autowired
    FamilyRelationshipRepository repository;

    @Test
    void test1() {
        Resident resident = Resident
                .builder()
                .name("남남이")
                .residentRegistrationNumber("123456-1234567")
                .genderCode("여")
                .birthDate(LocalDateTime.now())
                .birthPlaceCode("병원")
                .registrationBaseAddress("광주")
                .build();
        residentRepository.saveAndFlush(resident);

        Resident familyResident = residentRepository.findById(4).get();

        FamilyRelationship familyRelationship = FamilyRelationship
                .builder()
                .pk(FamilyRelationship.Pk.builder()
                        .baseResidentSerialNumber(resident.getResidentId())
                        .familyResidentSerialNumber(4)
                        .build())
                .familyRelationshipCode("부")
                .resident(resident)
                .familyResident(familyResident)
                .build();
        repository.saveAndFlush(familyRelationship);
    }

    @Test
    void test2() {
        Boolean result = repository.existsById(FamilyRelationship.Pk.builder()
                .baseResidentSerialNumber(1)
                .familyResidentSerialNumber(2)
                .build());
        assertThat(result).isTrue();

    }
}