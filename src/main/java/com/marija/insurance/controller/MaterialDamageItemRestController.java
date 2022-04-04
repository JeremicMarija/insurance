package com.marija.insurance.controller;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.services.MaterialDamageItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materialdamageitems")
public class MaterialDamageItemRestController {

    private final MaterialDamageItemService materialDamageItemService;

    public MaterialDamageItemRestController(MaterialDamageItemService materialDamageItemService) {
        this.materialDamageItemService = materialDamageItemService;
    }


    @PostMapping
    public ResponseEntity<MaterialDamageItem> saveDamageItem(@RequestBody MaterialDamageItem materialDamageItem){
        return new ResponseEntity<MaterialDamageItem>(materialDamageItemService.createMaterialDamageItem(materialDamageItem),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MaterialDamageItem>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(materialDamageItemService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<MaterialDamageItem> getDamageItemById(@PathVariable ("id") long damageItemId){
        return new ResponseEntity<MaterialDamageItem>(materialDamageItemService.getDamageItemById(damageItemId),HttpStatus.OK);
    }


}
