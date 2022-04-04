package com.marija.insurance.controller;

import com.marija.insurance.domain.DamageType;
import com.marija.insurance.services.DamageTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/damagetypes")
public class DamageTypeRestController {

    private final DamageTypeService damageTypeService;

    public DamageTypeRestController(DamageTypeService damageTypeService) {
        this.damageTypeService = damageTypeService;
    }
    @PostMapping
    public ResponseEntity<DamageType> saveDamageType(@RequestBody DamageType damageType){
        return new ResponseEntity<DamageType>(damageTypeService.createDamageType(damageType), HttpStatus.CREATED);
    }

}
