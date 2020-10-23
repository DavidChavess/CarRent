package com.shx.locacao.veiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long cpf;
    private String name;
    private LocalDate birthdate;
    private Boolean isActive;

    @OneToMany(mappedBy = "customer")
    private List<Rent> rentList = new ArrayList<>();

    public Customer(){}

    public Customer(Integer id, Long cpf, String name, LocalDate birthdate, Boolean isActive) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.isActive = isActive;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }
}
