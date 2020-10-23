package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.RentDTO;
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


    /*@Test
    @DisplayName("Deve calcular o valor da diaria")
    public void calculateValuePerDay(){
       // final Integer days = 3
       //LocalDate threeDaysAgo = LocalDate.now().plusDays(days);

        final BigDecimal valuePerDay = BigDecimal.valueOf(4.5);
        Vehicle v = new Vehicle();
        v.setValuePerDay( valuePerDay );

        Rent rent = new Rent(1,new Customer(), v, LocalDate.now(), null, null );
        when(repository.findById(Rent.class, 1)).thenReturn(rent);

        RentDTO rentDtoReturned = new RentDTO(1,new Customer(), v, LocalDate.now(), null, null );
        when(modelMapper.map(rent, RentDTO.class)).thenReturn(rentDtoReturned);

        RentDTO rentDTO = service.giveBackRent(1);

        assertThat(rentDTO.getValueTotal()).isEqualTo(BigDecimal.valueOf(13.5));
    }*/
}
