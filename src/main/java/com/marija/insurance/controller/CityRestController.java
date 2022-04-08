package com.marija.insurance.controller;

import com.marija.insurance.domain.City;
import com.marija.insurance.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

}
