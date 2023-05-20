package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
}
