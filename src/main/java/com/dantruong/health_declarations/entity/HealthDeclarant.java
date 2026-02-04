package com.dantruong.health_declarations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "health_declarants")
public class HealthDeclarant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
    private String gender;
    private String nationality;
    @Column(name = "citizen_id")
    private String citizenId;
    @Column(name = "phone_number")
    private String  phoneNumber;
    private String email;
}
