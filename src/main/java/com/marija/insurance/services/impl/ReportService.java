package com.marija.insurance.services.impl;

import com.marija.insurance.domain.Insured;
import com.marija.insurance.domain.MaterialDamage;
import com.marija.insurance.domain.MaterialDamageItem;
import com.marija.insurance.domain.Vehicle;
import com.marija.insurance.repository.InsuredRepository;
import com.marija.insurance.repository.MaterialDamageItemRepository;
import com.marija.insurance.repository.MaterialDamageRepository;
import com.marija.insurance.repository.VehicleRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private InsuredRepository insuredRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MaterialDamageRepository materialDamageRepository;

    @Autowired
    private MaterialDamageItemRepository materialDamageItemRepository;


    public byte[] exportReportInsuredsPdf() throws FileNotFoundException, JRException {

        List<Insured> insureds = insuredRepository.findAll();
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:insureds.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(insureds);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Marija Jeremic");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


    public byte[] exportReportVehiclesPdf() throws FileNotFoundException, JRException {

        List<Vehicle> vehicles = vehicleRepository.findAll();
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:vehicles.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vehicles);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Marija Jeremic");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


    public byte[] exportReportVehiclesOfInsuredPdf(Integer insuredId) throws FileNotFoundException, JRException {

        List<Vehicle> vehicles = vehicleRepository.findByInsuredId(insuredId);
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:vehiclesOfInsured.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vehicles);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Marija Jeremic");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[]  exportReportMaterialDamagesPdf() throws FileNotFoundException, JRException {

        List<MaterialDamage> materialDamages = materialDamageRepository.findAll();
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:materialdamages.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(materialDamages);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Marija Jeremic");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] exportReportMaterialDamagesOfVehiclePdf(Integer vehicleId) throws FileNotFoundException, JRException {

        List<MaterialDamage> materialDamages = materialDamageRepository.findByVehicleId(vehicleId);
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:materialdamagesOfVehicle.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(materialDamages);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Marija Jeremic");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public String exportReportMaterialDamagesOfVehicleStatistic(Integer vehicleId) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Pioniri\\Desktop\\Report";
        List<MaterialDamage> materialDamages = materialDamageRepository.findByVehicleIdForStatistic(vehicleId);
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:statisticsOfVehicleSuitability.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(materialDamages);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Marija Jeremic");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
//        if (reportFormat.equalsIgnoreCase("html")){
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\statisticsOfVehicleSuitability.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")){
        JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\statisticsOfVehicleSuitability.pdf");
//        }
        return "report generated in path : " + path;
    }

//    public String exportReportMaterialDamageItemsOfMaterialDamage(Integer materialDamageId) throws FileNotFoundException, JRException {
//        String path = "C:\\Users\\Pioniri\\Desktop\\Report";
//        List<MaterialDamageItem> materialDamageItems = materialDamageItemRepository.findByMaterialDamageId(materialDamageId);
//        //Load file and compile it
//        File file = ResourceUtils.getFile("classpath:materialdamageitemsOfMaterialdamage.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(materialDamageItems);
//        Map<String,Object> parameters = new HashMap<>();
//        parameters.put("createdBy","Marija Jeremic");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
////        if (reportFormat.equalsIgnoreCase("html")){
////            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\materialdamageitemsOfMaterialdamage.html");
////        }
////        if (reportFormat.equalsIgnoreCase("pdf")){
//        JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\materialdamageitemsOfMaterialdamage.pdf");
////        }
//        return "report generated in path : " + path;
//    }
    public byte[] exportReportMaterialDamageItemsOfMaterialDamagePdf(Integer materialDamageId) throws FileNotFoundException, JRException {

        List<MaterialDamageItem> materialDamageItems = materialDamageItemRepository.findByMaterialDamageId(materialDamageId);
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:materialdamageitemsOfMaterialdamage.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(materialDamageItems);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy","Marija Jeremic");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
