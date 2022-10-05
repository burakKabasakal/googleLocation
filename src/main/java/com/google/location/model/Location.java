package com.google.location.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Table
@Entity(name = "Location")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "longtitude",nullable = false)
    private Double longitude;

    @Column(name = "latitude",nullable = false)
    private Double latitude;

    @Column(name = "radius",nullable = false)
    private Integer radius;
}
