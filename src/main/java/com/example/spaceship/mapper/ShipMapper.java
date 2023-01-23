package com.example.spaceship.mapper;

import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.model.Ship;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShipMapper {
    Ship map(ShipCreateRequestDto shipCreateRequestDto);
}
