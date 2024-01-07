package com.bilgeadam.rentacar.dto.car;

import com.bilgeadam.rentacar.dto.common.EnumDTO;
import com.bilgeadam.rentacar.dto.common.SelectDataDTO;
import com.bilgeadam.rentacar.dto.model.ModelDTO;
import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    private ModelDTO model;
    private Integer year;
    private EnumDTO bodyType;
    private EnumDTO color;
    private EnumDTO fuelType;
}
