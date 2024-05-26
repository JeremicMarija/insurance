package com.marija.insurance.controller;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.services.InsuredService;
import com.marija.insurance.services.impl.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/insureds")
public class InsuredRestController {


    @Autowired
    private final InsuredService insuredService;

    @Autowired
    private ReportService reportService;


    public InsuredRestController(InsuredService insuredService) {
        this.insuredService = insuredService;
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<Insured> saveInsured(@RequestBody Insured insured){
        return new ResponseEntity<Insured>(insuredService.createInsured(insured), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Insured>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(insuredService.findAll());
    }

    @GetMapping("/download/insuredsReport")
    public ResponseEntity<byte[]> generateReport() throws FileNotFoundException, JRException{
        byte[] data = reportService.exportReportInsuredsPdf();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=insuredsReport.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
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


    @CrossOrigin
    @PutMapping("{id}")
    public ResponseEntity<Insured> updateInsured(@PathVariable("id") long id, @RequestBody Insured insured){
        return new ResponseEntity<Insured>(insuredService.updateInsured(insured,id),HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Insured>> searchInsureds(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String surname,
                                 @RequestParam(required = false) String policyNumber){

        System.out.println(surname + name + policyNumber);
        return ResponseEntity.ok(insuredService.searchInsureds(name,surname,policyNumber));

    }


}
