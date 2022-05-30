package com.marija.insurance.repository;

import com.marija.insurance.domain.MaterialDamageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialDamageItemRepository extends JpaRepository<MaterialDamageItem, Long> {

    @Query(value = "SELECT * FROM material_damage_item mdi WHERE mdi.materialdamage_id = :materialdamageId", nativeQuery = true)
    List<MaterialDamageItem>findByMaterialDamageId(Integer materialdamageId);
}
