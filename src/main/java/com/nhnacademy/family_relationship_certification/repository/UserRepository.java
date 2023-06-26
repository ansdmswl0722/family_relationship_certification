package com.nhnacademy.family_relationship_certification.repository;

import com.nhnacademy.family_relationship_certification.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
}
