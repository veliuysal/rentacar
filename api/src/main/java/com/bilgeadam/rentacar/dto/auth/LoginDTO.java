package com.bilgeadam.rentacar.dto.auth;

import lombok.Data;

@Data
public class LoginDTO {
    private String token;
    private Long customerId;
}
