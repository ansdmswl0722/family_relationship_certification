package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.config.DatabaseConfig;
import com.nhnacademy.family_relationship_certification.config.JpaConfig;
import com.nhnacademy.family_relationship_certification.config.RootConfig;
import com.nhnacademy.family_relationship_certification.config.WebConfig;
import com.nhnacademy.family_relationship_certification.entity.Resident;
import lombok.extern.slf4j.Slf4j;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy(
        @ContextConfiguration(
                classes = {RootConfig.class, WebConfig.class, DatabaseConfig.class, JpaConfig.class}
        )
)
@TestPropertySource("classpath:db.properties")
public class ResidentRepositoryTest {

    @Autowired
    ResidentRepository residentRepository;

    @Test
    void test1() {
        Resident resident = Resident
                .builder()
                .residentId(19)
                .name("남남이")
                .residentRegistrationNumber("123456-1234567")
                .genderCode("여")
                .birthDate(LocalDateTime.now())
                .birthPlaceCode("병원")
                .registrationBaseAddress("광주")
                .build();
        residentRepository.saveAndFlush(resident);
    }

    @Test
    void test2() {
        Resident resident = residentRepository.findById(1).get();
        assertThat(resident.getName()).isEqualTo("남길동");
        log.error("error");

    }

    @Test
    void test3() {
        Resident resident = Resident
                .builder()
                .name("남남이")
                .genderCode("여")
                .birthDate(LocalDateTime.now())
                .birthPlaceCode("병원")
                .registrationBaseAddress("광주")
                .build();

//        assertFalse(residentRepository.save(resident).) ;
    }

    @Test
    void testUpdate() {
        Resident resident = Resident
                .builder()
                .residentId(4)
                .name("남남이")
                .genderCode("여")
                .residentRegistrationNumber("123456-1234567")
                .birthDate(LocalDateTime.now())
                .birthPlaceCode("병원")
                .registrationBaseAddress("광주")
                .deathPlaceAddress("병원")
                .build();
        residentRepository.saveAndFlush(resident);

    }
}