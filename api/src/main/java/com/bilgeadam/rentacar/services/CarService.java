package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.dto.CarDTO;
import com.bilgeadam.rentacar.dto.common.EnumDTO;
import com.bilgeadam.rentacar.dto.model.ModelDTO;
import com.bilgeadam.rentacar.entities.Car;
import com.bilgeadam.rentacar.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelService modelService;

    public CarService(CarRepository carRepository, ModelService modelService) {
        this.carRepository = carRepository;
        this.modelService = modelService;
    }

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> dtos = cars.stream().map(this::getCarDTO).toList();
        return dtos;
    }

    public CarDTO getCarDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setYear(car.getYear());

        EnumDTO color = new EnumDTO();
        color.setValue(car.getColor().name());
        color.setOrdinal(car.getColor().ordinal());
        dto.setColor(color);

        EnumDTO bodyType = new EnumDTO();
        bodyType.setOrdinal(car.getBodyType().ordinal());
        bodyType.setValue(car.getBodyType().name());
        dto.setBodyType(bodyType);

        EnumDTO fuelType = new EnumDTO();
        fuelType.setValue(car.getFuelType().name());
        fuelType.setOrdinal(car.getFuelType().ordinal());
        dto.setFuelType(fuelType);

        ModelDTO model = modelService.getModelDTO(car.getModel());
        dto.setModel(model);

        return dto;
    }

}
