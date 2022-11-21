package com.example.spaceship.dto;

import com.example.spaceship.model.Ship;
import lombok.Data;

@Data
public class ShipDto {

    private Long id;

    private String name;

    private String planet;

    private String shipType;

    private int capacity;

    private double powerOfEngine;

    private double maxSpeed;

    private double mileage;

    private boolean used;

    private double rating;

    public static ShipDto toDto(Ship ship){

        ShipDto shipDto = new ShipDto();
        shipDto.setId(ship.getId());
        shipDto.setName(ship.getName());
        shipDto.setPlanet(ship.getPlanet());
        shipDto.setShipType(ship.getShipType());
        shipDto.setCapacity(shipDto.getCapacity());
        shipDto.setPowerOfEngine(ship.getPowerOfEngine());
        shipDto.setMaxSpeed(ship.getMaxSpeed());
        shipDto.setMileage(ship.getMileage());
        if(ship.getMileage()>0){
            shipDto.setUsed(true);
        }
        double k = 0;
        if (ship.getMileage() > 0) {
            k = 1.0;
        } else {
            k = 0.5;
        }
        double result = (ship.getPowerOfEngine() * ship.getMaxSpeed() * k)
                / ship.getMileage();
        shipDto.setRating(result);

        return shipDto;
    }
}
/// TODO: 10.11.2022 \ filter \ возврат ShipDTO вместо Ship \ метод UPDATE чтобы изменять корабль, получает ID
// TODO и остальные можно менять \ Рейтинг : капасити * год выпуска \ год
