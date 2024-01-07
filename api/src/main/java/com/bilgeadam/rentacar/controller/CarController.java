package com.bilgeadam.rentacar.controller;

import com.bilgeadam.rentacar.dto.CarDTO;
import com.bilgeadam.rentacar.services.CarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String isRunning() {
        return "API is working";
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarDTO> getAllModels() {
        return carService.getAllCars();
    }

}
