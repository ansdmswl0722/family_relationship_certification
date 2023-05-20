package com.nhnacademy.family_relationship_certification.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "birth_death_report_resident")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BirthDeathReportResident {
    @EmbeddedId
    private Pk pk;
    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @MapsId("reportResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;
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

    @Getter
    @Builder
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        private Integer residentSerialNumber;
        private Integer reportResidentSerialNumber;
        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode;
    }

}
