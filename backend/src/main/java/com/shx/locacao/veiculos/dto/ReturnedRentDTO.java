package com.shx.locacao.veiculos.dto;

public class ReturnedRentDTO {
    private Boolean returned;

    public ReturnedRentDTO(){}

    public ReturnedRentDTO(Boolean returned) {
        this.returned = returned;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}
