package com.marija.insurance.services.impl;

import com.marija.insurance.domain.DamageType;
import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.dto.MaterialDamageItemDto;
import com.marija.insurance.exception.ResourceNotFoundException;
import com.marija.insurance.repository.DamageTypeRepository;
import com.marija.insurance.repository.MaterialDamageItemRepository;
import com.marija.insurance.repository.MaterialDamageRepository;
import com.marija.insurance.services.MaterialDamageItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDamageItemServiceImpl implements MaterialDamageItemService {

    private final MaterialDamageItemRepository materialDamageItemRepository;

    private final ModelMapper modelMapper;

    private final DamageTypeRepository damageTypeRepository;

    private final MaterialDamageRepository materialDamageRepository;


    public MaterialDamageItemServiceImpl(MaterialDamageItemRepository materialDamageItemRepository, ModelMapper modelMapper, DamageTypeRepository damageTypeRepository, MaterialDamageRepository materialDamageRepository) {
        super();
        this.materialDamageItemRepository = materialDamageItemRepository;
        this.modelMapper = modelMapper;
        this.damageTypeRepository = damageTypeRepository;
        this.materialDamageRepository = materialDamageRepository;
    }

//    @Override
//    public MaterialDamageItem createMaterialDamageItem(MaterialDamageItem materialDamageItem) {
//
//        Optional<MaterialDamageItem> materialDamageItemOptional = materialDamageItemRepository.findById(materialDamageItem.getId());
//
//        if (materialDamageItemOptional.isPresent()){
//            throw new IllegalStateException("Material Damage Item exist");
//        }
//        return materialDamageItemRepository.save(materialDamageItem);
//
//    }
    @Override
    public MaterialDamageItem createMaterialDamageItem(MaterialDamageItemDto materialDamageItemDto) {

        Optional<MaterialDamageItem> materialDamageItemOptional = materialDamageItemRepository.findById(materialDamageItemDto.getId());

        if (materialDamageItemOptional.isPresent()){
            throw new IllegalStateException("Material Damage Item exist");
        }
        MaterialDamageItem materialDamageItem = modelMapper.map(materialDamageItemDto, MaterialDamageItem.class);
        DamageType damageType = damageTypeRepository.getById(materialDamageItemDto.getDamageTypeId());
        materialDamageItem.setDamageType(damageType);
        MaterialDamage materialDamage = materialDamageRepository.getById(materialDamageItemDto.getMaterialDamageId());
        materialDamageItem.setMaterialDamage(materialDamage);

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

    @Override
    public List<MaterialDamageItem> getMaterialDamageItemsByMaterialDamageId(Integer materialdamageId) {

        List<MaterialDamageItem> materialDamageItems = materialDamageItemRepository.findByMaterialDamageId(materialdamageId);
        if (!materialDamageItems.isEmpty()){
            return materialDamageItems;
        }else {
            throw new ResourceNotFoundException("Material Damage Item", "MaterialDamageId", materialdamageId);
        }
    }


}
