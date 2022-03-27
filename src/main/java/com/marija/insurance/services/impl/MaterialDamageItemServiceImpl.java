package com.marija.insurance.services.impl;

import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.repository.MaterialDamageItemRepository;
import com.marija.insurance.services.MaterialDamageItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDamageItemServiceImpl implements MaterialDamageItemService {

    private final MaterialDamageItemRepository materialDamageItemRepository;

    public MaterialDamageItemServiceImpl(MaterialDamageItemRepository materialDamageItemRepository) {
        super();
        this.materialDamageItemRepository = materialDamageItemRepository;
    }

    @Override
    public List<MaterialDamageItem> findAll() {
        return materialDamageItemRepository.findAll();
    }

    @Override
    public MaterialDamageItem save(MaterialDamageItem materialDamageItem) {
        return materialDamageItemRepository.save(materialDamageItem);
    }

    @Override
    public Optional<MaterialDamageItem> findById(long id) {
        return materialDamageItemRepository.findById(id);
    }


}
