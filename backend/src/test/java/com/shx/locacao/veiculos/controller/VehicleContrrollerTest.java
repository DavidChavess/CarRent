package com.shx.locacao.veiculos.controller;

import com.google.gson.Gson;
import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.dto.VehicleDTO;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.model.Vehicle;
import com.shx.locacao.veiculos.model.enumeration.Fuel;
import com.shx.locacao.veiculos.service.CustomerService;
import com.shx.locacao.veiculos.service.VehicleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = VehicleController.class)
@AutoConfigureMockMvc
public class VehicleContrrollerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    VehicleService service;

    private static String API = "/vehicles";

    @Test
    @DisplayName("Deve salvar um veiculo e retorna-lo")
    public void save() throws Exception {
        // Json que envio na requisição
        String json = new Gson().toJson(createVehicleDTO(null));

        // Simulo o retorno do obj salvo
        VehicleDTO vehicleSaved = createVehicleDTO(1);
        given(service.save(any(VehicleDTO.class))).willReturn(vehicleSaved);

        // Faço a requisição POST
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Verifico a resposta enviada para o client
        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(vehicleSaved.getId()))
                .andExpect(jsonPath("name").value(vehicleSaved.getName()))
                .andExpect(jsonPath("year").value(vehicleSaved.getYear()))
                .andExpect(jsonPath("model").value(vehicleSaved.getModel()))
                .andExpect(jsonPath("fuel").value(vehicleSaved.getFuel().toString()))
                .andExpect(jsonPath("valuePerDay").value(vehicleSaved.getValuePerDay()))
                .andExpect(jsonPath("rent").value(vehicleSaved.getRent()));
    }

    @Test
    @DisplayName("Deve buscar um veiculo pelo id e retorna-lo")
    public void getById() throws Exception {
        final Integer id = 1;

        // Simulo o retorno do obj salvo
        VehicleDTO vehicleReturned = createVehicleDTO(id);
        given(service.getById(any(Integer.class))).willReturn(vehicleReturned);

        // Faço a requisição GET
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API.concat("/"+id))
                .accept(MediaType.APPLICATION_JSON);

        // Verifico a resposta enviada para o client
        mvc.perform(request)
                .andExpect(jsonPath("id").value(vehicleReturned.getId()))
                .andExpect(jsonPath("name").value(vehicleReturned.getName()))
                .andExpect(jsonPath("year").value(vehicleReturned.getYear()))
                .andExpect(jsonPath("model").value(vehicleReturned.getModel()))
                .andExpect(jsonPath("fuel").value(vehicleReturned.getFuel().toString()))
                .andExpect(jsonPath("valuePerDay").value(vehicleReturned.getValuePerDay()))
                .andExpect(jsonPath("rent").value(vehicleReturned.getRent()));
    }

    @Test
    @DisplayName("Deve deletar um veiculo pelo id informado")
    public void deleteById() throws Exception {
        // Faço a requisição DELETE
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(API.concat("/"+1));

        // Verifico a resposta enviada para o client
        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve buscar todos os veiculos")
    public void getAll() throws Exception {
        List<VehicleDTO> vehiclesDTO = Arrays.asList(
                createVehicleDTO(1),
                createVehicleDTO(2));

        given(service.getAll()).willReturn(vehiclesDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API)
                .accept(MediaType.APPLICATION_JSON);

        // Verifico se o objeto que retornou na resposta foi o mesmo que eu simulei
        mvc.perform(request)
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[1].id").value(2));
    }

    @Test
    @DisplayName("Deve atualizar um veiculo")
    public void update() throws Exception {
        final Integer id = 1;
        VehicleDTO vehicleUpdate = createVehicleDTO(id);
        given(service.update(any(Integer.class), any(VehicleDTO.class) )).willReturn(vehicleUpdate);

        // Json que envio na requisição
        String json = new Gson().toJson(createVehicleDTO(id));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(API.concat("/"+id))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Verifico a resposta enviada para o client
        mvc.perform(request)
                .andExpect(jsonPath("id").value(vehicleUpdate.getId()))
                .andExpect(jsonPath("name").value(vehicleUpdate.getName()))
                .andExpect(jsonPath("year").value(vehicleUpdate.getYear()))
                .andExpect(jsonPath("model").value(vehicleUpdate.getModel()))
                .andExpect(jsonPath("fuel").value(vehicleUpdate.getFuel().toString()))
                .andExpect(jsonPath("valuePerDay").value(vehicleUpdate.getValuePerDay()))
                .andExpect(jsonPath("rent").value(vehicleUpdate.getRent()));
    }



    public static VehicleDTO createVehicleDTO(Integer id){
        return new VehicleDTO(id, "Fox", 2012, 2012, Fuel.FLEX, new BigDecimal("5.57"), false);
    }

    public static Vehicle createVehicle(Integer id){
        return new Vehicle(id, "Fox", 2012, 2012, Fuel.FLEX, new BigDecimal("5.57"), false);
    }

}
