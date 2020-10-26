package com.shx.locacao.veiculos.service.impl;

import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.exception.BusinessException;
import com.shx.locacao.veiculos.exception.ObjectNotFoundException;
import com.shx.locacao.veiculos.model.Vehicle;
import com.shx.locacao.veiculos.repository.GenericRepository;
import com.shx.locacao.veiculos.service.VehicleService;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.shx.locacao.veiculos.util.Validation.validVehicle;

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
        // valido o veiculo
        validVehicle(dto);

        // converto o dto que foi enviado na requisição para entidade, para gravar no banco
        Vehicle vehicle = modelMapper.map(dto, Vehicle.class);

        // salvo no banco de dados e retorno o obj
        vehicle = repository.save(vehicle);

        // converto novamente para dto e retorno o dto
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO getById(Integer id) {
        try {
            // busco no banco e converto para dto
            return modelMapper.map(repository.findById(Vehicle.class, id), VehicleDTO.class);

        }catch (IllegalArgumentException e){
            throw new ObjectNotFoundException("Veiculo não encontrado para o id informado");
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            repository.deleteById(Vehicle.class, id);

        }catch (IllegalArgumentException e){
            throw new ObjectNotFoundException("Veiculo não encontrado para o id informado");

        }catch (DataIntegrityViolationException e){
            throw new BusinessException("Você não pode apagar este veiculo pois ele esta associado a um aluguel");
        }

    }

    @Override
    public List<VehicleDTO> getAll() {
        return repository.findAll(Vehicle.class).stream()
                .map(v -> modelMapper.map(v, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO update(Integer id, VehicleDTO vehicleDTO) {
        try {
            // valido o veiculo
            validVehicle(vehicleDTO);

            Vehicle vehicle = repository.findById(Vehicle.class, id);

            vehicle.setName(vehicleDTO.getName());
            vehicle.setYear(vehicleDTO.getYear());
            vehicle.setModel(vehicleDTO.getModel());
            vehicle.setFuel(vehicleDTO.getFuel());
            vehicle.setRent(vehicleDTO.isRent());
            vehicle.setValuePerDay(vehicleDTO.getValuePerDay());
            vehicle.setBrand(vehicleDTO.getBrand());

            return modelMapper.map( repository.update(vehicle), VehicleDTO.class);

        } catch ( NullPointerException e ){
            throw new ObjectNotFoundException("Cliente não encontrado para o id informado");
        }
    }

}
