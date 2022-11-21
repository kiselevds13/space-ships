package com.example.spaceship.service;

import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.dto.ShipDto;
import com.example.spaceship.model.Ship;
import com.example.spaceship.repository.ShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipService {

    private final ShipRepository shipRepository;

    public String hello() {
        return "Hello my space friend! Here U can work with all our spaceships!";
    }

    public String add(ShipCreateRequestDto shipCreateRequestDto) {
        Ship ship = new Ship();
        if (shipCreateRequestDto.getName().equals(null)) {
            return "A ship must have a name";
        } else {
            ship.setName(shipCreateRequestDto.getName());
        }
        ship.setPlanet(shipCreateRequestDto.getPlanet());
        ship.setShipType(shipCreateRequestDto.getShipType());
        if (shipCreateRequestDto.getCapacity() <= 0) {
            return "Capacity cannot be negative or zero.";
        } else {
            ship.setCapacity(shipCreateRequestDto.getCapacity());
        }

        ship.setPowerOfEngine(shipCreateRequestDto.getPowerOfEngine());

        ship.setMaxSpeed(shipCreateRequestDto.getMaxSpeed());

        ship.setMileage(shipCreateRequestDto.getMileage());

        if (shipCreateRequestDto.getMileage() > 0) {
            ship.setUsed(true);
        } else {
            ship.setUsed(false);
        }
        double k = 0;
        if (shipCreateRequestDto.getMileage() > 0) {
            k = 1.0;
        } else {
            k = 0.5;
        }
        double result = (shipCreateRequestDto.getPowerOfEngine() * k)
                / shipCreateRequestDto.getMaxSpeed();
        ship.setRating(result);

        shipRepository.save(ship);
        return "Ship " + ship.getName() + " was added.";
    }

    public String deleteById(Long id) {
        shipRepository.deleteById(id);
        return "Ship with id " + id + " was deleted.";
    }

    public List<ShipDto> getAllShips() {
        List<Ship> all = shipRepository.findAll();
        List<ShipDto> shipDtos = new ArrayList<>();
        // Stream API
        ShipDto shipDto = ShipDto.toDto(all.get(0));
        shipDtos.add(shipDto);
        return shipDtos;
    }

    public ShipDto findById(Long id) {
        return ShipDto.toDto(shipRepository.findById(id).orElse(null));
    }

    public List<ShipDto> filter(String request) {
        List<ShipDto> resultList = new ArrayList<>();
        List<Ship> list = shipRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            ShipDto shipDto = new ShipDto();
            Ship ship = new Ship();
            if (ship.getId().toString().equals(request)) {

                resultList.add(shipDto);
            }
            if (ship.getName().equals(request)) {
                resultList.add(shipDto);
            }
            if (ship.getPlanet().equals(request)) {
                resultList.add(shipDto);
            }
            if (ship.getShipType().equals(request)) {
                resultList.add(shipDto);
            }
            if (Integer.toString(ship.getCapacity()).equals(request)) {
                resultList.add(shipDto);
            }
            if (Double.toString(ship.getPowerOfEngine()).equals(request)) {
                resultList.add(shipDto);
            }
            if (Double.toString(ship.getMaxSpeed()).equals(request)) {
                resultList.add(shipDto);
            }
            if (Double.toString(ship.getMileage()).equals(request)) {
                resultList.add(shipDto);
            }
            if (Double.toString(ship.getRating()).equals(request)) {
                resultList.add(shipDto);
            }
        }
        return resultList;
    }
}

