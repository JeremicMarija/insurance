package com.marija.insurance.services;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.dto.MaterialDamageDto;

import java.util.List;

public interface MaterialDamageService {

//    MaterialDamage createMaterialDamage(MaterialDamage materialDamage);
    MaterialDamage createMaterialDamage(MaterialDamageDto materialDamageDto);

    List<MaterialDamage> findAll();

    MaterialDamage getMaterialDamageById(long id);

    List<MaterialDamage> findByCity(String cityName);

    List<MaterialDamage> findByVehicle(String vehicleRegNum);

//    MaterialDamage updateMaterialDamage(MaterialDamage materialDamage, long id);
    MaterialDamage updateMaterialDamage(MaterialDamageDto materialDamageDto);

    List<MaterialDamage>getMaterialDamagesByVehicleId(Integer vehicleId);

    List<MaterialDamage> searchMaterialDamages(String vehicleRegNum);
}
