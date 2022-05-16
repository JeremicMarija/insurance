package com.marija.insurance.services.impl;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.dto.VehicleDto;
import com.marija.insurance.exception.ResourceNotFoundException;
import com.marija.insurance.repository.InsuredRepository;
import com.marija.insurance.repository.VehicleRepository;
import com.marija.insurance.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final ModelMapper modelMapper;

    private final InsuredRepository insuredRepository;

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, InsuredRepository insuredRepository) {

        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.insuredRepository = insuredRepository;
    }


//    @Override
//    public Vehicle createVehicle(Vehicle vehicle) {
//        Optional<Vehicle> vehicleOptional = vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber());
//        if(vehicleOptional.isPresent()){
//            throw new IllegalStateException("Vehicle exist");
//        }
//        return vehicleRepository.save(vehicle);
//    }
    @Override
        public Vehicle createVehicle(VehicleDto vehicleDto) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByRegistrationNumber(vehicleDto.getRegistrationNumber());
        if(vehicleOptional.isPresent()){
            throw new IllegalStateException("Vehicle exist");
        }
        Vehicle vehicle = modelMapper.map(vehicleDto,Vehicle.class);
        Insured insured = insuredRepository.getById(vehicleDto.getInsuredId());
        vehicle.setInsured(insured);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
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

//    @Override
//    public Vehicle updateVehicle(Vehicle vehicle, long id) {
//
//        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Vehicle","Id", id));
//
//        existingVehicle.setModel(vehicle.getModel());
//        existingVehicle.setBrand(vehicle.getBrand());
//        existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
//
//        vehicleRepository.save(existingVehicle);
//
//        return existingVehicle;
//    }
  @Override
  public Vehicle updateVehicle(VehicleDto vehicleDto) {

    Vehicle existingVehicle = vehicleRepository.findById(vehicleDto.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Vehicle","Id", vehicleDto.getId()));

    existingVehicle.setModel(vehicleDto.getModel());
    existingVehicle.setBrand(vehicleDto.getBrand());
    existingVehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());


    vehicleRepository.save(existingVehicle);

    return existingVehicle;
}



}
