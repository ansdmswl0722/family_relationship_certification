package com.nhnacademy.family_relationship_certification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "household")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Household {
    @Id
    @Column(name = "household_serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer householdId;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;

    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;




}
