package com.nhnacademy.family_relationship_certification.domain;

import lombok.Data;
import lombok.Getter;

@Getter
public class ResidentId {
    private Integer id;

    public ResidentId(Integer id) {
        this.id = id;
    }
}
