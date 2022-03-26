package com.marija.insurance.services;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    List<Vehicle> findAll();

    List<Vehicle> findByModel(String model);

    List<Vehicle> findByBrand(String brand);

    Optional<Vehicle> findByRegistrationNumber(String registartionNumber);

}
