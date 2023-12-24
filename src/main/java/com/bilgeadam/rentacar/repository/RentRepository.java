package com.bilgeadam.rentacar.repository;

import com.bilgeadam.rentacar.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    List<Rent> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date startDate, Date endDate);

    @Query("SELECT rent FROM Rent rent WHERE MONTH(rent.startDate) = :month AND YEAR(rent.startDate) = :year")
    List<Rent> getAllRentsByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);

}
