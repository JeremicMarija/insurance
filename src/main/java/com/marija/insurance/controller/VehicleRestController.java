package com.marija.insurance.controller;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.dto.VehicleDto;
import com.marija.insurance.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehicles")
public class VehicleRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final VehicleService vehicleService;


    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

//    @CrossOrigin
//    @PostMapping
//    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle){
//        return new ResponseEntity<Vehicle>(vehicleService.createVehicle(vehicle), HttpStatus.CREATED);
//    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto){

//        Vehicle vehicleRequest = modelMapper.map(vehicleDto, Vehicle.class);
        Vehicle vehicle = vehicleService.createVehicle(vehicleDto);
        VehicleDto vehicleResponse = modelMapper.map(vehicle, VehicleDto.class);

        return new ResponseEntity<VehicleDto>(vehicleResponse, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAllVehicles());
    }
//    @GetMapping
//    public List<VehicleDto> getAllVehicles(){
//        return vehicleService.getAllVehicles().stream().map(vehicle -> modelMapper.map(vehicle, VehicleDto.class)).collect(Collectors.toList());
//    }

//    @GetMapping("{id}")
//    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") long vehicleId) {
//        return new ResponseEntity<Vehicle>(vehicleService.getVehicleById(vehicleId),HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable(name = "id") Long vehicleId){
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        VehicleDto vehicleResponse = modelMapper.map(vehicle, VehicleDto.class);

        return ResponseEntity.ok().body(vehicleResponse);
    }


    @GetMapping("searchByModel/{model}")
    public ResponseEntity<List<Vehicle>> findByModel(@PathVariable String model){
        return new ResponseEntity<List<Vehicle>>(vehicleService.findByModel(model),HttpStatus.OK);
    }
    @GetMapping("searchByBrand/{brand}")
    public ResponseEntity<List<Vehicle>> findByBrand(@PathVariable String brand){
        return new ResponseEntity<List<Vehicle>>(vehicleService.findByBrand(brand),HttpStatus.OK);
    }

//    @GetMapping("searchByRegistrationNumber/{registrationNumber}")
//    public ResponseEntity<Vehicle> findByRegistrationNumber(@PathVariable String registrationNumber){
//        return new ResponseEntity<Vehicle>(vehicleService.findByRegistrationNumber(registrationNumber),HttpStatus.OK);
//    }

    //GET VEHICLES BY INSURED ID
    @GetMapping("insured/{insuredId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByInsuredId(@PathVariable Integer insuredId){
        return new ResponseEntity<List<Vehicle>>(vehicleService.getVehiclesByInsuredId(insuredId),HttpStatus.OK);
    }


//    @PutMapping("{id}")
//    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") long id, @RequestBody Vehicle vehicle){
//        return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicle,id),HttpStatus.OK);
//    }
    @CrossOrigin
    @PutMapping()
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody VehicleDto vehicleDto){
        return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicleDto),HttpStatus.OK);
    }



    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicles(@RequestParam String registrationNumber){

        System.out.println(registrationNumber);
        return ResponseEntity.ok(vehicleService.searchVehicles(registrationNumber));
    }

}
