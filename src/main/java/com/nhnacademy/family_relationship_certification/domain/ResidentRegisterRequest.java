package com.nhnacademy.family_relationship_certification.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidentRegisterRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String residentRegistrationNumber;
    @NotNull
    @NotBlank
    private String genderCode;
    @NotNull
    private LocalDateTime birthDate;
    @NotNull
    @NotBlank
    private String birthPlaceCode;
    @NotNull
    @NotBlank
    private String registrationBaseAddress;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;


}
