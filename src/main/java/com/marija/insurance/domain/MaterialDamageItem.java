package com.marija.insurance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class MaterialDamageItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private long itemNumber;

    @Column
    private String description;

    @Column
    private double estimatedPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "damagetype_id", referencedColumnName = "id")
    @NotNull
    private DamageType damageType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "materialdamage_id", referencedColumnName = "id")
    @NotNull
    private MaterialDamage materialDamage;


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

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public MaterialDamage getMaterialDamage() {
        return materialDamage;
    }

    public void setMaterialDamage(MaterialDamage materialDamage) {
        this.materialDamage = materialDamage;
    }
}
