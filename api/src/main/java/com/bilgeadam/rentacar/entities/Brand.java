package com.bilgeadam.rentacar.entities;

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
@Table(name = "brand", schema = "rent")
public class Brand {
    @Id
    @GeneratedValue(generator = "brand_id_generator")
    @SequenceGenerator(name = "brand_id_generator", schema ="rent", sequenceName = "brand_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private byte[] logo;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Model> models;
}
