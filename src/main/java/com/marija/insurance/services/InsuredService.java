package com.marija.insurance.services;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.exceptions.InsuredException;

import java.util.List;
import java.util.Optional;

public interface InsuredService {
    Insured save(Insured insured);

    Optional<Insured> findById(Long id);

    List<Insured> findAll();

    List<Insured> findByName(String name);

    List<Insured> findBySurname(String surname);

    Optional<Insured> findByPolicyNumber(String policyNumber);
}
