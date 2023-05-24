package com.nhnacademy.family_relationship_certification.domain.restRequest;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovementAddressRequest {
    private String houseMovementReportDate;
    @NotNull
    private String  houseMovementAddress;
    @NotNull
    private String lastAddressYN;
}
