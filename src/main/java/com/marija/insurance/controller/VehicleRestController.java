package com.marija.insurance.controller;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.services.InsuredService;
import com.marija.insurance.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleRestController {


    @Autowired
    private final VehicleService vehicleService;


    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping("all")
    public @ResponseBody ResponseEntity<List<Vehicle>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAll());
    }

    @GetMapping("id/{id}")
    public @ResponseBody ResponseEntity<Vehicle> findById(@PathVariable Long id){
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if(vehicle.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(vehicle.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("searchModel/{model}")
    public @ResponseBody ResponseEntity<List<Vehicle>> findByModel(@PathVariable String model){
        List<Vehicle> vehicles = vehicleService.findByModel(model);
        if (vehicles.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @GetMapping("searchBrand/{brand}")
    public @ResponseBody ResponseEntity<List<Vehicle>> findByBrand(@PathVariable String brand){
        List<Vehicle> vehicles = vehicleService.findByBrand(brand);
        if (vehicles.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @GetMapping("searchRegistrationNumber/{registrationNumber}")
    public @ResponseBody ResponseEntity<Vehicle> findByRegistrationNumber(@PathVariable String registrationNumber){
        Optional<Vehicle> vehicle = vehicleService.findByRegistrationNumber(registrationNumber);
        if (vehicle.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(vehicle.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("save")
    public @ResponseBody ResponseEntity<Vehicle> save(@Valid @RequestBody Vehicle vehicle){
        return ResponseEntity.ok(vehicleService.save(vehicle));
    }


}
