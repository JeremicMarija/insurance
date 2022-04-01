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


    @PostMapping
    public ResponseEntity<Insured> saveInsured(@RequestBody Insured insured){
        return new ResponseEntity<Insured>(insuredService.createInsured(insured), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Insured>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(insuredService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Insured> getInsuredById(@PathVariable("id") long insuredId) {
        return new ResponseEntity<Insured>(insuredService.getInsuredById(insuredId),HttpStatus.OK);
    }


    @GetMapping("searchByName/{name}")
    public ResponseEntity<List<Insured>> findByName(@PathVariable String name){
        return new ResponseEntity<List<Insured>>(insuredService.findByName(name),HttpStatus.OK);
    }


    @GetMapping("searchBySurname/{surname}")
    public ResponseEntity<List<Insured>> findBySurname(@PathVariable String surname){
        return new ResponseEntity<List<Insured>>(insuredService.findBySurname(surname),HttpStatus.OK);
    }


    @GetMapping("searchByPolicyNumber/{policyNumber}")
    public ResponseEntity<Insured> findByPolicyNumber(@PathVariable String policyNumber){
        return new ResponseEntity<Insured>(insuredService.findByPolicyNumber(policyNumber),HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<Insured> updateInsured(@PathVariable("id") long id, @RequestBody Insured insured){
        return new ResponseEntity<Insured>(insuredService.updateInsured(insured,id),HttpStatus.OK);
    }



//    @PutMapping("update/{id}")
//    public ResponseEntity<Insured> updateInsured(@PathVariable("id") long id, @RequestBody Insured insured){
//        Optional<Insured> insuredData = insuredService.findById(id);
//        if (insuredData.isPresent()){
//            Insured insured1 = insuredData.get();
//            insured1.setName(insured.getName());
//            insured1.setSurname(insured.getSurname());
//            insured1.setDateOfBirth(insured.getDateOfBirth());
//            insured1.setTypeOfInsurance(insured.getTypeOfInsurance());
//            insured1.setPolicyNumber(insured.getPolicyNumber());
//
//            return new ResponseEntity<>(insuredService.save(insured1),HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
