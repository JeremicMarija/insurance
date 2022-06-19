package com.marija.insurance.repository;

import com.marija.insurance.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


//    SELECT i FROM Insured i WHERE i.name like %?1
    @Query("SELECT v FROM Vehicle v WHERE v.model like %?1")
    List<Vehicle> findByModel(String model);

    @Query("SELECT v FROM Vehicle v WHERE v.brand like %?1")
    List<Vehicle> findByBrand(String brand);

    @Query("SELECT v FROM Vehicle v WHERE v.registrationNumber like %?1")
    Optional<Vehicle> findByRegistrationNumber(String  registrationNumber);

    @Query(value = "SELECT * FROM vehicle v WHERE v.insuerd_id = :insuredId", nativeQuery = true)
    List<Vehicle> findByInsuredId(Integer insuredId);

    @Query("SELECT v FROM Vehicle v WHERE (:registrationNumber is null or v.registrationNumber like %:registrationNumber%)")
    List<Vehicle> searchVehicles(String registrationNumber);


}
