package com.marija.insurance.services;

import com.marija.insurance.domain.Insured;

import java.util.List;

public interface InsuredService {

    Insured createInsured(Insured insured);

    List<Insured> findAll();

    Insured getInsuredById(long id);

    List<Insured> findByName(String name);

    List<Insured> findBySurname(String surname);

    Insured findByPolicyNumber(String policyNumber);

    Insured updateInsured(Insured insured, long id);

    List<Insured>searchInsureds(String name, String surname, String policyNumber);

}
