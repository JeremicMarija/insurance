package com.marija.insurance.controller;

import com.marija.insurance.domain.DamageType;
import com.marija.insurance.services.DamageTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/damagetypes")
public class DamageTypeRestController {

    private final DamageTypeService damageTypeService;

    public DamageTypeRestController(DamageTypeService damageTypeService) {
        this.damageTypeService = damageTypeService;
    }


    @GetMapping
    public ResponseEntity<List<DamageType>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(damageTypeService.findAll());
    }

//    @PostMapping
//    public ResponseEntity<DamageType> saveDamageType(@RequestBody DamageType damageType){
//        return new ResponseEntity<DamageType>(damageTypeService.createDamageType(damageType), HttpStatus.CREATED);
//    }

}
