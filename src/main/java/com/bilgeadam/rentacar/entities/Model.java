package com.bilgeadam.rentacar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "model", schema = "rent")
public class Model {

    @Id
    @GeneratedValue(generator = "model_id_generator")
    @SequenceGenerator(name = "model_id_generator", sequenceName = "model_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @JsonBackReference
    private Brand brand;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Car> cars;
}
