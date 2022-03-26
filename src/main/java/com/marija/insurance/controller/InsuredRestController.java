package com.marija.insurance.controller;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.exceptions.InsuredException;
import com.marija.insurance.services.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/insureds")
public class InsuredRestController {


    @Autowired
    private final InsuredService insuredService;


    public InsuredRestController(InsuredService insuredService) {
        this.insuredService = insuredService;
    }

    @GetMapping("all")
    public @ResponseBody ResponseEntity<List<Insured>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(insuredService.findAll());
    }

    @GetMapping("id/{id}")
    public @ResponseBody ResponseEntity<Insured> findById(@PathVariable Long id) {
        Optional<Insured> insured = insuredService.findById(id);
        if (insured.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(insured.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("searchName/{name}")
    public @ResponseBody ResponseEntity<List<Insured>> findByName(@PathVariable String name){
        List<Insured> insureds = insuredService.findByName(name);
        if (insureds.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(insureds);
    }


    @GetMapping("searchSurname/{surname}")
    public @ResponseBody ResponseEntity<List<Insured>> findBySurname(@PathVariable String surname){
        List<Insured> insureds = insuredService.findBySurname(surname);
        if (insureds.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(insureds);
    }


    @GetMapping("searchPolicyNumber/{policyNumber}")
    public @ResponseBody ResponseEntity<Insured> findByPolicyNumber(@PathVariable String policyNumber){
        Optional<Insured> insured = insuredService.findByPolicyNumber(policyNumber);
        if (insured.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(insured.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @PostMapping("save")
    public @ResponseBody ResponseEntity<Insured> save(@Valid @RequestBody Insured insured){
        return ResponseEntity.ok(insuredService.save(insured));
    }
}