package com.nhnacademy.family_relationship_certification.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public interface RelationshipDto {
     List<RelationshipCodeDto> getRelationships();
     String getName();
     LocalDateTime getBirthDate();
     String getResidentRegistrationNumber();
     String getGenderCode();
}
