package com.nhnacademy.family_relationship_certification.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "family_relationship")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;
    @ManyToOne
    @MapsId("baseResidentSerialNumber")
    @JoinColumn(name = "base_resident_serial_number")
    private Resident resident;
    @ManyToOne
    @MapsId("familyResidentSerialNumber")
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;
    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;


    @Getter
    @Builder
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "base_resident_serial_number")
        private Integer baseResidentSerialNumber;
        @Column(name = "family_resident_serial_number")
        private Integer familyResidentSerialNumber;
    }

}
