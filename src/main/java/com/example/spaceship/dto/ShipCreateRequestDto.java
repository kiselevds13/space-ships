package com.example.spaceship.dto;

import lombok.Data;

@Data
public class ShipCreateRequestDto {
    private Long id;

    private String name;

    private String planet;

    private String shipType;

    private int capacity;

    private double powerOfEngine;

    private double maxSpeed;

    private double mileage;

    private boolean used;

    private double rating;
}
