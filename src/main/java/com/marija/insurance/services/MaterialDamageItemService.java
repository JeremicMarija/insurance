package com.marija.insurance.services;

import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.dto.MaterialDamageItemDto;

import java.util.List;


public interface MaterialDamageItemService {

//    MaterialDamageItem createMaterialDamageItem(MaterialDamageItem materialDamageItem);
    MaterialDamageItem createMaterialDamageItem(MaterialDamageItemDto materialDamageItemDto);

    List<MaterialDamageItem> findAll();

    MaterialDamageItem getDamageItemById(long id);


}
