package com.bilgeadam.rentacar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent_car", schema = "rent")
public class RentCar {

    @Id
    @GeneratedValue(generator = "rent_car_id_generator")
    @SequenceGenerator(name = "rent_car_id_generator", schema = "rent", sequenceName = "rent_car_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rent_id", referencedColumnName = "id")
    @JsonBackReference
    private Rent rent;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @JsonBackReference
    private Car car;
}
