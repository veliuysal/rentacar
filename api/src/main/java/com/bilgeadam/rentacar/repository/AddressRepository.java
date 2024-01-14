package com.bilgeadam.rentacar.repository;

import com.bilgeadam.rentacar.entities.Address;
import com.bilgeadam.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
