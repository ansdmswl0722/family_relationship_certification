package com.nhnacademy.family_relationship_certification.domain;

import java.time.LocalDate;

public interface CertificateIssueDto {
     Long getCertificateId();
     ResidentDto getResident();
     LocalDate getCertificateIssueDate();

     interface ResidentDto {
          String getRegistrationBaseAddress();
     }

}
