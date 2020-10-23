package com.shx.locacao.veiculos.dto;

import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.model.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentDTO {

    private Integer id;
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate startRent;
    private LocalDate endRent;
    private BigDecimal valueTotal;

    public RentDTO(){}

    public RentDTO(Integer id, Customer customer, Vehicle vehicle, LocalDate startRent, LocalDate endRent, BigDecimal valueTotal) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startRent = startRent;
        this.endRent = endRent;
        this.valueTotal = valueTotal;
    }

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
}
