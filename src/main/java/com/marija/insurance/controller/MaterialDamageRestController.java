package com.marija.insurance.controller;

import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.dto.MaterialDamageDto;
import com.marija.insurance.services.MaterialDamageService;
import com.marija.insurance.services.impl.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/materialdamages")
public class MaterialDamageRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final MaterialDamageService materialDamageService;

    @Autowired
    private ReportService reportService;

    public MaterialDamageRestController(MaterialDamageService materialDamageService) {
        this.materialDamageService = materialDamageService;
    }


//    @PostMapping
//    public ResponseEntity<MaterialDamage> saveMaterialDamage(@RequestBody MaterialDamage materialDamage){
//        return new ResponseEntity<MaterialDamage>(materialDamageService.createMaterialDamage(materialDamage), HttpStatus.CREATED);
//    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<MaterialDamageDto>createMaterialDamage(@RequestBody MaterialDamageDto materialDamageDto){

        MaterialDamage materialDamage = materialDamageService.createMaterialDamage(materialDamageDto);
        MaterialDamageDto materialDamageResponse = modelMapper.map(materialDamage, MaterialDamageDto.class);

        return new ResponseEntity<MaterialDamageDto>(materialDamageResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MaterialDamage>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(materialDamageService.findAll());
    }


    @GetMapping("/download/materialDamagesReport")
    public ResponseEntity<byte[]> generateReport() throws FileNotFoundException, JRException{
        byte[] data = reportService.exportReportMaterialDamagesPdf();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=materialDamagesReport.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }


    @GetMapping("{id}")
    public ResponseEntity<MaterialDamageDto> getMaterialDamageById(@PathVariable(name = "id") long materialDamageId){
        MaterialDamage materialDamage = materialDamageService.getMaterialDamageById(materialDamageId);

        MaterialDamageDto materialDamageResponse = modelMapper.map(materialDamage, MaterialDamageDto.class);
        return ResponseEntity.ok().body(materialDamageResponse);

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable(name = "id") Long vehicleId){
//        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
//
//        VehicleDto vehicleResponse = modelMapper.map(vehicle, VehicleDto.class);
//
//        return ResponseEntity.ok().body(vehicleResponse);
//    }
//
//    //GET VEHICLES BY INSURED ID
//    @GetMapping("insured/{insuredId}")
//    public ResponseEntity<List<Vehicle>> getVehiclesByInsuredId(@PathVariable Integer insuredId){
//        return new ResponseEntity<List<Vehicle>>(vehicleService.getVehiclesByInsuredId(insuredId),HttpStatus.OK);
//    }
    //GET Material Damages By Vehicle ID
    @GetMapping("vehicle/{vehicleId}")
    public ResponseEntity<List<MaterialDamage>>getMaterialDamagesByVehicleId(@PathVariable Integer vehicleId){
        return new ResponseEntity<List<MaterialDamage>>(materialDamageService.getMaterialDamagesByVehicleId(vehicleId),HttpStatus.OK);
    }

    @GetMapping("/download/materialDamagesOfVehicle/{vehicleId}")
    public ResponseEntity<byte[]> generateReportOfMaterialDamagesOfVehicle(@PathVariable Integer vehicleId) throws FileNotFoundException, JRException{
        byte[] data = reportService.exportReportMaterialDamagesOfVehiclePdf(vehicleId);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=materialDamagesOfVehicle.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }

    @GetMapping("/vehicle/{vehicleId}/reportStatistics")
    public String generateReportOfVehiclesStatistics(@PathVariable Integer vehicleId) throws JRException, FileNotFoundException {
        return reportService.exportReportMaterialDamagesOfVehicleStatistic(vehicleId);
    }

//    @GetMapping("/vehicle/{vehicleId}/reportDamagesJson")
//    public ResponseEntity<List<MaterialDamage>>getMaterialDamagesByVehicleIdReport(@PathVariable Integer vehicleId){
//        return new ResponseEntity<List<MaterialDamage>>(materialDamageService.getMaterialDamagesByVehicleIdReport(vehicleId),HttpStatus.OK);
//    }


    @GetMapping("searchByCityName/{cityName}")
    public ResponseEntity<List<MaterialDamage>> findByCityName(@PathVariable String cityName){
        return new ResponseEntity<List<MaterialDamage>>(materialDamageService.findByCity(cityName),HttpStatus.OK);
    }

    @GetMapping("searchByVehicle/{vehicleRegNum}")
    public ResponseEntity<List<MaterialDamage>> findByVehicle(@PathVariable String vehicleRegNum){
        return new ResponseEntity<List<MaterialDamage>>(materialDamageService.findByVehicle(vehicleRegNum),HttpStatus.OK);
    }

//    @CrossOrigin
//    @PutMapping("{id}")
//    public ResponseEntity<MaterialDamage> updateMaterialDamage(@PathVariable("id") long id, @RequestBody MaterialDamage materialDamage){
//        return new ResponseEntity<MaterialDamage>(materialDamageService.updateMaterialDamage(materialDamage,id),HttpStatus.OK);
//    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<MaterialDamage> updateMaterialDamage(@RequestBody MaterialDamageDto materialDamageDto){
        return new ResponseEntity<MaterialDamage>(materialDamageService.updateMaterialDamage(materialDamageDto),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MaterialDamage>> searchMaterialDamage(@RequestParam String vehicleRegNum){
        return ResponseEntity.ok(materialDamageService.searchMaterialDamages(vehicleRegNum));
    }


}
