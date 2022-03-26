package com.marija.insurance.repository;

import com.marija.insurance.domain.MaterialDamageItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDamageItemRepository extends JpaRepository<MaterialDamageItem, Long> {
}
