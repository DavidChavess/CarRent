package com.shx.locacao.veiculos.controller;

import com.google.gson.Gson;
import com.shx.locacao.veiculos.dto.*;
import com.shx.locacao.veiculos.service.CustomerService;
import com.shx.locacao.veiculos.service.RentService;
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

import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomer;
import static com.shx.locacao.veiculos.controller.CustomerContrrollerTest.createCustomerDTO;
import static com.shx.locacao.veiculos.controller.VehicleContrrollerTest.createVehicle;
import static com.shx.locacao.veiculos.controller.VehicleContrrollerTest.createVehicleDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RentController.class)
@AutoConfigureMockMvc
public class RentControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    RentService service;

    private static String API = "/rents";

    @Test
    @DisplayName("Deve alugar um veiculo para um cliente")
    public void save() throws Exception {
        // Json que envio na requisição
        SaveRentDTO dto = new SaveRentDTO(1,1);
        String json = new Gson().toJson(dto);

        // Simulo o retorno do obj salvo
        RentDTO rentSaved = createRentDTO(1);
        given(service.save(any(SaveRentDTO.class))).willReturn(rentSaved);

        // Faço a requisição POST
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Verifico a resposta
        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(rentSaved.getId()));
    }

    @Test
    @DisplayName("Deve devolver um veiculo para um cliente")
    public void returnedRent() throws Exception {
        // Json que envio na requisição
        ReturnedRentDTO dto = new ReturnedRentDTO(true);
        String json = new Gson().toJson(dto);

        // Simulo o retorno do obj salvo
        RentDTO rentReturned = returnedRentDTO(1);
        given(service.returnedRent( any(Integer.class), any(ReturnedRentDTO.class))).willReturn(rentReturned);

        // Faço a requisição POST
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .patch(API.concat("/" + 1) )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Verifico a resposta
        mvc.perform(request)
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("startRent").value(LocalDate.now().toString()));
        //...
    }

    private RentDTO createRentDTO(Integer id) {
        return new RentDTO(1, createCustomerDTO(1, null), createVehicleDTO(1), LocalDate.now(), null, null, null);
    }

    private RentDTO returnedRentDTO(Integer id) {
        VehicleDTO vehicleDTO = createVehicleDTO(1);
        return new RentDTO(id,
                createCustomerDTO(1, null),
                vehicleDTO ,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                vehicleDTO.getValuePerDay().multiply(BigDecimal.valueOf(3)),
                true);
    }
}
