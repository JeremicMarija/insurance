package com.marija.insurance.controller;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.services.MaterialDamageItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("update/{id}")
    public ResponseEntity<MaterialDamageItem> updateMaterialDamageItem(@PathVariable("id") long id, @RequestBody MaterialDamageItem materialDamageItem){
        Optional<MaterialDamageItem> materialDamageItemData = materialDamageItemService.findById(id);
        if (materialDamageItemData.isPresent()){
            MaterialDamageItem materialDamageItem1  = materialDamageItemData.get();
            materialDamageItem1.setDescription(materialDamageItem.getDescription());
            materialDamageItem1.setEstimatedPrice(materialDamageItem.getEstimatedPrice());
            return new ResponseEntity<>(materialDamageItemService.save(materialDamageItem1),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
