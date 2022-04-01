package com.marija.insurance.repository;

import com.marija.insurance.domain.Insured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsuredRepository  extends JpaRepository<Insured,Long> {



    @Query("SELECT i FROM Insured i WHERE i.name like %?1")
    List<Insured> findByName(String name);


    @Query("SELECT i FROM Insured i WHERE i.surname like %?1")
    List<Insured> findBySurname(String surname);

    @Query("SELECT i FROM Insured i WHERE i.policyNumber like %?1")
    Optional<Insured> findByPolicyNumber(String policyNumber);



}
