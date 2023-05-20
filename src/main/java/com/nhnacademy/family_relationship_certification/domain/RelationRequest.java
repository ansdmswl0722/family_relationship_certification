package com.nhnacademy.family_relationship_certification.domain;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RelationRequest {
    @NotNull
    private int familySerialNumber;
    @NotNull
    private String relationship;


}
