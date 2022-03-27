package com.marija.insurance.repository;

import com.marija.insurance.domain.City;
import com.marija.insurance.domain.MaterialDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MaterialDamageRepository extends JpaRepository<MaterialDamage, Long> {

    @Query("select md from MaterialDamage md where md.city.name = ?1")
    List<MaterialDamage> findByCity(String cityName);

    @Query("select md from MaterialDamage md where md.vehicle.registrationNumber = ?1")
    List<MaterialDamage> findByVehicleRegNum(String vehicleRegNum);
}
