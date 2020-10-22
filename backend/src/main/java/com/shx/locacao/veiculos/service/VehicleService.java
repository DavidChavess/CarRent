package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO save(VehicleDTO vehicleDTO);

    VehicleDTO getById(Integer id);

    void deleteById(Integer id);

    List<VehicleDTO> getAll();

    VehicleDTO update(Integer id, VehicleDTO vehicleDTO);
}
