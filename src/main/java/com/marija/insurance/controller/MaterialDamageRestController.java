package com.marija.insurance.controller;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.services.CityService;
import com.marija.insurance.services.MaterialDamageItemService;
import com.marija.insurance.services.MaterialDamageService;
import com.marija.insurance.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materialdamage")
public class MaterialDamageRestController {

    @Autowired
    private final MaterialDamageService materialDamageService;

    public MaterialDamageRestController(MaterialDamageService materialDamageService) {
        this.materialDamageService = materialDamageService;
    }


    @GetMapping("all")
    public @ResponseBody ResponseEntity<List<MaterialDamage>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(materialDamageService.findAll());
    }

    @GetMapping("id/{id}")
    public @ResponseBody ResponseEntity<MaterialDamage> findById(@PathVariable Long id){
        Optional<MaterialDamage> materialDamage = materialDamageService.findById(id);
        if (materialDamage.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(materialDamage.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("save")
    public @ResponseBody ResponseEntity<MaterialDamage> save(@Valid @RequestBody MaterialDamage materialDamage){
        return ResponseEntity.ok(materialDamageService.save(materialDamage));
    }

}
