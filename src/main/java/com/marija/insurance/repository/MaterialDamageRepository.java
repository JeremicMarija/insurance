package com.marija.insurance.repository;

import com.marija.insurance.domain.MaterialDamage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDamageRepository extends JpaRepository<MaterialDamage, Long> {
}
