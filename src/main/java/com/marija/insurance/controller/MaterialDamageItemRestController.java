package com.marija.insurance.controller;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.dto.MaterialDamageItemDto;
import com.marija.insurance.services.MaterialDamageItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materialdamageitems")
public class MaterialDamageItemRestController {

    private final MaterialDamageItemService materialDamageItemService;

    @Autowired
    private ModelMapper modelMapper;

    public MaterialDamageItemRestController(MaterialDamageItemService materialDamageItemService) {
        this.materialDamageItemService = materialDamageItemService;
    }


//    @PostMapping
//    public ResponseEntity<MaterialDamageItem> saveDamageItem(@RequestBody MaterialDamageItem materialDamageItem){
//        return new ResponseEntity<MaterialDamageItem>(materialDamageItemService.createMaterialDamageItem(materialDamageItem),HttpStatus.CREATED);
//    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<MaterialDamageItemDto> createDamageItem(@RequestBody MaterialDamageItemDto materialDamageItemDto){

        MaterialDamageItem materialDamageItem = materialDamageItemService.createMaterialDamageItem(materialDamageItemDto);
        MaterialDamageItemDto materialDamageItemResponse = modelMapper.map(materialDamageItem, MaterialDamageItemDto.class);

        return new ResponseEntity<MaterialDamageItemDto>(materialDamageItemResponse,HttpStatus.CREATED);
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
