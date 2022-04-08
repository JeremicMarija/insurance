package com.marija.insurance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
public class DamageType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "damageType")
    private List<MaterialDamageItem> materialDamageItems;

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

    public List<MaterialDamageItem> getMaterialDamageItems() {
        return materialDamageItems;
    }

    public void setMaterialDamageItems(List<MaterialDamageItem> materialDamageItems) {
        this.materialDamageItems = materialDamageItems;
    }
}
