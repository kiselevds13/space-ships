package com.example.spaceship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "planet")
    private String planet;

    @Column(name = "ship_type")
    private String shipType;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "power_of_engine")
    private double powerOfEngine;

    @Column(name = "max_speed")
    private double maxSpeed;

    @Column(name = "mileage")
    private double mileage;

    @Column(name = "used")
    private boolean used;

    @Column(name = "rating")
    private double rating;
}
