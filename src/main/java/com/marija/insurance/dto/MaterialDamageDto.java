package com.marija.insurance.dto;

import com.marija.insurance.domain.TypeOfDamage;

import java.time.LocalDate;

public class MaterialDamageDto {
    private long id;
    private LocalDate entryDate;
    private TypeOfDamage typeOfDamage;
    private long cityId;
    private long vehicleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public TypeOfDamage getTypeOfDamage() {
        return typeOfDamage;
    }

    public void setTypeOfDamage(TypeOfDamage typeOfDamage) {
        this.typeOfDamage = typeOfDamage;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
