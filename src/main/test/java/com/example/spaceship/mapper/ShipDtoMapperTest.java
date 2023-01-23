package com.example.spaceship.mapper;

import com.example.spaceship.dto.ShipDto;
import com.example.spaceship.model.Ship;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShipDtoMapperTest {

    private final ShipDtoMapper mapper = Mappers.getMapper(ShipDtoMapper.class);

    @Test
    void shouldConvertShipToShipDto() {

        Ship ship = Ship.builder()
                .id(1L)
                .name("some name")
                .shipType("some type")
                .planet("some planet")
                .capacity(1)
                .powerOfEngine(1)
                .maxSpeed(1)
                .mileage(1)
                .build();

        ShipDto result = mapper.map(ship);

        assertThat(result.getId()).isEqualTo(ship.getId());
        assertThat(result.getName()).isEqualTo(ship.getName());
        assertThat(result.getPlanet()).isEqualTo(ship.getPlanet());
        assertThat(result.getCapacity()).isEqualTo(ship.getCapacity());
        assertThat(result.getPowerOfEngine()).isEqualTo(ship.getPowerOfEngine());
        assertThat(result.getMaxSpeed()).isEqualTo(ship.getMaxSpeed());
        assertThat(result.getMileage()).isEqualTo(ship.getMileage());
    }
}