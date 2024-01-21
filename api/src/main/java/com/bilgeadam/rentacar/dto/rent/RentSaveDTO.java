package com.bilgeadam.rentacar.dto.rent;

import lombok.Data;

import java.util.Date;

@Data
public class RentSaveDTO {
    private Integer carId;
    private Integer price;
    private Date startDate;
    private Date endDate;
    private Integer kilometers;
    private Integer fuelTank;
}
