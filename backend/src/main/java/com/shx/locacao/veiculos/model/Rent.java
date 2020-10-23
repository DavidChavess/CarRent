package com.shx.locacao.veiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Rent implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn( name = "customer_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @JoinColumn( name = "vehicle_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vehicle vehicle;

    private LocalDate startRent;
    private LocalDate endRent;
    private BigDecimal valueTotal;
    private Boolean returned;

    public Rent(){}

    public Rent(Integer id, Customer customer, Vehicle vehicle, LocalDate startRent, LocalDate endRent, BigDecimal valueTotal, Boolean returned) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startRent = startRent;
        this.endRent = endRent;
        this.valueTotal = valueTotal;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartRent() {
        return startRent;
    }

    public void setStartRent(LocalDate startRent) {
        this.startRent = startRent;
    }

    public LocalDate getEndRent() {
        return endRent;
    }

    public void setEndRent(LocalDate endRent) {
        this.endRent = endRent;
    }

    public BigDecimal getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(BigDecimal valueTotal) {
        this.valueTotal = valueTotal;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}
