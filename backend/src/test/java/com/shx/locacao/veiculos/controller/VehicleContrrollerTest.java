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


    public static VehicleDTO createVehicleDTO(Integer id){
        return new VehicleDTO(id, "Fox", 2012, 2012, Fuel.FLEX, new BigDecimal("5.57"), false);
    }

    public static Vehicle createVehicle(Integer id){
        return new Vehicle(id, "Fox", 2012, 2012, Fuel.FLEX, new BigDecimal("5.57"), false);
    }

}
