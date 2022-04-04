package com.marija.insurance.services;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;

import java.util.List;
import java.util.Optional;

public interface MaterialDamageItemService {

    MaterialDamageItem createMaterialDamageItem(MaterialDamageItem materialDamageItem);

    List<MaterialDamageItem> findAll();

    MaterialDamageItem getDamageItemById(long id);


}
