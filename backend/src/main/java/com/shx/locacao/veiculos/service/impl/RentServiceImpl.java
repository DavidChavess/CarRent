package com.shx.locacao.veiculos.service.impl;

import com.shx.locacao.veiculos.dto.RentDTO;
import com.shx.locacao.veiculos.dto.ReturnedRentDTO;
import com.shx.locacao.veiculos.dto.SaveRentDTO;
import com.shx.locacao.veiculos.exception.BusinessException;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.model.Rent;
import com.shx.locacao.veiculos.model.Vehicle;
import com.shx.locacao.veiculos.repository.GenericRepository;
import com.shx.locacao.veiculos.service.RentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {

    private final GenericRepository<Rent> repository;
    private final GenericRepository<Vehicle> repositoryVehicle;
    private final GenericRepository<Customer> repositoryCustomer;

    private final ModelMapper modelMapper;

    public RentServiceImpl(GenericRepository<Rent> repository,
                           GenericRepository<Vehicle> repositoryVehicle,
                           GenericRepository<Customer> repositoryCustomer,
                           ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.repositoryCustomer = repositoryCustomer;
        this.repositoryVehicle = repositoryVehicle;
    }

    @Override
    public RentDTO save(SaveRentDTO dto) {

        try{
            Vehicle v = repositoryVehicle.findById(Vehicle.class, dto.getVehicleId());
            Customer c = repositoryCustomer.findById(Customer.class, dto.getCustomerId());

            if (v.isRent()) throw new BusinessException("O veiculo já está alugado");

            if (!c.isActive()) throw new BusinessException("O cliente está inativo");

            Rent rent = new Rent();

            rent.setCustomer(c);
            rent.setVehicle(v);
            rent.setStartRent(LocalDate.now());

            // seto o veiculo como alugado
            v.setRent(true);

            // converto novamente para dto e retorno o dto
            return modelMapper.map(repository.save(rent), RentDTO.class);
        } catch (NullPointerException e){
            throw new BusinessException("Cliente ou veiculo não encontrado");
        }

    }

    @Override
    public RentDTO returnedRent(Integer codRent, ReturnedRentDTO dto) {
        Rent rent = repository.findById( Rent.class, codRent );
        LocalDate now = LocalDate.now();

        int days = Period.between(rent.getStartRent(), now).getDays();

        // se for 0 significa que ele pegou e entregou no mesmo dia, sendo assim cobro o valor da diaria
        if (days == 0) days = 1;

        // multiplica o valor por dia do veiculo pela quantidade de dias desde a data inicial da locação
        BigDecimal valueTotal = rent.getVehicle().getValuePerDay().multiply(BigDecimal.valueOf(days));

        rent.setReturned(dto.getReturned());
        rent.setValueTotal(valueTotal);
        rent.setEndRent(now);
        rent.getVehicle().setRent(false);

        return modelMapper.map(repository.update(rent), RentDTO.class);
    }

    @Override
    public List<RentDTO> getAll() {
        return repository.findAll(Rent.class).stream()
                .map(r -> modelMapper.map(r, RentDTO.class))
                .collect(Collectors.toList());
    }
}
