package com.bilgeadam.rentacar.services;

import com.bilgeadam.rentacar.dto.common.EnumDTO;
import com.bilgeadam.rentacar.dto.common.SelectDataDTO;
import com.bilgeadam.rentacar.enums.CarBodyType;
import com.bilgeadam.rentacar.enums.Color;
import com.bilgeadam.rentacar.enums.FuelType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommonServices {

    public List<SelectDataDTO> getAllYears() {
        List<SelectDataDTO> dtos = new ArrayList<>();
        Integer currentYear = LocalDate.now().getYear();
        for (int i = currentYear - 20; i <= currentYear; i++) {
            SelectDataDTO dto = new SelectDataDTO();
            dto.setKey(i);
            dto.setValue(String.valueOf(i));
            dtos.add(dto);
        }
        return dtos;
    }

    public List<EnumDTO> getAllColors() {
        List<EnumDTO> dtos = new ArrayList<>();
        for (Color color : Color.values()) {
            EnumDTO dto = new EnumDTO();
            dto.setOrdinal(color.ordinal());
            dto.setValue(color.name());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<EnumDTO> getAllFuelTypes() {
        List<EnumDTO> dtos = new ArrayList<>();
        for (FuelType fuelType : FuelType.values()) {
            EnumDTO dto = new EnumDTO();
            dto.setOrdinal(fuelType.ordinal());
            dto.setValue(fuelType.name());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<EnumDTO> getAllBodyTypes() {
        List<EnumDTO> dtos = new ArrayList<>();
        for (CarBodyType bodyType : CarBodyType.values()) {
            EnumDTO dto = new EnumDTO();
            dto.setOrdinal(bodyType.ordinal());
            dto.setValue(bodyType.name());
            dtos.add(dto);
        }
        return dtos;
    }

}
