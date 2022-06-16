package com.marija.insurance.controller;

import com.marija.insurance.domain.City;
import com.marija.insurance.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cities")
public class CityRestController {

    private final CityService cityService;


    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cityService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") long cityId){
        return new ResponseEntity<City>(cityService.getCityById(cityId), HttpStatus.OK);
    }
    //    @GetMapping("{id}")
//    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") long vehicleId) {
//        return new ResponseEntity<Vehicle>(vehicleService.getVehicleById(vehicleId),HttpStatus.OK);
//    }

}
