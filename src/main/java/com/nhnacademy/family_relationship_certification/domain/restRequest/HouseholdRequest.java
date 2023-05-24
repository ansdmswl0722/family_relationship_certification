package com.nhnacademy.family_relationship_certification.domain.restRequest;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdRequest {
    @NotNull
    private int residentId;
    @NotNull
    private String householdCompositionDate;
    @NotNull
    private String compositionReasonCode;
    @NotNull
    private String currentHouseMovementAddress;
}
