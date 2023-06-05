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


    @Query(value = "SELECT * FROM material_damage md WHERE md.vehicle_id = :vehicleId", nativeQuery = true)
    //@Query(value = "SELECT COUNT(md.vehicle_id), md.entry_date, md.vehicle_id FROM material_damage md WHERE md.entry_date > '1998-01-01' AND md.vehicle_id = :vehicleId GROUP BY md.vehicle_id", nativeQuery = true)
    List<MaterialDamage> findByVehicleIdForStatistic(Integer vehicleId);

    @Query("SELECT md FROM MaterialDamage md WHERE md.vehicle.registrationNumber like %?1")
    List<MaterialDamage> searchMaterialDamages(String vehicleRegNum);

}