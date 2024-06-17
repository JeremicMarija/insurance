package com.marija.insurance.services.impl;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.exception.ResourceNotFoundException;
import com.marija.insurance.repository.InsuredRepository;
import com.marija.insurance.services.InsuredService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    public Insured createInsured(Insured insured) {

        Optional<Insured> insuredOptional = insuredRepository.findByPolicyNumber(insured.getPolicyNumber());
        if(insuredOptional.isPresent()){
            throw new IllegalStateException("Insured exist");
        }
        return insuredRepository.save(insured);
    }



    @Override
    public List<Insured> findAll() {
        return insuredRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "insuredServiceCircuitBreaker", fallbackMethod = "fallbackGetInsuredById")
    public Insured getInsuredById(long id) {
        Optional<Insured> insured = insuredRepository.findById(id);

        if (insured.isPresent()){
            return insured.get();
        }else{
            throw new ResourceNotFoundException("Insured", "Id", id);
        }
    }

    // Fallback method for getInsuredById
    public Insured fallbackGetInsuredById(long id, Throwable t) {
        // Log the error
        System.err.println("Fallback method for getInsuredById invoked due to: " + t.getMessage());

        return null;
    }

    @Override
    public List<Insured> findByName(String name) {
        List<Insured> insureds = insuredRepository.findByName(name);

        if (!insureds.isEmpty()){
            return insureds;
        }else {
            throw new  ResourceNotFoundException("Insured", "Name",name);
        }
    }

    @Override
    public List<Insured> findBySurname(String surname) {
        List<Insured> insureds = insuredRepository.findBySurname(surname);

        if (!insureds.isEmpty()){
            return insureds;
        }else {
            throw new  ResourceNotFoundException("Insured", "Surname",surname);
        }
    }

    @Override
    public Insured findByPolicyNumber(String policyNumber) {
        Optional<Insured> insured = insuredRepository.findByPolicyNumber(policyNumber);

        if (insured.isPresent()){
            return insured.get();
        }else{
            throw new ResourceNotFoundException("Insured", "PolicyNumber", policyNumber);
        }
    }

    @Override
    public Insured updateInsured(Insured insured, long id) {

        Insured existingInsured = insuredRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Insured", "Id", id));

        existingInsured.setName(insured.getName());
        existingInsured.setSurname(insured.getSurname());
        existingInsured.setPolicyNumber(insured.getPolicyNumber());
        existingInsured.setDateOfBirth(insured.getDateOfBirth());
        existingInsured.setTypeOfInsurance(insured.getTypeOfInsurance());

        insuredRepository.save(existingInsured);

        return existingInsured;

    }

    @Override
    public List<Insured> searchInsureds(String name, String surname, String policyNumber) {
        List<Insured> insureds = insuredRepository.searchInsureds(name,surname,policyNumber);
        if (!insureds.isEmpty()){
            return insureds;
        }else {
            throw new  ResourceNotFoundException("Insured", "Required parameter",name);
        }
    }




}
