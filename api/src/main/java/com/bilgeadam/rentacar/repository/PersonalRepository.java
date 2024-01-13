package com.bilgeadam.rentacar.repository;

import com.bilgeadam.rentacar.entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    Optional<Personal> findByFirstName(String firstName);
}
