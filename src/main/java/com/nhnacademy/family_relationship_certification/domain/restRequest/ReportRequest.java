package com.nhnacademy.family_relationship_certification.domain.restRequest;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {
    @NotNull
    private Integer residentSerialNumber;
    @NotNull
    private String birthDeathTypeCode;
    private LocalDateTime birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    private String emailAddress;
    @NotNull
    private String phoneNumber;

}
