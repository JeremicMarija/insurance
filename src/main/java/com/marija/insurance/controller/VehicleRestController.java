package com.marija.insurance.controller;

import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleRestController {


    @Autowired
    private final VehicleService vehicleService;


    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @PostMapping
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle){
        return new ResponseEntity<Vehicle>(vehicleService.createVehicle(vehicle), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") long vehicleId) {
        return new ResponseEntity<Vehicle>(vehicleService.getVehicleById(vehicleId),HttpStatus.OK);
    }

    @GetMapping("searchByModel/{model}")
    public ResponseEntity<List<Vehicle>> findByModel(@PathVariable String model){
        return new ResponseEntity<List<Vehicle>>(vehicleService.findByModel(model),HttpStatus.OK);
    }
    @GetMapping("searchByBrand/{brand}")
    public ResponseEntity<List<Vehicle>> findByBrand(@PathVariable String brand){
        return new ResponseEntity<List<Vehicle>>(vehicleService.findByBrand(brand),HttpStatus.OK);
    }

    @GetMapping("searchByRegistrationNumber/{registrationNumber}")
    public ResponseEntity<Vehicle> findByRegistrationNumber(@PathVariable String registrationNumber){
        return new ResponseEntity<Vehicle>(vehicleService.findByRegistrationNumber(registrationNumber),HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") long id, @RequestBody Vehicle vehicle){
        return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicle,id),HttpStatus.OK);
    }


}
