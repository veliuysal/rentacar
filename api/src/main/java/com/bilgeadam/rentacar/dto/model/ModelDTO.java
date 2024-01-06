package com.bilgeadam.rentacar.dto.model;

import com.bilgeadam.rentacar.dto.brand.BrandDTO;
import lombok.Data;

@Data
public class ModelDTO {
    private Integer id;
    private String name;
    private BrandDTO brand;
}
