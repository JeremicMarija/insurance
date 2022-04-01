package com.marija.insurance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Insured {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Size(max = 30)
    @NotEmpty
    @NotNull
    @Column
    private String name;

    @Size(max = 30)
    @NotEmpty
    @NotNull
    @Column
    private String surname;

    @NotNull
    @Column
    private LocalDate dateOfBirth;

    @NotEmpty
    @NotNull
    @Column
    private String policyNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfInsurance typeOfInsurance;

    @JsonIgnore
    @OneToMany(mappedBy = "insured")
    private List<Vehicle> vehicles;




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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public TypeOfInsurance getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public void setTypeOfInsurance(TypeOfInsurance typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString(){
        return "Insured{" +
                "id= " + id +
                "name= " + name +
                "surname= " + surname +
                "dataOfBirth= " + dateOfBirth +
                "policyNumber= " + policyNumber +
                "typeOfInsurence= " + typeOfInsurance +
                "}";
    }
}
