package com.marija.insurance.services.impl;

import com.marija.insurance.domain.DamageType;
import com.marija.insurance.repository.DamageTypeRepository;
import com.marija.insurance.services.DamageTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DamageTypeServiceImpl implements DamageTypeService {

    private final DamageTypeRepository damageTypeRepository;

    public DamageTypeServiceImpl(DamageTypeRepository damageTypeRepository) {
        this.damageTypeRepository = damageTypeRepository;
    }

    @Override
    public List<DamageType> findAll() {
        return damageTypeRepository.findAll();
    }

//    @Override
//    public DamageType createDamageType(DamageType damageType) {
//
//        Optional<DamageType> damageTypeOptional = damageTypeRepository.findById(damageType.getId());
//
//        if (damageTypeOptional.isPresent()){
//            throw new IllegalStateException("Damage Type exist");
//        }
//        return damageTypeRepository.save(damageType);
//    }
}
