package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.model.Vehicle;
import com.shx.locacao.veiculos.repository.GenericRepository;
import com.shx.locacao.veiculos.service.impl.CustomerServiceImpl;
import com.shx.locacao.veiculos.service.impl.VehicleServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomer;
import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomerDTO;
import static com.shx.locacao.veiculos.controller.VehicleContrrollerTest.createVehicle;
import static com.shx.locacao.veiculos.controller.VehicleContrrollerTest.createVehicleDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class VehicleServiceTest {

    VehicleService service;

    @MockBean
    GenericRepository<Vehicle> repository;

    @MockBean
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
        service = new VehicleServiceImpl(repository, modelMapper);
    }

    @Test
    @DisplayName("Deve salvar um veiculo")
    public void saveVehicleTest(){
        VehicleDTO dto = createVehicleDTO(null);

        Vehicle vehicleSaving = createVehicle(null);
        Vehicle vehicleSaved = createVehicle(1);

        // converto dto para um veiculo a ser salvo, por isso o id esta null
        when(modelMapper.map(dto, Vehicle.class)).thenReturn(vehicleSaving);

        // salvo a entidade que eu acabei de converter e retorno ela com o id populado
        when(repository.save(vehicleSaving)).thenReturn(vehicleSaved);

        // converto a entidade que eu salvei para dto com o id populado
        when(modelMapper.map(vehicleSaved, VehicleDTO.class))
                .thenReturn(createVehicleDTO(1));

        // chamo o metodo do meu service
        VehicleDTO v = service.save(dto);

        // verificação se foi salvo
        assertThat(v.getId()).isNotNull();
        assertThat(v.getName()).isEqualTo(dto.getName());
        assertThat(v.getModel()).isEqualTo(dto.getModel());
        assertThat(v.getYear()).isEqualTo(dto.getYear());
        assertThat(v.getFuel()).isEqualTo(dto.getFuel());
        assertThat(v.getRent()).isEqualTo(dto.getRent());
        assertThat(v.getValuePerDay()).isEqualTo(dto.getValuePerDay());
    }


}
