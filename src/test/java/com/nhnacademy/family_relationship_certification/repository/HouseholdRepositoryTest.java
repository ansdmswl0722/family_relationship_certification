package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.config.DatabaseConfig;
import com.nhnacademy.family_relationship_certification.config.JpaConfig;
import com.nhnacademy.family_relationship_certification.config.RootConfig;
import com.nhnacademy.family_relationship_certification.config.WebConfig;
import com.nhnacademy.family_relationship_certification.entity.Household;
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
class HouseholdRepositoryTest {

    @Autowired
    HouseholdRepository householdRepository;
    @Autowired
    ResidentRepository residentRepository;

    @Test
    void test1() {
        Resident resident = residentRepository.findById(4).get();
        Household household = Household
                .builder()
                .resident(resident)
                .householdCompositionDate(LocalDateTime.now())
                .householdCompositionReasonCode("전입")
                .currentHouseMovementAddress("경기도 성남시 분당구 대왕판교로 645번길")
                .build();
        householdRepository.saveAndFlush(household);

    }
}