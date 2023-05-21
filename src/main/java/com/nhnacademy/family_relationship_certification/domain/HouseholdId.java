package com.nhnacademy.family_relationship_certification.domain;

import lombok.Data;

@Data
public class HouseholdId {

    private Integer id;

    public HouseholdId(Integer id) {
        this.id = id;
    }
}
