package com.nhnacademy.family_relationship_certification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String pwd;
    private String authority;
    @OneToOne
    @JoinColumn(name = "resident_id",referencedColumnName = "resident_serial_number")
    private Resident resident;


}
