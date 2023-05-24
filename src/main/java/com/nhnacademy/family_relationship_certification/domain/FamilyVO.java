package com.nhnacademy.family_relationship_certification.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FamilyVO {
    private String relationshipCode;
    private String name;
    private String birthDate;
    private String residentRegistrationNumber;
    private String gender;

}
