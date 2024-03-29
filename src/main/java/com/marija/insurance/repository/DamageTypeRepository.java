package com.marija.insurance.repository;

import com.marija.insurance.domain.DamageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageTypeRepository extends JpaRepository<DamageType, Long> {
}
