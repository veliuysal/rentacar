package com.bilgeadam.rentacar.services.auth;

import com.bilgeadam.rentacar.dto.auth.LoginDTO;
import com.bilgeadam.rentacar.dto.auth.SignUpRequest;
import com.bilgeadam.rentacar.dto.auth.SigninRequest;
import com.bilgeadam.rentacar.entities.Personal;
import com.bilgeadam.rentacar.enums.Role;
import com.bilgeadam.rentacar.repository.PersonalRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationService {
    private final PersonalRepository personalRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PersonalRepository personalRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.personalRepository = personalRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public LoginDTO signup(SignUpRequest request) {
        var user = Personal.builder().firstName(request.getFirstName()).surname(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.ROLE_USER.name()).build();
        personalRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return LoginDTO.builder().token(jwt).build();
    }

    public LoginDTO signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = personalRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return LoginDTO.builder().token(jwt).build();
    }
}
