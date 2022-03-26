package com.marija.insurance.repository;

import com.marija.insurance.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("select v from Vehicle v where v.model like %?1")
    List<Vehicle> findByModel(String model);

    @Query("select v from Vehicle v where v.brand like %?1")
    List<Vehicle> findByBrand(String brand);

    @Query("select v from Vehicle v where v.registrationNumber like %?1")
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}
