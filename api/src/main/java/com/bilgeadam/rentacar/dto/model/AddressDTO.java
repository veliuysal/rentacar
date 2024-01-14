package com.bilgeadam.rentacar.dto.model;

import lombok.Data;

@Data
public class AddressDTO {
    private String country;
    private String city;
    private String district;

    private String addressLine;
    private String postCode;

}
