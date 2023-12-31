package com.bilgeadam.rentacar.repository;

import com.bilgeadam.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query(value = "SELECT COALESCE(MAX(b.id), 0) FROM Brand b")
    Integer getMaxId();
}
