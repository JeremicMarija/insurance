package com.marija.insurance.services.impl;

import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.exception.ResourceNotFoundException;
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
    public MaterialDamageItem createMaterialDamageItem(MaterialDamageItem materialDamageItem) {

        Optional<MaterialDamageItem> materialDamageItemOptional = materialDamageItemRepository.findById(materialDamageItem.getId());

        if (materialDamageItemOptional.isPresent()){
            throw new IllegalStateException("Material Damage Item exist");
        }
        return materialDamageItemRepository.save(materialDamageItem);

    }

    @Override
    public List<MaterialDamageItem> findAll() {
        return materialDamageItemRepository.findAll();
    }

    @Override
    public MaterialDamageItem getDamageItemById(long id) {

        Optional<MaterialDamageItem> materialDamageItemOptional = materialDamageItemRepository.findById(id);

        if (materialDamageItemOptional.isPresent()){
            return materialDamageItemOptional.get();
        }else {
            throw new ResourceNotFoundException("Damage Item", "Id",id);
        }
    }


}
