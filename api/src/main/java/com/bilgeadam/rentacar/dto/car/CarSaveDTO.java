package com.bilgeadam.rentacar.dto.car;

import lombok.Data;

@Data
public class CarSaveDTO {
    private Integer id;
    private Integer modelId;
    private Integer year;
    private Integer bodyType;
    private Integer color;
    private Integer fuelType;
}
