package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.dto.car.CarDTO;
import com.bilgeadam.rentacar.dto.car.CarSaveDTO;
import com.bilgeadam.rentacar.dto.common.EnumDTO;
import com.bilgeadam.rentacar.dto.model.ModelDTO;
import com.bilgeadam.rentacar.entities.Car;
import com.bilgeadam.rentacar.entities.Model;
import com.bilgeadam.rentacar.repository.CarRepository;
import com.bilgeadam.rentacar.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final ModelService modelService;

    public CarService(CarRepository carRepository, ModelRepository modelRepository, ModelService modelService) {
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.modelService = modelService;
    }

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> dtos = cars.stream().map(this::getCarDTO).toList();
        return dtos;
    }

    public CarDTO getCarById(Integer id) {
        Optional<Car> optCar = carRepository.findById(id);
        return optCar.isEmpty() ? null : getCarDTO(optCar.get());
    }

    public CarDTO saveCar(CarSaveDTO dto) throws Exception {
        if (Objects.isNull(dto.getModelId()))
            throw new Exception("Model alanı boş olamaz");
        Optional<Model> optModel = modelRepository.findById(dto.getModelId());
        if (optModel.isEmpty())
            throw new Exception("Model Bulunuamadı");
        if (Objects.isNull(dto.getColor()))
            throw new Exception("Color alanı boş olamaz");
        if (Objects.isNull(dto.getBodyType()))
            throw new Exception("Body Type alanı boş olamaz");
        if (Objects.isNull(dto.getFuelType()))
            throw new Exception("Fuel Type alanı boş olamaz");
        if (Objects.isNull(dto.getYear()))
            throw new Exception("Year alanı boş olamaz");
        Car car = new Car();
        car.setYear(dto.getYear());
        car.setModel(optModel.get());

        return getCarDTO(carRepository.save(car));
    }

    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
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
