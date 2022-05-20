package com.marija.insurance.dto;

public class MaterialDamageItemDto {

    private long id;
    private long itemNumber;
    private String description;
    private double estimatedPrice;
    private long damageTypeId;
    private long materialDamageId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public long getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(long damageTypeId) {
        this.damageTypeId = damageTypeId;
    }

    public long getMaterialDamageId() {
        return materialDamageId;
    }

    public void setMaterialDamageId(long materialDamageId) {
        this.materialDamageId = materialDamageId;
    }
}
