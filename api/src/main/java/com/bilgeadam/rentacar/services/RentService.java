package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.dto.car.CarDTO;
import com.bilgeadam.rentacar.dto.rent.RentDTO;
import com.bilgeadam.rentacar.dto.rent.RentSaveDTO;
import com.bilgeadam.rentacar.entities.Rent;
import com.bilgeadam.rentacar.repository.RentRepository;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public RentDTO saveRent(RentSaveDTO dto) {
        Rent rent = new Rent();
        rent = rentRepository.save(rent);
        return getRentDTO(rent);
    }

    private RentDTO getRentDTO(Rent rent) {
        RentDTO dto = new RentDTO();
        return dto;
    }
}
