package com.marija.insurance.services.impl;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.domain.Vehicle;
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
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }
}
