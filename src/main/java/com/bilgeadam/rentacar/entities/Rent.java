package com.bilgeadam.rentacar.entities;

import com.bilgeadam.rentacar.enums.FuelTank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent", schema = "rent")
public class Rent {

    @Id
    @GeneratedValue(generator = "rent_id_generator")
    @SequenceGenerator(name = "rent_id_generator", sequenceName = "rent_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "price", scale = 10, precision = 2)
    private BigDecimal price;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "kilometers")
    private Integer kilometers;

    @Column(name = "fuel_tank")
    @Enumerated(EnumType.STRING)
    private FuelTank fuelTank;

    //TODO: Kullanıcı, Sözleşme kuralları

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rent_car", schema = "rent",
            joinColumns = @JoinColumn(name = "rent_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
}
