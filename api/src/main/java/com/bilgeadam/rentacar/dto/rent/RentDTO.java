package com.bilgeadam.rentacar.dto.rent;

import com.bilgeadam.rentacar.dto.car.CarDTO;
import com.bilgeadam.rentacar.dto.common.EnumDTO;
import lombok.Data;

import java.util.Date;

@Data
public class RentDTO {
    private CarDTO car;
    private Integer price;
    private Date startDate;
    private Date endDate;
    private Integer kilometers;
    private EnumDTO fuelTank;
}
