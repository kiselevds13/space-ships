package com.example.spaceship.service;

import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.dto.ShipDto;
import com.example.spaceship.mapper.ShipDtoMapper;
import com.example.spaceship.mapper.ShipMapper;
import com.example.spaceship.repository.ShipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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
}
