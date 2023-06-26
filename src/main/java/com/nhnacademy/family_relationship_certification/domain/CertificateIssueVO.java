package com.nhnacademy.family_relationship_certification.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class CertificateIssueVO {
    private Long certificateId;
    private String baseAddress;
    private LocalDate dateOfIssue;

}
