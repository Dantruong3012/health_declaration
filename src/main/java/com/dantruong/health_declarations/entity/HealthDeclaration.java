package com.dantruong.health_declarations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "health_declarations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthDeclaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_declarant_id")
    private HealthDeclarant healthDeclarant;
    @Column(name = "travel_type")
    private String travelType;
    @Column(name = "vehicle_number")
    private String vehicleNumber;
    @Column(name = "seat_number")
    private String seatNumber;
    @Column(name = "departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    @Column(name = "arrivalDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;
    @Column(name = "visited_locations")
    private String visitedLocations;
    private String city;
    private String district;
    private String ward;
    @Column(name = "detail_address")
    private String detailAddress;
    private String symptoms;
    @Column(name = "exposure_history")
    private String exposureHistory;
    @Column(name = "created_at")
    private Timestamp createdAt;
}
