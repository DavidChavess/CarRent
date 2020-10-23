package com.shx.locacao.veiculos.dto;

public class SaveRentDTO {
    private Integer customerId;
    private Integer vehicleId;

    public SaveRentDTO(){}

    public SaveRentDTO(Integer customerId, Integer vehicleId) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}
