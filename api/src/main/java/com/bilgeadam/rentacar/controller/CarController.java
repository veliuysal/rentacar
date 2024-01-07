package com.bilgeadam.rentacar.controller;

import com.bilgeadam.rentacar.dto.car.CarDTO;
import com.bilgeadam.rentacar.dto.brand.BrandUpdateDTO;
import com.bilgeadam.rentacar.dto.car.CarSaveDTO;
import com.bilgeadam.rentacar.entities.Brand;
import com.bilgeadam.rentacar.services.CarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CarDTO getBrandByID(@PathVariable("id") Integer id) {
        return carService.getCarById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CarDTO saveCategory(@RequestBody CarSaveDTO dto) throws Exception {
        return carService.saveCar(dto);
    }
    
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteBrand(@PathVariable("id") Integer id) throws Exception {
        carService.deleteCar(id);
        return "Silme İşlemi Başarılı.";
    }

}
