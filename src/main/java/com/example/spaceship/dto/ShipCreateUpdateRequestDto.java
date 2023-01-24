package com.example.spaceship.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipCreateUpdateRequestDto {
    private Long id;

    private String name;

    private String planet;

    private String shipType;

    private int capacity;

    private double powerOfEngine;

    private double maxSpeed;

    private double mileage;
}
