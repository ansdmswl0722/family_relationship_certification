package com.nhnacademy.family_relationship_certification.domain.restRequest;

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
