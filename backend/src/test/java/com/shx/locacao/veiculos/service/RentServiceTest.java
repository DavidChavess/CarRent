package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.RentDTO;
import com.shx.locacao.veiculos.dto.ReturnedRentDTO;
import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.model.Rent;
import com.shx.locacao.veiculos.model.Vehicle;
import com.shx.locacao.veiculos.repository.GenericRepository;
import com.shx.locacao.veiculos.service.impl.CustomerServiceImpl;
import com.shx.locacao.veiculos.service.impl.RentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomer;
import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomerDTO;
import static com.shx.locacao.veiculos.controller.VehicleContrrollerTest.createVehicle;
import static com.shx.locacao.veiculos.controller.VehicleContrrollerTest.createVehicleDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class RentServiceTest {

    RentService service;

    @MockBean
    GenericRepository<Customer> cutomerRepository;

    @MockBean
    GenericRepository<Vehicle> vehicleRepository;

    @MockBean
    GenericRepository<Rent> repository;

    @MockBean
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
        service = new RentServiceImpl(repository, vehicleRepository, cutomerRepository, modelMapper);
    }


    @Test
    @DisplayName("Deve retornar um veiculo com o valor da diaria calculado")
    public void calculateValuePerDay(){
        BigDecimal valuePerDay = new BigDecimal("4");

        Vehicle vehicle = createVehicle(1);
        vehicle.setValuePerDay( valuePerDay );

        // quando eu chamar o findById pra eu retornar um aluguel eu quero que retorne esse aluguel abaixo

        Rent rent = new Rent(1, createCustomer(1), vehicle, LocalDate.now(), null, null, null );
        when(repository.findById(Rent.class, 1)).thenReturn(rent);

        // Quando eu chamar o update eu quero que retorne um aluguel passado 3 dias
        // Ja com o valor calculado

        Rent rentUpdated = new Rent(
                1,
                createCustomer(1),
                vehicle,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                vehicle.getValuePerDay().multiply(BigDecimal.valueOf(3)),
                true);

        when(repository.update(rent)).thenReturn(rentUpdated);


        // converto esse aluguel que retornou no update para um dto

        VehicleDTO vehicleDTO = createVehicleDTO(1);
        vehicleDTO.setValuePerDay( valuePerDay );

        RentDTO rentDtoReturned = new RentDTO(
                1,
                createCustomerDTO(1, null),
                vehicleDTO,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                vehicleDTO.getValuePerDay().multiply(BigDecimal.valueOf(3)),
                true);

        when(modelMapper.map(rentUpdated, RentDTO.class)).thenReturn(rentDtoReturned);

        // chamo o metodo do service
        RentDTO returnedRent = service.returnedRent(1, new ReturnedRentDTO(true));

        // no final, o valor total deve ser 12, porque se passaram 3 dias, e valor da diaria do veiculo era 4 reais
        assertThat(returnedRent.getValueTotal()).isEqualTo(BigDecimal.valueOf(12));
    }
}
