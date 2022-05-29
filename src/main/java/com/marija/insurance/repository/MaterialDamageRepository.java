package com.marija.insurance.repository;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialDamageRepository extends JpaRepository<MaterialDamage, Long> {

    @Query("SELECT md FROM MaterialDamage md WHERE md.city.name = ?1")
    List<MaterialDamage> findByCity(String cityName);

    @Query("SELECT md FROM MaterialDamage md WHERE md.vehicle.registrationNumber = ?1")
    List<MaterialDamage> findByVehicleRegNum(String vehicleRegNum);

    @Query(value = "SELECT * FROM material_damage md WHERE md.vehicle_id = :vehicleId", nativeQuery = true)
    List<MaterialDamage> findByVehicleId(Integer vehicleId);

}
