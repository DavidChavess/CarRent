package com.shx.locacao.veiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shx.locacao.veiculos.model.enumeration.Fuel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicle implements BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer year;
    private Integer model;
    private Integer fuel;
    private BigDecimal valuePerDay;
    private Boolean isRent;
    private String brand;

    @OneToMany(mappedBy = "vehicle")
    private List<Rent> rentList = new ArrayList<>();

    public Vehicle(){}

    public Vehicle(Integer id, String name, Integer year, Integer model, Fuel fuel, BigDecimal valuePerDay, Boolean isRent, String brand) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.model = model;
        this.fuel = fuel.getCod();
        this.valuePerDay = valuePerDay;
        this.isRent = isRent;
        this.brand = brand;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Fuel getFuel() {
        return Fuel.toEnum(fuel);
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel.getCod();
    }

    public BigDecimal getValuePerDay() {
        return valuePerDay;
    }

    public void setValuePerDay(BigDecimal valuePerDay) {
        this.valuePerDay = valuePerDay;
    }

    public Boolean isRent() {
        return isRent;
    }

    public void setRent(Boolean isRent) {
        this.isRent = isRent;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }
}
