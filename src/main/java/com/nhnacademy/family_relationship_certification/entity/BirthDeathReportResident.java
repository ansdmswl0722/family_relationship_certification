package com.nhnacademy.family_relationship_certification.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "birth_death_report_resident")
@Entity
@Setter
@Getter
public class BirthDeathReportResident {
    @Id
    @Column(name = "resident_serial_number")
    private Integer residentSerialNumber;
    @Column(name = "birth_death_type_code")
    private String birthDeathTypeCode;
    @Column(name = "report_resident_serial_number")
    private Integer reportResidentSerialNumber;
    @Column(name = "birth_death_report_date")
    private LocalDateTime birthDeathReportDate;
    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;
    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private String phoneNumber;


}
