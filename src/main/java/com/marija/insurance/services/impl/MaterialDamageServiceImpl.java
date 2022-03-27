package com.marija.insurance.services.impl;

import com.marija.insurance.domain.City;
import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.repository.MaterialDamageRepository;
import com.marija.insurance.services.MaterialDamageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDamageServiceImpl implements MaterialDamageService {

    private final MaterialDamageRepository materialDamageRepository;

    public MaterialDamageServiceImpl(MaterialDamageRepository materialDamageRepository) {
        super();
        this.materialDamageRepository = materialDamageRepository;
    }

    @Override
    public List<MaterialDamage> findAll() {
        return materialDamageRepository.findAll();
    }

    @Override
    public MaterialDamage save(MaterialDamage materialDamage) {
        return materialDamageRepository.save(materialDamage);
    }

    @Override
    public Optional<MaterialDamage> findById(Long id) {
        return materialDamageRepository.findById(id);
    }

    @Override
    public List<MaterialDamage> findByCity(String cityName) {
        return materialDamageRepository.findByCity(cityName);
    }

    @Override
    public List<MaterialDamage> findByVehicleRegNum(String vehicleRegNum) {
        return materialDamageRepository.findByVehicleRegNum(vehicleRegNum);
    }

}
