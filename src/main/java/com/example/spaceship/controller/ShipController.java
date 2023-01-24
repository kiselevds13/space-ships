package com.example.spaceship.controller;

import com.example.spaceship.dto.ShipCreateRequestDto;
import com.example.spaceship.dto.ShipCreateUpdateRequestDto;
import com.example.spaceship.dto.ShipDto;
import com.example.spaceship.service.ShipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/ship")
@RequiredArgsConstructor
public class ShipController {
    private final ShipService service;

    @GetMapping("/hello")
    public String hello() {
        return service.hello();
    }

    @GetMapping("/find/{id}")
    public ShipDto findByID(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public ShipDto add(@RequestBody ShipCreateRequestDto shipCreateRequestDto) {
        return service.add(shipCreateRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/get_all_ships")
    public List<ShipDto> get() {
        return service.getAllShips();
    }

    @PostMapping("/update")
    public ShipDto update(@RequestBody ShipCreateUpdateRequestDto requestDto) {
        return service.update(requestDto);
    }
}
