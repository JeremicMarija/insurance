package com.marija.insurance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Size(max = 30,min = 1,message = "the number of characters must be min 1 and max 30")
    @NotEmpty
    @NotNull
    @Column
    private String model;

    @Size(max = 30,min = 1,message = "the number of characters must be min 1 and max 30")
    @NotEmpty
    @NotNull
    @Column
    private String brand;

    @Size(max = 30,min = 3,message = "the number of characters must be min 3 and max 30")
    @NotEmpty
    @NotNull
    @Column
    private String registrationNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private List<MaterialDamage> damages;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insuerd_id", referencedColumnName = "id")
    @NotNull
    private Insured insured;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<MaterialDamage> getDamages() {
        return damages;
    }

    public void setDamages(List<MaterialDamage> damages) {
        this.damages = damages;
    }

    public Insured getInsured() {
        return insured;
    }

    public void setInsured(Insured insured) {
        this.insured = insured;
    }
}
