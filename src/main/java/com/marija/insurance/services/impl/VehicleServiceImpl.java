package com.marija.insurance.services.impl;

import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.exception.ResourceNotFoundException;
import com.marija.insurance.repository.VehicleRepository;
import com.marija.insurance.services.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        super();
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber());
        if(vehicleOptional.isPresent()){
            throw new IllegalStateException("Vehicle exist");
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isPresent()){
            return vehicle.get();
        }else{
            throw new ResourceNotFoundException("Vehicle", "Id", id);
        }
    }

    @Override
    public List<Vehicle> findByModel(String model) {
        List<Vehicle> vehicles = vehicleRepository.findByModel(model);

        if (!vehicles.isEmpty()){
            return vehicles;
        }else {
            throw new  ResourceNotFoundException("Vehicle", "Model", model);
        }
    }

    @Override
    public List<Vehicle> findByBrand(String brand) {
        List<Vehicle> vehicles = vehicleRepository.findByBrand(brand);

        if (!vehicles.isEmpty()){
            return vehicles;
        }else {
            throw new  ResourceNotFoundException("Vehicle", "Brand", brand);
        }
    }

    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {
        Optional<Vehicle> vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);

        if (vehicle.isPresent()){
            return vehicle.get();
        }else{
            throw new ResourceNotFoundException("Vehicle", "RegistrationNumber", registrationNumber);
        }
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle, long id) {

        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle","Id", id));

        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setBrand(vehicle.getBrand());
        existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());

        vehicleRepository.save(existingVehicle);

        return existingVehicle;
    }


}
