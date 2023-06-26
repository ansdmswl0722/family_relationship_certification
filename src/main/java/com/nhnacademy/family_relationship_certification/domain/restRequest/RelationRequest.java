package com.nhnacademy.family_relationship_certification.domain.restRequest;

import lombok.*;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
public class RelationRequest {
    @NotNull
    private int familySerialNumber;
    @NotNull
    private String relationship;


}
