package com.example.spaceship.service;

import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.dto.ShipDto;
import com.example.spaceship.exception.EntityNotFoundException;
import com.example.spaceship.mapper.ShipDtoMapper;
import com.example.spaceship.mapper.ShipMapper;
import com.example.spaceship.model.Ship;
import com.example.spaceship.repository.ShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipService {

    private final ShipRepository shipRepository;
    private final ShipDtoMapper shipDtoMapper;
    private final ShipMapper shipMapper;

    public String hello() {
        return "Hello my space friend! Here U can work with all our spaceships!";
    }

    public ShipDto add(ShipCreateRequestDto shipCreateRequestDto) {
        log.info("Start add");
        Ship ship = shipMapper.map(shipCreateRequestDto);
        shipRepository.save(ship);
        ShipDto shipDto = shipDtoMapper.map(ship);
        return shipDto;
    }

    public String deleteById(Long id) {
        log.info("deleting ship by ID " + id);
        shipRepository.deleteById(id);
        return "Ship with id " + id + " was deleted.";
    }

    public List<ShipDto> getAllShips() {
        List<Ship> all = shipRepository.findAll();
        List<ShipDto> shipDto = all.stream().map(shipDtoMapper::map).collect(Collectors.toList());

        return shipDto;
    }

    public ShipDto findById(Long id) {

        Ship ship = shipRepository.findById(id).orElse(null);

        if (ship == null) {
            String errMsg = String.format("Ship with id: %s wasn't found", id);
            log.error(errMsg);
            throw new EntityNotFoundException(errMsg);
        }
        return shipDtoMapper.map(ship);
    }
}
