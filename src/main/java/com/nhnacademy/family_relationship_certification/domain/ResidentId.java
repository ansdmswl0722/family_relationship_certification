package com.nhnacademy.family_relationship_certification.domain;

import lombok.Data;

@Data
public class ResidentId {
    private Integer id;

    public ResidentId(Integer id) {
        this.id = id;
    }
}
