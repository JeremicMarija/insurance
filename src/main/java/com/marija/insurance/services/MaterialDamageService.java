package com.marija.insurance.services;

import com.marija.insurance.domain.MaterialDamage;

import java.util.List;

public interface MaterialDamageService {

    MaterialDamage createMaterialDamage(MaterialDamage materialDamage);

    List<MaterialDamage> findAll();

    MaterialDamage getMaterialDamageById(long id);

    List<MaterialDamage> findByCity(String cityName);

    List<MaterialDamage> findByVehicle(String vehicleRegNum);

    MaterialDamage updateMaterialDamage(MaterialDamage materialDamage, long id);


}
