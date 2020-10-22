package com.shx.locacao.veiculos.service;

import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.model.Vehicle;
import com.shx.locacao.veiculos.model.enumeration.Fuel;
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

import java.math.BigDecimal;
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

        // converto dto para um veiculo a ser salvo, por isso o id esta null
        Vehicle vehicleSaving = createVehicle(null);
        when(modelMapper.map(dto, Vehicle.class)).thenReturn(vehicleSaving);

        // salvo a entidade que eu acabei de converter e retorno ela com o id populado
        Vehicle vehicleSaved = createVehicle(1);
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

    @Test
    @DisplayName("Deve buscar um veiculo pelo id")
    public void getVehicleById() {
        final Integer id = 1;
        Vehicle vehicle = createVehicle(id);

        // busco um cliente no banco pelo id
        when(repository.findById(Vehicle.class, id)).thenReturn(vehicle);

        // converto esse veiculo para um dto
        VehicleDTO dtoReturned = createVehicleDTO(id);
        when(modelMapper.map(vehicle, VehicleDTO.class)).thenReturn(dtoReturned);

        // chamo o metodo do meu service
        VehicleDTO v = service.getById(id);

        // verifico se retornou corretamente
        assertThat(v.getId()).isNotNull();
        assertThat(v.getName()).isEqualTo(dtoReturned.getName());
        assertThat(v.getModel()).isEqualTo(dtoReturned.getModel());
        assertThat(v.getYear()).isEqualTo(dtoReturned.getYear());
        assertThat(v.getFuel()).isEqualTo(dtoReturned.getFuel());
        assertThat(v.getRent()).isEqualTo(dtoReturned.getRent());
        assertThat(v.getValuePerDay()).isEqualTo(dtoReturned.getValuePerDay());
    }

    @Test
    @DisplayName("Deve deletar um veiculo pelo id")
    public void deleteVehicleById() {
        final Integer id = 1;

        // chamo o metodo do meu service
        service.deleteById(id);

        // verifico se foi chamado para deleção
        verify(repository).deleteById(Vehicle.class, id);
    }

    @Test
    @DisplayName("Deve buscar todos os veiculo")
    public void getAllVehicles() {
        List<Vehicle> vehicles = Arrays.asList(
                createVehicle(1),
                createVehicle(2)
        );

        // busco todos os clientes no banco
        when(repository.findAll(Vehicle.class)).thenReturn(vehicles);

        // chamo o metodo do meu service
        List<VehicleDTO> v = service.getAll();

        // verifico se retornou corretamente
        assertThat(v).hasSize(2);
        assertThat(v).isNotEmpty();
    }

    @Test
    @DisplayName("Deve atualizar um veiculo")
    public void updateVehicleTest(){
        final Integer id = 1;

        // Busco um cliente da base pelo id
        Vehicle vehicleReturnedById = createVehicle(id);
        when(repository.findById(Vehicle.class, id)).thenReturn(vehicleReturnedById);

        // quando eu chamar o metodo update passando um cliente eu devolve um cliente alterado
        Vehicle vehicleUpdate =  new Vehicle(id, "Gol", 2010, 2008, Fuel.GASOLINA, new BigDecimal("2.57"), false);
        when(repository.update(vehicleReturnedById)).thenReturn(vehicleUpdate);

        // converto para dto
        VehicleDTO vehicleUpdateDTO = new VehicleDTO(id, "Gol", 2010, 2008, Fuel.GASOLINA, new BigDecimal("2.57"), false);
        when(modelMapper.map(vehicleUpdate, VehicleDTO.class))
                .thenReturn(vehicleUpdateDTO);

        // chamo o metodo do meu service para atualizar
        VehicleDTO v = service.update(id, vehicleUpdateDTO);

        // verifico se retornou corretamente
        assertThat(v.getId()).isNotNull();
        assertThat(v.getName()).isEqualTo(vehicleUpdate.getName());
        assertThat(v.getModel()).isEqualTo(vehicleUpdate.getModel());
        assertThat(v.getYear()).isEqualTo(vehicleUpdate.getYear());
        assertThat(v.getFuel()).isEqualTo(vehicleUpdate.getFuel());
        assertThat(v.getRent()).isEqualTo(vehicleUpdate.getRent());
        assertThat(v.getValuePerDay()).isEqualTo(vehicleUpdate.getValuePerDay());
    }



}
