package com.marija.insurance.services.impl;

import com.marija.insurance.domain.City;
import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.exception.ResourceNotFoundException;
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
    public MaterialDamage createMaterialDamage(MaterialDamage materialDamage) {
        Optional<MaterialDamage> materialDamageOptional = materialDamageRepository.findById(materialDamage.getId());

        if (materialDamageOptional.isPresent()){
            throw new IllegalStateException("Material Damage exist");
        }
        return materialDamageRepository.save(materialDamage);

    }

    @Override
    public List<MaterialDamage> findAll() {
        return materialDamageRepository.findAll();
    }

    @Override
    public MaterialDamage getMaterialDamageById(long id) {

        Optional<MaterialDamage> materialDamage = materialDamageRepository.findById(id);

        if (materialDamage.isPresent()){
            return materialDamage.get();
        }else {
            throw new ResourceNotFoundException("Material Damage", "Id",id);
        }
    }

    @Override
    public List<MaterialDamage> findByCity(String cityName) {

        List<MaterialDamage> materialDamages = materialDamageRepository.findByCity(cityName);

        if (!materialDamages.isEmpty()){
            return materialDamages;
        }else {
            throw new ResourceNotFoundException("Material Damage", "City", cityName);
        }
    }

    @Override
    public List<MaterialDamage> findByVehicle(String vehicleRegNum) {

        List<MaterialDamage> materialDamages = materialDamageRepository.findByVehicleRegNum(vehicleRegNum);

        if (!materialDamages.isEmpty()){
            return materialDamages;
        }else {
            throw new ResourceNotFoundException("Material Damage", "Vehicle", vehicleRegNum);
        }
    }

    @Override
    public MaterialDamage updateMaterialDamage(MaterialDamage materialDamage, long id) {

        MaterialDamage existingMaterialDamage = materialDamageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Material Damage","Id",id));

        existingMaterialDamage.setTypeOfDamage(materialDamage.getTypeOfDamage());
        existingMaterialDamage.setEntryDate(materialDamage.getEntryDate());

        materialDamageRepository.save(existingMaterialDamage);

        return existingMaterialDamage;

    }


}
