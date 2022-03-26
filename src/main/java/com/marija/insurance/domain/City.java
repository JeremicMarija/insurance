package com.marija.insurance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String zipCode;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<MaterialDamage> materialDamages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<MaterialDamage> getMaterialDamages() {
        return materialDamages;
    }

    public void setMaterialDamages(List<MaterialDamage> materialDamages) {
        this.materialDamages = materialDamages;
    }
}
