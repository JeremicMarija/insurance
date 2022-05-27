package com.marija.insurance.services;

import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.dto.VehicleDto;

import java.util.List;

public interface VehicleService {

//    Vehicle createVehicle(Vehicle vehicle);
    Vehicle createVehicle(VehicleDto vehicleDto);

    List<Vehicle> getAllVehicles();

    Vehicle getVehicleById(long id);

    List<Vehicle> findByModel(String model);

    List<Vehicle> findByBrand(String brand);

    Vehicle findByRegistrationNumber(String registrationNumber);

//    Vehicle updateVehicle(Vehicle vehicle, long id);
//    Vehicle updateVehicle(Vehicle vehicle, long id)
    Vehicle updateVehicle(VehicleDto vehicleDto);

    List<Vehicle>getVehiclesByInsuredId(Integer insuredId);

}
