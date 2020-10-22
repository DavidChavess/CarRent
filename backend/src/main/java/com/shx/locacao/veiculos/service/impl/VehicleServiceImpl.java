package com.shx.locacao.veiculos.service.impl;

import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.model.Vehicle;
import com.shx.locacao.veiculos.repository.GenericRepository;
import com.shx.locacao.veiculos.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final GenericRepository<Vehicle> repository;
    private final ModelMapper modelMapper;

    public VehicleServiceImpl(GenericRepository<Vehicle> repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VehicleDTO save(VehicleDTO dto) {
        // converto o dto que foi enviado na requisição para entidade, para gravar no banco
        Vehicle vehicle = modelMapper.map(dto, Vehicle.class);

        // salvo no banco de dados e retorno o obj
        vehicle = repository.save(vehicle);

        // converto novamente para dto e retorno o dto
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

}
