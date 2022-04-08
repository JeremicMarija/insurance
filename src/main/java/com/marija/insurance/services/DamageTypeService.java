package com.marija.insurance.services;

import com.marija.insurance.domain.DamageType;

import java.util.List;

public interface DamageTypeService {

    List<DamageType> findAll();
//    DamageType createDamageType(DamageType damageType);
}
