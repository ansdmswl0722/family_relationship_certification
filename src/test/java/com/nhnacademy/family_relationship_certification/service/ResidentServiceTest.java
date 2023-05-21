package com.nhnacademy.family_relationship_certification.service;

import com.nhnacademy.family_relationship_certification.config.DatabaseConfig;
import com.nhnacademy.family_relationship_certification.config.JpaConfig;
import com.nhnacademy.family_relationship_certification.config.RootConfig;
import com.nhnacademy.family_relationship_certification.config.WebConfig;
import com.nhnacademy.family_relationship_certification.domain.restRequest.RelationRequest;
import com.nhnacademy.family_relationship_certification.domain.ResidentId;
import com.nhnacademy.family_relationship_certification.domain.restRequest.ResidentRegisterRequest;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import com.nhnacademy.family_relationship_certification.repository.ResidentRepository;
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

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy(
        @ContextConfiguration(
                classes = {RootConfig.class, WebConfig.class, DatabaseConfig.class, JpaConfig.class}
        )
)
@TestPropertySource("classpath:db.properties")
class ResidentServiceTest {

    @Autowired
    ResidentService residentService;
    @Autowired
    ResidentRepository residentRepository;

    @Test
    void testGetResidents() {
        //Given
        //When
        //Then
    }

    @Test
    void testCreateResident() {
        ResidentRegisterRequest request = ResidentRegisterRequest.builder()
                .name("member")
                .residentRegistrationNumber("123456-1234567")
                .genderCode("남")
                .birthDate(LocalDateTime.now())
                .birthPlaceCode("병원")
                .registrationBaseAddress("전라남도 나주시 금계23번길")
                .build();
        ResidentId id = residentService.createResident(request);
        assertThat(residentRepository.existsById(id.getId())).isTrue();
    }
    @Test
    void testUpdate() {

    }

    @Test
    void testAddRelationship() {
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
        RelationRequest request = new RelationRequest(4,"부");
        residentService.addRelationship(resident.getResidentId(),request);
    }
}