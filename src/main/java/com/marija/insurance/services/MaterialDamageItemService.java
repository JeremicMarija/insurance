package com.marija.insurance.services;

import com.marija.insurance.domain.MaterialDamageItem;

import java.util.List;


public interface MaterialDamageItemService {

    MaterialDamageItem createMaterialDamageItem(MaterialDamageItem materialDamageItem);

    List<MaterialDamageItem> findAll();

    MaterialDamageItem getDamageItemById(long id);


}
