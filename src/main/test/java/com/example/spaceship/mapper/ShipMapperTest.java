package com.example.spaceship.mapper;

import com.example.spaceship.constant.Planet;
import com.example.spaceship.constant.ShipType;
import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.model.Hangar;
import com.example.spaceship.model.Ship;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShipMapperTest {

    private final ShipMapper mapper = Mappers.getMapper(ShipMapper.class);

    @Test
    void shouldConvertShipCreateRequestDtoToShip() {

        ShipCreateRequestDto requestDto = ShipCreateRequestDto.builder()
                .id(1L)
                .name("some name")
                .shipType(ShipType.EXPLORERSHIP)
                .planet(Planet.EARTH)
                .capacity(1)
                .powerOfEngine(1)
                .maxSpeed(1)
                .mileage(1)
                .build();

        Ship result = mapper.map(requestDto);

        assertThat(result.getId()).isEqualTo(requestDto.getId());
        assertThat(result.getName()).isEqualTo(requestDto.getName());
        assertThat(result.getPlanet()).isEqualTo(requestDto.getPlanet());
        assertThat(result.getCapacity()).isEqualTo(requestDto.getCapacity());
        assertThat(result.getPowerOfEngine()).isEqualTo(requestDto.getPowerOfEngine());
        assertThat(result.getMaxSpeed()).isEqualTo(requestDto.getMaxSpeed());
        assertThat(result.getMileage()).isEqualTo(requestDto.getMileage());
    }
}