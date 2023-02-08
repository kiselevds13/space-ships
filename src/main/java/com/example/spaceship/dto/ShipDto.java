package com.example.spaceship.dto;

import com.example.spaceship.constant.Planet;
import com.example.spaceship.constant.ShipType;
import com.example.spaceship.model.Hangar;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipDto {

    private Long id;

    private String name;

    private Planet planet;

    private ShipType shipType;

    private int capacity;

    private double powerOfEngine;

    private double maxSpeed;

    private double mileage;

    private Hangar hangar;
}
