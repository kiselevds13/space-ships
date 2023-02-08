package com.example.spaceship.model;

import com.example.spaceship.constant.Planet;
import com.example.spaceship.constant.ShipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ship")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "planet")
    private Planet planet;

    @Enumerated(EnumType.STRING)
    @Column(name = "ship_type")
    private ShipType shipType;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "power_of_engine")
    private double powerOfEngine;

    @Column(name = "max_speed")
    private double maxSpeed;

    @Column(name = "mileage")
    private double mileage;

    @ManyToOne
    @JoinColumn(name = "hangar_id", referencedColumnName = "id")
    private Hangar hangar;
}
