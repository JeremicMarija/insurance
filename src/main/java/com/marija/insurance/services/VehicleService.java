package com.marija.insurance.services;

import com.marija.insurance.domain.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);

    List<Vehicle> findAll();

    Vehicle getVehicleById(long id);

    List<Vehicle> findByModel(String model);

    List<Vehicle> findByBrand(String brand);

    Vehicle findByRegistrationNumber(String registrationNumber);

    Vehicle updateVehicle(Vehicle vehicle, long id);

}
