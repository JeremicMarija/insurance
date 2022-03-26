package com.marija.insurance.services;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface MaterialDamageService {
    List<MaterialDamage> findAll();

    MaterialDamage save(MaterialDamage materialDamage);

    Optional<MaterialDamage> findById(Long id);

}
