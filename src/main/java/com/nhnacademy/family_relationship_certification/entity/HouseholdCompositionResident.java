package com.nhnacademy.family_relationship_certification.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "household_composition_resident")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdCompositionResident {

    @EmbeddedId
    private Pk pk;

    @MapsId("householdId")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;
    @MapsId("residentId")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @Column(name = "report_date")
    private LocalDateTime reportDate;
    @Column(name = "household_relationship_code")
    private String householdRelationshipCode;
    @Column(name = "household_composition_change_reason_code")
    private String householdCompositionChangeReasonCode;

    @Getter
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number")
        private Integer householdId;
        @Column(name = "resident_serial_number")
        private Integer residentId;

    }

}
