package com.bilgeadam.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address", schema = "rent")
public class Address {

    @Id
    @GeneratedValue(generator = "address_id_generator")
    @SequenceGenerator(name = "address_id_generator", schema = "rent", sequenceName = "address_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "address_line")
    private String addressLine;

    @Column(name = "postal_code")
    private String postCode;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private List<Personal> personals;
}
