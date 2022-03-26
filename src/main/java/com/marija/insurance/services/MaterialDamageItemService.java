package com.marija.insurance.services;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;

import java.util.List;

public interface MaterialDamageItemService {
    List<MaterialDamageItem> findAll();

    MaterialDamageItem save(MaterialDamageItem materialDamageItem);

}
