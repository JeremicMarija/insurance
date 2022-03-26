package com.marija.insurance.repository;

import com.marija.insurance.domain.Insured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuredRepository  extends JpaRepository<Insured,Long> {

    @Query("select i from Insured i where i.name like %?1")
    Optional<Insured> findByName(String name);

    @Query("select i from Insured i where i.surname like %?1")
    Optional<Insured> findBySurname(String surname);

    @Query("select i from Insured i where i.policyNumber like %?1")
    Optional<Insured> findByPolicyNumber(String policyNumber);
}
