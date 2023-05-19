package com.nhnacademy.family_relationship_certification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "certificate_issue")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number")
    private Integer certificateId;
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @Column(name = "certificate_type_code")
    private String certificateTypeCode;
    @Column(name = "certificate_issue_date")
    private LocalDateTime certificateIssueDate;

}
