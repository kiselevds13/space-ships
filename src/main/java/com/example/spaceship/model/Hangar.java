package com.example.spaceship.model;

import com.example.spaceship.constant.Planet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hangar")
public class Hangar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "planet")
    private Planet planet;

    @OneToMany(mappedBy = "hangar")
    private List<Ship> shipList = new ArrayList<>();

    @Column(name = "is_available")
    private boolean available;
}
