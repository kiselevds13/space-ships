package com.example.spaceship.service;

import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.dto.ShipDto;
import com.example.spaceship.exception.EntityNotFoundException;
import com.example.spaceship.mapper.ShipDtoMapper;
import com.example.spaceship.mapper.ShipMapper;
import com.example.spaceship.model.Ship;
import com.example.spaceship.repository.ShipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ShipServiceMockTest {

    private ShipService shipService;
    @Mock
    private ShipRepository shipRepository;
    @Mock
    private ShipDtoMapper shipDtoMapper;
    @Mock
    private ShipMapper shipMapper;

    public static final long ID = 1L;
    public static final String NAME = "some name";
    public static final String PLANET = "some planet";
    public static final String SHIP_TYPE = "some ship type";
    public static final int CAPACITY = 1;
    public static final double POWER_OF_ENGINE = 1.1;
    public static final double MAX_SPEED = 10.1;
    public static final double MILEAGE = 100.1;

    @BeforeEach
    void setUp() {
        shipService = new ShipService(shipRepository, shipDtoMapper, shipMapper);
    }

    @Test
    void shouldSayHello() {
        String message = "Hello my space friend! Here U can work with all our spaceships!";
        String result = shipService.hello();

        assertThat(result).isNotNull()
                .isEqualTo(message);
    }

    @Test
    void shouldAddNewShip() {
        ShipCreateRequestDto requestDto = ShipCreateRequestDto.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        Ship ship = Ship.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        ShipDto shipDto = ShipDto.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        when(shipMapper.map(any())).thenReturn(ship);
        when(shipDtoMapper.map(any())).thenReturn(shipDto);

        ShipDto result = shipService.add(requestDto);

        assertThat(result).isNotNull();

        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getShipType()).isEqualTo(SHIP_TYPE);
        assertThat(result.getPlanet()).isEqualTo(PLANET);
        assertThat(result.getCapacity()).isEqualTo(CAPACITY);
        assertThat(result.getPowerOfEngine()).isEqualTo(POWER_OF_ENGINE);
        assertThat(result.getMaxSpeed()).isEqualTo(MAX_SPEED);
        assertThat(result.getMileage()).isEqualTo(MILEAGE);
    }

    @Test
    void shouldDeleteById() {
        String message = "Ship with id " + ID + " was deleted.";

        String result = shipService.deleteById(ID);

        assertThat(result).isNotNull()
                .isEqualTo(message);
    }

    @Test
    void shouldGetAllShips() {

        Ship ship = Ship.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        ShipDto shipDto = ShipDto.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        when(shipRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(ship)));

        when(shipDtoMapper.map(any()))
                .thenReturn(shipDto);

        List<ShipDto> result = shipService.getAllShips();

        Assertions.assertThat(result)
                .isNotNull()
                .hasSize(result.size());
    }

    @Test
    void shouldFindById() {
        ShipDto shipDto = ShipDto.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        Ship ship = Ship.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        when(shipRepository.findById(any())).thenReturn(Optional.ofNullable(ship));
        when(shipDtoMapper.map(any())).thenReturn(shipDto);

        ShipDto result = shipService.findById(any());

        assertThat(result).isNotNull();
        assertThat(result.getId())
                .isNotNull()
                .isEqualTo(ID);
    }
    @Test
    void shouldThrowEntityNotFoundExceptionInFindById() {

        when(shipRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class, () -> shipRepository.findById(ID)
        );

        assertTrue(thrown.getMessage().contentEquals(String.format("Ship with id or version:" +
                " %s wasn't found", ID)));
    }
}