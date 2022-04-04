package com.marija.insurance.repository;

import com.marija.insurance.domain.MaterialDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialDamageRepository extends JpaRepository<MaterialDamage, Long> {

    @Query("SELECT md FROM MaterialDamage md WHERE md.city.name = ?1")
    List<MaterialDamage> findByCity(String cityName);

    @Query("SELECT md FROM MaterialDamage md WHERE md.vehicle.registrationNumber = ?1")
    List<MaterialDamage> findByVehicleRegNum(String vehicleRegNum);
}
