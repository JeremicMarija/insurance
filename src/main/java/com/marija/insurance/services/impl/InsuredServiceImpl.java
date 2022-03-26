package com.marija.insurance.services.impl;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.repository.InsuredRepository;
import com.marija.insurance.services.InsuredService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuredServiceImpl implements InsuredService {

    private final InsuredRepository insuredRepository;

    public InsuredServiceImpl(InsuredRepository insuredRepository) {
        super();
        this.insuredRepository = insuredRepository;
    }

    @Override
    public Insured save(Insured insured) {
        return insuredRepository.save(insured);
    }

    @Override
    public List<Insured> findAll() {
        return insuredRepository.findAll();
    }



    @Override
    public Optional<Insured> findById(Long id) {
        return insuredRepository.findById(id);
    }

    @Override
    public List<Insured> findByName(String name) {
        return insuredRepository.findByName(name);
    }

    @Override
    public List<Insured> findBySurname(String surname) {
        return insuredRepository.findBySurname(surname);
    }

    @Override
    public Optional<Insured> findByPolicyNumber(String policyNumber) {
        return insuredRepository.findByPolicyNumber(policyNumber);
    }

}
