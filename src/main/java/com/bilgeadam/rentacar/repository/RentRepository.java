package com.bilgeadam.rentacar.repository;

import com.bilgeadam.rentacar.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
}
