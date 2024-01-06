package com.bilgeadam.rentacar.repository;

import com.bilgeadam.rentacar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {

    List<Model> findAllByBrand_id(Integer id);
}
