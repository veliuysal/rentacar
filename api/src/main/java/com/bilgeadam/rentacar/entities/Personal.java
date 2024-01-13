package com.bilgeadam.rentacar.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personal")
@Getter
@Setter
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    private String surname;
    private String password;

    private String roles;
}
