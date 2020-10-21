package com.shx.locacao.veiculos.controller;

import com.google.gson.Gson;
import com.shx.locacao.veiculos.dto.CustomerDTO;
import com.shx.locacao.veiculos.model.Customer;
import com.shx.locacao.veiculos.service.CustomerService;
import com.shx.locacao.veiculos.service.CustomerServiceTest;
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

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CustomerController.class)
@AutoConfigureMockMvc
public class CustomerContrrollerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerService service;

    private static String API = "/customers";

    @Test
    @DisplayName("Deve salvar um cliente retorna-lo com as propriedades coretas ")
    public void save() throws Exception {
        // Json que envio na requisição
        String json = new Gson().toJson(createCustomerDTO(null, null));

        // Simulo o retorno do obj salvo
        CustomerDTO customerSaved = createCustomerDTO(1, LocalDate.now());
        given(service.save(any(CustomerDTO.class))).willReturn(customerSaved);

        // Faço a requisição POST
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Verifico a resposta
        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(customerSaved.getId()))
                .andExpect(jsonPath("cpf").value(customerSaved.getCpf()))
                .andExpect(jsonPath("birthdate").value(customerSaved.getBirthdate().toString()))
                .andExpect(jsonPath("name").value(customerSaved.getName()))
                .andExpect(jsonPath("status").value(customerSaved.getStatus()));
    }

    @Test
    @DisplayName("Deve buscar um cliente pelo id")
    public void getById() throws Exception {
        // Simulo o cliente que será retornado no meu getById, quando passar o id 1
        Integer id = 1;
        CustomerDTO customer = createCustomerDTO(id, LocalDate.now());
        given(service.getById(id)).willReturn(customer);

        // Faço a requisição get na url localhost:8080/....customers/1
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API.concat("/") + id)
                .accept(MediaType.APPLICATION_JSON);

        // Verifico se o objeto que retornou na resposta foi o mesmo que eu simulei
        mvc.perform(request)
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("cpf").value(customer.getCpf()))
                .andExpect(jsonPath("birthdate").value(customer.getBirthdate().toString()))
                .andExpect(jsonPath("name").value(customer.getName()))
                .andExpect(jsonPath("status").value(customer.getStatus()));
    }

    @Test
    @DisplayName("Deve deletar um cliente pelo id")
    public void delteById() throws Exception {
        // Faço a requisição delete na url localhost:8080/....customers/1
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(API.concat("/") + 1);

        // verifico se a requisição retornou status no content
        mvc.perform(request)
                .andExpect(status().isNoContent());
    }


    public static CustomerDTO createCustomerDTO(Integer id, LocalDate birthdate){
        return new CustomerDTO(id, 11111111111L,"teste", birthdate, true);
    }

    public static Customer createCustomer(Integer id){
        return new Customer(id, 11111111111L,"teste", LocalDate.now(), true);
    }
}
