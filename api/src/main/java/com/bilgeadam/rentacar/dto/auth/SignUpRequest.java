package com.bilgeadam.rentacar.dto.auth;

import com.bilgeadam.rentacar.dto.model.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String surname;
    private String email;
    private String password;

    private AddressDTO address;
}