package com.marija.insurance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 37 * hash + Objects.hashCode(this.username);
//        hash = 37 * hash + Objects.hashCode(this.password);
//        return hash;
//    }
}
