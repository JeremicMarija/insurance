package com.marija.insurance.controller;

import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.services.MaterialDamageItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materialdamageitem")
public class MaterialDamageItemRestController {

    private final MaterialDamageItemService materialDamageItemService;

    public MaterialDamageItemRestController(MaterialDamageItemService materialDamageItemService) {
        this.materialDamageItemService = materialDamageItemService;
    }


    @GetMapping("all")
    public @ResponseBody ResponseEntity<List<MaterialDamageItem>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(materialDamageItemService.findAll());
    }


    @PostMapping("save")
    public @ResponseBody ResponseEntity<MaterialDamageItem> save(@Valid @RequestBody MaterialDamageItem materialDamageItem){
        return ResponseEntity.ok(materialDamageItemService.save(materialDamageItem));
    }
}
