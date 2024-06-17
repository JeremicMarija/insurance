package com.marija.insurance.controller;

import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.dto.VehicleDto;
import com.marija.insurance.services.VehicleService;
import com.marija.insurance.services.impl.ReportService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehicles")
public class VehicleRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final VehicleService vehicleService;

    @Autowired
    private ReportService reportService;

    private final RestTemplate restTemplate;


    public VehicleRestController(VehicleService vehicleService, RestTemplate restTemplate) {
        this.vehicleService = vehicleService;
        this.restTemplate = restTemplate;
    }


    @CrossOrigin
    @PostMapping
    @CircuitBreaker(name = "backendService", fallbackMethod = "fallbackCreateVehicle")
    @Retry(name = "backendService")
    @TimeLimiter(name = "backendService")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto){

//        Vehicle vehicleRequest = modelMapper.map(vehicleDto, Vehicle.class);

        //OLD part
//        Vehicle vehicle = vehicleService.createVehicle(vehicleDto);
//        VehicleDto vehicleResponse = modelMapper.map(vehicle, VehicleDto.class);
//        return new ResponseEntity<VehicleDto>(vehicleResponse, HttpStatus.CREATED);

        ResponseEntity<Vehicle> responseEntity = restTemplate.getForEntity("http://localhost:8081/vehicle/" + vehicleDto.getRegistrationNumber(), Vehicle.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            Vehicle vehicleData = responseEntity.getBody();

            VehicleDto vehicleResponse = new VehicleDto();
            vehicleResponse.setBrand(vehicleData.getBrand());
            vehicleResponse.setModel(vehicleData.getModel());
            vehicleResponse.setRegistrationNumber(vehicleData.getRegistrationNumber());
            vehicleResponse.setInsuredId(vehicleDto.getInsuredId());

            // Kreiranje vozila i čuvanje u bazi
            vehicleService.createVehicle(vehicleResponse);

            // Vraćanje odgovora
            return new ResponseEntity<>(vehicleResponse, HttpStatus.CREATED);
        }else {
            // Vraćanje odgovora ako se vozilo ne može naći u drugom backendu
            return  ResponseEntity.notFound().build();
        }
        // Fallback metoda koja će se pozvati u slučaju greške

    }
    public ResponseEntity<VehicleDto> fallbackCreateVehicle(VehicleDto vehicleDto, Throwable throwable) {
        // Logika za fallback može uključivati vraćanje default odgovora ili specifične poruke o grešci
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }


    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAllVehicles());
    }

    @GetMapping("/download/vehiclesReport")
    public ResponseEntity<byte[]> generateReport() throws FileNotFoundException, JRException{
        byte[] data = reportService.exportReportVehiclesPdf();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vehiclesReport.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable(name = "id") Long vehicleId){
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        VehicleDto vehicleResponse = modelMapper.map(vehicle, VehicleDto.class);

        return ResponseEntity.ok().body(vehicleResponse);
    }


    @GetMapping("searchByModel/{model}")
    public ResponseEntity<List<Vehicle>> findByModel(@PathVariable String model){
        return new ResponseEntity<List<Vehicle>>(vehicleService.findByModel(model),HttpStatus.OK);
    }
    @GetMapping("searchByBrand/{brand}")
    public ResponseEntity<List<Vehicle>> findByBrand(@PathVariable String brand){
        return new ResponseEntity<List<Vehicle>>(vehicleService.findByBrand(brand),HttpStatus.OK);
    }


    //GET VEHICLES BY INSURED ID
    @GetMapping("insured/{insuredId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByInsuredId(@PathVariable Integer insuredId){
        return new ResponseEntity<List<Vehicle>>(vehicleService.getVehiclesByInsuredId(insuredId),HttpStatus.OK);
    }
    @GetMapping("/download/vehiclesOfInsured/{insuredId}")
    public ResponseEntity<byte[]> generateReportOfVehicles(@PathVariable Integer insuredId) throws FileNotFoundException, JRException{
        byte[] data = reportService.exportReportVehiclesOfInsuredPdf(insuredId);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vehiclesOfInsured.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody VehicleDto vehicleDto){
        return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicleDto),HttpStatus.OK);
    }



    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicles(@RequestParam String registrationNumber){

        System.out.println(registrationNumber);
        return ResponseEntity.ok(vehicleService.searchVehicles(registrationNumber));
    }

}
