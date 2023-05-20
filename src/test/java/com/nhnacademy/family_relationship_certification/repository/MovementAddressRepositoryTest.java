package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.config.DatabaseConfig;
import com.nhnacademy.family_relationship_certification.config.JpaConfig;
import com.nhnacademy.family_relationship_certification.config.RootConfig;
import com.nhnacademy.family_relationship_certification.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
class MovementAddressRepositoryTest {
    @Autowired
    MovementAddressRepository movementAddressRepository;

    @Test
    void test1() {

    }
}