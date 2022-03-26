package com.marija.insurance.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class MaterialDamage {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column
    private LocalDateTime entryDate;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfDamage typeOfDamage;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    @NotNull
    private City city;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @NotNull
    private Vehicle vehicle;


    @JsonIgnore
    @OneToMany(mappedBy = "materialDamage")
    private List<MaterialDamageItem> materialDamageItems;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public TypeOfDamage getTypeOfDamage() {
        return typeOfDamage;
    }

    public void setTypeOfDamage(TypeOfDamage typeOfDamage) {
        this.typeOfDamage = typeOfDamage;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
