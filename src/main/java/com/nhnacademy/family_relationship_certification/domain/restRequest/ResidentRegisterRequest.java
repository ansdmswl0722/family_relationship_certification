package com.nhnacademy.family_relationship_certification.domain.restRequest;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidentRegisterRequest {
    @NotNull
    private String name;
    @NotNull
    private String residentRegistrationNumber;
    @NotNull
    private String genderCode;
    @NotNull
    private LocalDateTime birthDate;
    @NotNull
    private String birthPlaceCode;
    @NotNull
    private String registrationBaseAddress;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;


}
