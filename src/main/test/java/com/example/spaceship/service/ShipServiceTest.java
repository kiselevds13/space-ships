package com.example.spaceship.service;

import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.dto.ShipCreateUpdateRequestDto;
import com.example.spaceship.dto.ShipDto;
import com.example.spaceship.exception.EntityNotFoundException;
import com.example.spaceship.mapper.ShipDtoMapper;
import com.example.spaceship.mapper.ShipMapper;
import com.example.spaceship.repository.ShipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ShipServiceTest {

    private ShipService shipService;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private ShipDtoMapper shipDtoMapper;
    @Autowired
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
    public void shouldSayHello() {
        String message = "Hello my space friend! Here U can work with all our spaceships!";

        String result = shipService.hello();

        assertThat(result).isNotNull()
                .isEqualTo(message);
    }

    @Test
    public void shouldAddNewShip() {


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
    public void shouldDeleteById() {
        String message = "Ship with id " + ID + " was deleted.";

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

        shipService.add(requestDto);

        String result = shipService.deleteById(ID);

        assertThat(result).isNotNull()
                .isEqualTo(message);
    }

    @Test
    public void shouldGetAllShips() {
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

        shipService.add(requestDto);

        List<ShipDto> result = shipService.getAllShips();

        Assertions.assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(result.size());

        assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(requestDto);
    }

    @Test
    public void shouldFindById() {

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

        shipService.add(requestDto);

        ShipDto result = shipService.findById(ID);

        assertThat(result).isNotNull()
                .isEqualTo(shipDtoMapper.map(shipMapper.map(requestDto)));
    }

    @Test
    void shouldUpdateShip() {
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

        shipService.add(requestDto);

        ShipCreateUpdateRequestDto requestUpdateDto = ShipCreateUpdateRequestDto.builder()
                .id(ID)
                .name(NAME)
                .shipType(SHIP_TYPE)
                .planet(PLANET)
                .capacity(CAPACITY)
                .powerOfEngine(POWER_OF_ENGINE)
                .maxSpeed(MAX_SPEED)
                .mileage(MILEAGE)
                .build();

        ShipDto result = shipService.update(requestUpdateDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(requestDto.getId());
        assertThat(result.getName()).isEqualTo(requestDto.getName());
        assertThat(result.getPlanet()).isEqualTo(requestDto.getPlanet());
        assertThat(result.getCapacity()).isEqualTo(requestDto.getCapacity());
        assertThat(result.getPowerOfEngine()).isEqualTo(requestDto.getPowerOfEngine());
        assertThat(result.getMaxSpeed()).isEqualTo(requestDto.getMaxSpeed());
        assertThat(result.getMileage()).isEqualTo(requestDto.getMileage());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionInFindById() {

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class, () -> shipService.findById(ID)
        );

        assertTrue(thrown.getMessage().contentEquals(String.format("Ship with id: %s wasn't found", ID)));
    }
}
