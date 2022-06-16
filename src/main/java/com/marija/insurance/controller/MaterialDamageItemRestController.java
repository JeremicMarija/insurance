package com.marija.insurance.controller;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.dto.MaterialDamageDto;
import com.marija.insurance.dto.MaterialDamageItemDto;
import com.marija.insurance.services.MaterialDamageItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/materialdamageitems")
// /materialDamages/{id - materialDamage} / create
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

    //GET Material Damages By Vehicle ID
//    @GetMapping("vehicle/{vehicleId}")
//    public ResponseEntity<List<MaterialDamage>>getMaterialDamagesByVehicleId(@PathVariable Integer vehicleId){
//        return new ResponseEntity<List<MaterialDamage>>(materialDamageService.getMaterialDamagesByVehicleId(vehicleId),HttpStatus.OK);
//    }
    //GET Material Damage Items By Material Damage ID
    @GetMapping("materialdamage/{materialdamageId}")
    public ResponseEntity<List<MaterialDamageItem>>getMatreialDamageItemsByMaterialDamageId(@PathVariable Integer materialdamageId){
        return new ResponseEntity<List<MaterialDamageItem>>(materialDamageItemService.getMaterialDamageItemsByMaterialDamageId(materialdamageId),HttpStatus.OK);
    }


//    @GetMapping("{id}")
//    public ResponseEntity<MaterialDamageItem> getDamageItemById(@PathVariable ("id") long damageItemId){
//        return new ResponseEntity<MaterialDamageItem>(materialDamageItemService.getDamageItemById(damageItemId),HttpStatus.OK);
//    }
    @GetMapping("{id}")
    public ResponseEntity<MaterialDamageItemDto> getDamageItemById(@PathVariable (name ="id") long damageItemId){

        MaterialDamageItem materialDamageItem = materialDamageItemService.getDamageItemById(damageItemId);
        MaterialDamageItemDto materialDamageItemResponse = modelMapper.map(materialDamageItem, MaterialDamageItemDto.class);

        return ResponseEntity.ok().body(materialDamageItemResponse);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<MaterialDamageDto> getMaterialDamageById(@PathVariable(name = "id") long materialDamageId){
//        MaterialDamage materialDamage = materialDamageService.getMaterialDamageById(materialDamageId);
//
//        MaterialDamageDto materialDamageResponse = modelMapper.map(materialDamage, MaterialDamageDto.class);
//        return ResponseEntity.ok().body(materialDamageResponse);
//
//    }


}
