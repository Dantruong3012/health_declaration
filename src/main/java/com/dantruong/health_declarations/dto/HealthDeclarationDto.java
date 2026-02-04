package com.dantruong.health_declarations.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class HealthDeclarationDto {
    private Integer healthDeclarantId;
    private String name;
    private Integer yearOfBirth;
    private String gender;
    private String nationality;
    private String citizenId;
    private String  phoneNumber;
    private String email;
    // email
    private Integer healthDeclarationID;
    private String travelType;
    private String vehicleNumber;
    private String seatNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;
    private String visitedLocations;
    private String city;
    private String district;
    private String ward;
    private String detailAddress;
    private String symptoms;
    private String exposureHistory;
    private Timestamp createdAt;
}
