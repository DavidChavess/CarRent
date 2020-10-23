package com.shx.locacao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.model.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentDTO {

    private Integer id;
    private CustomerDTO customer;
    private VehicleDTO vehicle;
    private LocalDate startRent;
    private LocalDate endRent;
    private BigDecimal valueTotal;
    private Boolean returned;

    public RentDTO(){}

    public RentDTO(Integer id, CustomerDTO customer, VehicleDTO vehicle, LocalDate startRent, LocalDate endRent, BigDecimal valueTotal, Boolean returned) {
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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
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
