package com.marija.insurance.controller;

import com.marija.insurance.domain.City;
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

    @GetMapping("searchDamageByCityName/{cityName}")
    public @ResponseBody ResponseEntity<List<MaterialDamage>> findByCity(@PathVariable String cityName){
        List<MaterialDamage> materialDamages = materialDamageService.findByCity(cityName);
        if (materialDamages.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(materialDamages);
    }
    @GetMapping("searchDamageByVehicleRegNam/{vehicleRegNum}")
    public @ResponseBody ResponseEntity<List<MaterialDamage>> findByVehicle(@PathVariable String vehicleRegNum){
        List<MaterialDamage> materialDamages = materialDamageService.findByVehicleRegNum(vehicleRegNum);
        if (materialDamages.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(materialDamages);
    }

    @PostMapping("save")
    public @ResponseBody ResponseEntity<MaterialDamage> save(@Valid @RequestBody MaterialDamage materialDamage){
        return ResponseEntity.ok(materialDamageService.save(materialDamage));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<MaterialDamage> updateMaterialDamage(@PathVariable("id") long id, @RequestBody MaterialDamage materialDamage){
        Optional<MaterialDamage> materialDamageData = materialDamageService.findById(id);
        if (materialDamageData.isPresent()){
           MaterialDamage materialDamage1  = materialDamageData.get();
           materialDamage1.setTypeOfDamage(materialDamage.getTypeOfDamage());

            return new ResponseEntity<>(materialDamageService.save(materialDamage1),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
